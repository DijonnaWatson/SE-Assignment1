
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

    //original implementation
    long originalStart = System.currentTimeMillis();
    for (int i = 0; i < NUM_RUNS; i++) {
      originalEngine.getFactors(testNumber);
    }
    long originalEnd = System.currentTimeMillis();
    long originalTime = (originalEnd - originalStart) / NUM_RUNS;
    System.out.println(
        "Original Implementation Average Time: " + originalTime + " ms");

    //optimized implementation
    long optimizedStart = System.currentTimeMillis();
    for (int i = 0; i < NUM_RUNS; i++) {
      optimizedEngine.getFactors(testNumber);
    }
    long optimizedEnd = System.currentTimeMillis();
    long optimizedTime = (optimizedEnd - optimizedStart) / NUM_RUNS;
    System.out.println(
        "Optimized Implementation Average Time: " + optimizedTime + " ms");

    //calculating the improvement
    double improvement =
        ((originalTime - optimizedTime) / (double) originalTime) * 100;
    System.out.println("Performance Improvement: " + improvement + "%");

    //testing if the improvement percentage is larger than 10
    //if not the test will fail
    assertTrue(improvement >= 10,
        "Optimized implementation did not achieve at least 10% improvement. "
        + "Improvement: "
            + improvement + "%");
  }
}
