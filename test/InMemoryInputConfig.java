package com.example.factorfinder;
import java.util.ArrayList;
import java.util.List;

public class InMemoryInputConfig implements InputConfig {
  private final List<Integer> inputData;

  public InMemoryInputConfig(List<Integer> inputData) {
    this.inputData = new ArrayList<>(inputData);
  }

  @Override
  public List<Integer> getInputData() {
    return new ArrayList<>(inputData);
  }

  @Override
  public String getFilePath() {
    // Return the file path as a string
    return ""; // Replace with the actual file path
  }
}
