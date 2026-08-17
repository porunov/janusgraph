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

package org.janusgraph.diskstorage.lucene;

import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;
import org.janusgraph.core.JanusGraphVertex;
import org.janusgraph.core.JanusGraphVertexProperty;
import org.janusgraph.core.Multiplicity;
import org.janusgraph.core.PropertyKey;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.janusgraph.core.schema.SchemaStatus;
import org.janusgraph.diskstorage.configuration.ModifiableConfiguration;
import org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration;
import org.janusgraph.graphdb.database.StandardJanusGraph;
import org.janusgraph.graphdb.database.index.CdcElementChange;
import org.janusgraph.graphdb.database.index.MixedIndexUpdateApplier;
import org.janusgraph.graphdb.database.management.ManagementSystem;
import org.janusgraph.graphdb.internal.ElementCategory;
import org.janusgraph.graphdb.relations.RelationIdentifier;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import static org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration.INDEX_BACKEND;
import static org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration.INDEX_DIRECTORY;
import static org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration.STORAGE_BACKEND;
import static org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration.STORAGE_DIRECTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifies the per-index CDC mutation modes on the synchronous commit path:
 * cdc-only (enabled + !synchronous) skips the synchronous mixed-index ADDITIONS (the CDC worker rebuilds those from
 * current state), while dual (enabled + synchronous) still writes them. Relation-element DOCUMENT DELETIONS remain
 * synchronous even in cdc-only mode, because CDC events cannot always identify a deleted relation's document
 * (value-region identities are absent from tombstones; whole-row vertex removals emit a single partition delete).
 * The graph data is persisted in all cases.
 */
public class CdcSkipMutationTest {

    private static final String INDEX = "search";
    private static final String VERTEX_INDEX = "vsearch";

    @TempDir
    Path tempDir;

    private JanusGraph openGraph(boolean cdcEnabled, boolean synchronous) {
        ModifiableConfiguration config = GraphDatabaseConfiguration.buildGraphConfiguration();
        config.set(STORAGE_BACKEND, "berkeleyje");
        config.set(STORAGE_DIRECTORY, tempDir.resolve("bdb").toString());
        config.set(INDEX_BACKEND, "lucene", INDEX);
        config.set(INDEX_DIRECTORY, tempDir.resolve("lucene").toString(), INDEX);
        config.set(GraphDatabaseConfiguration.INDEX_CDC_ENABLED, cdcEnabled, INDEX);
        config.set(GraphDatabaseConfiguration.INDEX_CDC_SYNCHRONOUS, synchronous, INDEX);
        return JanusGraphFactory.open(config.getConfiguration());
    }

    private void createMixedIndex(JanusGraph g) throws InterruptedException {
        JanusGraphManagement mgmt = g.openManagement();
        PropertyKey name = mgmt.makePropertyKey("name").dataType(String.class).make();
        mgmt.buildIndex(VERTEX_INDEX, Vertex.class).addKey(name).buildMixedIndex(INDEX);
        mgmt.commit();
        ManagementSystem.awaitGraphIndexStatus(g, VERTEX_INDEX).status(SchemaStatus.ENABLED)
            .timeout(60, ChronoUnit.SECONDS).call();
    }

    @Test
    public void cdcOnlySkipsSynchronousMixedIndexWrite() throws Exception {
        JanusGraph g = openGraph(true, false);
        try {
            createMixedIndex(g);
            g.addVertex("name", "alice");
            g.tx().commit();
            // The vertex is persisted in the graph (full scan does not use the mixed index).
            assertEquals(1L, g.traversal().V().count().next().longValue());
            // But the mixed-index document was NOT written synchronously (cdc-only mode).
            assertEquals(0L, g.indexQuery(VERTEX_INDEX, "v.name:alice").vertexStream().count());
        } finally {
            g.close();
        }
    }

    @Test
    public void dualWritesMixedIndexSynchronously() throws Exception {
        JanusGraph g = openGraph(true, true);
        try {
            createMixedIndex(g);
            g.addVertex("name", "bob");
            g.tx().commit();
            assertEquals(1L, g.traversal().V().count().next().longValue());
            // dual mode: the mixed index IS written synchronously.
            assertEquals(1L, g.indexQuery(VERTEX_INDEX, "v.name:bob").vertexStream().count());
        } finally {
            g.close();
        }
    }

    @Test
    public void cdcOnlyRemovesEdgeDocumentSynchronouslyWhenBothEndpointsAreRemoved() throws Exception {
        // A removed relation's document cannot always be identified from CDC events: a whole-row vertex removal
        // emits ONE partition-level delete with no per-edge identity, and when both endpoints die in the same
        // transaction (as here) no mirror tombstone survives on any row either. Relation-document DELETIONS
        // therefore stay synchronous even in cdc-only mode: after this commit the edge document must be gone
        // WITHOUT any CDC applier involvement.
        JanusGraph g = openGraph(true, false);
        try {
            JanusGraphManagement mgmt = g.openManagement();
            PropertyKey since = mgmt.makePropertyKey("since").dataType(String.class).make();
            mgmt.buildIndex("esearch", Edge.class).addKey(since).buildMixedIndex(INDEX);
            mgmt.commit();
            ManagementSystem.awaitGraphIndexStatus(g, "esearch").status(SchemaStatus.ENABLED)
                .timeout(60, ChronoUnit.SECONDS).call();

            JanusGraphVertex a = (JanusGraphVertex) g.addVertex();
            JanusGraphVertex b = (JanusGraphVertex) g.addVertex();
            Edge e = a.addEdge("knows", b, "since", "2050");
            g.tx().commit();
            RelationIdentifier eid = (RelationIdentifier) e.id();
            // Seed the document the way the CDC pipeline would (cdc-only skips synchronous additions).
            new MixedIndexUpdateApplier((StandardJanusGraph) g, Collections.singleton(INDEX))
                .apply(Collections.singletonList(new CdcElementChange(ElementCategory.EDGE, eid)));
            assertEquals(1L, g.indexQuery("esearch", "e.since:2050").edgeStream().count());

            g.traversal().V(a.id(), b.id()).drop().iterate(); // both endpoints in ONE transaction
            g.tx().commit();
            assertEquals(0L, g.indexQuery("esearch", "e.since:2050").edgeStream().count(),
                "the edge document must be removed synchronously by the deleting commit itself");
        } finally {
            g.close();
        }
    }

    @Test
    public void cdcOnlyLeavesMirrorIdentifiedEdgeDeletionsToTheWorker() throws Exception {
        // When at least one endpoint of a MULTI edge survives the transaction, that endpoint's row keeps an
        // ordinary column tombstone carrying the full edge identity (the "mirror" copy) -- the CDC worker removes
        // the document from that event. The commit must NOT pay a redundant synchronous delete then: for a
        // super-node removal with surviving neighbors that would be one synchronous index operation per incident
        // edge, exactly the per-edge burst that storage.drop-whole-row-on-vertex-removal exists to avoid.
        JanusGraph g = openGraph(true, false);
        try {
            JanusGraphManagement mgmt = g.openManagement();
            PropertyKey since = mgmt.makePropertyKey("since").dataType(String.class).make();
            mgmt.buildIndex("esearch", Edge.class).addKey(since).buildMixedIndex(INDEX);
            mgmt.commit();
            ManagementSystem.awaitGraphIndexStatus(g, "esearch").status(SchemaStatus.ENABLED)
                .timeout(60, ChronoUnit.SECONDS).call();

            JanusGraphVertex hub = (JanusGraphVertex) g.addVertex();
            JanusGraphVertex neighbor = (JanusGraphVertex) g.addVertex();
            Edge e = hub.addEdge("knows", neighbor, "since", "2060");
            g.tx().commit();
            RelationIdentifier eid = (RelationIdentifier) e.id();
            MixedIndexUpdateApplier applier =
                new MixedIndexUpdateApplier((StandardJanusGraph) g, Collections.singleton(INDEX));
            applier.apply(Collections.singletonList(new CdcElementChange(ElementCategory.EDGE, eid)));
            assertEquals(1L, g.indexQuery("esearch", "e.since:2060").edgeStream().count());

            g.traversal().V(hub.id()).drop().iterate(); // the neighbor survives -> its mirror tombstone identifies the edge
            g.tx().commit();
            assertEquals(1L, g.indexQuery("esearch", "e.since:2060").edgeTotals().longValue(),
                "the commit leaves a mirror-identified edge deletion to the CDC worker (no synchronous delete)");

            // ...and the worker's event-driven removal converges it, completing the division of labor.
            applier.apply(Collections.singletonList(new CdcElementChange(ElementCategory.EDGE, eid)));
            // Index queries read through the thread-bound transaction's snapshot; refresh it so the assertion
            // observes the applier's (separate-transaction) removal rather than the searcher cached above.
            g.tx().rollback();
            assertEquals(0L, g.indexQuery("esearch", "e.since:2060").edgeTotals().longValue(),
                "the CDC applier removes the document from the mirror event");
        } finally {
            g.close();
        }
    }

    @Test
    public void cdcOnlyRemovesConstrainedMultiplicityEdgeDocumentSynchronously() throws Exception {
        // Constrained-multiplicity edges keep their relation id in the storage VALUE region, which a Cassandra
        // delete tombstone does not carry -- CDC events can never identify their documents. The deleting commit
        // must remove the document synchronously, with no CDC applier involvement.
        JanusGraph g = openGraph(true, false);
        try {
            JanusGraphManagement mgmt = g.openManagement();
            PropertyKey level = mgmt.makePropertyKey("level").dataType(String.class).make();
            mgmt.makeEdgeLabel("owns").multiplicity(Multiplicity.SIMPLE).make();
            mgmt.buildIndex("osearch", Edge.class).addKey(level).buildMixedIndex(INDEX);
            mgmt.commit();
            ManagementSystem.awaitGraphIndexStatus(g, "osearch").status(SchemaStatus.ENABLED)
                .timeout(60, ChronoUnit.SECONDS).call();

            JanusGraphVertex a = (JanusGraphVertex) g.addVertex();
            JanusGraphVertex b = (JanusGraphVertex) g.addVertex();
            Edge e = a.addEdge("owns", b, "level", "gold");
            g.tx().commit();
            RelationIdentifier eid = (RelationIdentifier) e.id();
            new MixedIndexUpdateApplier((StandardJanusGraph) g, Collections.singleton(INDEX))
                .apply(Collections.singletonList(new CdcElementChange(ElementCategory.EDGE, eid)));
            assertEquals(1L, g.indexQuery("osearch", "e.level:gold").edgeStream().count());

            g.traversal().E(eid).drop().iterate();
            g.tx().commit();
            assertEquals(0L, g.indexQuery("osearch", "e.level:gold").edgeStream().count(),
                "the constrained-multiplicity edge document must be removed synchronously");
        } finally {
            g.close();
        }
    }

    @Test
    public void cdcOnlyKeepsReplacedRelationDocumentForTheWorkerToRefresh() throws Exception {
        // Updating a constrained-multiplicity edge deletes and re-adds the relation under the SAME id. The old
        // document must NOT be removed synchronously then: the worker rewrites the same document id from current
        // state, and a synchronous whole-document delete could land after that rewrite (e.g. a delayed index
        // write), erasing the fresh document with no later event to restore it. Skipping the delete leaves the
        // document briefly stale (ordinary CDC lag) instead of indefinitely missing.
        JanusGraph g = openGraph(true, false);
        try {
            JanusGraphManagement mgmt = g.openManagement();
            PropertyKey level = mgmt.makePropertyKey("level").dataType(String.class).make();
            mgmt.makeEdgeLabel("owns").multiplicity(Multiplicity.SIMPLE).make();
            mgmt.buildIndex("rsearch", Edge.class).addKey(level).buildMixedIndex(INDEX);
            mgmt.commit();
            ManagementSystem.awaitGraphIndexStatus(g, "rsearch").status(SchemaStatus.ENABLED)
                .timeout(60, ChronoUnit.SECONDS).call();

            JanusGraphVertex a = (JanusGraphVertex) g.addVertex();
            JanusGraphVertex b = (JanusGraphVertex) g.addVertex();
            Edge e = a.addEdge("owns", b, "level", "silver");
            g.tx().commit();
            RelationIdentifier eid = (RelationIdentifier) e.id();
            MixedIndexUpdateApplier applier =
                new MixedIndexUpdateApplier((StandardJanusGraph) g, Collections.singleton(INDEX));
            applier.apply(Collections.singletonList(new CdcElementChange(ElementCategory.EDGE, eid)));
            assertEquals(1L, g.indexQuery("rsearch", "e.level:silver").edgeTotals().longValue());

            g.traversal().E(eid).property("level", "gold").iterate(); // update = delete + re-add of the same id
            g.tx().commit();
            assertEquals(1L, g.indexQuery("rsearch", "e.level:silver").edgeTotals().longValue(),
                "the replaced relation's document is left for the worker to refresh (no synchronous delete)");

            applier.apply(Collections.singletonList(new CdcElementChange(ElementCategory.EDGE, eid)));
            g.tx().rollback(); // refresh the thread-bound snapshot to observe the applier's rewrite
            assertEquals(1L, g.indexQuery("rsearch", "e.level:gold").edgeTotals().longValue(),
                "the worker rewrote the same document from current state");
            assertEquals(0L, g.indexQuery("rsearch", "e.level:silver").edgeTotals().longValue(),
                "the stale value is gone after the worker's refresh");
        } finally {
            g.close();
        }
    }

    @Test
    public void cdcOnlyRemovesMetaPropertyDocumentSynchronously() throws Exception {
        // A removed vertex property's relation id also lives in the value region: the meta-property document
        // (property-element mixed index) is likewise only removable by the deleting commit itself.
        JanusGraph g = openGraph(true, false);
        try {
            JanusGraphManagement mgmt = g.openManagement();
            mgmt.makePropertyKey("sensor").dataType(String.class).make();
            PropertyKey reading = mgmt.makePropertyKey("reading").dataType(String.class).make();
            mgmt.buildIndex("psearch", JanusGraphVertexProperty.class).addKey(reading).buildMixedIndex(INDEX);
            mgmt.commit();
            ManagementSystem.awaitGraphIndexStatus(g, "psearch").status(SchemaStatus.ENABLED)
                .timeout(60, ChronoUnit.SECONDS).call();

            JanusGraphVertex v = (JanusGraphVertex) g.addVertex();
            JanusGraphVertexProperty<?> p = (JanusGraphVertexProperty<?>) v.property("sensor", "s1");
            p.property("reading", "hot");
            g.tx().commit();
            RelationIdentifier pid = (RelationIdentifier) p.id();
            new MixedIndexUpdateApplier((StandardJanusGraph) g, Collections.singleton(INDEX))
                .apply(Collections.singletonList(new CdcElementChange(ElementCategory.PROPERTY, pid)));
            assertEquals(1L, g.indexQuery("psearch", "p.reading:hot").propertyStream().count());

            g.traversal().V(v.id()).properties("sensor").drop().iterate(); // vertex stays, property goes
            g.tx().commit();
            assertEquals(0L, g.indexQuery("psearch", "p.reading:hot").propertyStream().count(),
                "the meta-property document must be removed synchronously");
        } finally {
            g.close();
        }
    }

    @Test
    public void synchronousWriteKeptWhenSynchronousFalseButCdcDisabled() throws Exception {
        // cdc.synchronous=false WITHOUT cdc.enabled=true is a dead setting (warned at startup): CDC is off for this
        // backend, so the synchronous mixed-index write must still happen. A regression keying the cdc-only skip on
        // cdc.synchronous alone would silently stop maintaining the index with NO worker to compensate.
        JanusGraph g = openGraph(false, false);
        try {
            createMixedIndex(g);
            g.addVertex("name", "carol");
            g.tx().commit();
            assertEquals(1L, g.indexQuery(VERTEX_INDEX, "v.name:carol").vertexStream().count(),
                "the synchronous mixed-index write must NOT be skipped while cdc.enabled=false");
        } finally {
            g.close();
        }
    }

    @Test
    public void compositeIndexUnaffectedByCdcOnlyMode() throws Exception {
        // cdc-only filters mixed-index updates out at generation time (StandardJanusGraph.commitIndexAppliesToFilter);
        // composite indexes live in the primary storage backend and must never be caught by that filter.
        JanusGraph g = openGraph(true, false);
        try {
            JanusGraphManagement mgmt = g.openManagement();
            PropertyKey code = mgmt.makePropertyKey("code").dataType(String.class).make();
            mgmt.buildIndex("byCode", Vertex.class).addKey(code).buildCompositeIndex();
            mgmt.commit();
            ManagementSystem.awaitGraphIndexStatus(g, "byCode").status(SchemaStatus.ENABLED)
                .timeout(60, ChronoUnit.SECONDS).call();
            g.addVertex("code", "x1");
            g.tx().commit();
            assertEquals(1L, g.traversal().V().has("code", "x1").count().next().longValue(),
                "composite index lookups must keep working in cdc-only mode");
        } finally {
            g.close();
        }
    }
}
