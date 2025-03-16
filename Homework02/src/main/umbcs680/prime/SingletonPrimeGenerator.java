package umbcs680.prime;
import java.util.*;

public class SingletonPrimeGenerator {
    private static SingletonPrimeGenerator instance = null;
    private long from, to;
    private LinkedList<Long> primes = new LinkedList<Long>();

    private SingletonPrimeGenerator(long from, long to) {
        if (from >= 1 && to > from) {
            this.from = from;
            this.to = to;
            generatePrimes(); // Generate primes immediately upon instantiation
        } else {
            throw new IllegalArgumentException("Wrong input values: from=" + from + " to=" + to);
        }
    }

    public static SingletonPrimeGenerator getInstance(long from, long to) {
        if (instance == null) {
            synchronized (SingletonPrimeGenerator.class) {
                if (instance == null) {
                    instance = new SingletonPrimeGenerator(from, to);
                }
            }
        }
        // If the existing instance has different range, reinitialize
        if (instance.from != from || instance.to != to) {
            synchronized (SingletonPrimeGenerator.class) {
                instance = new SingletonPrimeGenerator(from, to);
            }

        }
        return instance;
    }

    public LinkedList<Long> getPrimes() {
        return new LinkedList<>(primes); // Return a copy to prevent external modification
    }

    private boolean isEven(long n) {
        return n % 2 == 0;
    }

    private boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        if (n > 2 && isEven(n)) {
            return false;
        }
        long i;
        for (i = (long) Math.sqrt(n); n % i != 0 && i >= 1; i--) {}
        return i == 1;
    }

    private void generatePrimes() {
        primes.clear(); // Clear any existing primes before generating
        for (long n = from; n <= to; n++) {
            if (isPrime(n)) {
                primes.add(n);
            }
        }
    }

    public static void main(String[] args) {
        SingletonPrimeGenerator gen = SingletonPrimeGenerator.getInstance(1, 100);
        LinkedList<Long> primes = gen.getPrimes();
        Iterator<Long> it = primes.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }
        System.out.println("\n" + gen.getPrimes().size() + " primes are found.");

        // Test Singleton property: ensure same instance is returned

        SingletonPrimeGenerator gen2 = SingletonPrimeGenerator.getInstance(1, 100);
        System.out.println("Are gen and gen2 the same instance? " + (gen == gen2)); // Should print true

        // Test different range
        SingletonPrimeGenerator gen3 = SingletonPrimeGenerator.getInstance(1, 20);
        LinkedList<Long> primes3 = gen3.getPrimes();
        System.out.println("Primes between 1 and 20:");
        primes3.forEach(prime -> System.out.print(prime + ", "));
        System.out.println();

    }
}