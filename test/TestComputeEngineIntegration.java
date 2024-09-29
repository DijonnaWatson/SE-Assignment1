package com.example.factorfinder;

import com.example.factorfinder.FactorComputeEngineImpl;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestComputeEngineIntegration {
  private FactorComputeEngine factorComputeEngine;
  private TestFactorDataStore dataStore;

  @BeforeEach
  public void setUp() {
    // Initialize the empty implementations and the test-only data store
    factorComputeEngine =
        (FactorComputeEngine) new TestFactorComputeEngine(); // Replace with
                                                             // your empty
                                                             // implementation
    dataStore =
        new TestFactorDataStore(); // Replace with your test-only data store
  }

  @Test
  public void testIntegration() {
    // Given input
    int[] input = {1, 10, 25};

    // Process each input and collect results
    for (int number : input) {
      // Call the factor compute engine
      int[] factors = factorComputeEngine.getFactors(number);

      // Store the results in the test data store
      dataStore.storeFactors(number, factors);
    }

    // Validate results for number 10
    int[] expectedFactorsFor10 = {1, 2, 5, 10}; // Expected factors for 10
    int[] actualFactorsFor10 = dataStore.retrieveFactors(10);
    assertArrayEquals(expectedFactorsFor10, actualFactorsFor10,
        "Factors for 10 do not match");

    // Validate results for number 25
    int[] expectedFactorsFor25 = {1, 5, 25}; // Expected factors for 25
    int[] actualFactorsFor25 = dataStore.retrieveFactors(25);
    assertArrayEquals(expectedFactorsFor25, actualFactorsFor25,
        "Factors for 25 do not match");

    // Validation of output consistency (even though it will fail at this stage)
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
    FactorComputeEngine engine = new FactorComputeEngineImpl();
    int negativeInput = -15;

    // We expect an empty factor list or an IllegalArgumentException.
    // Adjust expected behavior based on the design decision.
    List<Integer> factors = engine.computeFactors(negativeInput);

    // Verify that the result is as expected (e.g., an empty list or an
    // exception).
    assertTrue(
        factors.isEmpty(), "Factors of a negative number should be empty");
  }
}


