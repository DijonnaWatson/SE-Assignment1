import java.util.ArrayList;
import java.util.List;

public class FactorComputeEngineImpl implements FactorComputeEngine {
  /**
   * Input some number from the user
   * returns array of the factors
   */
  @Override
  public int[] getFactors(int number) {
    List<Integer> factors = new ArrayList<>();
    for (int i = number; i > 0; i--) {
      if (number % i == 0) {
        factors.add((int) i); // Add factor to list
      }
    }
    // Convert ArrayList to array
    //.mapToInt() operation converts each Integer object in the stream to its
    // corresponding int primitive value. (Integer::intValue) is shortHand for
    // the intValue() method one EACH Integer Object in the stream. Integer ->
    // Integer.intValue() is what it should look like when expanded .toArray
    // collects the elements in the IntStream and returns them as an int[]
    // array.
    return factors.stream().mapToInt(Integer::intValue).toArray();
  }

  @Override
  public void setDelimiter(String delimiter) {
    // TODO Auto-generated method stub
  }
}
