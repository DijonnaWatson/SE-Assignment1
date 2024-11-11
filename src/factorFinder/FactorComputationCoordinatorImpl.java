package com.example.factorfinder;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FactorComputationCoordinatorImpl implements FactorComputationCordinator {
  private final FactorComputeEngineImpl computeEngine; // Reference to the compute engine
  private final FactorDataStoreImpl dataStore; // Reference to the data store
  private final ExecutorService executor;

  // Constructor to initialize the compute engine and data store
  public FactorComputationCoordinatorImpl(
      FactorComputeEngineImpl computeEngine, FactorDataStoreImpl dataStore) {
    this.computeEngine = computeEngine;
    this.dataStore = dataStore;
    this.executor = Executors.newFixedThreadPool(2);
  }

  @Override
  public FactorComputeResult compute(FactorComputeRequest request) {
    FileInputConfig inputConfig =
        request.getInputConfig(); // Here we receive requests from the user to start the computation
    List<Integer> inputData =
        dataStore.read(inputConfig); // Request that the data storage component read integers from
                                     // specified location
    FileOutputConfig outputConfig = request.getOutputConfig();

    // validating for the inputData
    if (inputData == null || inputData.isEmpty()) {
      return FactorComputeResult.FAILURE; // so inputData was null, no data was available
    }

    try {
      List<Future<List<String>>> futures = new ArrayList<>();
      
      // Passes the integers to the compute component
      for (Integer number : inputData) {
        Callable<List<String>> task = () -> computeEngine.getFormattedFactors(number);
        futures.add(executor.submit(task)); // Submit each task to the executor
      }

      // Process each future result
      for (Future<List<String>> future : futures) {
        List<String> formattedFactors = future.get(); // Retrieve computed factors

        // Write result to output if output configuration is provided
        if (outputConfig != null) {
          WriteResult writeResult = dataStore.appendSingleResult(outputConfig, formattedFactors);
          if (writeResult.getStatus() != WriteResult.WriteResultStatus.SUCCESS) {
            return FactorComputeResult.FAILURE; // Writing failed
          }
        }
      }
    } catch (Exception e) {
      // Handle exception
      e.printStackTrace();
      return FactorComputeResult.FAILURE;
    }

    return FactorComputeResult
        .SUCCESS; // Both the input and output file paths were correct and existed
  }
}
