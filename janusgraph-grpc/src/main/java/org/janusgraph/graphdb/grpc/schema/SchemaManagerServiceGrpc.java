package org.janusgraph.graphdb.grpc.schema;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.34.0)",
    comments = "Source: janusgraph/schema/v1/schema_manager.proto")
public final class SchemaManagerServiceGrpc {

  private SchemaManagerServiceGrpc() {}

  public static final String SERVICE_NAME = "janusgraph.schema.v1.SchemaManagerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest,
      org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse> getGetVertexLabelByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetVertexLabelByName",
      requestType = org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest.class,
      responseType = org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest,
      org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse> getGetVertexLabelByNameMethod() {
    io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest, org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse> getGetVertexLabelByNameMethod;
    if ((getGetVertexLabelByNameMethod = SchemaManagerServiceGrpc.getGetVertexLabelByNameMethod) == null) {
      synchronized (SchemaManagerServiceGrpc.class) {
        if ((getGetVertexLabelByNameMethod = SchemaManagerServiceGrpc.getGetVertexLabelByNameMethod) == null) {
          SchemaManagerServiceGrpc.getGetVertexLabelByNameMethod = getGetVertexLabelByNameMethod =
              io.grpc.MethodDescriptor.<org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest, org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetVertexLabelByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SchemaManagerServiceMethodDescriptorSupplier("GetVertexLabelByName"))
              .build();
        }
      }
    }
    return getGetVertexLabelByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest,
      org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse> getGetVertexLabelsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetVertexLabels",
      requestType = org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest.class,
      responseType = org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest,
      org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse> getGetVertexLabelsMethod() {
    io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest, org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse> getGetVertexLabelsMethod;
    if ((getGetVertexLabelsMethod = SchemaManagerServiceGrpc.getGetVertexLabelsMethod) == null) {
      synchronized (SchemaManagerServiceGrpc.class) {
        if ((getGetVertexLabelsMethod = SchemaManagerServiceGrpc.getGetVertexLabelsMethod) == null) {
          SchemaManagerServiceGrpc.getGetVertexLabelsMethod = getGetVertexLabelsMethod =
              io.grpc.MethodDescriptor.<org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest, org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetVertexLabels"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SchemaManagerServiceMethodDescriptorSupplier("GetVertexLabels"))
              .build();
        }
      }
    }
    return getGetVertexLabelsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest,
      org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse> getGetEdgeLabelByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEdgeLabelByName",
      requestType = org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest.class,
      responseType = org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest,
      org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse> getGetEdgeLabelByNameMethod() {
    io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest, org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse> getGetEdgeLabelByNameMethod;
    if ((getGetEdgeLabelByNameMethod = SchemaManagerServiceGrpc.getGetEdgeLabelByNameMethod) == null) {
      synchronized (SchemaManagerServiceGrpc.class) {
        if ((getGetEdgeLabelByNameMethod = SchemaManagerServiceGrpc.getGetEdgeLabelByNameMethod) == null) {
          SchemaManagerServiceGrpc.getGetEdgeLabelByNameMethod = getGetEdgeLabelByNameMethod =
              io.grpc.MethodDescriptor.<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest, org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEdgeLabelByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SchemaManagerServiceMethodDescriptorSupplier("GetEdgeLabelByName"))
              .build();
        }
      }
    }
    return getGetEdgeLabelByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest,
      org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse> getGetEdgeLabelsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEdgeLabels",
      requestType = org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest.class,
      responseType = org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest,
      org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse> getGetEdgeLabelsMethod() {
    io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest, org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse> getGetEdgeLabelsMethod;
    if ((getGetEdgeLabelsMethod = SchemaManagerServiceGrpc.getGetEdgeLabelsMethod) == null) {
      synchronized (SchemaManagerServiceGrpc.class) {
        if ((getGetEdgeLabelsMethod = SchemaManagerServiceGrpc.getGetEdgeLabelsMethod) == null) {
          SchemaManagerServiceGrpc.getGetEdgeLabelsMethod = getGetEdgeLabelsMethod =
              io.grpc.MethodDescriptor.<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest, org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEdgeLabels"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SchemaManagerServiceMethodDescriptorSupplier("GetEdgeLabels"))
              .build();
        }
      }
    }
    return getGetEdgeLabelsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SchemaManagerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SchemaManagerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SchemaManagerServiceStub>() {
        @java.lang.Override
        public SchemaManagerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SchemaManagerServiceStub(channel, callOptions);
        }
      };
    return SchemaManagerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SchemaManagerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SchemaManagerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SchemaManagerServiceBlockingStub>() {
        @java.lang.Override
        public SchemaManagerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SchemaManagerServiceBlockingStub(channel, callOptions);
        }
      };
    return SchemaManagerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SchemaManagerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SchemaManagerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SchemaManagerServiceFutureStub>() {
        @java.lang.Override
        public SchemaManagerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SchemaManagerServiceFutureStub(channel, callOptions);
        }
      };
    return SchemaManagerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SchemaManagerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getVertexLabelByName(org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetVertexLabelByNameMethod(), responseObserver);
    }

    /**
     */
    public void getVertexLabels(org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetVertexLabelsMethod(), responseObserver);
    }

    /**
     */
    public void getEdgeLabelByName(org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetEdgeLabelByNameMethod(), responseObserver);
    }

    /**
     */
    public void getEdgeLabels(org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetEdgeLabelsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetVertexLabelByNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest,
                org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse>(
                  this, METHODID_GET_VERTEX_LABEL_BY_NAME)))
          .addMethod(
            getGetVertexLabelsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest,
                org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse>(
                  this, METHODID_GET_VERTEX_LABELS)))
          .addMethod(
            getGetEdgeLabelByNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest,
                org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse>(
                  this, METHODID_GET_EDGE_LABEL_BY_NAME)))
          .addMethod(
            getGetEdgeLabelsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest,
                org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse>(
                  this, METHODID_GET_EDGE_LABELS)))
          .build();
    }
  }

  /**
   */
  public static final class SchemaManagerServiceStub extends io.grpc.stub.AbstractAsyncStub<SchemaManagerServiceStub> {
    private SchemaManagerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SchemaManagerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SchemaManagerServiceStub(channel, callOptions);
    }

    /**
     */
    public void getVertexLabelByName(org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetVertexLabelByNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getVertexLabels(org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetVertexLabelsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEdgeLabelByName(org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetEdgeLabelByNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEdgeLabels(org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetEdgeLabelsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SchemaManagerServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SchemaManagerServiceBlockingStub> {
    private SchemaManagerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SchemaManagerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SchemaManagerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse getVertexLabelByName(org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetVertexLabelByNameMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse getVertexLabels(org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetVertexLabelsMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse getEdgeLabelByName(org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetEdgeLabelByNameMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse getEdgeLabels(org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetEdgeLabelsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SchemaManagerServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SchemaManagerServiceFutureStub> {
    private SchemaManagerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SchemaManagerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SchemaManagerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse> getVertexLabelByName(
        org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetVertexLabelByNameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse> getVertexLabels(
        org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetVertexLabelsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse> getEdgeLabelByName(
        org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetEdgeLabelByNameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse> getEdgeLabels(
        org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetEdgeLabelsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_VERTEX_LABEL_BY_NAME = 0;
  private static final int METHODID_GET_VERTEX_LABELS = 1;
  private static final int METHODID_GET_EDGE_LABEL_BY_NAME = 2;
  private static final int METHODID_GET_EDGE_LABELS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SchemaManagerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SchemaManagerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_VERTEX_LABEL_BY_NAME:
          serviceImpl.getVertexLabelByName((org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameRequest) request,
              (io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetVertexLabelByNameResponse>) responseObserver);
          break;
        case METHODID_GET_VERTEX_LABELS:
          serviceImpl.getVertexLabels((org.janusgraph.graphdb.grpc.schema.GetVertexLabelsRequest) request,
              (io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetVertexLabelsResponse>) responseObserver);
          break;
        case METHODID_GET_EDGE_LABEL_BY_NAME:
          serviceImpl.getEdgeLabelByName((org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameRequest) request,
              (io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelByNameResponse>) responseObserver);
          break;
        case METHODID_GET_EDGE_LABELS:
          serviceImpl.getEdgeLabels((org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsRequest) request,
              (io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.schema.GetEdgeLabelsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SchemaManagerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SchemaManagerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.janusgraph.graphdb.grpc.schema.SchemaManager.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SchemaManagerService");
    }
  }

  private static final class SchemaManagerServiceFileDescriptorSupplier
      extends SchemaManagerServiceBaseDescriptorSupplier {
    SchemaManagerServiceFileDescriptorSupplier() {}
  }

  private static final class SchemaManagerServiceMethodDescriptorSupplier
      extends SchemaManagerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SchemaManagerServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SchemaManagerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SchemaManagerServiceFileDescriptorSupplier())
              .addMethod(getGetVertexLabelByNameMethod())
              .addMethod(getGetVertexLabelsMethod())
              .addMethod(getGetEdgeLabelByNameMethod())
              .addMethod(getGetEdgeLabelsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
