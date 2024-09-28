package factorfinder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestFactorDataStore {
  @Test
  public void testFactorDataStoreWithMocks() {
    // Create mocks for FactorDataStore, InputConfig, OutputConfig, WriteResult,
    // etc.
    FactorDataStore mockFactorDataStore = mock(FactorDataStore.class);
    InputConfig mockInputConfig = mock(InputConfig.class);
    OutputConfig mockOutputConfig = mock(OutputConfig.class);
    WriteResult mockWriteResult = mock(WriteResult.class);

    // Mock the behavior of the read method to return a list of integers
    when(mockFactorDataStore.read(mockInputConfig))
        .thenReturn(Arrays.asList(1, 2, 3, 4, 5));

    // Mock the behavior of the appendSingleResult method to return a successful
    // WriteResult
    when(mockWriteResult.getStatus())
        .thenReturn(WriteResult.WriteResultStatus.SUCCESS);
    when(mockFactorDataStore.appendSingleResult(
             any(OutputConfig.class), anyList()))
        .thenReturn(mockWriteResult);

    // Call the prototype method with mocks
    prototype(mockFactorDataStore, mockInputConfig, mockOutputConfig);

    // Verify that the read method was called once with mockInputConfig as an
    // argument
    verify(mockFactorDataStore, times(1)).read(mockInputConfig);

    // Verify that the appendSingleResult was called five times with
    // mockOutputConfig and some string as arguments
    verify(mockFactorDataStore, times(5))
        .appendSingleResult(eq(mockOutputConfig), anyList());
  }

  // Modified prototype method to accept mock InputConfig and OutputConfig
  public void prototype(FactorDataStore apiToCall, InputConfig inputConfig,
      OutputConfig outputConfig) {
    // Read data from the data store using the input configuration
    Iterable<Integer> loadedData = apiToCall.read(inputConfig);

    // Process the loaded data
    for (int i : loadedData) {
      String result = "" + i; // convert int to string

      // Create a list to hold results for appending
      List<String> resultsToAppend =
          List.of(result); // Use List.of for single element

      // Append the result list to the output configuration
      WriteResult writeResult =
          apiToCall.appendSingleResult(outputConfig, resultsToAppend);

      // Check the write result status
      if (writeResult.getStatus() != WriteResult.WriteResultStatus.SUCCESS) {
        System.out.println("Write result failed."); // Handle failure case
      }
    }
  }
}
