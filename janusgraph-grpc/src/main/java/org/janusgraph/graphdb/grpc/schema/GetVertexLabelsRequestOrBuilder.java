// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: janusgraph/schema/v1/schema_manager.proto

package org.janusgraph.graphdb.grpc.schema;

public interface GetVertexLabelsRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:janusgraph.schema.v1.GetVertexLabelsRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.janusgraph.types.v1.JanusGraphContext context = 1 [json_name = "context"];</code>
   * @return Whether the context field is set.
   */
  boolean hasContext();
  /**
   * <code>.janusgraph.types.v1.JanusGraphContext context = 1 [json_name = "context"];</code>
   * @return The context.
   */
  org.janusgraph.graphdb.grpc.types.JanusGraphContext getContext();
  /**
   * <code>.janusgraph.types.v1.JanusGraphContext context = 1 [json_name = "context"];</code>
   */
  org.janusgraph.graphdb.grpc.types.JanusGraphContextOrBuilder getContextOrBuilder();
}
