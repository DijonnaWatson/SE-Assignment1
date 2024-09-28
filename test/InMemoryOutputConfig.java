package com.example.factorfinder;
import java.util.ArrayList;
import java.util.List;

public class InMemoryOutputConfig implements OutputConfig {
  private final List<String> outputData;

  public InMemoryOutputConfig() {
    this.outputData = new ArrayList<>();
  }

  @Override
  public void writeOutputData(List<String> data) {
    outputData.addAll(data);
  }

  @Override
  public List<String> getOutputData() {
    return new ArrayList<>(outputData);
  }

  @Override
  public String getFilePath() {
    // Return the file path as a string
    return ""; // Replace with the actual file path or leave as empty string
  }
}
