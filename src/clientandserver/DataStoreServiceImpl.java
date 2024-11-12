package com.example.clientandserver;

import apiProto.DataStore;
import apiProto.DataStoreServiceGrpc.DataStoreServiceImplBase;
import com.example.factorFinder.FactorDataStoreImpl;
import com.example.factorFinder.FileInputConfig;
import com.example.factorFinder.FileOutputConfig;
import com.example.factorFinder.WriteResult;
import java.util.List;

public class DataStoreServiceImpl extends DataStoreServiceImplBase {
  private final FactorDataStoreImpl factorDataStore;

  // constructor
  public DataStoreServiceImpl() {
    this.factorDataStore = new FactorDataStoreImpl();
  }

  public void read(apiProto.DataStore.DataStoreReadRequest request,
      io.grpc.stub.StreamObserver<apiProto.DataStore.DataStoreReadResponse>
          responseObserver) {
    DataStore.DataStoreReadResponse.Builder responseBuilder =
        DataStore.DataStoreReadResponse.newBuilder();

    FileInputConfig inputConfig =
        new FileInputConfig(request.getInputFilePath());
    List<Integer> data = factorDataStore.read(inputConfig);

    if (data != null) {
      responseBuilder.addAllData(data).setStatus("success");
    } else {
      responseBuilder.setStatus("failure").setErrorMessage(
          "File path is null or file read error");
    }

    responseObserver.onNext(responseBuilder.build());
    responseObserver.onCompleted();
  }

  public void write(apiProto.DataStore.DataStoreWriteRequest request,
      io.grpc.stub.StreamObserver<apiProto.DataStore.DataStoreWriteResponse>
          responseObserver) {
    DataStore.DataStoreWriteResponse.Builder responseBuilder =
        DataStore.DataStoreWriteResponse.newBuilder();

    FileOutputConfig outputConfig =
        new FileOutputConfig(request.getOutputFilePath());
    WriteResult result = factorDataStore.appendSingleResult(
        outputConfig, request.getResultsList());

    if (result == WriteResult.SUCCESS) {
      responseBuilder.setStatus("success");
    } else {
      responseBuilder.setStatus("failure").setErrorMessage(
          "Error writing to file");
    }

    responseObserver.onNext(responseBuilder.build());
    responseObserver.onCompleted();
  }
}
