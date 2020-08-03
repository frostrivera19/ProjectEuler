// Java 14.0.1
// frostrivera19
// Project Euler Problem 21: Amicable Numbers
// Evaluate the sum of all the amicable numbers under 10000
// https://projecteuler.net/archives
// Solved 26 May 2020

import java.util.*;

public class Main {
    public static void main(String[] args) {

        int ans = sumOfAmicable(10000);
        System.out.println(ans);
    }

    private static int sumOfAmicable(int limit) {
        ArrayList<Boolean> allPrimes = deliberateAllPrimes(20 * limit);
        HashSet<Integer> amicableNums = new HashSet<>();
        int sum = 0;

        for (int i = 220; i < limit; i++) {
            // 220 is the smallest amicable number
            if (amicableNums.contains(i)) {
                continue;
            }
            int sister = amicable(i, allPrimes);
            if (sister > 0) {
                amicableNums.add(i);
                amicableNums.add(sister);
                sum = sum + i + sister;
            }
        }

        System.out.println(amicableNums);

        return sum;
    }

    private static int amicable(int num, ArrayList<Boolean> primes) {
        int sister = sumAllFactors(num, primes) - num;
        if (num == sumAllFactors(sister, primes) - sister
                && num != sister) {
            // amicable
            return sister;
        } else {
            return -1;
        }
    }

    private static int sumAllFactors(int number, ArrayList<Boolean> primes) {
        HashSet<Integer> factors = allFactors(number, primes);
        int sum = 0;
        for (Integer factor : factors) {
            sum += factor;
        }
        return sum;
    }

    private static HashSet<Integer> allFactors(int number,
                                               ArrayList<Boolean> primes) {

        HashSet<Integer> factors = new HashSet<>();
        factors.add(1);
        for (int i = 2; i <= number; i++) {
            if (primes.get(i) && number % i == 0) {
                for (int mul = 1; mul * i <= number; mul++) {
                    if (number % (mul * i) == 0) {
                        factors.add(mul * i);
                    }
                }
            }
        }

        return factors;
    }

    private static ArrayList<Boolean> deliberateAllPrimes(int higher) {
        ArrayList<Boolean> allPrimes =
                new ArrayList<>(Arrays.asList(new Boolean [higher + 1]));
        Collections.fill(allPrimes, true);
        allPrimes.set(0, false); // 0 == not prime
        allPrimes.set(1, false);

        boolean even = false;
        for (int i = 3; i <= Math.sqrt(higher); i++) {
            if (even) {
                allPrimes.set(i, false);
                even = false;
                continue;
            } else if (allPrimes.get(i)) { // if i is prime
                int multiplier = 2;
                while (i * multiplier <= higher) {
                    allPrimes.set(i * multiplier, false);
                    multiplier++;
                }
            }
            even = true;
        }
        even = (int) Math.sqrt(higher) % 2 == 0;
        for (int i = (int) Math.sqrt(higher); i <= higher + 1; i++) {
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