package com.example.clientandserver;

import java.util.List;
import java.util.stream.Collectors;
import apiProto.CoordinatorEngine.coordinatorResponse;
import apiProto.DataStore.DataStoreReadRequest;
import apiProto.DataStore.DataStoreReadResponse;
import apiProto.DataStore.DataStoreWriteRequest;
// import apiProto.DataStoreServiceGrpc;
import apiProto.DataStoreServiceGrpc.DataStoreServiceBlockingStub;
import apiProto.FactorServiceGrpc.FactorServiceImplBase;
import com.example.factorFinder.FactorComputationCoordinatorImpl;
import com.example.factorFinder.FactorComputeEngineImpl;
import com.example.factorFinder.FactorComputeRequest;
import com.example.factorFinder.FactorDataStoreImpl;
import com.example.factorFinder.FileInputConfig;
import com.example.factorFinder.FileOutputConfig;

public class FactorServiceImpl extends FactorServiceImplBase {
 private FactorComputationCoordinatorImpl coordinator;
 public DataStoreServiceBlockingStub dataStoreStub;
 // Constructor
 public FactorServiceImpl(FactorComputeEngineImpl computeEngine,
   
     DataStoreServiceBlockingStub dataStoreStub) {
   this.dataStoreStub = dataStoreStub;
   this.coordinator =
       new FactorComputationCoordinatorImpl(computeEngine,  dataStoreStub);
 }
 public void compute(apiProto.CoordinatorEngine.coordinatorRequest request,
     io.grpc.stub.StreamObserver<
         apiProto.CoordinatorEngine.coordinatorResponse> responseObserver) {
   // create a request from API 1 and save it
   FileInputConfig internalInputFile =
       new FileInputConfig(request.getInputFile());
   FileOutputConfig internalOutputFile =
       new FileOutputConfig(request.getOutputFile());
   char internalDelimier = request.getDelimiter().charAt(0);
   FactorComputeRequest internalRequest = new FactorComputeRequest(
       internalInputFile, internalOutputFile, internalDelimier);
  
   // Save result from request into the internal type
   factorFinder.FactorComputeResult internalResponse = coordinator.compute(
       internalRequest); // TODO : make type FactorComputeRequest and get
                         // Result but need to make it back into the
                         // coordinatorRepsonse
   // Handle interaction using DataStore
//    if (internalResponse == factorFinder.FactorComputeResult.SUCCESS) {
//TODO:would add wherever the stuff is being called    
//    	DataStoreReadRequest readRequest =
//          DataStoreReadRequest.newBuilder()
//              .setInputFilePath(request.getInputFile())
//              .build();
//      DataStoreReadResponse readResponse = dataStoreStub.read(
//          readRequest); // Call the read method on the DataStore stub
     // convert the data from integers to string
//      List<Integer> dataList = readResponse.getDataList();
//      List<String> stringResults =
//          dataList.stream()
//              .map(String::valueOf) // convert all the Integers to String
//              .collect(Collectors.toList());
//
//      DataStoreWriteRequest writeRequest =
//          DataStoreWriteRequest.newBuilder()
//              .setOutputFilePath(request.getOutputFile())
//              .addAllResults(stringResults)
//              .build();
//
//      dataStoreStub.write(writeRequest); // write the data to the stub
     // Map internalResponse to the gRPC response format
     apiProto.CoordinatorEngine.coordinatorResponse.FactorComputeResult grpcStatus;
     if (internalResponse == factorFinder.FactorComputeResult.SUCCESS) {
       grpcStatus =  apiProto.CoordinatorEngine.coordinatorResponse.FactorComputeResult.success;
     } else {
       grpcStatus =  apiProto.CoordinatorEngine.coordinatorResponse.FactorComputeResult.failure;
     }
     // Translate response back into external type
     coordinatorResponse externalResponse =
         coordinatorResponse.newBuilder().setStatus(grpcStatus).build();
     responseObserver.onNext(externalResponse); // sending the byte back to the
                                                // Client on the open socket
     responseObserver.onCompleted();
  
 }
}
