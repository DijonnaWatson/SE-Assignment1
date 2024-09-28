package com.example.factorfinder;
import java.util.ArrayList;
import java.util.List;

public class FactorComputationCoordinatorImpl
    implements FactorComputationCordinator {
  private final FactorComputeEngine
      computeEngine; // Reference to the compute engine
  private final FactorDataStore dataStore; // Reference to the data store

  // Constructor to initialize the compute engine and data store
  public FactorComputationCoordinatorImpl(
      FactorComputeEngine computeEngine, FactorDataStore dataStore) {
    this.computeEngine = computeEngine;
    this.dataStore = dataStore;
  }

  @Override
  public FactorComputeResult compute(FactorComputeRequest request) {
    // Step 1: Receive request from the user to start computation
    InputConfig inputConfig =
        request.getInputConfig(); // Get the input configuration

    // Step 2: Request that the data storage component read integers
    List<Integer> inputData = (List<Integer>) dataStore.read(
        inputConfig); // Read input data using data store

    if (inputData == null || inputData.isEmpty()) {
      return FactorComputeResult
          .FAILURE; // Handle case where no data is available
    }

    // Step 3: Pass the integers to the compute component
    for (Integer number : inputData) {
      int[] factors =
          computeEngine.getFactors(number); // Compute factors for each integer

      // Step 4: Request that the data storage component write the results to
      // the output
      OutputConfig outputConfig =
          request.getOutputConfig(); // Get output configuration
      if (outputConfig != null) {
        // Format the factors and write them to the output using data store
        WriteResult writeResult = dataStore.appendSingleResult(outputConfig,
            formatFactors(factors)); // Write formatted factors to output

        // Check if writing was successful
        if (writeResult.getStatus() != WriteResult.WriteResultStatus.SUCCESS) {
          return FactorComputeResult.FAILURE; // If writing fails
        }
      }
    }

    return FactorComputeResult
        .SUCCESS; // Indicate that the computation was successful
  }

  // Method to assist with formatting
  private List<String> formatFactors(int[] factors) {
    List<String> formattedFactors = new ArrayList<>();
    for (int factor : factors) {
      formattedFactors.add(
          String.valueOf(factor)); // Convert each factor to String
    }
    return formattedFactors; // Return the formatted list of factors
  }
}
