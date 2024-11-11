

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import factorFinder.FactorComputeEngineImpl;

class TestFactorComputeEngine {
  FactorComputeEngineImpl mockEngine = mock(FactorComputeEngineImpl.class);
  @Test
  public void testGetFactors() {
    // I did change a bit of this to play around with it that's why the factor
    // is 50 instead of 10 and there's Syste.out.println stuff in the code now

    // defining mock behaivor for getfactors method
    when(mockEngine.getFactors(50)).thenReturn(new int[] {1, 2, 5, 10, 25});

    int[] factors = mockEngine.getFactors(50);

    // verifying if getfactors method was called
    verify(mockEngine).getFactors(50);

    // Different way of checking to see that the result matches what we expect
    // System.out.println("factors of 50:"+java.util.Arrays.toString(factors));

    // checking returned results vs expected
    assertArrayEquals(new int[] {1, 2, 5, 10, 25}, factors);
  }

  @Test
  public void testSetDelimiter() {
    // calling setdelimiter method
    mockEngine.setDelimiter(";");

    // verifying setdelimiter method was called with ";"
    verify(mockEngine).setDelimiter(";");
  }

  @Test
  public void testGetFormattedFactors() {
    // defining expected result of getformattedfactors
    List<String> factors = new ArrayList<>();
    factors.add("1,2,5,10");
    when(mockEngine.getFormattedFactors(10)).thenReturn(factors);

    List<String> formattedFactors = mockEngine.getFormattedFactors(10);

    // verifying that getformatted factors was called
    verify(mockEngine).getFormattedFactors(10);

    // Different way of checking to see that the result matches what we expect
    //    System.out.println("formatted results: "+  formattedFactors);

    // checking if the expected value and result value match
    assertEquals(factors, formattedFactors);
  }

  @Test
  public void testPrototype() {
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
    assertArrayEquals(new int[] {1, 2, 5, 10}, result);
  }
}
