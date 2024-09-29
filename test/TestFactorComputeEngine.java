package com.example.factorfinder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class TestFactorComputeEngine {
  @Test
  public void testGetFactors() {

    FactorComputeEngineImpl mockEngine = mock(FactorComputeEngineImpl.class);

    // defining mock behaivor for getfactors method
    when(mockEngine.getFactors(10)).thenReturn(new int[] {1, 2, 5, 10});

    
    int[] factors = mockEngine.getFactors(10);

    // verifying if getfactors method was called
    verify(mockEngine).getFactors(10);

    // checking returned results vs expected
    assertArrayEquals(new int[] {1, 2, 5, 10}, factors);
  }

  @Test
  public void testSetDelimiter() {

    FactorComputeEngineImpl mockEngine = mock(FactorComputeEngineImpl.class);

    // calling setdelimiter method
    mockEngine.setDelimiter(";");

    // verifying setdelimiter method was called with ";"
    verify(mockEngine).setDelimiter(";");
  }

  @Test
  public void testGetFormattedFactors() {

    FactorComputeEngineImpl mockEngine = mock(FactorComputeEngineImpl.class);

    // defining expected result of getformattedfactors
    when(mockEngine.getFormattedFactors(10)).thenReturn("1,2,5,10");


    String formattedFactors = mockEngine.getFormattedFactors(10);

    // verifying that getformatted factors was called
    verify(mockEngine).getFormattedFactors(10);

    // checking if the expected value and result value match
    assertEquals("1,2,5,10", formattedFactors);
  }

  @Test
  public void testPrototype() {

    FactorComputeEngineImpl mockEngine = mock(FactorComputeEngineImpl.class);

    // defining mock behaivor for getfactors method
    when(mockEngine.getFactors(10)).thenReturn(new int[] {1, 2, 5, 10});

    // call the prototype
    prototype(mockEngine);

    verify(mockEngine).getFactors(10);
  }

  public void prototype(FactorComputeEngineImpl engine) {
    // testing getfactors method
    int[] result = engine.getFactors(10);

    
    System.out.println("Factors of 10: " + java.util.Arrays.toString(result));

    // verifying if mock behaivor is correct
    assertArrayEquals(new int[] {1, 2, 5, 10},
        result); 
  }
}
