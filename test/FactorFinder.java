//Main IF
public interface FactorFinder {
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
