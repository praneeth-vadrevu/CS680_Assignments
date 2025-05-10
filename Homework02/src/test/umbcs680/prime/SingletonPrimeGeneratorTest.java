package umbcs680.prime;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class SingletonPrimeGeneratorTest {

    private SingletonPrimeGenerator generator;

    @BeforeEach
    public void setUp() {
        generator = SingletonPrimeGenerator.getInstance();
    }

    @Test
    public void testGeneratePrimesInValidRange() {
        generator.setRange(1, 10);
        generator.generatePrimes();
        LinkedList<Long> expected = new LinkedList<>(Arrays.asList(2L, 3L, 5L, 7L));
        assertIterableEquals(expected, generator.getPrimes());
    }

    @Test
    public void testGeneratePrimesWithSinglePrime() {
        generator.setRange(13, 13);
        generator.generatePrimes();
        LinkedList<Long> expected = new LinkedList<>(Arrays.asList(13L));
        assertIterableEquals(expected, generator.getPrimes());
    }

    @Test
    public void testGeneratePrimesWithNoPrimes() {
        generator.setRange(14, 16);
        generator.generatePrimes();
        assertTrue(generator.getPrimes().isEmpty());
    }

    @Test
    public void testRangeWithOneAsBoundary() {
        generator.setRange(1, 1);
        generator.generatePrimes();
        assertTrue(generator.getPrimes().isEmpty());
    }

    @Test
    public void testRangeWithNegativeStartShouldThrowException() {
        assertThrows(RuntimeException.class, () -> {
            generator.setRange(-10, 10);
        });
    }

    @Test
    public void testRangeWithStartGreaterThanEndShouldThrowException() {
        assertThrows(RuntimeException.class, () -> {
            generator.setRange(10, 1);
        });
    }

    @Test
    public void testLargeRange() {
        generator.setRange(1, 100);
        generator.generatePrimes();
        assertEquals(25, generator.getPrimes().size());
    }



    @Test
    public void testIsPrimeMethod() {
        assertTrue(generator.isPrime(7));
        assertFalse(generator.isPrime(9));
        assertFalse(generator.isPrime(1));
    }

}
