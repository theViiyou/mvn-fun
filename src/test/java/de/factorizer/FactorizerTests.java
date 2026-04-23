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
        assertEquals(List.of(), factorizer.factorize(1L));
        assertEquals(List.of(2L), factorizer.factorize(2L));
        assertEquals(List.of(3L), factorizer.factorize(3L));
        assertEquals(List.of(2L, 2L), factorizer.factorize(4L));
        assertEquals(List.of(3L, 3L, 3L), factorizer.factorize(27L));
        assertEquals(List.of(2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L),
                factorizer.factorize(65536L));
        assertEquals(List.of(7L, 23L, 59L, 1153L), factorizer.factorize(10952347L));
        assertEquals(List.of(100000039L), factorizer.factorize(100000039L));

    }

    /**
     * Test valid corner cases: n=0, n=2147483646 (MAX_INT-1), n=2147483647
     * (MAX_INT).
     */
    @Test
    @Order(200)
    void test200_factorize_corner_cases() {
        // test code
        assertEquals(List.of(), factorizer.factorize(0L));
        assertEquals(List.of(2L, 3L, 715827883L, 2147483647L), factorizer.factorize(Long.MAX_VALUE - 1));
        // assertThrows(OutOfMemoryError.class, () ->
        // factorizer.factorize(Long.MAX_VALUE)); factorize angepasst auf i*i
        assertEquals(List.of(7L, 7L, 73L, 127L, 337L, 92737L, 649657L), factorizer.factorize(Long.MAX_VALUE));

    }

    /**
     * Test (invalid) exception cases: n=-1, n=-10, n=-2147483648.
     * Exception cases that test that method {@code factorize(int n)} throws
     * {@code IllegalArgumentException} with message: "illegal negative parameter:
     * n".
     */
    @ParameterizedTest
    @ValueSource(longs = { -1, -10, Long.MIN_VALUE })
    @Order(300)
    void test300_factorize_exception_cases(Long n) {
        // test code
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            factorizer.factorize(n);
        });
        assertEquals("illegal negative parameter: n", ex.getMessage());
    }

}
