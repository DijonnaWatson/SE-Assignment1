import org.junit.jupiter.api.Test;


public class PrototypeFindingAllFactors{
	public void prototype(InputSource input, OutputSource output ) {
		//user chooses source destination of input number
		InputSourceDestination inputSourceDestination = input.getSource(new InputSourceRequest());
		//load the number from the input source
		if(inputSourceDestination.resultStatus().success()) {
			//Do computation to get factors of given input
			UserInputNumber inputNumber = inputSourceDestination.getInputNumber();
			FactorFinder factorfinderResponse = input.getFactors(inputNumber);

			//user chooses whether to use default delimeter or enter their own
			ChooseDelimeterResponse delimeterResponse = input.setDelimeter(new ChooseDelimeterRequest());
			//user chooses source destination to output the factors 
			OutputSourceDestination outputSourceDestination = output.getOutputSource(new OutputSourceRequest());
			if(outputSourceDestination.resultStatus().success()) {
				//Display the factors of the given number
			}

		}


	}
}