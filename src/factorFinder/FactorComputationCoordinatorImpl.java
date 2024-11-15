package factorFinder;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import apiProto.DataStore.DataStoreReadRequest;
import apiProto.DataStore.DataStoreReadResponse;
import apiProto.DataStore.DataStoreWriteRequest;
import apiProto.DataStore.DataStoreWriteResponse;
import apiProto.DataStoreServiceGrpc.DataStoreServiceBlockingStub;

public class FactorComputationCoordinatorImpl implements FactorComputationCoordinator {
	private final FactorComputeEngineImpl computeEngine; // Reference to the compute engine
	private   FactorDataStoreImpl dataStore; // Reference to the data store
	private final ExecutorService executor;
	private  DataStoreServiceBlockingStub dataStoreStub;
	
	// Constructor to initialize the compute engine and data store
	public FactorComputationCoordinatorImpl(
			FactorComputeEngineImpl computeEngine, FactorDataStoreImpl dataStore) {
		this.computeEngine = computeEngine;
		this.dataStore = dataStore;
		this.executor = Executors.newFixedThreadPool(2);
	}
	
	//Constructor of the DataStore gRpc
	public FactorComputationCoordinatorImpl(
	FactorComputeEngineImpl computeEngine, DataStoreServiceBlockingStub dataStoreStub) {
	this.computeEngine = computeEngine;
	this.dataStoreStub = dataStoreStub;
	this.executor = Executors.newFixedThreadPool(2); // Adjust thread pool size as needed
	}
	
	@Override
	public FactorComputeResult compute(FactorComputeRequest request) {
	    try {
	        // Translate FactorComputeRequest to external DataStoreReadRequest
	        DataStoreReadRequest readRequest = DataStoreReadRequest.newBuilder()
	                .setInputFilePath(request.getInputConfig().getFilePath())
	                .build();
	        List<Integer> inputData = null;
	        // If dataStoreStub is initialized, use it to read data
	        if (dataStoreStub != null) {
	            // Using gRPC stub to read data
	            DataStoreReadResponse readResponse = dataStoreStub.read(readRequest);
	            if (!"success".equals(readResponse.getStatus())) {
	                System.err.println("Error reading input file: " + readResponse.getErrorMessage());
	                return FactorComputeResult.FAILURE;
	            }
	            inputData = readResponse.getDataList();//set inputData equal to the external inputData
	        } else if (dataStore != null) {
	            // Using the local FactorDataStoreImpl to read data
	            FileInputConfig inputConfig = new FileInputConfig(request.getInputConfig().getFilePath());
	            inputData = dataStore.read(inputConfig);//set inputData equal to the internal inputData
	        } else {
	            System.err.println("No valid data store available.");
	            return FactorComputeResult.FAILURE;
	        }
	        //if the inputData is null regardless
	        if (inputData == null || inputData.isEmpty()) {
	            System.err.println("No data found in the input file.");
	            return FactorComputeResult.FAILURE;
	        }
	        // Actually set the delimiter
	        computeEngine.setDelimiter(request.getDelimiter() + "");
	       
	        // Compute factors using the compute engine
	        List<Future<List<String>>> futures = new ArrayList<>();
	       
	     // Passes the integers to the compute component
	        for (Integer number : inputData) {
	            Callable<List<String>> task = () -> computeEngine.getFormattedFactors(number);
	            futures.add(executor.submit(task));// Submit each task to the executor
	        }
	        List<String> formattedFactors = new ArrayList<>();
	        for (Future<List<String>> future : futures) {
	        	formattedFactors.addAll(future.get());
	        }
	        
	        //Translate results to DataStoreWriteRequest
	        DataStoreWriteRequest writeRequest = DataStoreWriteRequest.newBuilder()
	                .setOutputFilePath(request.getOutputConfig().getFilePath())
	                .addAllResults(formattedFactors)
	                .build();
	        // Write output data to the external dataStore Write Response, using the external DataStoreServiceImpl write method
	        if (dataStoreStub != null) {
	            // Using gRPC stub to write data
	            DataStoreWriteResponse writeResponse = dataStoreStub.write(writeRequest);
	            if (!"success".equals(writeResponse.getStatus())) {
	                System.err.println("Error writing output file: " + writeResponse.getErrorMessage());
	                return FactorComputeResult.FAILURE;
	            }
	        } else if (dataStore != null) {
	        	 FileOutputConfig outputConfig = request.getOutputConfig();
	            // Using the local FactorDataStoreImpl to write data
	            WriteResult writeResult = dataStore.appendSingleResult(outputConfig, formattedFactors);
	            if (writeResult.getStatus() != WriteResult.WriteResultStatus.SUCCESS) {
	                System.err.println("Error writing output file.");
	                return FactorComputeResult.FAILURE;
	            }
	        }
	        else {
	            System.err.println("No valid data store available.");
	            return FactorComputeResult.FAILURE;
	        }
	        return FactorComputeResult.SUCCESS;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return FactorComputeResult.FAILURE;
	    }
	}
}
