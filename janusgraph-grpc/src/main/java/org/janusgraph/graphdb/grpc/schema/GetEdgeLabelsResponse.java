// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: janusgraph/schema/v1/schema_manager.proto

package org.janusgraph.graphdb.grpc.schema;

/**
 * Protobuf type {@code janusgraph.schema.v1.GetEdgeLabelsResponse}
 */
public final class GetEdgeLabelsResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:janusgraph.schema.v1.GetEdgeLabelsResponse)
    GetEdgeLabelsResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetEdgeLabelsResponse.newBuilder() to construct.
  private GetEdgeLabelsResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetEdgeLabelsResponse() {
    edgeLabels_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetEdgeLabelsResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetEdgeLabelsResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              edgeLabels_ = new java.util.ArrayList<org.janusgraph.graphdb.grpc.types.EdgeLabel>();
              mutable_bitField0_ |= 0x00000001;
            }
            edgeLabels_.add(
                input.readMessage(org.janusgraph.graphdb.grpc.types.EdgeLabel.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        edgeLabels_ = java.util.Collections.unmodifiableList(edgeLabels_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.janusgraph.graphdb.grpc.schema.SchemaManager.internal_static_janusgraph_schema_v1_GetEdgeLabelsResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.janusgraph.graphdb.grpc.schema.SchemaManager.internal_static_janusgraph_schema_v1_GetEdgeLabelsResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse.class, org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse.Builder.class);
  }

  public static final int EDGE_LABELS_FIELD_NUMBER = 1;
  private java.util.List<org.janusgraph.graphdb.grpc.types.EdgeLabel> edgeLabels_;
  /**
   * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
   */
  @java.lang.Override
  public java.util.List<org.janusgraph.graphdb.grpc.types.EdgeLabel> getEdgeLabelsList() {
    return edgeLabels_;
  }
  /**
   * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
   */
  @java.lang.Override
  public java.util.List<? extends org.janusgraph.graphdb.grpc.types.EdgeLabelOrBuilder> 
      getEdgeLabelsOrBuilderList() {
    return edgeLabels_;
  }
  /**
   * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
   */
  @java.lang.Override
  public int getEdgeLabelsCount() {
    return edgeLabels_.size();
  }
  /**
   * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
   */
  @java.lang.Override
  public org.janusgraph.graphdb.grpc.types.EdgeLabel getEdgeLabels(int index) {
    return edgeLabels_.get(index);
  }
  /**
   * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
   */
  @java.lang.Override
  public org.janusgraph.graphdb.grpc.types.EdgeLabelOrBuilder getEdgeLabelsOrBuilder(
      int index) {
    return edgeLabels_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < edgeLabels_.size(); i++) {
      output.writeMessage(1, edgeLabels_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < edgeLabels_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, edgeLabels_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse)) {
      return super.equals(obj);
    }
    org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse other = (org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse) obj;

    if (!getEdgeLabelsList()
        .equals(other.getEdgeLabelsList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getEdgeLabelsCount() > 0) {
      hash = (37 * hash) + EDGE_LABELS_FIELD_NUMBER;
      hash = (53 * hash) + getEdgeLabelsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code janusgraph.schema.v1.GetEdgeLabelsResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:janusgraph.schema.v1.GetEdgeLabelsResponse)
      org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.janusgraph.graphdb.grpc.schema.SchemaManager.internal_static_janusgraph_schema_v1_GetEdgeLabelsResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.janusgraph.graphdb.grpc.schema.SchemaManager.internal_static_janusgraph_schema_v1_GetEdgeLabelsResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse.class, org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse.Builder.class);
    }

    // Construct using org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getEdgeLabelsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (edgeLabelsBuilder_ == null) {
        edgeLabels_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        edgeLabelsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.janusgraph.graphdb.grpc.schema.SchemaManager.internal_static_janusgraph_schema_v1_GetEdgeLabelsResponse_descriptor;
    }

    @java.lang.Override
    public org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse getDefaultInstanceForType() {
      return org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse.getDefaultInstance();
    }

    @java.lang.Override
    public org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse build() {
      org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse buildPartial() {
      org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse result = new org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse(this);
      int from_bitField0_ = bitField0_;
      if (edgeLabelsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          edgeLabels_ = java.util.Collections.unmodifiableList(edgeLabels_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.edgeLabels_ = edgeLabels_;
      } else {
        result.edgeLabels_ = edgeLabelsBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse) {
        return mergeFrom((org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse other) {
      if (other == org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse.getDefaultInstance()) return this;
      if (edgeLabelsBuilder_ == null) {
        if (!other.edgeLabels_.isEmpty()) {
          if (edgeLabels_.isEmpty()) {
            edgeLabels_ = other.edgeLabels_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureEdgeLabelsIsMutable();
            edgeLabels_.addAll(other.edgeLabels_);
          }
          onChanged();
        }
      } else {
        if (!other.edgeLabels_.isEmpty()) {
          if (edgeLabelsBuilder_.isEmpty()) {
            edgeLabelsBuilder_.dispose();
            edgeLabelsBuilder_ = null;
            edgeLabels_ = other.edgeLabels_;
            bitField0_ = (bitField0_ & ~0x00000001);
            edgeLabelsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getEdgeLabelsFieldBuilder() : null;
          } else {
            edgeLabelsBuilder_.addAllMessages(other.edgeLabels_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<org.janusgraph.graphdb.grpc.types.EdgeLabel> edgeLabels_ =
      java.util.Collections.emptyList();
    private void ensureEdgeLabelsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        edgeLabels_ = new java.util.ArrayList<org.janusgraph.graphdb.grpc.types.EdgeLabel>(edgeLabels_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        org.janusgraph.graphdb.grpc.types.EdgeLabel, org.janusgraph.graphdb.grpc.types.EdgeLabel.Builder, org.janusgraph.graphdb.grpc.types.EdgeLabelOrBuilder> edgeLabelsBuilder_;

    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public java.util.List<org.janusgraph.graphdb.grpc.types.EdgeLabel> getEdgeLabelsList() {
      if (edgeLabelsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(edgeLabels_);
      } else {
        return edgeLabelsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public int getEdgeLabelsCount() {
      if (edgeLabelsBuilder_ == null) {
        return edgeLabels_.size();
      } else {
        return edgeLabelsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public org.janusgraph.graphdb.grpc.types.EdgeLabel getEdgeLabels(int index) {
      if (edgeLabelsBuilder_ == null) {
        return edgeLabels_.get(index);
      } else {
        return edgeLabelsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public Builder setEdgeLabels(
        int index, org.janusgraph.graphdb.grpc.types.EdgeLabel value) {
      if (edgeLabelsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEdgeLabelsIsMutable();
        edgeLabels_.set(index, value);
        onChanged();
      } else {
        edgeLabelsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public Builder setEdgeLabels(
        int index, org.janusgraph.graphdb.grpc.types.EdgeLabel.Builder builderForValue) {
      if (edgeLabelsBuilder_ == null) {
        ensureEdgeLabelsIsMutable();
        edgeLabels_.set(index, builderForValue.build());
        onChanged();
      } else {
        edgeLabelsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public Builder addEdgeLabels(org.janusgraph.graphdb.grpc.types.EdgeLabel value) {
      if (edgeLabelsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEdgeLabelsIsMutable();
        edgeLabels_.add(value);
        onChanged();
      } else {
        edgeLabelsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public Builder addEdgeLabels(
        int index, org.janusgraph.graphdb.grpc.types.EdgeLabel value) {
      if (edgeLabelsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEdgeLabelsIsMutable();
        edgeLabels_.add(index, value);
        onChanged();
      } else {
        edgeLabelsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public Builder addEdgeLabels(
        org.janusgraph.graphdb.grpc.types.EdgeLabel.Builder builderForValue) {
      if (edgeLabelsBuilder_ == null) {
        ensureEdgeLabelsIsMutable();
        edgeLabels_.add(builderForValue.build());
        onChanged();
      } else {
        edgeLabelsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public Builder addEdgeLabels(
        int index, org.janusgraph.graphdb.grpc.types.EdgeLabel.Builder builderForValue) {
      if (edgeLabelsBuilder_ == null) {
        ensureEdgeLabelsIsMutable();
        edgeLabels_.add(index, builderForValue.build());
        onChanged();
      } else {
        edgeLabelsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public Builder addAllEdgeLabels(
        java.lang.Iterable<? extends org.janusgraph.graphdb.grpc.types.EdgeLabel> values) {
      if (edgeLabelsBuilder_ == null) {
        ensureEdgeLabelsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, edgeLabels_);
        onChanged();
      } else {
        edgeLabelsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public Builder clearEdgeLabels() {
      if (edgeLabelsBuilder_ == null) {
        edgeLabels_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        edgeLabelsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public Builder removeEdgeLabels(int index) {
      if (edgeLabelsBuilder_ == null) {
        ensureEdgeLabelsIsMutable();
        edgeLabels_.remove(index);
        onChanged();
      } else {
        edgeLabelsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public org.janusgraph.graphdb.grpc.types.EdgeLabel.Builder getEdgeLabelsBuilder(
        int index) {
      return getEdgeLabelsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public org.janusgraph.graphdb.grpc.types.EdgeLabelOrBuilder getEdgeLabelsOrBuilder(
        int index) {
      if (edgeLabelsBuilder_ == null) {
        return edgeLabels_.get(index);  } else {
        return edgeLabelsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public java.util.List<? extends org.janusgraph.graphdb.grpc.types.EdgeLabelOrBuilder> 
         getEdgeLabelsOrBuilderList() {
      if (edgeLabelsBuilder_ != null) {
        return edgeLabelsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(edgeLabels_);
      }
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public org.janusgraph.graphdb.grpc.types.EdgeLabel.Builder addEdgeLabelsBuilder() {
      return getEdgeLabelsFieldBuilder().addBuilder(
          org.janusgraph.graphdb.grpc.types.EdgeLabel.getDefaultInstance());
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public org.janusgraph.graphdb.grpc.types.EdgeLabel.Builder addEdgeLabelsBuilder(
        int index) {
      return getEdgeLabelsFieldBuilder().addBuilder(
          index, org.janusgraph.graphdb.grpc.types.EdgeLabel.getDefaultInstance());
    }
    /**
     * <code>repeated .janusgraph.types.v1.EdgeLabel edge_labels = 1 [json_name = "edgeLabels"];</code>
     */
    public java.util.List<org.janusgraph.graphdb.grpc.types.EdgeLabel.Builder> 
         getEdgeLabelsBuilderList() {
      return getEdgeLabelsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        org.janusgraph.graphdb.grpc.types.EdgeLabel, org.janusgraph.graphdb.grpc.types.EdgeLabel.Builder, org.janusgraph.graphdb.grpc.types.EdgeLabelOrBuilder> 
        getEdgeLabelsFieldBuilder() {
      if (edgeLabelsBuilder_ == null) {
        edgeLabelsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            org.janusgraph.graphdb.grpc.types.EdgeLabel, org.janusgraph.graphdb.grpc.types.EdgeLabel.Builder, org.janusgraph.graphdb.grpc.types.EdgeLabelOrBuilder>(
                edgeLabels_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        edgeLabels_ = null;
      }
      return edgeLabelsBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:janusgraph.schema.v1.GetEdgeLabelsResponse)
  }

  // @@protoc_insertion_point(class_scope:janusgraph.schema.v1.GetEdgeLabelsResponse)
  private static final org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse();
  }

  public static org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetEdgeLabelsResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetEdgeLabelsResponse>() {
    @java.lang.Override
    public GetEdgeLabelsResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetEdgeLabelsResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetEdgeLabelsResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetEdgeLabelsResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

