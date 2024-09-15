
public interface InputSource {
	int readFromFile();   // Method to read from file
    int readFromConsole();  // Method to read from console
	void setDelimiter(String delimiter);  // Set the delimiter for output
}
