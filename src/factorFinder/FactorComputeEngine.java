package com.example.factorfinder;

/**
 * API 3: Conceptual API within the compute engine process
 *
 * Lots of options for this! Right now, because it's a conceptual boundary, we
 * don't have to worry about things like wire compatibility, so the return type
 * can be more concrete (depending on exactly what you're computing, yours might
 * be a long, Set<Integer>, byte[], etc)
 *
 * However, there's no harm in adding a wrapper around the result (ex:
 * SingleComputationResult); you'll notice that by leaving it concrete, we're
 * relying on the fact that this API is within a single process, and therefore
 * can have Exceptions propagate across it. There are tradeoffs here - on the
 * one hand, using a simple type simplifies both the use and implementation of
 * the interface; on the other hand, by building the 'in the same process'
 * behavior into the design, we'll have to change the return value if this
 * component ever moves to a different process or server (foreshadowing...).
 *
 * For the input, there's also nothing wrong with using a wrapper around the
 * int, but since we're given from the system specification that it will always
 * be an int, it's fine to just use that as well.
 */

public interface FactorComputeEngine {
  /**
   * Calculates the factors of the given number.
   *
   * @param number The number to find factors for.
   * @return An array of factors of the given number.
   */
  int[] getFactors(int number);
  
  /**
   * Sets the delimiter to be used in the output.
   *
   * @param delimiter The delimiter to use between factors.
   */
  void setDelimiter(String delimiter);
}
