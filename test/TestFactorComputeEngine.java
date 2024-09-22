import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class TestFactorComputeEngine {
  @Test
  public void testFactorComputeEngine() {
    // Create a mock of FactorComputeEngine
    FactorComputeEngine mockEngine = mock(FactorComputeEngine.class);

    // Define the behavior of the mock for the getFactors method
    when(mockEngine.getFactors(10)).thenReturn(new int[] {1, 2, 5, 10});

    // Call the prototype method
    prototype(mockEngine);

    // Verify that getFactors(10) was called
    verify(mockEngine).getFactors(10);
  }

  public void prototype(FactorComputeEngine engine) {
    // This prototype ends up being the simplest, even though the implementation
    // of it is likely to be the most complex of the components. We know the
    // input here is going to be an int, so the parameters are nice and simple,
    // and I've chosen to represent the output as a String - that's a
    // somewhat-opinionated result type, and it would be completely fine to have
    // a more general wrapper. By choosing a String, I'm forcing the
    // ComputeEngine component to handle all result formatting as well as the
    // actual computation - since these two things are going to be very tightly
    // coupled as far as the implementation (how to format a result really
    // depends on what that result represents), this is reasonable to put in one
    // component, but keeping things more general is also fine. API Design is
    // much more art than science!
    int[] result = engine.getFactors(10); // Testing the mockEngine

    // The mock worked since it printed the correct the array of factors
    System.out.println("Result: " + java.util.Arrays.toString(result));
  }
}
