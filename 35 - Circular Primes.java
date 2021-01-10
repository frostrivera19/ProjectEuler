package transport;

/*
* Java 14.0.1
frostrivera19
Project Euler Problem 34: Circular Primes
https://projecteuler.net/archives
Solved 24 Dec 2020
* */

/*
The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.

There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.

How many circular primes are there below one million?
* */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {


    public static void main(String[] args) {
        System.out.println(run());
    }

    private static int run() {
        ArrayList<Boolean> primes = deliberateAllPrimes((int) Math.pow(10, 6));
        int count = 0;

        for (int i = 0; i < primes.size(); i++) {
            if (!primes.get(i)) {
                continue;
            }
            count += isCircularPrime(primes, i);
        }
        return count;
    }

    private static int isCircularPrime(ArrayList<Boolean> primes,
                                           int prime) {
        int[] allCircular = allCirculars(prime);
        for (int d : allCircular) {
            if (!primes.get(d)) {
                return 0;
            }
        }

        int minus = 0;
        for (int ind = 0; ind < allCircular.length; ind++) {
            primes.set(allCircular[ind], false);
            if (ind > 0 && allCircular[ind] == allCircular[ind - 1]) {
                minus = 1;
            } else {
                System.out.print(allCircular[ind] + " ");
            }
        }
        System.out.println();
        return allCircular.length - minus;
    }

    private static int[] allCirculars(int num) {
        if (num < 10) {
            return new int[]{num};
        }

        String numString = String.valueOf(num);
        int len = numString.length();
        int[] allCirculars = new int[len];

        for (int i = 0; i < len; i++) {
            int upperDigit = Character.getNumericValue(numString.charAt(0));
            if (String.valueOf(num).length() != len) {
                break;
            }
            num = num % (int) Math.pow(10, len - 1);
            num = num * 10 + upperDigit;
            allCirculars[i] = num;
            numString = String.valueOf(num);
        }
        return allCirculars;
//        String numString = String.valueOf(num);
//        ArrayList<Integer> allDigits = new ArrayList<>(numString.length());
//        int numTemp = num;
//        while (numTemp > 0) {
//            allDigits.add(numTemp % 10);
//            numTemp /= 10;
//        }
//
//        ArrayList<Integer> allCirculars =
//                new ArrayList<>(fact(numString.length()));
//        for (int i = 0; i < allDigits.size(); i++) {
//            ArrayList<Integer> allDigits2 = new ArrayList<>(allDigits.size());
//            allDigits2.addAll(allDigits);
//            StringBuilder onePossible = new StringBuilder();
//            onePossible.append(allDigits2.remove(i));
//            permutate(allDigits2, onePossible, allCirculars);
//        }
//
//        return allCirculars.stream().mapToInt(i -> i).toArray();
    }

    private static void permutate(ArrayList<Integer> allDigits2,
                                  StringBuilder onePossible,
                                  ArrayList<Integer> allCirculars) {

        if (allDigits2.size() == 0) {
            int onePoss = Integer.parseInt(onePossible.toString());
            allCirculars.add(onePoss);
            return;
        }


        for (int j = 0; j < allDigits2.size(); j++) {
            ArrayList<Integer> allDigits3 = new ArrayList<>(allDigits2.size());
            allDigits3.addAll(allDigits2);
            StringBuilder onePossible2 = new StringBuilder(onePossible.toString());
            onePossible.append(allDigits3.remove(j));
            permutate(allDigits3, onePossible, allCirculars);
            onePossible = onePossible2;
        }
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
        for (int i = (int) Math.sqrt(higher); i <= higher; i++) {
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
