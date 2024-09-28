package com.example.factorfinder;
import java.util.List;
import java.util.ArrayList;

public class InMemoryInputConfig implements InputConfig {
  private final List<Integer> inputData;

  public InMemoryInputConfig(List<Integer> inputData) {
    this.inputData = new ArrayList<>(inputData);
  }

  @Override
  public List<Integer> getInputData() {
    String getFilePath(); // Method to get the file path for input data
    return new ArrayList<>(inputData);
  }
}
