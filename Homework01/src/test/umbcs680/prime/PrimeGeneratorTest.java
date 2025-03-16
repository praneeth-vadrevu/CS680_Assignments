package umbcs680.prime;
import org.junit.Test;
import static org.junit.Assert.*;
import umbcs680.prime.PrimeGenerator;
import java.util.*;

public class PrimeGeneratorTest {

    @Test
    public void testEmptyRangeWhereFromEqualsTo() {
        PrimeGenerator generator = new PrimeGenerator(7, 7);
        generator.generatePrimes();
        LinkedList<Long> primes = generator.getPrimes();
        LinkedList<Long> expectedPrimes = new LinkedList<>(Arrays.asList(7L));
        assertEquals(expectedPrimes, primes);
    }
    @Test(expected = RuntimeException.class)
    public void testRangeWithNegativeNumbers() {
        new PrimeGenerator(-10, 10);
    }
    @Test
    public void testValidRangeWithKnownPrimes(){
        PrimeGenerator generator = new PrimeGenerator(1,10);
        generator.generatePrimes();
        LinkedList <Long> primes = generator.getPrimes();
        LinkedList<Long> expectedPrimes = new LinkedList<>(Arrays.asList(2L, 3L, 5L, 7L));
        assertEquals(expectedPrimes, primes);
    }
    @Test
    public void testRangeWithNoPrimes() {
        PrimeGenerator generator = new PrimeGenerator(14, 16);
        generator.generatePrimes();
        LinkedList<Long> primes = generator.getPrimes();
        assertTrue(primes.isEmpty());
    }
    @Test
    public void testSinglePrimeInRange() {
        PrimeGenerator generator = new PrimeGenerator(13, 13);
        generator.generatePrimes();
        LinkedList<Long> primes = generator.getPrimes();
        LinkedList<Long> expectedPrimes = new LinkedList<>(Arrays.asList(13L));
        assertEquals(expectedPrimes, primes);
    }
    @Test
    public void testLargeRange() {
        PrimeGenerator generator = new PrimeGenerator(1, 100);
        generator.generatePrimes();
        LinkedList<Long> primes = generator.getPrimes();
        assertEquals(25, primes.size()); // There are 25 primes between 1 and 100.
    }
    @Test(expected = RuntimeException.class)
    public void testInvalidRangeWhereFromGreaterThanTo() {
        new PrimeGenerator(10, 1);
    }
    @Test
    public void testRangeWithSingleNumberOne() {
        PrimeGenerator generator = new PrimeGenerator(1, 1);
        generator.generatePrimes();
        LinkedList<Long> primes = generator.getPrimes();
        assertTrue(primes.isEmpty());
    }
    @Test
    public void testRangeWithOnlyOddNumbers() {
        PrimeGenerator generator = new PrimeGenerator(11, 19);
        generator.generatePrimes();
        LinkedList<Long> primes = generator.getPrimes();
        LinkedList<Long> expectedPrimes = new LinkedList<>(Arrays.asList(11L, 13L, 17L, 19L));
        assertEquals(expectedPrimes, primes);
    }
    @Test
    public void testRangeWithOnlyEvenNumbers() {
        PrimeGenerator generator = new PrimeGenerator(10, 20);
        generator.generatePrimes();
        LinkedList<Long> primes = generator.getPrimes();
        LinkedList<Long> expectedPrimes = new LinkedList<>(Arrays.asList(11L, 13L, 17L, 19L));
        assertEquals(expectedPrimes, primes);
    }

}