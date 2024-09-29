package com.example.factorfinder;

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
    // creating mocks for FactorDataStore, InputConfig, OutputConfig, WriteResult
    FactorDataStoreImpl mockFactorDataStore = mock(FactorDataStoreImpl.class);
    FileInputConfig mockInputConfig = mock(FileInputConfig.class);
    FileOutputConfig mockOutputConfig = mock(FileOutputConfig.class);
    WriteResult mockWriteResult = mock(WriteResult.class);

    when(mockFactorDataStore.read(mockInputConfig))
        .thenReturn(Arrays.asList(1, 2, 3, 4, 5));

    when(mockWriteResult.getStatus())
        .thenReturn(WriteResult.WriteResultStatus.SUCCESS);
    when(mockFactorDataStore.appendSingleResult(
             any(OutputConfig.class), anyList()))
        .thenReturn(mockWriteResult);

    prototype(mockFactorDataStore, mockInputConfig, mockOutputConfig);

    // verifying that the read method was called once with mockInputConfig
    verify(mockFactorDataStore, times(1)).read(mockInputConfig);

    // verifying if appendsingleresult method was called correct number of times
    verify(mockFactorDataStore, times(5))
        .appendSingleResult(eq(mockOutputConfig), anyList());
  }

  
  public void prototype(FactorDataStoreImpl apiToCall,
      FileInputConfig inputConfig, FileOutputConfig outputConfig) {
    // iterable reads data from the input destination
    Iterable<Integer> loadedData = apiToCall.read(inputConfig);

    // for each loop that takes loaded data and converts it to a string
    for (int i : loadedData) {
      String result = String.valueOf(i); 

      // creating string list for results
      List<String> resultsToAppend =
          List.of(result); 

      // adding result string list to output destination
      WriteResult writeResult =
          apiToCall.appendSingleResult(outputConfig, resultsToAppend);

      // Check the write result status, if failed print message out to console
      if (writeResult.getStatus() != WriteResult.WriteResultStatus.SUCCESS) {
        System.out.println("Write result failed.");
      }
    }
  }
}
