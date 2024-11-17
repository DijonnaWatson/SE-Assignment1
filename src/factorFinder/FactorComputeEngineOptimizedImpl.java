package com.example.factorfinder;

import java.util.ArrayList;
import java.util.List;

public class FactorComputeEngineOptimizedImpl implements FactorComputeEngine {
  private String delimiter = ",";

  
  public int[] getFactors(int number) {
    if (number < 1) {  //checks for valid input
      System.out.println("The number was < 1 in the getFactors() method");
      return new int[] {}; //return empty array for non valid input
    }

    List<Integer> factors = new ArrayList<>();
    int sqrt = (int) Math.sqrt(number);
    for (int i = 1; i <= sqrt; i++) {
      if (number % i == 0) {
        factors.add(i);
        if (i != number / i) { // Avoid duplicate factors for perfect squares
          factors.add(number / i);
        }
      }
    }
    return factors.stream().mapToInt(Integer::intValue).sorted().toArray();
  }

  
  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }

  
  public List<String> getFormattedFactors(int number) {
    // TODO:see if this classifies as validation, i can only return null since
    // it only returns Lists validated the number
    if (number < 1) {
      System.out.println(
          "The number was < 1 in the getFormattedFactors() method");
      return new ArrayList<>(); //return empty list if not number not valid
    }

    int[] factors = getFactors(number);
    List<String> formattedFactors = new ArrayList<>();
    for (int factor : factors) {
      formattedFactors.add(String.valueOf(factor) + delimiter);
    }
    return formattedFactors;
  }
}
