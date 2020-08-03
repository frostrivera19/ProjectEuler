// Java 14.0.1
// frostrivera19
// Project Euler Problem 23 : Non-abundant sums
// Find the sum of all the positive integers which cannot be written as the sum
// of two abundant numbers.
// https://projecteuler.net/archives
// Solved 30 May 2020

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int ans = sumNotAbundant();
        System.out.println(ans);
    }

    private static int sumNotAbundant() {
        final int LIMIT_ABUNDANT = 28123;
        final int MIN_ABUNDANT = 12;
        boolean[] abundants = new boolean[LIMIT_ABUNDANT + 1];
        boolean[] sumOfAbundants = new boolean[LIMIT_ABUNDANT + 1];
        ArrayList<Boolean> primes = deliberateAllPrimes(LIMIT_ABUNDANT);

        abundants[MIN_ABUNDANT] = true;

        for (int i = MIN_ABUNDANT + 1; i <= LIMIT_ABUNDANT; i++) {
            if (isAbundant(i, primes)) {
                abundants[i] = true;
            }
        }

        int sumNotAbundant = 0;
        for (int i = 0; i <= MIN_ABUNDANT; i++) {
            sumNotAbundant += i;
        }


        for (int i = MIN_ABUNDANT + 1; i <= LIMIT_ABUNDANT; i++) {
            for (int j = MIN_ABUNDANT; j <= i; j++) {
                if (abundants[i - j] && abundants[j]) {
                    sumOfAbundants[i] = true;
                    break;
                }
            }
            if (!sumOfAbundants[i]) {
                sumNotAbundant += i;
            }
        }

        return sumNotAbundant;
    }

    private static boolean isAbundant(int num, ArrayList<Boolean> primes) {
        HashSet<Integer> factors = allFactors(num, primes);
        factors.remove(num);

        Iterator<Integer> facts = factors.iterator();
        int sumFactors = 0;

        while (facts.hasNext()) {
            sumFactors += facts.next();
        }

        return sumFactors > num;
    }

    private static HashSet<Integer> allFactors(int num,
                                               ArrayList<Boolean> primes) {

        ArrayList<Integer> primeFactors = primeFactors(num, primes);
        HashSet<Integer> allFactors = new HashSet<>(primeFactors.size());

        for (int i = 1; i < Math.pow(2, primeFactors.size()); i++) {
            StringBuilder binRepStr = new StringBuilder();
            binRepStr.append(Integer.toBinaryString(i));
            while (binRepStr.length() < primeFactors.size()) {
                binRepStr.insert(0, '0');
            }
            char[] binRep = new String(binRepStr).toCharArray();

            int currentFactor = 1;
            for (int j = 0; j < binRep.length; j++) {
                if (binRep[j] == '1') {
                    currentFactor *= primeFactors.get(j);
                }
            }
            allFactors.add(currentFactor);
        }

        allFactors.add(1);
        allFactors.add(num);

        return allFactors;
    }

    private static ArrayList<Integer> primeFactors(int num,
                                                   ArrayList<Boolean> primes) {
        ArrayList<Integer> primeFactors = new ArrayList<>();

        if (primes.get(num)) {
            primeFactors.add(num);
            return primeFactors;
        }


        int number = num;
        while (number % 2 == 0) {
            primeFactors.add(2);
            number /= 2;
        }

        int divisor = 3;
        while (number != 1 && divisor <= number) {
            while (number % divisor == 0) {
                primeFactors.add(divisor);
                number /= divisor;
            }
            do {
                divisor += 2;
            } while (!primes.get(divisor));
        }

        return primeFactors;
    }

    private static ArrayList<Boolean> deliberateAllPrimes(int limit) {
        ArrayList<Boolean> allPrimes =
                new ArrayList<>(Arrays.asList(new Boolean [limit + 1]));
        Collections.fill(allPrimes, true);
        allPrimes.set(0, false); // 0 == not prime
        allPrimes.set(1, false);

        boolean even = false;
        for (int i = 3; i <= Math.sqrt(limit); i++) {
            if (even) {
                allPrimes.set(i, false);
                even = false;
                continue;
            } else if (allPrimes.get(i)) { // if i is prime
                int multiplier = 2;
                while (i * multiplier <= limit) {
                    allPrimes.set(i * multiplier, false);
                    multiplier++;
                }
            }
            even = true;
        }
        even = (int) Math.sqrt(limit) % 2 == 0;
        for (int i = (int) Math.sqrt(limit); i <= limit; i++) {
            if (even) {
                allPrimes.set(i, false);
                even = false;
            } else {
                even = true;
            }
        }
        return allPrimes;
    }


}