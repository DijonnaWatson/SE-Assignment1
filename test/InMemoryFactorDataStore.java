
import java.util.ArrayList;
import java.util.List;

import com.example.factorfinder.FactorDataStore;
import com.example.factorfinder.FileInputConfig;
import com.example.factorfinder.FileOutputConfig;
import com.example.factorfinder.WriteResult;

// In-Memory Data Store Implementation
public class InMemoryFactorDataStore implements FactorDataStore {
  private List<String> storedResults =
      new ArrayList<>(); // Declare and initialize storedResults
  @Override
  public Iterable<Integer> read(FileInputConfig input) {
    return input.getInputData();
  }

  @Override
  public WriteResult appendSingleResult(
      FileOutputConfig output, List<String> results) {
    // In-memory store simulation: append results to the list
    storedResults.addAll(results);

    // Returning a successful write result
    return WriteResult.SUCCESS;
  }
}
