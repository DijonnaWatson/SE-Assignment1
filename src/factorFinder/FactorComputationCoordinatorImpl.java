package com.example.factorfinder;
import java.util.List;
public class FactorComputationCoordinatorImpl
    implements FactorComputationCordinator {
  private final FactorComputeEngineImpl
      computeEngine; // Reference to the compute engine
  private final FactorDataStoreImpl dataStore; // Reference to the data store

  // Constructor to initialize the compute engine and data store
  public FactorComputationCoordinatorImpl(
      FactorComputeEngineImpl computeEngine, FactorDataStoreImpl dataStore) {
    this.computeEngine = computeEngine;
    this.dataStore = dataStore;
  }

  @Override
  public FactorComputeResult compute(FactorComputeRequest request) {
    // validating the inputConfig of request
    if (request.getInputConfig() != null) {
      // Correlates to step 2a. Here we receive requests from the user to start
      // the computation
      FileInputConfig inputConfig = request.getInputConfig();

      // Correlates to step 2b. Request that the data storage component read
      // integers from specified location
      List<Integer> inputData = dataStore.read(inputConfig);

      // another check to make sure the filepath isn't empty
      if (inputData.isEmpty()) {
        return FactorComputeResult
            .FAILURE; // If statement handles case where no data is available
      }

      // Correlates to step 2c. Passes the integers to the compute component
      for (Integer number : inputData) {
        List<String> formattedFactors = computeEngine.getFormattedFactors(
            number); // Compute the passed integer's factors

        // Correlates to step 2d. Request that the data storage component write
        // whatever result to the output
        FileOutputConfig outputConfig = request.getOutputConfig();

        // validating the outputConfig of the request
        if (outputConfig != null) {
          WriteResult writeResult = dataStore.appendSingleResult(outputConfig,
              formattedFactors); // Write formatted factors to output

          if (writeResult.getStatus()
              != WriteResult.WriteResultStatus.SUCCESS) {
            return FactorComputeResult.FAILURE; // Handles if writing fails
          }
        }
      }

      return FactorComputeResult.SUCCESS; // Both the input and output file
                                          // paths were correct and existed
    } else{
      return FactorComputeResult.FAILURE; // so inputCOnfig was null
    }
  }
}
