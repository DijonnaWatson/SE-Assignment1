package clientandserver;

import apiProto.DataStoreServiceGrpc.DataStoreServiceImplBase;

public class DataStoreServiceImpl extends DataStoreServiceImplBase{
	
	public void read(apiProto.DataStore.DataStoreReadRequest request,
	      io.grpc.stub.StreamObserver<
	          apiProto.DataStore.DataStoreReadResponse> responseObserver  ) {
	}
	
	public void write(apiProto.DataStore.DataStoreWriteRequest request,
	      io.grpc.stub.StreamObserver<
	          apiProto.DataStore.DataStoreWriteResponse> responseObserver  ) {
		
	}

}
