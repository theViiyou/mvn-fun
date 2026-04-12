package de.factorizer;

import java.util.List;

/**
 * Interface of a singleton component that factorizes numbers
 * into prime factors.
 */
public interface Factorizer {

    /**
     * Method accepts numbers as {@code args} and outputs lines with the
     * number {@code n}, its factors and an indicator whether {@code n} is a
     * prime number.
     * <p>For example:</p>
     * <pre>
     * {@code - n=3 -> [3] (prime number)
     * - n=27 -> [3, 3, 3]
     * - n=1092 -> [2, 2, 3, 7, 13]
     * - n=65536 -> [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
     * - n=10952347 -> [7, 23, 59, 1153]
     * - n=100000039 -> [100000039] (prime number)
     * }
     * </pre>
     * @param args numbers to factorize
     */
    public void run(String[] args);

    /**
     * Method accepts a number {@code n} and returns its factors.
     * @param n number to factorize
     * @return factors
     */
    public List<Integer> factorize(Integer n);

    /**
     * Return reference to singleton instance of (hidden) implementation class
     * that implements the {@link Factorizer} interface.
     * @return reference to instance that implements the {@link Factorizer} interface
     */
    static Factorizer getInstance() {
        return FactorizerImpl.getInstance();
    }
}
