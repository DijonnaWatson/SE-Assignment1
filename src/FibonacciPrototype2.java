package fibonacci.se;
import java.util.Scanner;

public class FibonacciPrototype {

	public static  void main(String[] args) {
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Enter Fibonacci Sequence Terms(between 3 - 45): "); 
		
		int numberOfTerms = userInput.nextInt(); //scanner input for number of terms in fibonacci
		
		FibonacciMethod fibMethod = new FibonacciMethod();
		
        int result = fibMethod.Fibonacci(numberOfTerms, 1, 2);
        
        System.out.println("The final term in the fibonacci sequence starting "
        		+ "with first term: 1 and second term: 2 is " + result);
	}

}
