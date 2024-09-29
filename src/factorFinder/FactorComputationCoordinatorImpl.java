package com.example.factorfinder;
import java.util.ArrayList;
import java.util.List;

public class FactorComputationCordinatorImpl
    implements FactorComputationCoordinator {
  private final FactorComputeEngine
      computeEngine; // Reference to the compute engine
  private final FactorDataStore dataStore; // Reference to the data store

  // Constructor to initialize the compute engine and data store
  public FactorComputationCordinatorImpl(
      FactorComputeEngine computeEngine, FactorDataStore dataStore) {
    this.computeEngine = computeEngine;
    this.dataStore = dataStore;
  }

  @Override
  public FactorComputeResult compute(FactorComputeRequest request) {
    // Correlates to step 2a. Here we receive requests from the user to start
    // the computation
    FileInputConfig inputConfig = request.getInputConfig();

    // Correlates to step 2b. Request that the data storage component read
    // integers from specified location
    List<Integer> inputData = (List<Integer>) dataStore.read(inputConfig);

    if (inputData == null || inputData.isEmpty()) {
      return FactorComputeResult
          .FAILURE; // If statement handles case where no data is available
    }

    // Correlates to step 2c. Passes the integers to the compute component
    for (Integer number : inputData) {
      int[] factors = computeEngine.getFactors(
          number); // Compute the passed integer's factors

      // Correlates to step 2d. Request that the data storage component write
      // whatever result to the output
      FileOutputConfig outputConfig = request.getOutputConfig();
      if (outputConfig != null) {
        WriteResult writeResult = dataStore.appendSingleResult(outputConfig,
            formatFactors(factors)); // Write formatted factors to output

        if (writeResult.getStatus() != WriteResult.WriteResultStatus.SUCCESS) {
          return FactorComputeResult.FAILURE; // Handles if writing fails
        }
      }
    }

    return FactorComputeResult.SUCCESS;
  }

  // Need this method to help with formatting (things were breaking in eclipse
  // without it so i figured we just keep it)
  private List<String> formatFactors(int[] factors) {
    List<String> formattedFactors = new ArrayList<>();
    for (int factor : factors) {
      formattedFactors.add(String.valueOf(factor));
    }
    return formattedFactors;
  }
}
