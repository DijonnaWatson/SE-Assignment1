package com.example.factorfinder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.factorfinder.FactorComputeEngineImpl;
import com.example.factorfinder.FactorDataStoreImpl;
import com.example.factorfinder.FileInputConfig;
import com.example.factorfinder.FileOutputConfig;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestComputeEngineIntegration {
  private FactorComputeEngineImpl factorComputeEngine;
  private FactorDataStoreImpl dataStore;
  private FileOutputConfig outputConfig;

  @BeforeEach
  public void setUp() {
    // Initialize the actual implementations
    factorComputeEngine = new FactorComputeEngineImpl();
    dataStore = new FactorDataStoreImpl();

    outputConfig = new FileOutputConfig("output.txt");
  }

  @Test
  public void testIntegration() {
    // Given input
    int[] input = {1, 10, 25};

    // Process each input and collect results
    for (int number : input) {
      // Call the factor compute engine
      int[] factors = factorComputeEngine.getFactors(number);

      // converting factors into string list
      List<String> factorsList = IntStream.of(factors)
                                     .mapToObj(String::valueOf)
                                     .collect(Collectors.toList());

      // Directly store factors in the data store for validation
      dataStore.appendSingleResult(outputConfig, factorsList);
    }

    // Validate results for number 10
    int[] expectedFactorsFor10 = {1, 2, 5, 10}; // Expected factors for 10
    int[] actualFactorsFor10 = factorComputeEngine.getFactors(
        10); // Ensure your data store has this method
    assertArrayEquals(expectedFactorsFor10, actualFactorsFor10,
        "Factors for 10 do not match");

    // Validate results for number 25
    int[] expectedFactorsFor25 = {1, 5, 25}; // Expected factors for 25
    int[] actualFactorsFor25 = factorComputeEngine.getFactors(
        25); // Ensure your data store has this method
    assertArrayEquals(expectedFactorsFor25, actualFactorsFor25,
        "Factors for 25 do not match");

    // Validation of output consistency
    String expectedOutputFor10 =
        "Result: " + java.util.Arrays.toString(expectedFactorsFor10);
    String expectedOutputFor25 =
        "Result: " + java.util.Arrays.toString(expectedFactorsFor25);

    // Simulating the output of the compute engine
    String actualOutputFor10 = getOutputString(actualFactorsFor10);
    String actualOutputFor25 = getOutputString(actualFactorsFor25);

    assertEquals(expectedOutputFor10, actualOutputFor10,
        "Output for 10 does not match expected output");
    assertEquals(expectedOutputFor25, actualOutputFor25,
        "Output for 25 does not match expected output");
  }

  private String getOutputString(int[] factors) {
    return "Result: " + Arrays.toString(factors);
  }

  @Test
  public void testNegativeNumberFactors() {
    FactorComputeEngineImpl engine = new FactorComputeEngineImpl();
    int negativeInput = -15;

    // We expect an empty factor list or an exception.
    int[] factors = engine.getFactors(negativeInput);
    List<String> formattedFactors =
        engine.getFormattedFactors(negativeInput); // see how to validate

    // Verify that we get an empty array
    assertTrue(
        factors.length == 0, "Factors of a negative number should be empty");

    // Verify that we get an empty list
    assertTrue(formattedFactors.isEmpty(),
        "Factors of a negative number should be empty");
  }

  @Test
  public void testPrimeNumberFactors() {
    int primeNumber = 13;
    int[] expectedFactors = {1, 13};
    int[] actualFactors = factorComputeEngine.getFactors(primeNumber);

    // Validate that the factors of 13 are {1, 13}
    assertArrayEquals(expectedFactors, actualFactors,
        "Factors for prime number do not match");
  }
  @Test
  public void testZeroInputFactors() {
    int[] factors = factorComputeEngine.getFactors(0);
    List<String> formattedFactors = factorComputeEngine.getFormattedFactors(0);

    // Verify that we get an empty array for 0
    assertTrue(factors.length == 0, "Factors for 0 should be empty");

    // Verify that we get an empty list for 0
    assertTrue(formattedFactors.isEmpty(), "Factors for 0 should be empty");
  }
  @Test
  public void testIOExceptionForNonExistentFile() {
    // Simulate an invalid file path that does not exist
    FileInputConfig nonExistentInputConfig =
        new FileInputConfig("non_existent_file.txt");

    // We expect an IOException, but since the current code only prints an
    // error, we will check for the message output in the console
    List<Integer> result = dataStore.read(nonExistentInputConfig);

    // Validate that the result is null (since in the implementation, if the
    // file does not exist or path is null, it prints an error and returns null)
    assertNull(
        result, "Expected result to be null when the file does not exist");
  }
}
