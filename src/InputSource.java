public interface InputSource {
	InputSourceDestination inputSource(InputSourceRequest InputSourceRequest);
//	int readFromFile();   // Method to read from file
  //  int readFromConsole();  // Method to read from console
	void setDelimiter(String delimiter);  // Set the delimiter for output
	
	InputSourceDestination getSource(InputSourceRequest inputSourceRequest);
	
	FactorFinder getFactors(UserInputNumber inputNumber);
	
	ChooseDelimeterResponse setDelimeter(ChooseDelimeterRequest chooseDelimeterRequest);
}
