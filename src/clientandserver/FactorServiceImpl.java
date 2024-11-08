package clientandserver;

import apiProto.CoordinatorEngine.coordinatorResponse;
import apiProto.CoordinatorEngine.coordinatorResponse.FactorComputeResult;
import apiProto.FactorServiceGrpc.FactorServiceImplBase;
//import clientandserver.FactorComputeRequest;

public class FactorServiceImpl extends FactorServiceImplBase{
	FactorComputationCordinatorImpl coordinator;
	
	public void compute(apiProto.CoordinatorEngine.coordinatorRequest request,
			io.grpc.stub.StreamObserver<apiProto.CoordinatorEngine.coordinatorResponse> responseObserver) {
		
		// create a request from API 1 and save it
		FileInputConfig internallInputFile = new FileInputConfig(request.getInputFile());
		FileOutputConfig internalOutputFile = new FileOutputConfig(request.getOutputFile());
		char internalDelimier = request.getDelimiter().charAt(0);
		
		FactorComputeRequest internalRequest = new FactorComputeRequest(internallInputFile, internalOutputFile, internalDelimier);
		
		//Save result from request into the internal type
		FactorComputeResult internalResponse = coordinator.compute(internalRequest);//TODO : make type FactorComputeRequest and get Result but need to make it back into the coordinatorRepsonse
		
		//Translate it back into external type 
		coordinatorResponse	externalResponse = new coordinatorResponse(internalResponse);
		responseObserver.onNext(externalResponse);//sending the byte back to the Client on the open socket
		responseObserver.onCompleted();
	}
		
	public FactorServiceImpl(FactorComputeEngineImpl computeEngine, FactorDataStoreImpl dataStore) {

		coordinator = new FactorComputationCordinatorImpl(computeEngine,dataStore);
	}

}
