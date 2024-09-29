package com.example.factorfinder;
import com.example.factorfinder.WriteResult;
import java.util.ArrayList;
import java.util.List;

// In-Memory Data Store Implementation
public class InMemoryFactorDataStore implements FactorDataStore {
  private List<String> storedResults =
      new ArrayList<>(); // Declare and initialize storedResults
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
