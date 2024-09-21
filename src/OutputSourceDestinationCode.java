
public enum OutputSourceDestinationCode {
	SUCCESS(1, true),
	FAILURE(2, false);
	
	private final int number;
	private final boolean success;
	
	private OutputSourceDestinationCode(int number, boolean success) {
		this.number = number;
		this.success = success;
	}

	boolean success() {
		// TODO Auto-generated method stub
		return success;
	}


}
