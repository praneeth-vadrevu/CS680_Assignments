package umbcs680.prime;

import java.util.LinkedList;

public class SingletonPrimeGenerator {
    private static SingletonPrimeGenerator instance = null;
    private long from, to;
    private LinkedList<Long> primes = new LinkedList<>();

    // Private constructor to enforce Singleton
    private SingletonPrimeGenerator() {}

    // Static factory method to get the singleton instance
    public static SingletonPrimeGenerator getInstance() {
        if (instance == null) {
            instance = new SingletonPrimeGenerator();
        }
        return instance;
    }

    // Setter for range values
    public void setRange(long from, long to) {
        if (from >= 1 && to >= from) {
            this.from = from;
            this.to = to;
        } else {
            throw new RuntimeException("Invalid range: from=" + from + " to=" + to);
        }
    }

    // Return the list of generated primes
    public LinkedList<Long> getPrimes() {
        return primes;
    }

    // Prime number check
    public boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n > 2 && n % 2 == 0) return false;
        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Generate primes in the set range
    public void generatePrimes() {
        primes.clear();
        for (long n = from; n <= to; n++) {
            if (isPrime(n)) {
                primes.add(n);
            }
        }
    }
}
