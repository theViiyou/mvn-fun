package de.factorizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FactorizerTests {

    private final Factorizer factorizer = FactorizerImpl.getInstance();

    /**
     * Test regular cases: n=1, n=2, n=3, n=4, n=27, n=65536, n=10952347,
     * n=100000039 (prime number).
     */
    @Test
    @Order(110)
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
    @Order(210)
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
    @Order(310)
    void test300_factorize_exception_cases(Long n) {
        // test code
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            factorizer.factorize(n);
        });
        assertEquals("illegal negative parameter: n", ex.getMessage());
    }

    /**
     * Object under test: reference to singleton instance of (hidden) implementation
     * class.
     */

    private List<Long> expected;
    List<Long> actual;
    private long n;

    @Test
    @Order(100)
    void test100_factorize_case_n_is_0() {
        n = 0L;
        actual = factorizer.factorize(n);
        assertTrue(actual.isEmpty()); // factorize(0) -> empty list
    }

    @Test
    @Order(101)
    void test101_factorize_case_n_is_1() {
        n = 1L;
        actual = factorizer.factorize(n);
        assertTrue(actual.isEmpty());
        // factorize(1) -> empty list
    }

    @Test
    @Order(102)
    void test102_factorize_case_n_is_2() {
        n = 2L;
        expected = List.of(2L);
        actual = factorizer.factorize(n);
        assertIterableEquals(expected, actual); // factorize(2) -> [2]
    }

    @Test
    @Order(103)
    void test103_factorize_case_n_is_3() {
        n = 3L;

        expected = List.of(3L);
        actual = factorizer.factorize(n);
        assertIterableEquals(expected, actual); // factorize(3) -> [3]
        // factorize(3) -> [3]
    }

    @Test
    @Order(112)
    void test112_factorize_case_n_is_12() {
        n = 12L;
        expected = List.of(3L, 2L, 2L); // mind order, could also be [2, 3, 2]
        actual = factorizer.factorize((long) n);
        assertThat(actual, Matchers.containsInAnyOrder(expected.toArray(new Long[0])));
        //
        // use Hamcrest matcher to compare two lists ignoring order
        // assertThat(expected, Matchers.containsInAnyOrder(actual.toArray()));
    }

    /**
     * Test other regular cases: n=1, n=2, n=3, n=4, n=27, n=65536, n=10952347,
     * n=100000039 (prime number).
     */
    @Test
    @Order(200)
    void test200_factorize_other_regular_cases() {
        //
        Stream.of(1, 2, 3, 4, 27, 65536, 10952347, 100000039)
                .forEach(n -> {
                    switch (n) {
                        case 1:
                            expected = List.of();
                            break;
                        case 2:
                            expected = List.of(2L);
                            break;
                        case 3:
                            expected = List.of(3L);
                            break;
                        case 4:
                            expected = List.of(2L, 2L);
                            break;
                        case 27:
                            expected = List.of(3L, 3L, 3L);
                            break;
                        case 65536:
                            expected = List.of(2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L);
                            break;
                        case 10952347:
                            expected = List.of(7L, 23L, 59L, 1153L);
                            break;
                        case 100000039:
                            expected = List.of(100000039L);
                            break;

                    }
                    actual = factorizer.factorize((long) n);
                    assertThat(actual, Matchers.containsInAnyOrder(expected.toArray(new Long[0])));

                });
    }

    /**
     * Test valid corner cases: n=0, n=2147483646 (MAX_INT-1), n=2147483647
     * (MAX_INT).
     */
    @Test
    @Order(300)
    void test300_factorize_corner_cases() {
        long n = 0L;
        List<Long> actual = factorizer.factorize(n);
        assertTrue(actual.isEmpty());

        // Fall: Integer.MAX_VALUE - 1
        n = Integer.MAX_VALUE - 1L;
        List<Long> expected = List.of(2L, 3L, 3L, 7L, 11L, 31L, 151L, 331L);
        actual = factorizer.factorize(n);
        assertThat(actual, containsInAnyOrder(expected.toArray(new Long[0])));

        // Fall: Integer.MAX_VALUE (Primzahl)
        n = (long) Integer.MAX_VALUE;
        actual = factorizer.factorize(n); // Zuerst berechnen, dann prüfen

        // Da 2147483647 prim ist, enthält die Liste genau diese Zahl
        assertFalse(actual.isEmpty());
        assertThat(actual, containsInAnyOrder(n));
    }

    /**
     * Test (invalid) exception cases: n=-1, n=-10, n=-2147483648 (-MAX_INT).
     * Exception cases that test that method {@code factorize(int n)} throws
     * {@code IllegalArgumentException} with message: "illegal negative parameter:
     * n".
     */
    @Test
    @Order(400)
    void test400_factorize_exception_cases() {
        Stream.of(-1L, -10L, (long) Integer.MIN_VALUE)
                .forEach(n -> {
                    var expected = assertThrows(IllegalArgumentException.class, () -> factorizer.factorize(n));
                    assertEquals("illegal negative parameter: n", expected.getMessage());
                });
    }

}
