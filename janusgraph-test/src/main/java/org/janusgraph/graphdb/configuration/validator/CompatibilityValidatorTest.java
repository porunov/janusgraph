// Copyright 2019 JanusGraph Authors
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

package org.janusgraph.graphdb.configuration.validator;

import org.janusgraph.core.JanusGraphException;
import org.janusgraph.graphdb.configuration.JanusGraphConstants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Validator tests for backward compatibility with Titan
 */
public class CompatibilityValidatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldThrowExceptionOnNullVersion() {

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("JanusGraph version nor Titan compatibility have not been initialized");

        CompatibilityValidator.validateBackwardCompatibilityWithTitan(null, "");
    }

    @Test
    public void shouldThrowExceptionOnTitanIncompatibleVersion() {

        exception.expect(JanusGraphException.class);

        CompatibilityValidator.validateBackwardCompatibilityWithTitan("not_compatible", "");
    }

    @Test
    public void shouldPassOnJanusGraphIdStoreName() {

        CompatibilityValidator.validateBackwardCompatibilityWithTitan(
            JanusGraphConstants.TITAN_COMPATIBLE_VERSIONS.get(0),
            JanusGraphConstants.JANUSGRAPH_ID_STORE_NAME);
    }

    @Test
    public void shouldPassOnTitanIdStoreName() {

        CompatibilityValidator.validateBackwardCompatibilityWithTitan(
            JanusGraphConstants.TITAN_COMPATIBLE_VERSIONS.get(0),
            JanusGraphConstants.TITAN_ID_STORE_NAME);
    }

    @Test
    public void shouldThrowExceptionOnIncompatibleIdStoreName() {

        expectIncompatibleIdStoreNameException();

        CompatibilityValidator.validateBackwardCompatibilityWithTitan(
            JanusGraphConstants.TITAN_COMPATIBLE_VERSIONS.get(0), "not_compatible");
    }

    @Test
    public void shouldThrowExceptionOnNullIdStoreName() {

        expectIncompatibleIdStoreNameException();

        CompatibilityValidator.validateBackwardCompatibilityWithTitan(
            JanusGraphConstants.TITAN_COMPATIBLE_VERSIONS.get(0), null);
    }

    private void expectIncompatibleIdStoreNameException(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("ID store for Titan compatibility has not been initialized to: " + JanusGraphConstants.TITAN_ID_STORE_NAME);
    }

}
