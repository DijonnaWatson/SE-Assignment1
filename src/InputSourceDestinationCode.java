
public enum InputSourceDestinationCode {
	SUCCESS(1, true),
	FAILURE(2, false);
	
	private final int number;
	private final boolean success;
	
	private InputSourceDestinationCode(int number, boolean success) {
		this.number = number;
		this.success = success;
	}

	public int getNumber() {
		return number;
	}
	boolean success() {
		// TODO Auto-generated method stub
		return success;
	}

}
