package com.example.factorfinder;

// Enum for WriteResult to represent possible outcomes
enum WriteResult { SUCCESS, FAILURE }

// In-Memory Data Store Implementation
class InMemoryFactorDataStore implements FactorDataStore {
  @Override
  public Iterable<Integer> read(InputConfig input) {
    return input.getInputData();
  }

  @Override
  public WriteResult appendSingleResult(OutputConfig output, String result) {
    output.writeOutputData(List.of(result));
    return WriteResult.SUCCESS;
  }
}
