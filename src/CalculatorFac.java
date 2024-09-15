package com.example.factorial;

public class CalculatorFac implements ComputeFactorial {

  @Override
    public long calculateFactorial(int n) {
    if (n == 0) {
      return 1;
    }
    long result = 1;
    for (int i = 1; i <= n; i++) {
      result *= i;
    }
    return result;
  }
}
