package com.example.factorfinder;

import java.util.List;

public class FactorComputationCoordinatorImpl
    implements FactorComputationCordinator {
  private final FactorComputeEngineImpl computeEngine;// Reference to the compute engine
  private final FactorDataStoreImpl dataStore;// Reference to the data store

  // Constructor to initialize the compute engine and data store
  public FactorComputationCoordinatorImpl(
      FactorComputeEngineImpl computeEngine, FactorDataStoreImpl dataStore) {
    this.computeEngine = computeEngine;
    this.dataStore = dataStore;
  }

  @Override
  public FactorComputeResult compute(FactorComputeRequest request) {
    FileInputConfig inputConfig = request.getInputConfig();// Here we receive requests from the user to start the computation
    List<Integer> inputData =  dataStore.read(inputConfig);// Request that the data storage component read integers from specified location

    //validating for the inputData
    if (inputData == null || inputData.isEmpty()) {
      return FactorComputeResult.FAILURE;//so inputData was null, no data was available
    }

    try {
      // Passes the integers to the compute component
      for (Integer number : inputData) {
        List<String> formattedFactors =
            computeEngine.getFormattedFactors(number);// Compute the passed integer's factors
        FileOutputConfig outputConfig = request.getOutputConfig();// Request that the data storage component write whatever result to the output

        // validating the outputConfig of the request
        if (outputConfig != null) {
          WriteResult writeResult =
              dataStore.appendSingleResult(outputConfig, formattedFactors);// Write formatted factors to output
          if (writeResult.getStatus()
              != WriteResult.WriteResultStatus.SUCCESS) {
            return FactorComputeResult.FAILURE;// Handles if writing fails
          }
        }
      }
    } catch (Exception e) {
      // Handle exception
      e.printStackTrace();
      return FactorComputeResult.FAILURE;
    }

    return FactorComputeResult.SUCCESS;//Both the input and output file paths were correct and existed
  }
}
