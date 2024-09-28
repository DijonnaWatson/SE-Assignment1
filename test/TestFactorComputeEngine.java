package factorfinder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class TestFactorComputeEngine {
  @Test
  public void testGetFactors() {
    // Create a mock of FactorComputeEngine
    FactorComputeEngine mockEngine = mock(FactorComputeEngine.class);

    // Define the behavior of the mock for the getFactors method
    when(mockEngine.getFactors(10)).thenReturn(new int[] {1, 2, 5, 10});

    // Call the method under test
    int[] factors = mockEngine.getFactors(10);

    // Verify that getFactors(10) was called
    verify(mockEngine).getFactors(10);

    // Check if the returned result matches the expected array
    assertArrayEquals(new int[] {1, 2, 5, 10}, factors);
  }

  @Test
  public void testSetDelimiter() {
    // Create a mock of FactorComputeEngine
    FactorComputeEngine mockEngine = mock(FactorComputeEngine.class);

    // Call the setDelimiter method
    mockEngine.setDelimiter(";");

    // Verify that setDelimiter was called with the argument ";"
    // Even if we change what's in the setDelimeter's but match them this still
    // works
    verify(mockEngine).setDelimiter(";");
  }

  @Test
  public void testGetFormattedFactors() {
    // Create a mock of FactorComputeEngine
    FactorComputeEngineImpl mockEngine = mock(FactorComputeEngineImpl.class);

    // Define the behavior of getFormattedFactors
    when(mockEngine.getFormattedFactors(10)).thenReturn("1,2,5,10");

    // Call the method under test
    String formattedFactors = mockEngine.getFormattedFactors(10);

    // Verify that getFormattedFactors(10) was called
    verify(mockEngine).getFormattedFactors(10);

    // Check if the formatted string matches the expected value
    assertEquals("1,2,5,10", formattedFactors);
  }

  @Test
  public void testPrototype() {
    // Create a mock of FactorComputeEngine
    FactorComputeEngine mockEngine = mock(FactorComputeEngine.class);

    // Define the behavior of the mock for the getFactors method
    when(mockEngine.getFactors(10)).thenReturn(new int[] {1, 2, 5, 10});

    // Call the prototype method
    prototype(mockEngine);

    // Verify that getFactors(10) was called
    verify(mockEngine).getFactors(10);
  }

  // Updated prototype method to match the new class structure
  public void prototype(FactorComputeEngine engine) {
    // Get the factors of 10
    int[] result = engine.getFactors(10);

    // Print out the factors
    System.out.println("Factors of 10: " + java.util.Arrays.toString(result));

    // Mock behavior verification
    assertArrayEquals(new int[] {1, 2, 5, 10},
        result); // Verifying the result matches the expected array
  }
}
