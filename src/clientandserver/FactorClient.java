package clientandserver;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import apiProto.CoordinatorEngine.coordinatorRequest;
import apiProto.CoordinatorEngine.coordinatorResponse;
import apiProto.FactorServiceGrpc;
import apiProto.FactorServiceGrpc.FactorServiceBlockingStub;
//import apiProto.FactorServiceGrpc.FactorServiceBlockingStub;
import io.grpc.Channel;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
/**
 * Question 2 & 3 on Assignment 7
 * This is Client file for the Factor
 */

public class FactorClient {

	private static String inputFile;
	private static String outputFile;
	private static String delimiter;


	private final FactorServiceBlockingStub blockingStub; 
	public FactorClient(Channel channel) {
		blockingStub = FactorServiceGrpc.newBlockingStub(channel);
	}


	public void order() {   
		//Create the request
		coordinatorRequest request = coordinatorRequest.newBuilder()
				.setInputFile(inputFile)
				.setOutputFile(outputFile)
				.setDelimiter(delimiter)
				.build();

		coordinatorResponse response;

		try {

			response = blockingStub.compute(request);//made me create a compute method in the FactorService gRpc file
		}  catch (StatusRuntimeException e) {
			e.printStackTrace();
			return;
		}

	}

	public static void main(String[] args) throws Exception {

		//step 2b
		System.out.println("Enter an input file to load:");
		Scanner userInput = new Scanner(System.in);

		inputFile = userInput.nextLine();

		System.out.println("Enter output file name:");
		userInput.next();

		outputFile = userInput.nextLine();

		System.out.println("Enter a delimiter:");
		userInput.next();

		delimiter = userInput.nextLine();

		userInput.close();

		String target = "localhost:15000";  // server/port we want to connect to 

		ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create())
				.build();
		try {
			FactorClient client = new FactorClient(channel); // Boilerplate TODO: update to this class name
			client.order();
		} finally {
			channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
		}
	}
}


//original code 
//
//
//import java.util.Scanner;
//
//import apiProto.CoordinatorEngine.coordinatorRequest;
//import apiProto.CoordinatorEngine.coordinatorResponse;
//import apiProto.FactorServiceGrpc;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
////import apiProto.coordinatorEngine;
//public class FactorClient {
//
//public static void main(String[] args) {
//
//	System.out.println("Enter an input file to load:");
//	Scanner userInput = new Scanner(System.in);
//
//	String inputFile = userInput.nextLine();
//
//	System.out.println("Enter output file name:");
//	userInput.next();
//
//	String outputFile = userInput.nextLine();
//
//	System.out.println("Enter a delimiter:");
//	userInput.next();
//
//	String delimiter = userInput.nextLine();
//
//	userInput.close();
//
//	//Set up the gRPC channel and stub
//	ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
//			.usePlaintext() // Disable TLS for local testing
//			.build();
//	FactorServiceGrpc.FactorServiceBlockingStub stub = FactorServiceGrpc.newBlockingStub(channel);
//
//	//Create the request
//	coordinatorRequest request = coordinatorRequest.newBuilder()
//			.setInputFile(inputFile)
//			.setOutputFile(outputFile)
//			.setDelimiter(delimiter)
//			.build();
//
//	
//	try {
//		
//		//Create a response and call compute method on the stud
//		coordinatorResponse response = stub.compute(request);
//		
//		//Hadnle the different responses
//		if (response.getStatus() == coordinatorResponse.FactorComputeResult.success) {
//			System.out.println("Computation succeeded!");
//		} else {
//			System.out.println("Computation failed.");
//		}
//	} catch (Exception e) {
//		System.err.println("RPC failed: " + e.getMessage());
//	} finally {
//		channel.shutdown();//Shut down the channel
//	}
//}
//}
//
//
//
