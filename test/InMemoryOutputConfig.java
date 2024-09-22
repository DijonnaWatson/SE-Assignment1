package com.example.factorfinder;

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
