
import com.example.factorfinder.FactorComputeEngine;
import com.example.factorfinder.FactorComputeEngineImpl;
import com.example.factorfinder.FactorComputeEngineOptimizedImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FactorComputeEngineBenchmarkTest {
  private static final int NUM_RUNS = 100;

  @Test
  public void benchmarkFactorComputeEngines() {
    int testNumber = 1_000_000; //test number to find factors of 

    FactorComputeEngine originalEngine = new FactorComputeEngineImpl();
    FactorComputeEngine optimizedEngine = new FactorComputeEngineOptimizedImpl();

    long originalStart = System.nanoTime();
    for (int i = 0; i < NUM_RUNS; i++) {
        originalEngine.getFactors(testNumber);
    }
    long originalEnd = System.nanoTime();
    // Calculate total time in nanoseconds, then divide by NUM_RUNS to get the average time per run
    long originalTimeInNs = (originalEnd - originalStart) / NUM_RUNS;

    System.out.println("Original Implementation Average Time: " + originalTimeInNs + " ns");

    long optimizedStart = System.nanoTime();
    for (int i = 0; i < NUM_RUNS; i++) {
        optimizedEngine.getFactors(testNumber);
    }
    long optimizedEnd = System.nanoTime();
    // Calculate total time in nanoseconds, then divide by NUM_RUNS to get the average time per run
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
