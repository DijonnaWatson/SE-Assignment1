package com.example.factorfinder;
import java.util.List;
import java.util.ArrayList;

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
}
