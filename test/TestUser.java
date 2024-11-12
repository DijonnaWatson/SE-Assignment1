

import java.io.File;

import com.example.factorfinder.FactorComputationCoordinatorImpl;
import com.example.factorfinder.FactorComputeRequest;
import com.example.factorfinder.FileInputConfig;
import com.example.factorfinder.FileOutputConfig;

public class TestUser {
  private final FactorComputationCoordinatorImpl coordinator;

  public TestUser(FactorComputationCoordinatorImpl coordinator) {
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
