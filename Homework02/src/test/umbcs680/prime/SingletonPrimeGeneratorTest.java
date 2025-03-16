import org.junit.jupiter.api.Test;
import umbcs680.prime.SingletonPrimeGenerator;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonPrimeGeneratorTest {

    @Test
    public void testSingletonInstance() {
        SingletonPrimeGenerator gen1 = SingletonPrimeGenerator.getInstance(1, 100);
        SingletonPrimeGenerator gen2 = SingletonPrimeGenerator.getInstance(1, 100);
        assertSame(gen1, gen2, "Should be the same instance");
    }

    @Test
    public void testDifferentRangeCreatesNewInstance() {
        SingletonPrimeGenerator gen1 = SingletonPrimeGenerator.getInstance(1, 100);
        SingletonPrimeGenerator gen2 = SingletonPrimeGenerator.getInstance(1, 20); //Different range
        //The code in getInstance re-initializes rather than return new instances
        assertSame(gen1, gen2, "If same instance should be returned");
    }

    @Test
    public void testPrimesInRange1To10() {
        SingletonPrimeGenerator gen = SingletonPrimeGenerator.getInstance(1, 10);
        LinkedList<Long> primes = gen.getPrimes();
        assertNotNull(primes, "Prime list should not be null");
        assertEquals(4, primes.size(), "Should have 4 primes between 1 and 10");
        assertTrue(primes.contains(2L));
        assertTrue(primes.contains(3L));
        assertTrue(primes.contains(5L));
        assertTrue(primes.contains(7L));
    }

    @Test
    public void testPrimesInRange1To20() {
        SingletonPrimeGenerator gen = SingletonPrimeGenerator.getInstance(1, 20);
        LinkedList<Long> primes = gen.getPrimes();
        assertNotNull(primes, "Prime list should not be null");
        assertEquals(8, primes.size(), "Should have 8 primes between 1 and 20");
        assertTrue(primes.contains(2L));
        assertTrue(primes.contains(3L));
        assertTrue(primes.contains(5L));
        assertTrue(primes.contains(7L));
        assertTrue(primes.contains(11L));
        assertTrue(primes.contains(13L));
        assertTrue(primes.contains(17L));
        assertTrue(primes.contains(19L));
    }

    @Test
    public void testEmptyRange() {
        SingletonPrimeGenerator gen = SingletonPrimeGenerator.getInstance(1, 1);
        LinkedList<Long> primes = gen.getPrimes();
        assertEquals(0, primes.size(), "Should have 0 primes");
    }


    @Test
    public void testGetPrimesReturnsDefensiveCopy() {
        SingletonPrimeGenerator gen = SingletonPrimeGenerator.getInstance(1, 10);
        LinkedList<Long> primes1 = gen.getPrimes();
        LinkedList<Long> primes2 = gen.getPrimes();

        // Modify primes1 to verify if primes2 is affected or not
        primes1.add(11L);

        assertNotEquals(primes1.size(), primes2.size(), "Modifying one list should not affect another");

    }

    @Test
    public void testInvalidRangeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SingletonPrimeGenerator.getInstance(10, 1); //Invalid range
        });
    }

    @Test
    public void testFromLessThanOneThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SingletonPrimeGenerator.getInstance(0, 10);
        });
    }

    @Test
    public void testZeroPrimesGenerated() {
        SingletonPrimeGenerator gen = SingletonPrimeGenerator.getInstance(7,7);
        LinkedList<Long> primes = gen.getPrimes();
        assertEquals(1, primes.size());
        assertTrue(primes.contains(7L));

    }

    @Test
    public void testSingletonWithDifferentRange() {
        SingletonPrimeGenerator gen1 = SingletonPrimeGenerator.getInstance(1, 10);
        LinkedList<Long> primes1 = gen1.getPrimes();
        assertEquals(4, primes1.size(), "Should have 4 primes");

        SingletonPrimeGenerator gen2 = SingletonPrimeGenerator.getInstance(1, 20);
        LinkedList<Long> primes2 = gen2.getPrimes();
        assertEquals(8, primes2.size(), "Should have 8 primes after re-initialization");

        SingletonPrimeGenerator gen3 = SingletonPrimeGenerator.getInstance(1, 10);
        LinkedList<Long> primes3 = gen3.getPrimes();
        assertEquals(4, primes3.size(), "Range should be reseted correctly");
    }

}