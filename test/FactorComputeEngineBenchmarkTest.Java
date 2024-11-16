
import com.example.factorfinder.FactorComputeEngine;
import com.example.factorfinder.FactorComputeEngineImpl;
import com.example.factorfinder.FactorComputeEngineOptimized;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class FactorComputeEngineBenchmarkTest {
  private static final int NUM_RUNS = 100;

  // Using an in-memory data store (map) to store computed factors
  private static final Map<Integer, int[]> dataStore = new HashMap<>();

  @Test
  public void benchmarkFactorComputeEngines() {
    int testNumber = 1_000_000; // Test number to find factors of

    FactorComputeEngine originalEngine = new FactorComputeEngineImpl();
    FactorComputeEngine optimizedEngine = new FactorComputeEngineOptimizedImpl();

    // Benchmark for the original engine with data store usage
    long originalStart = System.nanoTime();
    for (int i = 0; i < NUM_RUNS; i++) {
        // Store the result in the data store after calculating factors
        int[] factors = originalEngine.getFactors(testNumber);
        dataStore.put(testNumber, factors);  // Simulate storing computed factors
    }
    long originalEnd = System.nanoTime();
    long originalTimeInNs = (originalEnd - originalStart) / NUM_RUNS;

    System.out.println("Original Implementation Average Time: " + originalTimeInNs + " ns");

    // Benchmark for the optimized engine with data store usage
    long optimizedStart = System.nanoTime();
    for (int i = 0; i < NUM_RUNS; i++) {
        // Store the result in the data store after calculating factors
        int[] factors = optimizedEngine.getFactors(testNumber);
        dataStore.put(testNumber, factors);  // Simulate storing computed factors
    }
    long optimizedEnd = System.nanoTime();
    long optimizedTimeInNs = (optimizedEnd - optimizedStart) / NUM_RUNS;

    System.out.println("Optimized Implementation Average Time: " + optimizedTimeInNs + " ns");

    // Calculating the improvement percentage
    double improvement = ((originalTimeInNs - optimizedTimeInNs) / (double) originalTimeInNs) * 100;
    System.out.println("Performance Improvement: " + improvement + "%");

    if (optimizedTimeInNs < originalTimeInNs) {
        System.out.println("Optimized implementation is faster than the original implementation since it improved by more than 10%");
    } else {
        System.out.println("Optimized implementation is slower than the original implementation since it improved by less than 10%");
    }
  }
}
