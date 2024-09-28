
package com.example.factorfinder;
import com.example.factorfinder.InputConfig;
import com.example.factorfinder.OutputConfig;

public class FactorComputeRequest {
  private static final char DEFAULT_DELIMITER = ';';

  private final InputConfig inputConfig;
  private final OutputConfig outputConfig;
  private final char delimiter;

  public FactorComputeRequest(
      InputConfig inputConfig, OutputConfig outputConfig) {
    this(inputConfig, outputConfig, DEFAULT_DELIMITER); // default delimiter: ;
  }

  public FactorComputeRequest(
      InputConfig inputConfig, OutputConfig outputConfig, char delimiter) {
    this.inputConfig = inputConfig;
    this.outputConfig = outputConfig;
    this.delimiter = delimiter;
  }

  public char getDelimiter() {
    return delimiter;
  }

  public InputConfig getInputConfig() {
    return inputConfig;
  }

  public OutputConfig getOutputConfig() {
    return outputConfig;
  }
}
