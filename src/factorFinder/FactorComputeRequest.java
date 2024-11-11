
package com.example.factorfinder;
import com.example.factorfinder.FileInputConfig;
import com.example.factorfinder.FileOutputConfig;

public class FactorComputeRequest {
  private static final char DEFAULT_DELIMITER = ';';

  private final FileInputConfig inputConfig;
  private final FileOutputConfig outputConfig;
  private final char delimiter;

  // Constructor for when the user used the default delimiter
  public FactorComputeRequest(
      FileInputConfig inputConfig, FileOutputConfig outputConfig) {
    this(inputConfig, outputConfig, DEFAULT_DELIMITER); // default delimiter: ;
  }

  // Constructor for when the user used their own delimiter
  public FactorComputeRequest(FileInputConfig inputConfig,
      FileOutputConfig outputConfig, char delimiter) {
    this.inputConfig = inputConfig;
    this.outputConfig = outputConfig;
    this.delimiter = delimiter;
  }

  public char getDelimiter() {
    return delimiter;
  }

  public FileInputConfig getInputConfig() {
    return inputConfig;
  }

  public FileOutputConfig getOutputConfig() {
    return outputConfig;
  }
}
