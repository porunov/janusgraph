// Copyright 2023 JanusGraph Authors
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

package org.janusgraph.diskstorage.cql.strategy;

import com.datastax.oss.driver.api.core.CqlSession;
import org.janusgraph.diskstorage.StaticBuffer;
import org.janusgraph.diskstorage.configuration.Configuration;
import org.janusgraph.diskstorage.cql.util.TokenRangeKeyGroup;
import org.janusgraph.diskstorage.keycolumnvalue.StoreTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GroupedExecutionStrategyBuilderTest {

    @Test
    public void shouldBuildDefaultStrategy(){
        GroupedExecutionStrategy strategy = GroupedExecutionStrategyBuilder.build(null,
            null, GroupedExecutionStrategyBuilder.DEFAULT_CLASS);
        Assertions.assertEquals(TokenAwareGroupedExecutionStrategy.class, strategy.getClass());
    }

    @Test
    public void shouldBuildSpecificStrategy(){
        GroupedExecutionStrategy strategy = GroupedExecutionStrategyBuilder.build(null,
            null, TestGroupedExecutionStrategy.class.getName());
        Assertions.assertEquals(TestGroupedExecutionStrategy.class, strategy.getClass());
    }

    @Test
    public void shouldFailBuildCustomStrategyWhichThrowsException(){
        Assertions.assertThrows(IllegalStateException.class, () -> GroupedExecutionStrategyBuilder.build(null,
            null, TestFailingGroupedExecutionStrategy.class.getName()));
    }

    @Test
    public void shouldFailBuildCustomStrategyWithoutNecessaryConstructor(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> GroupedExecutionStrategyBuilder.build(null,
            null, TestNoArgsGroupedExecutionStrategy.class.getName()));
    }

    @Test
    public void shouldFailBuildNonExistingClass(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> GroupedExecutionStrategyBuilder.build(null,
            null, "NonExistingStrategyImplementation"));
    }

    @Test
    public void shouldFailBuildIfNotImplementsProperInterface(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> GroupedExecutionStrategyBuilder.build(null,
            null, Integer.class.getName()));
    }

    private static class TestGroupedExecutionStrategy implements GroupedExecutionStrategy{

        public TestGroupedExecutionStrategy(Configuration configuration, CqlSession session){
            // `configuration` and `session` is ignored
        }

        @Override
        public <R, Q> void execute(R futureResult,
                                   Q queries,
                                   List<StaticBuffer> keys,
                                   ResultFiller<R, Q, TokenRangeKeyGroup> withKeysGroupingFiller,
                                   ResultFiller<R, Q, List<StaticBuffer>> withoutKeysGroupingFiller,
                                   StoreTransaction txh,
                                   int keysGroupingLimit){
            // ignored
        }
    }

    public static class TestNoArgsGroupedExecutionStrategy implements GroupedExecutionStrategy{

        @Override
        public <R, Q> void execute(R futureResult, Q queries, List<StaticBuffer> keys, ResultFiller<R, Q, TokenRangeKeyGroup> withKeysGroupingFiller, ResultFiller<R, Q, List<StaticBuffer>> withoutKeysGroupingFiller, StoreTransaction txh, int keysGroupingLimit) {
            // ignored
        }
    }

    private static class TestFailingGroupedExecutionStrategy implements GroupedExecutionStrategy{

        public TestFailingGroupedExecutionStrategy(Configuration configuration, CqlSession session){
            throw new RuntimeException();
        }

        @Override
        public <R, Q> void execute(R futureResult,
                                   Q queries,
                                   List<StaticBuffer> keys,
                                   ResultFiller<R, Q, TokenRangeKeyGroup> withKeysGroupingFiller,
                                   ResultFiller<R, Q, List<StaticBuffer>> withoutKeysGroupingFiller,
                                   StoreTransaction txh,
                                   int keysGroupingLimit){
            // ignored
        }
    }
}
