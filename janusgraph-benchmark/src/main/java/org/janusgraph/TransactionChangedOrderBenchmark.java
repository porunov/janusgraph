// Copyright 2026 JanusGraph Authors
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

package org.janusgraph;

import org.apache.tinkerpop.gremlin.process.traversal.Order;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;
import org.janusgraph.core.JanusGraphTransaction;
import org.janusgraph.core.PropertyKey;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.janusgraph.core.schema.Mapping;
import org.janusgraph.diskstorage.BackendException;
import org.janusgraph.diskstorage.configuration.ModifiableConfiguration;
import org.janusgraph.diskstorage.es.ElasticSearchIndex;
import org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Queries which an index sorts, in a transaction which changed the order key of many vertices: a mixed index on
 * Elasticsearch holds {@code kind} and {@code updatedAt} of 50,000 vertices, the transaction sets {@code updatedAt} of
 * {@code changed} of them, and each operation asks ten times for the ten vertices of the kind with the latest
 * {@code updatedAt}. A vertex whose {@code updatedAt} the transaction changed may belong among them although the index
 * holds its old value, so each query looks at all of those vertices. Only the queries are measured.
 * <p>
 * The Elasticsearch node defaults to 127.0.0.1:9200; {@code -Dbench.es.host} and {@code -Dbench.es.port} point the
 * benchmark at another.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(1)
public class TransactionChangedOrderBenchmark {

    @Param({"1000", "50000"})
    int changed;

    @Param({"50000"})
    int vertices;

    JanusGraph graph;
    List<Object> ids;

    @Setup
    public void setUp() throws InterruptedException {
        final ModifiableConfiguration config = GraphDatabaseConfiguration.buildGraphConfiguration();
        config.set(GraphDatabaseConfiguration.STORAGE_BACKEND, "inmemory");
        config.set(GraphDatabaseConfiguration.INDEX_BACKEND, "elasticsearch", "search");
        config.set(GraphDatabaseConfiguration.INDEX_NAME, "changedorderbench", "search");
        config.set(GraphDatabaseConfiguration.INDEX_HOSTS,
            new String[]{System.getProperty("bench.es.host", "127.0.0.1")}, "search");
        config.set(GraphDatabaseConfiguration.INDEX_PORT, Integer.getInteger("bench.es.port", 9200), "search");
        config.set(ElasticSearchIndex.NUMBER_OF_SHARDS, 1, "search");
        config.set(ElasticSearchIndex.NUMBER_OF_REPLICAS, 0, "search");
        graph = JanusGraphFactory.open(config.getConfiguration());
        final JanusGraphManagement management = graph.openManagement();
        final PropertyKey kind = management.makePropertyKey("kind").dataType(String.class).make();
        final PropertyKey updatedAt = management.makePropertyKey("updatedAt").dataType(Long.class).make();
        management.buildIndex("byKindAndUpdatedAt", Vertex.class).addKey(kind, Mapping.STRING.asParameter())
            .addKey(updatedAt).buildMixedIndex("search");
        management.commit();

        JanusGraphTransaction tx = graph.newTransaction();
        for (int i = 0; i < vertices; i++) {
            tx.addVertex("kind", "k", "updatedAt", (long) i);
            if (i % 10_000 == 9_999) {
                tx.commit();
                tx = graph.newTransaction();
            }
        }
        tx.commit();
        //Elasticsearch shows what it indexed after its next refresh
        while (graph.traversal().V().has("kind", "k").count().next() < vertices) {
            graph.tx().rollback();
            Thread.sleep(200);
        }
        ids = graph.traversal().V().has("kind", "k").id().toList();
        graph.tx().rollback();
    }

    @TearDown
    public void tearDown() throws BackendException {
        JanusGraphFactory.drop(graph);
    }

    /**
     * A transaction which set {@code updatedAt} of the first {@code changed} vertices to values later than any the
     * index holds.
     */
    @State(Scope.Thread)
    public static class ChangedTransaction {
        JanusGraphTransaction tx;

        @Setup(Level.Invocation)
        public void change(TransactionChangedOrderBenchmark benchmark) {
            tx = benchmark.graph.newTransaction();
            for (int i = 0; i < benchmark.changed; i++) {
                tx.traversal().V(benchmark.ids.get(i)).property("updatedAt", (long) (benchmark.vertices + i)).iterate();
            }
        }

        @TearDown(Level.Invocation)
        public void rollback() {
            tx.rollback();
        }
    }

    @Benchmark
    public void latestTenOfTheKind(ChangedTransaction changes, Blackhole blackhole) {
        for (int i = 0; i < 10; i++) {
            final GraphTraversal<Vertex, Object> latest = changes.tx.traversal().V().has("kind", "k")
                .order().by("updatedAt", Order.desc).limit(10).id();
            while (latest.hasNext()) {
                blackhole.consume(latest.next());
            }
        }
    }
}
