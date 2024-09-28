package com.example.factorfinder;
import com.example.factorfinder.WriteResult;
import java.util.List;


// In-Memory Data Store Implementation
public class InMemoryFactorDataStore implements FactorDataStore {
  @Override
  public Iterable<Integer> read(InputConfig input) {
    return input.getInputData();
  }
}
