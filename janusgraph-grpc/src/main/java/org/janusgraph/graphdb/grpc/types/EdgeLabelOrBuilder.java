// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: janusgraph/types/v1/schema_types.proto

package org.janusgraph.graphdb.grpc.types;

public interface EdgeLabelOrBuilder extends
    // @@protoc_insertion_point(interface_extends:janusgraph.types.v1.EdgeLabel)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The Internal id.
   * </pre>
   *
   * <code>.google.protobuf.Int64Value id = 1 [json_name = "id"];</code>
   * @return Whether the id field is set.
   */
  boolean hasId();
  /**
   * <pre>
   * The Internal id.
   * </pre>
   *
   * <code>.google.protobuf.Int64Value id = 1 [json_name = "id"];</code>
   * @return The id.
   */
  com.google.protobuf.Int64Value getId();
  /**
   * <pre>
   * The Internal id.
   * </pre>
   *
   * <code>.google.protobuf.Int64Value id = 1 [json_name = "id"];</code>
   */
  com.google.protobuf.Int64ValueOrBuilder getIdOrBuilder();

  /**
   * <pre>
   * The EdgeLabel Name.
   * </pre>
   *
   * <code>string name = 2 [json_name = "name"];</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <pre>
   * The EdgeLabel Name.
   * </pre>
   *
   * <code>string name = 2 [json_name = "name"];</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   * Defines queryable direction.
   * </pre>
   *
   * <code>.janusgraph.types.v1.EdgeLabel.Direction direction = 3 [json_name = "direction"];</code>
   * @return The enum numeric value on the wire for direction.
   */
  int getDirectionValue();
  /**
   * <pre>
   * Defines queryable direction.
   * </pre>
   *
   * <code>.janusgraph.types.v1.EdgeLabel.Direction direction = 3 [json_name = "direction"];</code>
   * @return The direction.
   */
  org.janusgraph.graphdb.grpc.types.EdgeLabel.Direction getDirection();

  /**
   * <pre>
   * Defines Multiplicity of a edge.
   * </pre>
   *
   * <code>.janusgraph.types.v1.EdgeLabel.Multiplicity multiplicity = 4 [json_name = "multiplicity"];</code>
   * @return The enum numeric value on the wire for multiplicity.
   */
  int getMultiplicityValue();
  /**
   * <pre>
   * Defines Multiplicity of a edge.
   * </pre>
   *
   * <code>.janusgraph.types.v1.EdgeLabel.Multiplicity multiplicity = 4 [json_name = "multiplicity"];</code>
   * @return The multiplicity.
   */
  org.janusgraph.graphdb.grpc.types.EdgeLabel.Multiplicity getMultiplicity();
}
