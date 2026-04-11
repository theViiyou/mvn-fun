package de.factorizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FactorizerTests {
    Factorizer factorizer = FactorizerImpl.getInstance();

    /**
     * Test regular cases: n=1, n=2, n=3, n=4, n=27, n=65536, n=10952347,
     * n=100000039 (prime number).
     */
    @Test
    @Order(100)
    void test100_factorize_regular_cases() {
        // test code
        assertEquals(List.of(), factorizer.factorize(1));
        assertEquals(List.of(2), factorizer.factorize(2));
        assertEquals(List.of(3), factorizer.factorize(3));
        assertEquals(List.of(2, 2), factorizer.factorize(4));
        assertEquals(List.of(3, 3, 3), factorizer.factorize(27));
        assertEquals(List.of(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2), factorizer.factorize(65536));
        assertEquals(List.of(7, 23, 59, 1153), factorizer.factorize(10952347));
        assertEquals(List.of(100000039), factorizer.factorize(100000039));

    }

    /**
     * Test valid corner cases: n=0, n=2147483646 (MAX_INT-1), n=2147483647
     * (MAX_INT).
     */
    @Test
    @Order(200)
    void test200_factorize_corner_cases() {
        // test code
        assertEquals(List.of(), factorizer.factorize(0));
        assertEquals(List.of(2, 3, 3, 7, 11, 31, 151, 331), factorizer.factorize(Integer.MAX_VALUE - 1));
        // assertThrows(OutOfMemoryError.class, () ->
        // factorizer.factorize(Integer.MAX_VALUE)); factorize angepasst auf i*i
        assertEquals(List.of(2147483647), factorizer.factorize(Integer.MAX_VALUE));

    }

    /**
     * Test (invalid) exception cases: n=-1, n=-10, n=-2147483648.
     * Exception cases that test that method {@code factorize(int n)} throws
     * {@code IllegalArgumentException} with message: "illegal negative parameter:
     * n".
     */
    @ParameterizedTest
    @ValueSource(ints = { -1, -10, Integer.MIN_VALUE })
    @Order(300)
    void test300_factorize_exception_cases(int n) {
        // test code
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            factorizer.factorize(n);
        });
        assertEquals("illegal negative parameter: n", ex.getMessage());
    }

}
