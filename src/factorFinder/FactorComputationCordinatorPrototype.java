package com.example.factorfinder;

import java.util.List;

public class FactorComputationCordinatorPrototype {
  public void prototype(FactorComputationCordinator apiToCall) {
    InputConfig inputConfig = new InputConfig() {
      @Override
      public List<Integer> getInputData() {
        return this
            .getInputData; // Changed this from null to return the input data.
                           // It didn't give an error in eclipse and it just
                           // made more sense than returning null.
      }

      @Override
      public String getFilePath() {
        return this.getFilePath; // Similar change as above.
      }
    };

    OutputConfig outputConfig =
        request.getOutputConfig(); // Can replace if necessary just copied what
                                   // we had for the implementation

    FactorComputeRequest request =
        new FactorComputeRequest(inputConfig, outputConfig, ',');

    FactorComputeResult result = apiToCall.compute(request);

    if (result.getStatus().isSuccess()) {
      System.out.println("Status retrieval successful.");
    }
  }
}
