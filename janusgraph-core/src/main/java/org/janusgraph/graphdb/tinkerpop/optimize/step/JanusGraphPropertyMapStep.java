package org.janusgraph.graphdb.tinkerpop.optimize.step;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.Traverser;
import org.apache.tinkerpop.gremlin.process.traversal.step.Profiling;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.PropertiesStep;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.PropertyMapStep;
import org.apache.tinkerpop.gremlin.process.traversal.step.util.HasContainer;
import org.apache.tinkerpop.gremlin.process.traversal.step.util.WithOptions;
import org.apache.tinkerpop.gremlin.process.traversal.util.MutableMetrics;
import org.apache.tinkerpop.gremlin.process.traversal.util.TraversalInterruptedException;
import org.apache.tinkerpop.gremlin.process.traversal.util.TraversalProduct;
import org.apache.tinkerpop.gremlin.process.traversal.util.TraversalUtil;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.apache.tinkerpop.gremlin.structure.PropertyType;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.apache.tinkerpop.gremlin.structure.util.wrapped.WrappedVertex;
import org.janusgraph.core.BaseVertexQuery;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphException;
import org.janusgraph.core.JanusGraphMultiVertexQuery;
import org.janusgraph.core.JanusGraphProperty;
import org.janusgraph.core.JanusGraphVertex;
import org.janusgraph.core.JanusGraphVertexQuery;
import org.janusgraph.graphdb.query.BaseQuery;
import org.janusgraph.graphdb.query.JanusGraphPredicateUtils;
import org.janusgraph.graphdb.query.Query;
import org.janusgraph.graphdb.query.profile.QueryProfiler;
import org.janusgraph.graphdb.query.vertex.BasicVertexCentricQueryBuilder;
import org.janusgraph.graphdb.tinkerpop.JanusGraphIoRegistry;
import org.janusgraph.graphdb.tinkerpop.optimize.JanusGraphTraversalUtil;
import org.janusgraph.graphdb.tinkerpop.profile.TP3ProfileWrapper;
import org.janusgraph.graphdb.types.VertexLabelVertex;
import org.janusgraph.graphdb.types.system.BaseLabel;
import org.janusgraph.graphdb.types.system.BaseVertexLabel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// JanusGraphPropertiesStep<E> extends PropertiesStep<E> implements HasStepFolder<Element, E>, Profiling, MultiQueriable<Element,E>

// JanusGraphPropertyMapStep<E extends Element> extends PropertyMapStep<E> implements MultiQueriable<JanusGraphProperty> {
public class JanusGraphPropertyMapStep<K, E> extends PropertyMapStep<K, E> implements Profiling, MultiQueriable<Element, Map<K, E>> {

    private boolean useMultiQuery = false;
    private Set<Vertex> verticesToPrefetch = new HashSet<>();
    private Map<JanusGraphVertex, Map<K, E>> multiQueryResults = null;
    private QueryProfiler queryProfiler = QueryProfiler.NO_OP;

    public JanusGraphPropertyMapStep(PropertyMapStep<K, E> originalStep) {
        super(originalStep.getTraversal(), originalStep.getReturnType(), originalStep.getPropertyKeys());
        originalStep.getLabels().forEach(this::addLabel);

        if (originalStep instanceof JanusGraphPropertyMapStep) {
            JanusGraphPropertyMapStep originalJanusGraphPropertiesStep = (JanusGraphPropertyMapStep) originalStep;
            this.useMultiQuery = originalJanusGraphPropertiesStep.useMultiQuery;
        }
    }


    @Override
    public void setMetrics(MutableMetrics metrics) {
        queryProfiler = new TP3ProfileWrapper(metrics);
    }

    @Override
    public void setUseMultiQuery(boolean useMultiQuery) {
        this.useMultiQuery = useMultiQuery;
    }

    @Override
    public void registerFutureVertexForPrefetching(Vertex futureVertex) {
        verticesToPrefetch.add(futureVertex);
    }

//    protected Traverser.Admin<Map<K, E>> processNextStart() {
//        Traverser.Admin<Element> traverser = this.starts.next();
//        return traverser.split(this.map(traverser), this);
//    }

    @Override
    protected Map<K, E> map(final Traverser.Admin<Element> traverser) {

        Element wrappedElement = traverser.get();

        if (useMultiQuery && wrappedElement instanceof Vertex) {
            if (multiQueryResults == null || !multiQueryResults.containsKey(wrappedElement)) {
                prefetchNextBatch(traverser);
            }
            return multiQueryResults.get(wrappedElement);
        } else if (wrappedElement instanceof JanusGraphVertex || wrappedElement instanceof WrappedVertex) {
            final JanusGraphVertexQuery query = makeQuery((JanusGraphTraversalUtil.getJanusGraphVertex(traverser)).query());
            return convertIterator(query.properties());
        } else {
            //It is some other element (edge or vertex property)

            //this asks for properties
            Iterator<? extends Property<?>> propertiesIt = traverser.get().properties(getPropertyKeys());
            return propertiesIt;
        }
    }






    private <Q extends BaseVertexQuery> Q makeQuery(Q query) {
        final String[] keys = getPropertyKeys();
        query.keys(keys);
//        for (final HasContainer condition : hasContainers) {
//            query.has(condition.getKey(), JanusGraphPredicateUtils.convert(condition.getBiPredicate()), condition.getValue());
//        }
//        for (final HasStepFolder.OrderEntry order : orders) query.orderBy(order.key, order.order);
//        if (limit != BaseQuery.NO_LIMIT) query.limit(limit);
        ((BasicVertexCentricQueryBuilder) query).profiler(queryProfiler);
        return query;
    }

    private Iterator<E> convertIterator(Iterable<? extends JanusGraphProperty> iterable) {
        if (getReturnType().forProperties()) {
            return (Iterator<E>) iterable.iterator();
        }
        assert getReturnType().forValues();
        return (Iterator<E>) Iterators.transform(iterable.iterator(), Property::value);
    }

    private void addPropertiesForPrefetch(final Traverser.Admin<Element> traverser, final JanusGraphMultiVertexQuery multiQuery){
        if (verticesToPrefetch.isEmpty()) {
            multiQuery.addVertex(JanusGraphTraversalUtil.getJanusGraphVertex(traverser));
        } else {
            multiQuery.addAllVertices(verticesToPrefetch);
        }
    }

    private boolean includeToken(final int token) {
        return 0 != (this.tokens & token);
    }

    /**
     * This initialisation method is called when an attempt to retrieve a vertex from the cached multiQuery results
     * doesn't find an entry.
     */
    private void prefetchNextBatch(final Traverser.Admin<Element> traverser) {

        Map<Object, Map<Object, Object>> prefetchedResult = new HashMap<>();

        Map<Object, Object> map = new LinkedHashMap();
        Element element = (Element)traverser.get();
        boolean isVertex = element instanceof Vertex;
        if (this.returnType == PropertyType.VALUE) {
            if (this.includeToken(WithOptions.ids)) {
                if (verticesToPrefetch.isEmpty()) {
                    JanusGraphVertex vertex = JanusGraphTraversalUtil.getJanusGraphVertex(traverser);
                    prefetchedResult.computeIfAbsent(vertex.id(), key -> new LinkedHashMap<>())
                        .put(T.id, vertex.id());
                } else {
                    verticesToPrefetch.forEach(vertex ->
                        prefetchedResult.computeIfAbsent(vertex.id(), key -> new LinkedHashMap<>())
                            .put(T.id, vertex.id()));
                }
            }

            if (element instanceof VertexProperty) {
                if (this.includeToken(WithOptions.keys)) {
                    map.put(T.key, ((VertexProperty)element).key());
                }

                if (this.includeToken(WithOptions.values)) {
                    map.put(T.value, ((VertexProperty)element).value());
                }
            } else if (this.includeToken(WithOptions.labels)) {

                final JanusGraphMultiVertexQuery labelsMultiQuery = JanusGraphTraversalUtil.getTx(getTraversal()).multiQuery();
                addPropertiesForPrefetch(traverser, labelsMultiQuery);
                ((BasicVertexCentricQueryBuilder) labelsMultiQuery).profiler(queryProfiler);
                Map<JanusGraphVertex, Iterable<JanusGraphVertex>> vertexLabels = ((JanusGraphMultiVertexQuery) ((BasicVertexCentricQueryBuilder) labelsMultiQuery).noPartitionRestriction())
                    .types(BaseLabel.VertexLabelEdge).direction(Direction.OUT).vertices();

                vertexLabels.forEach((vertex, labelVertices) -> {
                    Iterator<JanusGraphVertex> labelVerticesIt = labelVertices.iterator();
                    String label = Vertex.DEFAULT_LABEL;
                    if(labelVerticesIt.hasNext()){
                        JanusGraphVertex labelVertex = labelVerticesIt.next();
                        if(labelVertex instanceof VertexLabelVertex){
                            label = ((VertexLabelVertex) labelVertex).name();
                        }
                    }
                    prefetchedResult.computeIfAbsent(vertex.id(), key -> new LinkedHashMap<>())
                        .put(T.label, label);
                });
            }
        }

        final JanusGraphMultiVertexQuery propertiesMultiQuery = JanusGraphTraversalUtil.getTx(getTraversal()).multiQuery();
        addPropertiesForPrefetch(traverser, propertiesMultiQuery);
        makeQuery(propertiesMultiQuery);

        verticesToPrefetch.clear();

        try {
            Map<JanusGraphVertex, Iterable<JanusGraphVertex>> multiQueryProperties = propertiesMultiQuery.properties();
            // TODO: convert properties to collection values and add them into the map
            // Replace multiQueryResults with the map
        } catch (JanusGraphException janusGraphException) {
            if (janusGraphException.isCausedBy(InterruptedException.class)) {
                TraversalInterruptedException traversalInterruptedException = new TraversalInterruptedException();
                traversalInterruptedException.initCause(janusGraphException);
                throw traversalInterruptedException;
            }
            throw janusGraphException;
        }





//        final Map<Object, Object> map = new LinkedHashMap<>();
//        final Element element = traverser.get();
//        final boolean isVertex = element instanceof Vertex;
//        if (this.returnType == PropertyType.VALUE) {
//            if (includeToken(WithOptions.ids)) map.put(T.id, element.id());
//            if (element instanceof VertexProperty) {
//                if (includeToken(WithOptions.keys)) map.put(T.key, ((VertexProperty<?>) element).key());
//                if (includeToken(WithOptions.values)) map.put(T.value, ((VertexProperty<?>) element).value());
//            } else {
//                if (includeToken(WithOptions.labels)) map.put(T.label, element.label());
//            }
//        }
//
//        final Iterator<? extends Property> properties = null == this.propertyTraversal ?
//            element.properties(this.propertyKeys) :
//            TraversalUtil.applyAll(traverser, this.propertyTraversal);
//
//        while (properties.hasNext()) {
//            final Property<?> property = properties.next();
//            final Object value = this.returnType == PropertyType.VALUE ? property.value() : property;
//            if (isVertex) {
//                map.compute(property.key(), (k, v) -> {
//                    final List<Object> values = v != null ? (List<Object>) v : new ArrayList<>();
//                    values.add(value);
//                    return values;
//                });
//            } else {
//                map.put(property.key(), value);
//            }
//        }
//        if (!traversalRing.isEmpty()) {
//            // will cop a ConcurrentModification if a key is dropped so need this little copy here
//            final Set<Object> keys = new HashSet<>(map.keySet());
//            for (final Object key : keys) {
//                map.compute(key, (k, v) -> {
//                    final TraversalProduct product = TraversalUtil.produce(v, (Traversal.Admin) this.traversalRing.next());
//
//                    // compute() should take the null and remove the key
//                    return product.isProductive() ? product.get() : null;
//                });
//            }
//            this.traversalRing.reset();
//        }
    }

//    @Override
//    protected Iterator<E> flatMap(final Traverser.Admin<Element> traverser) {
//        if (useMultiQuery && traverser.get() instanceof Vertex) {
//            if (multiQueryResults == null || !multiQueryResults.containsKey(traverser.get())) {
//                prefetchNextBatch(traverser);
//            }
//            return convertIterator(multiQueryResults.get(traverser.get()));
//        } else if (traverser.get() instanceof JanusGraphVertex || traverser.get() instanceof WrappedVertex) {
//            final JanusGraphVertexQuery query = makeQuery((JanusGraphTraversalUtil.getJanusGraphVertex(traverser)).query());
//            return convertIterator(query.properties());
//        } else {
//            //It is some other element (edge or vertex property)
//            Iterator<E> iterator;
//            if (getReturnType().forValues()) {
//                assert orders.isEmpty() && hasContainers.isEmpty();
//                iterator = traverser.get().values(getPropertyKeys());
//            } else {
//                //this asks for properties
//                assert orders.isEmpty();
//                Iterator<? extends Property<?>> propertiesIt = traverser.get().properties(getPropertyKeys());
//                if(hasContainers.isEmpty()){
//                    iterator = (Iterator<E>) propertiesIt;
//                } else {
//                    List<E> properties = new LinkedList<>();
//                    propertiesIt.forEachRemaining(e -> {
//                        if(HasContainer.testAll(e, hasContainers)){
//                            properties.add((E) e);
//                        }
//                    });
//                    iterator = properties.iterator();
//                }
//            }
//            if (limit!=Query.NO_LIMIT) iterator = Iterators.limit(iterator,limit);
//            return iterator;
//        }
//    }







//    @Override
//    public <S extends Element> List<Map<K, E>> executeMultiQuery(Traversal.Admin<?, S> traversal, Class<S> elementClass, List<Element> queries) {
//        // Get the JanusGraph object from the traversal
//        JanusGraph graph = traversal.asAdmin().getGraph().traversal().getGraph().get();
//
//        // Create a new JanusGraphMultiQuery object
//        JanusGraphMultiQuery multiQuery = graph.multiQuery();
//
//        // Add each query to the multi-query object
//        for (Element query : queries) {
//            multiQuery.addQuery(query.query().properties(getPropertyKeys()));
//        }
//
//        // Execute the multi-query and return the results as a list of property maps
//        List<Map<K, E>> results = new ArrayList<>();
//        multiQuery.properties().forEachRemaining(props -> {
//            Map<K, E> result = (Map<K, E>) props.valueMap();
//            results.add(result);
//        });
//
//        return results;
//    }
//
//    @Override
//    public boolean isStepReplaceable(Class<? extends PropertyMapStep> clazz) {
//        // This implementation only applies to the PropertyMapStep class
//        return clazz.equals(PropertyMapStep.class);
//    }
//
//    @Override
//    protected Map<K, E> map(final Element element) {
//        if (element instanceof JanusGraphProperty) {
//            // If the element is a JanusGraphProperty, return a map with the property value
//            JanusGraphProperty property = (JanusGraphProperty) element;
//            return (Map<K, E>) super.map(property.value());
//        } else {
//            // Otherwise, return an empty map
//            return super.map(element);
//        }
//    }
//
//    @Override
//    public JanusGraphPropertyMapStep<K, E> clone() {
//        JanusGraphPropertyMapStep<K, E> clone = (JanusGraphPropertyMapStep<K, E>) super.clone();
//        clone.setKeepLabels(false);
//        clone.setTraversal(this.getTraversal().clone());
//        clone.integrateChild(this.getTraversal().clone().asAdmin());
//        return clone;
//    }
}
