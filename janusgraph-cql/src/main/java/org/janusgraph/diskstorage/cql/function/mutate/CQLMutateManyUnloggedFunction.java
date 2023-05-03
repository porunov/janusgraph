// Copyright 2021 JanusGraph Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.janusgraph.diskstorage.cql.function.mutate;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.AsyncResultSet;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchableStatement;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.DefaultBatchType;
import io.vavr.collection.Iterator;
import io.vavr.collection.Seq;
import io.vavr.concurrent.Future;
import org.janusgraph.diskstorage.BackendException;
import org.janusgraph.diskstorage.StaticBuffer;
import org.janusgraph.diskstorage.common.DistributedStoreManager;
import org.janusgraph.diskstorage.cql.CQLKeyColumnValueStore;
import org.janusgraph.diskstorage.cql.function.ConsumerWithBackendException;
import org.janusgraph.diskstorage.keycolumnvalue.KCVMutation;
import org.janusgraph.diskstorage.keycolumnvalue.StoreTransaction;
import org.janusgraph.diskstorage.util.backpressure.QueryBackPressure;
import org.janusgraph.diskstorage.util.time.TimestampProvider;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import static org.janusgraph.diskstorage.cql.CQLKeyColumnValueStore.EXCEPTION_MAPPER;
import static org.janusgraph.diskstorage.cql.CQLTransaction.getTransaction;

public class CQLMutateManyUnloggedFunction extends AbstractCQLMutateManyFunction implements CQLMutateManyFunction {

    private final CqlSession session;
    private final int batchSize;
    private final QueryBackPressure queryBackPressure;
    private final ExecutorService executorService;

    public CQLMutateManyUnloggedFunction(int batchSize, CqlSession session, Map<String, CQLKeyColumnValueStore> openStores,
                                         TimestampProvider times, boolean assignTimestamp,
                                         ConsumerWithBackendException<DistributedStoreManager.MaskedTimestamp> sleepAfterWriteFunction,
                                         QueryBackPressure queryBackPressure,
                                         ExecutorService executorService) {
        super(sleepAfterWriteFunction, assignTimestamp, times, openStores);
        this.session = session;
        this.batchSize = batchSize;
        this.queryBackPressure = queryBackPressure;
        this.executorService = executorService;
    }

    // Create an async un-logged batch per partition key
    @Override
    public void mutateMany(final Map<String, Map<StaticBuffer, KCVMutation>> mutations, final StoreTransaction txh) throws BackendException {

        final DistributedStoreManager.MaskedTimestamp commitTime = createMaskedTimestampFunction.apply(txh);

        final Future<Seq<AsyncResultSet>> result = Future.sequence(this.executorService, Iterator.ofAll(mutations.entrySet()).flatMap(tableNameAndMutations -> {
            final String tableName = tableNameAndMutations.getKey();
            final Map<StaticBuffer, KCVMutation> tableMutations = tableNameAndMutations.getValue();
            final CQLKeyColumnValueStore columnValueStore = getColumnValueStore(tableName);
            return Iterator.ofAll(tableMutations.entrySet()).flatMap(keyAndMutations -> {
                final StaticBuffer key = keyAndMutations.getKey();
                final KCVMutation keyMutations = keyAndMutations.getValue();
                return toGroupedBatchableStatementsSequenceIterator(commitTime, keyMutations, columnValueStore, key)
                    .map(group -> Future.fromJavaFuture(this.executorService, execAsyncUnlogged(group, txh)));
            });
        }));

        result.await();
        if (result.isFailure()) {
            throw EXCEPTION_MAPPER.apply(result.getCause().get());
        }

        sleepAfterWriteFunction.accept(commitTime);
    }

    protected CompletableFuture<AsyncResultSet> execAsyncUnlogged(Seq<BatchableStatement<BoundStatement>> group, StoreTransaction txh){
        queryBackPressure.acquireBeforeQuery();
        try{
            return this.session.executeAsync(
                BatchStatement.newInstance(DefaultBatchType.UNLOGGED)
                    .addAll(group)
                    .setConsistencyLevel(getTransaction(txh).getWriteConsistencyLevel())
            ).whenComplete((asyncResultSet, throwable) -> queryBackPressure.releaseAfterQuery()).toCompletableFuture();
        } catch (RuntimeException e){
            queryBackPressure.releaseAfterQuery();
            throw e;
        }
    }

    protected Iterator<Seq<BatchableStatement<BoundStatement>>> toGroupedBatchableStatementsSequenceIterator(
        final DistributedStoreManager.MaskedTimestamp commitTime,
        final KCVMutation keyMutations,
        final CQLKeyColumnValueStore columnValueStore,
        final StaticBuffer key){

        Iterator<BatchableStatement<BoundStatement>> deletions = deletionsFunction
            .getBatchableStatementsForColumnOperation(commitTime, keyMutations, columnValueStore, key);
        Iterator<BatchableStatement<BoundStatement>> additions = additionsFunction
            .getBatchableStatementsForColumnOperation(commitTime, keyMutations, columnValueStore, key);

        return Iterator.concat(deletions, additions)
            .grouped(this.batchSize);
    }
}
