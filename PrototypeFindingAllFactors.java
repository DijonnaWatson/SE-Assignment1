import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PrototypeFindingAllFactors implements FactorFinder, InputSource, OutputDestination {
	private String delimiter = ", ";  // Default delimiter
	private static final String INPUT_FILE = "Input.txt";  // Default input file
	private static final String OUTPUT_FILE = "Output.txt";  // Default output file

	// Implementation of getFactors method from FactorFinder interface
	@Override
	public int[] getFactors(int number) {
		ArrayList<Integer> factorsList = new ArrayList<>();
		for (int i = number; i > 0; i--) {
			if (number % i == 0) {
				factorsList.add(i); // Add factor to list
			}
		}
		// Convert ArrayList to array
		int[] factors = new int[factorsList.size()];
		for (int i = 0; i < factorsList.size(); i++) {
			factors[i] = factorsList.get(i);
		}
		return factors;
	}

	// Implementation of setDelimiter method from FactorFinder interface
	@Override
	public void setDelimiter(String delimiter) {
		if (!delimiter.isEmpty()) {
			this.delimiter = delimiter;  // Use custom delimiter if provided
		}
	}

	// Implementation of outputFactors method from OutputDestination interface
	@Override
	public void outputFactors(int[] factors, String outputDestination) {
		if (outputDestination.equalsIgnoreCase("console")) {
			for (int i = 0; i < factors.length; i++) {
				if (i > 0) {
					System.out.print(delimiter);
				}
				System.out.print(factors[i]);
			}
			System.out.println();
		} else {
			try (FileWriter writer = new FileWriter(OUTPUT_FILE)) {
				for (int i = 0; i < factors.length; i++) {
					if (i > 0) {
						writer.write(delimiter);
					}
					writer.write(String.valueOf(factors[i]));
				}
				System.out.println("Factors written to file: " + OUTPUT_FILE);
			} catch (IOException e) {
				System.out.println("Error writing to file: " + e.getMessage());
			}
		}
	}

	// Method to read input from a file (InputSource interface)
	@Override
	public int readFromFile() {
		int number = 0;
		try (Scanner fileScanner = new Scanner(new File(INPUT_FILE))) {
			if (fileScanner.hasNextInt()) {
				number = fileScanner.nextInt();
				System.out.println("Number read from file: " + number);
			} else {
				System.out.println("No valid number found in the input file.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found: " + e.getMessage());
		}
		return number;
	}

	// Method to read input from the console (InputSource interface)
	@Override
	public int readFromConsole() {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Enter a positive whole number: ");
		int number = userInput.nextInt();
		userInput.nextLine();  // Consume the leftover newline
		return number;
	}

	// Main method to test the program
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);

		// Instantiate the class that implements FactorFinder, InputSource, and OutputDestination
		PrototypeFindingAllFactors factorFinder = new PrototypeFindingAllFactors();

		// Input source selection
		System.out.print("Would you like to input the number from the file or console? (file/console): ");
		String inputSourceChoice = userInput.nextLine();

		int number = 0;
		if (inputSourceChoice.equalsIgnoreCase("file")) {
			number = factorFinder.readFromFile();
			if (number == 0) {
				System.out.println("Exiting program due to invalid input.");
				return;
			}
		} else if (inputSourceChoice.equalsIgnoreCase("console")) {
			number = factorFinder.readFromConsole();
		} else {
			System.out.println("Invalid input source choice.");
			return;
		}

		// Delimiter input with default handling
		System.out.print("Enter a delimiter (leave blank for default ', '): ");
		String delimiter = userInput.nextLine();
		factorFinder.setDelimiter(delimiter);

		// Get the factors of the number
		int[] factors = factorFinder.getFactors(number);

		// Output destination choice
		System.out.print("Would you like to output to a file or console? (file/console): ");
		String destinationChoice = userInput.nextLine();
		if (destinationChoice.equalsIgnoreCase("file")) {
			factorFinder.outputFactors(factors, OUTPUT_FILE);
		} else {
			factorFinder.outputFactors(factors, "console");
		}

		userInput.close();
	}
}
