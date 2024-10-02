package com.example.factorfinder;
import java.util.ArrayList;
import java.util.List;

public class FactorComputeEngineImpl implements FactorComputeEngine {
  String delimiter = ",";

  /**
   * Input some number from the user
   * returns array of the factors
   */
  public int[] getFactors(int number) {
    List<Integer> factors = new ArrayList<>();
    for (int i = 1; i <= number; i++) { // Start from 1 and go to number
      if (number % i == 0) {
        factors.add(i); // Add factor to list
      }
    }
    // Convert ArrayList to array
    //.mapToInt() operation converts each Integer object in the stream to its
    // corresponding int primitive value. (Integer::intValue) is shortHand for
    // the intValue() method one EACH Integer Object in the stream. Integer ->
    // Integer.intValue() is what it should look like when expanded .toArray
    // collects the elements in the IntStream and returns them as an int[]
    // array.
    return factors.stream().mapToInt(Integer::intValue).toArray();
  }

  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }

  public List<String> getFormattedFactors(int number) {
    // TODO:see if this classifies as validation, i can only return null since
    // it only returns Lists validate the number, since this is calls the
    // getFactors method if the number is validated in here, it should be fine
    // in the method
    if (number <= 1) {
      int[] factors = getFactors(number);
      List<String> formattedFactors = new ArrayList<>();
      for (int factor : factors) {
        formattedFactors.add(String.valueOf(factor));
      }
      return formattedFactors;
    } else{
      return null; // number was not positive
  }
}
