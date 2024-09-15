import java.util.Scanner;
public class factorials {
	
	 
	    public static void main(String[] args) {
	       int number;
	       
	    	Scanner keyboard = new Scanner(System.in);

	        // Get user input for source, delimiter, and destination
	        System.out.println("Computer Factorial of a number 1-20: ");
	        number = keyboard.nextInt();
	        
	        if (number > 20) {
	        	System.out.println("Your number is too large");
	        	System.out.println("\nComputer Factorial of a number 1-20: ");
		        number = keyboard.nextInt();
	        }
	        
	        computeFactorial calculator = new CalculatorFac();
	        long result = calculator.calculateFactorial(number);
	        
	        System.out.println("The factorial of your number is: " + result);
	        
	        
	        	
	        keyboard.close();
	}
}

