import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TestFactorComputeEngineTest {
  @Mock private FactorComputeEngine engine; // Mock the API dependency

  @InjectMocks
  private TestFactorComputeEngine testFactorComputeEngine; // Class under test

  @BeforeEach
  void setUp() {
    // Any setup can be done here if needed
  }

  @Test
  void testPrototype() {
    // Arrange
    int number = 1;
    int[] expectedFactors = {1}; // Expected output for the input number
    when(engine.getFactors(number))
        .thenReturn(expectedFactors); // Mock the method

    // Act
    testFactorComputeEngine.prototype(engine); // Call the method under test

    // Assert
    verify(engine, times(1)).getFactors(number); // Verify interaction
    // Additional assertions can be added based on what prototype does
  }
}
