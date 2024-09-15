
public class CalculatorFac implements computeFactorial {

	@Override
	    public long calculateFactorial(int number) {
	        if (number < 0) {
	            throw new IllegalArgumentException("Number must be non-negative.");
	        }
	        return factorial(number);
	    }
	    
	    private long factorial(int number) {
	        long result = 1;
	        for (int i = 1; i <= number; i++) {
	            result *= i; //multiply result by i then change result to the product of that multiplication
	        }
	        return result;
	    }
	
	}


