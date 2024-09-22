package com.example.factorfinder;
import com.example.factorfinder.WriteResult;

// Enum for WriteResult to represent possible outcomes
enum WriteResult { SUCCESS, FAILURE }

// In-Memory Data Store Implementation
public class InMemoryFactorDataStore implements FactorDataStore {
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
