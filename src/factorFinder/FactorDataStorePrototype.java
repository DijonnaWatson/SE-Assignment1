package com.example.factorfinder;

import java.util.ArrayList;
import java.util.List;

public class FactorDataStorePrototype {
  public void prototype(FactorDataStore apiToCall) {
    InputConfig inputConfig = new InputConfig() {
      @Override
      public List<Integer> getInputData() {
        return new ArrayList<>(); // Return actual input data as needed
      }

      @Override
      public String getFilePath() {
        return ""; // Return default file path or an empty string
      }
    };

    OutputConfig outputConfig = null; // Can replace if necessary

    Iterable<Integer> loadedData = apiToCall.read(inputConfig);

    for (int i : loadedData) {
      String result = "" + i; // Convert int to string

      List<String> resultsToAppend = new ArrayList<>();
      resultsToAppend.add(result); // Add the single result to the list

      WriteResult writeResult =
          apiToCall.appendSingleResult(outputConfig, resultsToAppend);

      if (writeResult.getStatus() != WriteResult.WriteResultStatus.SUCCESS) {
        System.out.println("Write result failed."); // Handle failure case
      }
    }
  }
}
