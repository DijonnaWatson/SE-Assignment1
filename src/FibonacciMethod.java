package com.example.fibonaccise;

public class FibonacciMethod implements FibonacciInterface {
  /**
   * Computes fibonacci sequence up to specified term.
   *
   * @param numberOfTerms, number of terms in the fibonacci sequence.
   * @param firstTerm, first term inputed by user.
   * @param secondTerm, second term inputed by user.
   * @return, returns the final term in the sequence
   */
  public int fibonacci(int numberOfTerms, int firstTerm, int secondTerm) {
    if (numberOfTerms < 3) {
      throw new IllegalArgumentException(
          "The number of terms cannot be less than three.");
    }
    else if (numberOfTerms > 45) {
      throw new IllegalArgumentException(
          "The number of terms cannot be greater than forty-five.");
    }
    int storedTerm = 0;
    for (int i = 2; i < numberOfTerms; i++) {
      storedTerm = firstTerm + secondTerm;
      firstTerm = secondTerm;
      secondTerm = storedTerm;
    }
    return storedTerm;
  }
}
