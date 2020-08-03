// Java 14.0.1
// frostrivera19
// Project Euler Problem 12 : Highest divisible triangular number
// https://projecteuler.net/archives
// Solved 18 May 2020

package transport;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) {
        System.out.println(highestTriNumber(500, 2000000));
    }

    public static int highestTriNumber(int numOfDivisors, int primeLimit) {
        int i = 1;

        int sumTorial = i;
        ArrayList<Boolean> allPrimes = deliberateAllPrimes(primeLimit);
        int numberOfFactors = numFactors(
                new BigInteger(String.valueOf(sumTorial)), allPrimes);
        while (numberOfFactors < numOfDivisors) {
            sumTorial = sumTorial + (i + 1);
            i++;
            numberOfFactors = numFactors(
                    new BigInteger(String.valueOf(sumTorial)), allPrimes);
        }
        return sumTorial;
    }

    public static int numFactors(BigInteger number,
                                 ArrayList<Boolean> allPrimes) {


        BigInteger num = number;
        BigInteger pDivisor = BigInteger.TWO;
        HashMap<BigInteger, Integer> primeFactors = new HashMap<>();

        int count = 1;
        while (num.remainder(pDivisor).equals(BigInteger.ZERO)) {
            primeFactors.put(pDivisor, count);
            count++;
            num = num.divide(pDivisor);
        }
        pDivisor = new BigInteger("3");
        while (num.compareTo(BigInteger.ONE) > 0) {
            count = 1;
            while (num.remainder(pDivisor).equals(BigInteger.ZERO)) {
                primeFactors.put(pDivisor, count);
                count++;
                num = num.divide(pDivisor);
            }

            pDivisor = pDivisor.add(BigInteger.TWO);
            while (!allPrimes.get(pDivisor.intValue())) {
                // get to the next prime factor
                pDivisor = pDivisor.add(BigInteger.TWO);
            }

        }

        int numberOfFactors = 1;
        for (int occurrence : primeFactors.values()) {
            numberOfFactors *= occurrence + 1;
        }
        return numberOfFactors;
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
