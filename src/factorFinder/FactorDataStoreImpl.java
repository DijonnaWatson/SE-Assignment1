package com.example.factorfinder;

import java.util.ArrayList;
import java.util.List;

public class FactorDataStoreImpl implements FactorDataStore {
  @Override
  public List<Integer> read(InputConfig inputConfig) {
    // retrieve input data from the InputConfig
    List<Integer> data = inputConfig.getInputData();
    // If the data is not null return the data else return an empty arraylist
    return data != null ? data : new ArrayList<>();
  }

  @Override
  public WriteResult appendSingleResult(
      OutputConfig outputConfig, List<String> results) {
    // Logic to append results to the output configuration
    outputConfig.writeOutputData(results); // Write all results at once
    return WriteResult.SUCCESS; // Indicate success
  }
}
