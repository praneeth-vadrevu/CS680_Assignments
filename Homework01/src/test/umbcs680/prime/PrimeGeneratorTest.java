package umbcs680.prime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class PrimeGeneratorTest {

    @Test
    public void testValidRangeReturnsCorrectPrimes() {
        PrimeGenerator gen = new PrimeGenerator(1, 10);
        gen.generatePrimes();
        Long[] expectedPrimes = {2L, 3L, 5L, 7L};
        assertArrayEquals(expectedPrimes, gen.getPrimes().toArray());
    }

    @Test
    public void testGetPrimesReturnsEmptyBeforeGeneration() {
        PrimeGenerator gen = new PrimeGenerator(1, 10);
        assertTrue(gen.getPrimes().isEmpty(), "Primes list should be empty before generation");
    }

    @Test
    public void testSinglePrimeInRange() {
        PrimeGenerator gen = new PrimeGenerator(13, 13);
        gen.generatePrimes();
        Long[] expectedPrimes = {13L};
        assertArrayEquals(expectedPrimes, gen.getPrimes().toArray());
    }

    @Test
    public void testRangeWithNoPrimes() {
        PrimeGenerator gen = new PrimeGenerator(14, 16);
        gen.generatePrimes();
        assertTrue(gen.getPrimes().isEmpty(), "Expected no primes in range 14-16");
    }

    @Test
    public void testLargeRangeCountsPrimes() {
        PrimeGenerator gen = new PrimeGenerator(1, 100);
        gen.generatePrimes();
        assertEquals(25, gen.getPrimes().size(), "Expected 25 primes between 1 and 100");
    }

    @Test
    public void testRangeStartingFromOneYieldsNoPrimeOne() {
        PrimeGenerator gen = new PrimeGenerator(1, 1);
        gen.generatePrimes();
        assertTrue(gen.getPrimes().isEmpty(), "1 is not a prime, expect empty result");
    }

    @Test
    public void testRangeOfOddNumbers() {
        PrimeGenerator gen = new PrimeGenerator(11, 19);
        gen.generatePrimes();
        Long[] expectedPrimes = {11L, 13L, 17L, 19L};
        assertArrayEquals(expectedPrimes, gen.getPrimes().toArray());
    }

    @Test
    public void testRangeOfEvenNumbers() {
        PrimeGenerator gen = new PrimeGenerator(10, 20);
        gen.generatePrimes();
        Long[] expectedPrimes = {11L, 13L, 17L, 19L};
        assertArrayEquals(expectedPrimes, gen.getPrimes().toArray());
    }

    @Test
    public void testIsEvenMethod() {
        PrimeGenerator gen = new PrimeGenerator(1, 2);
        assertTrue(gen.isEven(2));
        assertFalse(gen.isEven(3));
    }

    @Test
    public void testIsPrimeMethodWithKnownPrimesAndNonPrimes() {
        PrimeGenerator gen = new PrimeGenerator(1, 10);
        assertTrue(gen.isPrime(2));
        assertTrue(gen.isPrime(3));
        assertTrue(gen.isPrime(5));
        assertTrue(gen.isPrime(7));
        assertFalse(gen.isPrime(4));
        assertFalse(gen.isPrime(6));
    }

    // Negative Tests
    @Test
    public void testThrowsExceptionForNegativeStart() {
        assertThrows(RuntimeException.class, () -> new PrimeGenerator(-10, 10));
    }

    @Test
    public void testThrowsExceptionForReverseRange() {
        assertThrows(RuntimeException.class, () -> new PrimeGenerator(100, 1));
    }

    @Test
    public void testThrowsExceptionForEqualStartAndEndLessThanOne() {
        assertThrows(RuntimeException.class, () -> new PrimeGenerator(0, 0));
    }
}
