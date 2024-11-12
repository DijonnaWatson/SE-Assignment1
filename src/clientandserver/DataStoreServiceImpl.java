package com.example.clientandserver;

import apiProto.DataStore;
import apiProto.DataStoreServiceGrpc.DataStoreServiceImplBase;
import com.example.factorfinder.FactorDataStoreImpl;
import com.example.factorfinder.FileInputConfig;
import com.example.factorfinder.FileOutputConfig;
import com.example.factorfinder.WriteResult;
import io.grpc.stub.StreamObserver;
import java.util.List;

public class DataStoreServiceImpl extends DataStoreServiceImplBase {
  private final FactorDataStoreImpl factorDataStore;

  public DataStoreServiceImpl() {
    this.factorDataStore = new FactorDataStoreImpl();
  }

  @Override
  public void read(DataStore.DataStoreReadRequest request,
      StreamObserver<DataStore.DataStoreReadResponse> responseObserver) {
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

  @Override
  public void write(DataStore.DataStoreWriteRequest request,
      StreamObserver<DataStore.DataStoreWriteResponse> responseObserver) {
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
