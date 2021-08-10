package org.janusgraph.graphdb.grpc;

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
    comments = "Source: janusgraph/v1/graph_manager.proto")
public final class JanusGraphManagerServiceGrpc {

  private JanusGraphManagerServiceGrpc() {}

  public static final String SERVICE_NAME = "janusgraph.v1.JanusGraphManagerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest,
      org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse> getGetJanusGraphContextsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetJanusGraphContexts",
      requestType = org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest.class,
      responseType = org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest,
      org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse> getGetJanusGraphContextsMethod() {
    io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest, org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse> getGetJanusGraphContextsMethod;
    if ((getGetJanusGraphContextsMethod = JanusGraphManagerServiceGrpc.getGetJanusGraphContextsMethod) == null) {
      synchronized (JanusGraphManagerServiceGrpc.class) {
        if ((getGetJanusGraphContextsMethod = JanusGraphManagerServiceGrpc.getGetJanusGraphContextsMethod) == null) {
          JanusGraphManagerServiceGrpc.getGetJanusGraphContextsMethod = getGetJanusGraphContextsMethod =
              io.grpc.MethodDescriptor.<org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest, org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetJanusGraphContexts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new JanusGraphManagerServiceMethodDescriptorSupplier("GetJanusGraphContexts"))
              .build();
        }
      }
    }
    return getGetJanusGraphContextsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest,
      org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse> getGetJanusGraphContextByGraphNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetJanusGraphContextByGraphName",
      requestType = org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest.class,
      responseType = org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest,
      org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse> getGetJanusGraphContextByGraphNameMethod() {
    io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest, org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse> getGetJanusGraphContextByGraphNameMethod;
    if ((getGetJanusGraphContextByGraphNameMethod = JanusGraphManagerServiceGrpc.getGetJanusGraphContextByGraphNameMethod) == null) {
      synchronized (JanusGraphManagerServiceGrpc.class) {
        if ((getGetJanusGraphContextByGraphNameMethod = JanusGraphManagerServiceGrpc.getGetJanusGraphContextByGraphNameMethod) == null) {
          JanusGraphManagerServiceGrpc.getGetJanusGraphContextByGraphNameMethod = getGetJanusGraphContextByGraphNameMethod =
              io.grpc.MethodDescriptor.<org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest, org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetJanusGraphContextByGraphName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new JanusGraphManagerServiceMethodDescriptorSupplier("GetJanusGraphContextByGraphName"))
              .build();
        }
      }
    }
    return getGetJanusGraphContextByGraphNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.GetVersionRequest,
      org.janusgraph.graphdb.grpc.GetVersionResponse> getGetVersionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetVersion",
      requestType = org.janusgraph.graphdb.grpc.GetVersionRequest.class,
      responseType = org.janusgraph.graphdb.grpc.GetVersionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.GetVersionRequest,
      org.janusgraph.graphdb.grpc.GetVersionResponse> getGetVersionMethod() {
    io.grpc.MethodDescriptor<org.janusgraph.graphdb.grpc.GetVersionRequest, org.janusgraph.graphdb.grpc.GetVersionResponse> getGetVersionMethod;
    if ((getGetVersionMethod = JanusGraphManagerServiceGrpc.getGetVersionMethod) == null) {
      synchronized (JanusGraphManagerServiceGrpc.class) {
        if ((getGetVersionMethod = JanusGraphManagerServiceGrpc.getGetVersionMethod) == null) {
          JanusGraphManagerServiceGrpc.getGetVersionMethod = getGetVersionMethod =
              io.grpc.MethodDescriptor.<org.janusgraph.graphdb.grpc.GetVersionRequest, org.janusgraph.graphdb.grpc.GetVersionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetVersion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.GetVersionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.janusgraph.graphdb.grpc.GetVersionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new JanusGraphManagerServiceMethodDescriptorSupplier("GetVersion"))
              .build();
        }
      }
    }
    return getGetVersionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static JanusGraphManagerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JanusGraphManagerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JanusGraphManagerServiceStub>() {
        @java.lang.Override
        public JanusGraphManagerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JanusGraphManagerServiceStub(channel, callOptions);
        }
      };
    return JanusGraphManagerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static JanusGraphManagerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JanusGraphManagerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JanusGraphManagerServiceBlockingStub>() {
        @java.lang.Override
        public JanusGraphManagerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JanusGraphManagerServiceBlockingStub(channel, callOptions);
        }
      };
    return JanusGraphManagerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static JanusGraphManagerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JanusGraphManagerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JanusGraphManagerServiceFutureStub>() {
        @java.lang.Override
        public JanusGraphManagerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JanusGraphManagerServiceFutureStub(channel, callOptions);
        }
      };
    return JanusGraphManagerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class JanusGraphManagerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getJanusGraphContexts(org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetJanusGraphContextsMethod(), responseObserver);
    }

    /**
     */
    public void getJanusGraphContextByGraphName(org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetJanusGraphContextByGraphNameMethod(), responseObserver);
    }

    /**
     */
    public void getVersion(org.janusgraph.graphdb.grpc.GetVersionRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.GetVersionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetVersionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetJanusGraphContextsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest,
                org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse>(
                  this, METHODID_GET_JANUS_GRAPH_CONTEXTS)))
          .addMethod(
            getGetJanusGraphContextByGraphNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest,
                org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse>(
                  this, METHODID_GET_JANUS_GRAPH_CONTEXT_BY_GRAPH_NAME)))
          .addMethod(
            getGetVersionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.janusgraph.graphdb.grpc.GetVersionRequest,
                org.janusgraph.graphdb.grpc.GetVersionResponse>(
                  this, METHODID_GET_VERSION)))
          .build();
    }
  }

  /**
   */
  public static final class JanusGraphManagerServiceStub extends io.grpc.stub.AbstractAsyncStub<JanusGraphManagerServiceStub> {
    private JanusGraphManagerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JanusGraphManagerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JanusGraphManagerServiceStub(channel, callOptions);
    }

    /**
     */
    public void getJanusGraphContexts(org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetJanusGraphContextsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getJanusGraphContextByGraphName(org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetJanusGraphContextByGraphNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getVersion(org.janusgraph.graphdb.grpc.GetVersionRequest request,
        io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.GetVersionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetVersionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class JanusGraphManagerServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<JanusGraphManagerServiceBlockingStub> {
    private JanusGraphManagerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JanusGraphManagerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JanusGraphManagerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse getJanusGraphContexts(org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetJanusGraphContextsMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse getJanusGraphContextByGraphName(org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetJanusGraphContextByGraphNameMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.janusgraph.graphdb.grpc.GetVersionResponse getVersion(org.janusgraph.graphdb.grpc.GetVersionRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetVersionMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class JanusGraphManagerServiceFutureStub extends io.grpc.stub.AbstractFutureStub<JanusGraphManagerServiceFutureStub> {
    private JanusGraphManagerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JanusGraphManagerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JanusGraphManagerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse> getJanusGraphContexts(
        org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetJanusGraphContextsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse> getJanusGraphContextByGraphName(
        org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetJanusGraphContextByGraphNameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.janusgraph.graphdb.grpc.GetVersionResponse> getVersion(
        org.janusgraph.graphdb.grpc.GetVersionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetVersionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_JANUS_GRAPH_CONTEXTS = 0;
  private static final int METHODID_GET_JANUS_GRAPH_CONTEXT_BY_GRAPH_NAME = 1;
  private static final int METHODID_GET_VERSION = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final JanusGraphManagerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(JanusGraphManagerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_JANUS_GRAPH_CONTEXTS:
          serviceImpl.getJanusGraphContexts((org.janusgraph.graphdb.grpc.GetJanusGraphContextsRequest) request,
              (io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.GetJanusGraphContextsResponse>) responseObserver);
          break;
        case METHODID_GET_JANUS_GRAPH_CONTEXT_BY_GRAPH_NAME:
          serviceImpl.getJanusGraphContextByGraphName((org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameRequest) request,
              (io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.GetJanusGraphContextByGraphNameResponse>) responseObserver);
          break;
        case METHODID_GET_VERSION:
          serviceImpl.getVersion((org.janusgraph.graphdb.grpc.GetVersionRequest) request,
              (io.grpc.stub.StreamObserver<org.janusgraph.graphdb.grpc.GetVersionResponse>) responseObserver);
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

  private static abstract class JanusGraphManagerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    JanusGraphManagerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.janusgraph.graphdb.grpc.GraphManagerService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("JanusGraphManagerService");
    }
  }

  private static final class JanusGraphManagerServiceFileDescriptorSupplier
      extends JanusGraphManagerServiceBaseDescriptorSupplier {
    JanusGraphManagerServiceFileDescriptorSupplier() {}
  }

  private static final class JanusGraphManagerServiceMethodDescriptorSupplier
      extends JanusGraphManagerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    JanusGraphManagerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (JanusGraphManagerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new JanusGraphManagerServiceFileDescriptorSupplier())
              .addMethod(getGetJanusGraphContextsMethod())
              .addMethod(getGetJanusGraphContextByGraphNameMethod())
              .addMethod(getGetVersionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
