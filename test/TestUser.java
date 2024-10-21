package com.example.factorfinder;

import java.io.File;

public class TestUser {
  private final FactorComputationCordinatorImpl coordinator;

  public TestUser(FactorComputationCordinatorImpl coordinator) {
    this.coordinator = coordinator;
  }

  public void run(String outputPath) {
    char delimiter = ';';
    String inputPath = "test" + File.separatorChar + "testInputFile.txt";

    FileInputConfig inputFile = new FileInputConfig(
        inputPath); // need the inputFile to be type FileInputConfig
    FileOutputConfig outputFile = new FileOutputConfig(
        outputPath); // need the inputFile to be type FileOutputConfig

    // Created a request from the inputFile,outputFile, and delimiter, to than
    // use the compute method in FactorComputeEngineImpl
    FactorComputeRequest request =
        new FactorComputeRequest(inputFile, outputFile, delimiter);
    coordinator.compute(
        request); // now I can call the compute method on the coordinator
  }
}
