package com.example.factorfinder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestFactorComputeEngine {
  @Mock private FactorComputeEngine engine; // Mock the dependency

  @InjectMocks
  private TestFactorComputeEngine
      testFactorComputeEngine; // The class under test

  public TestFactorComputeEngine() {
    MockitoAnnotations.openMocks(this); // Initialize mocks
  }

  public String prototype(FactorComputeEngine engine) {
    int[] result = engine.getFactors(1);
    return Arrays.toString(result); // Assuming you want to return a string
                                    // representation of the factors
  }

  @Test
  public void testPrototype() {
    // Arrange
    int[] mockFactors = {1}; // Mocked return value for the getFactors method
    when(engine.getFactors(1)).thenReturn(mockFactors);

    // Act
    String result = prototype(engine);

    // Assert
    assertEquals("[1]", result); // Change based on expected output
    verify(engine, times(1))
        .getFactors(1); // Verify that getFactors was called once
  }
}
