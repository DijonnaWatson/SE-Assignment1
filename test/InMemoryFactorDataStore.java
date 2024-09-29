package com.example.factorfinder;
import com.example.factorfinder.WriteResult;
import java.util.List;

// In-Memory Data Store Implementation
public class InMemoryFactorDataStore implements FactorDataStore {
  @Override
  public Iterable<Integer> read(InputConfig input) {
    return input.getInputData();
  }

  @Override
  public WriteResult appendSingleResult(
      OutputConfig output, List<String> results) {
    // In-memory store simulation: append results to the list
    storedResults.addAll(results);

    // Returning a successful write result
    return WriteResult.SUCCESS;
  }
}
