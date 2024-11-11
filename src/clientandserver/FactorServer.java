package clientandserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import apiProto.DataStoreServiceGrpc;
import apiProto.DataStoreServiceGrpc.DataStoreServiceBlockingStub;
import factorfinder.FactorComputeEngineImpl;
import factorfinder.FactorDataStoreImpl;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.protobuf.services.ProtoReflectionService;

public class FactorServer {
  private Server server;
  private FactorComputeEngineImpl computeEngine = new FactorComputeEngineImpl();
  private FactorDataStoreImpl dataStore = new FactorDataStoreImpl();

  // Adding stub to communicate with the DataStore Service
  private DataStoreServiceBlockingStub dataStoreStub;

  private void start() throws IOException {
    /* The port on which the server should run */
    int port = 15000; // Boilerplate TODO: Consider changing the port (only one
                      // server per port)

    // Create a channel to communicate with the DataStore service
    //TODO: I changed the 50051 to port
    ManagedChannel dataStoreChannel =
        ManagedChannelBuilder.forAddress("localhost", port)
            .usePlaintext() // Disable encryption for local communication
            .build();

    // Create a blocking stub for the DataStore service
    dataStoreStub = DataStoreServiceGrpc.newBlockingStub(dataStoreChannel);

    server =
        Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
            .addService(new FactorServiceImpl(computeEngine, dataStore,
                dataStoreStub)) // Added the parameters because of the
                                // constructor I made in the FactorServiceImpl
            .addService(ProtoReflectionService.newInstance())
            .build()
            .start();
    System.out.println("Server started on port " + port);

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.err.println(
            "*** shutting down gRPC server since JVM is shutting down");
        try {
          if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
          }
        } catch (InterruptedException e) {
          e.printStackTrace(System.err);
        }
        System.err.println("*** server shut down");
      }
    });
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon
   * threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  public static void main(String[] args) throws Exception {
    FactorServer server =
        new FactorServer(); // Boilerplate TODO: Change name of class
    server.start();
    server.blockUntilShutdown();
  }
}
