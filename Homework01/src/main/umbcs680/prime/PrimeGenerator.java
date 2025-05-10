package umbcs680.prime;

import java.util.*;

public class PrimeGenerator {
	protected long from, to;
	protected LinkedList<Long> primes = new LinkedList<>();

	public PrimeGenerator(long from, long to) {
		if (from >= 1 && to >= from) {
			this.from = from;
			this.to = to;
		} else {
			throw new RuntimeException("Wrong input values: from=" + from + " to=" + to);
		}
	}

	public LinkedList<Long> getPrimes() {
		return primes;
	}

	protected boolean isEven(long n) {
		return n % 2 == 0;
	}

	protected boolean isPrime(long n) {
		if (n <= 1) {
			return false; // 1 or lower are not prime
		}
		if (n == 2) {
			return true; // 2 is the only even prime
		}
		if (isEven(n)) {
			return false; // other even numbers are not prime
		}

		long sqrtN = (long) Math.sqrt(n);
		for (long i = 3; i <= sqrtN; i += 2) {
			if (n % i == 0) {
				return false; // found a divisor
			}
		}
		return true; // no divisors found, it's prime
	}

	public void generatePrimes() {
		for (long n = from; n <= to; n++) {
			if (isPrime(n)) {
				primes.add(n);
			}
		}
	}

	public static void main(String[] args) {
		PrimeGenerator gen = new PrimeGenerator(1, 100);
		gen.generatePrimes();
		LinkedList<Long> primes = gen.getPrimes();
		Iterator<Long> it = primes.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + ", ");
		}
		System.out.println("\n" + gen.getPrimes().size() + " primes are found.");
	}
}
