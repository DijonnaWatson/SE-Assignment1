package com.example.factorfinder;

import java.util.List;

public class FactorComputationCoordinatorPrototype {
  public void prototype(FactorComputationCoordinator apiToCall) {
    // Use concrete implementations of InputConfig and OutputConfig
    FileInputConfig inputConfig =
        new FileInputConfig("input.txt"); // just used temporary files
    FileOutputConfig outputConfig =
        new FileOutputConfig("output.txt"); // just used temporary files

    // FactorComputeRequest with the specified input, output, and delimiter
    FactorComputeRequest request =
        new FactorComputeRequest(inputConfig, outputConfig, ',');

    // Call the compute method using the apiToCall instance
    FactorComputeResult result = apiToCall.compute(request);

    // Check if the result indicates success and print a message
    if (result.getStatus().isSuccess()) {
      System.out.println("Prototype Status retrieval successful.");
    } else {
      System.out.println("Prototype Status retrieval failed.");
    }
  }
}
