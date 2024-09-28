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
    return new ArrayList<>(inputData);
  }
}
