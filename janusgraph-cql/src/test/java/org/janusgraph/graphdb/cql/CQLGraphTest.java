// Copyright 2017 JanusGraph Authors
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

package org.janusgraph.graphdb.cql;

import io.github.artsok.RepeatedIfExceptionsTest;
import org.janusgraph.JanusGraphCassandraContainer;
import org.janusgraph.diskstorage.configuration.ConfigElement;
import org.janusgraph.diskstorage.configuration.WriteConfiguration;
import org.janusgraph.graphdb.JanusGraphTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.janusgraph.diskstorage.cql.CQLConfigOptions.ATOMIC_BATCH_MUTATE;
import static org.janusgraph.diskstorage.cql.CQLConfigOptions.BASE_PROGRAMMATIC_CONFIGURATION_ENABLED;
import static org.janusgraph.diskstorage.cql.CQLConfigOptions.BATCH_STATEMENT_SIZE;
import static org.janusgraph.diskstorage.cql.CQLConfigOptions.EXECUTOR_SERVICE_ENABLED;
import static org.janusgraph.diskstorage.cql.CQLConfigOptions.LOCAL_DATACENTER;
import static org.janusgraph.diskstorage.cql.CQLConfigOptions.STRING_CONFIGURATION;
import static org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration.ASSIGN_TIMESTAMP;
import static org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration.CONNECTION_TIMEOUT;
import static org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration.STORAGE_HOSTS;
import static org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration.STORAGE_PORT;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Testcontainers
public class CQLGraphTest extends JanusGraphTest {
    @Container
    public static final JanusGraphCassandraContainer cqlContainer = new JanusGraphCassandraContainer();

    private static final String DATASTAX_JAVA_DRIVER_STRING_CONFIGURATION_PATTERN =
        "datastax-java-driver {\n" +
            "advanced.address-translator.class = \"PassThroughAddressTranslator\"\n" +
            "basic.contact-points = [ \"${hostname}:${port}\" ]\n" +
            "basic.session-name = JanusGraphCQLSession\n" +
            "basic.load-balancing-policy{\n" +
            "    local-datacenter = \"${datacenter}\"\n" +
            "}\n" +
            "basic.request.timeout = ${timeout} milliseconds\n"+
            "advanced.connection.connect-timeout = ${timeout} milliseconds\n"+
            "advanced.connection.pool.local.size = 1\n"+
            "advanced.connection.pool.remote.size = 1\n"+
            "advanced.connection.max-requests-per-connection = 1024\n"+
            "advanced.metadata.schema.debouncer.window = 0\n"+
            "advanced.netty.io-group.size = 0\n"+
            "advanced.netty.admin-group.size = 0\n"+
            "advanced.netty.io-group.shutdown.quiet-period = 0\n"+
            "advanced.netty.admin-group.shutdown.quiet-period = 0\n"+
            "}\n";

    @Override
    public WriteConfiguration getConfiguration() {

        WriteConfiguration wc = cqlContainer.getConfiguration(getClass().getSimpleName()).getConfiguration();

        String[] hostname = wc.get(ConfigElement.getPath(STORAGE_HOSTS), String[].class);
        String port = wc.get(ConfigElement.getPath(STORAGE_PORT), Integer.class).toString();
        Duration timeout = wc.get(ConfigElement.getPath(CONNECTION_TIMEOUT), Duration.class);
        if(timeout == null){
            timeout = CONNECTION_TIMEOUT.getDefaultValue();
        }

        String preparedDataStaxConfiguration = DATASTAX_JAVA_DRIVER_STRING_CONFIGURATION_PATTERN
            .replace("${hostname}", hostname[0])
            .replace("${port}", port)
            .replace("${datacenter}", LOCAL_DATACENTER.getDefaultValue())
            .replace("${timeout}", String.valueOf(timeout.toMillis()));

        wc.set(ConfigElement.getPath(BASE_PROGRAMMATIC_CONFIGURATION_ENABLED), false);
        wc.set(ConfigElement.getPath(STRING_CONFIGURATION), preparedDataStaxConfiguration);

        return wc;
    }

    @Test
    public void testHasTTL() {
        assertTrue(features.hasCellTTL());
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Override
    public void simpleLogTest() throws InterruptedException{
        super.simpleLogTest();
    }

    protected static Stream<Arguments> generateConsistencyConfigs() {
        return Arrays.stream(new Arguments[]{
            arguments(true, true, 20, true),
            arguments(true, false, 20, true),
            arguments(true, false, 1, true),
            arguments(false, true, 20, true),
            arguments(false, false, 20, true),
            arguments(false, false, 1, true),
            arguments(true, true, 20, false),
            arguments(true, false, 20, false),
            arguments(true, false, 1, false),
            arguments(false, true, 20, false),
            arguments(false, false, 20, false),
            arguments(false, false, 1, false),
        });
    }

    @Override
    @Test
    @Disabled
    public void testConsistencyEnforcement() {
        // disable original test in favour of parameterized test
    }

    @ParameterizedTest
    @MethodSource("generateConsistencyConfigs")
    public void testConsistencyEnforcement(boolean assignTimestamp, boolean atomicBatch, int batchSize, boolean executorServiceEnabled) {
        clopen(option(ASSIGN_TIMESTAMP), assignTimestamp, option(ATOMIC_BATCH_MUTATE), atomicBatch, option(BATCH_STATEMENT_SIZE), batchSize, option(EXECUTOR_SERVICE_ENABLED), executorServiceEnabled);
        super.testConsistencyEnforcement();
    }

    @Override
    @Test
    @Disabled
    public void testConcurrentConsistencyEnforcement() {
        // disable original test in favour of parameterized test
    }

    @ParameterizedTest
    @MethodSource("generateConsistencyConfigs")
    public void testConcurrentConsistencyEnforcement(boolean assignTimestamp, boolean atomicBatch, int batchSize, boolean executorServiceEnabled) throws Exception {
        clopen(option(ASSIGN_TIMESTAMP), assignTimestamp, option(ATOMIC_BATCH_MUTATE), atomicBatch, option(BATCH_STATEMENT_SIZE), batchSize, option(EXECUTOR_SERVICE_ENABLED), executorServiceEnabled);
        super.testConcurrentConsistencyEnforcement();
    }
}
