// Java 14.0.1
// frostrivera19
// Project Euler Problem 14 : Largest Collatz Sequence
// https://projecteuler.net/archives
// Solved 19 May 2020

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Longest = " + longestCollatz(1000000));

    }

    private static long longestCollatz(int starterLimit) {
        HashMap<Long, Integer> collatzLengths = new HashMap<>(starterLimit);
        long[] maxChain = {0, 0};
        for (long i = 1; i < starterLimit; i++) {
            // note: starting at the square root of the starterLimit is just
            // a guess without strong grounds.
            // Fill up the hashmap with all collatz chain lengths for
            // starting number i and keep track of the longest chain
            int lengthOfChain = collatzLength(i, collatzLengths);
            collatzLengths.put(i, lengthOfChain);
            if (lengthOfChain > maxChain[1]) {
                maxChain = new long[]{i, lengthOfChain};
            }
//            System.out.println("Testing start number: " + i);
        }
        return maxChain[0];
    }


    private static int collatzLength(long i,
                                  HashMap<Long, Integer> collatzLengths) {

        if (collatzLengths.containsKey(i)) {
            return collatzLengths.get(i);
        }

        if (i == 1) {
            return 1;
        } else {
            if (i % 2 == 0) {
                return 1 + collatzLength(i / 2, collatzLengths);
            } else {
                return 1 + collatzLength(3 * i + 1, collatzLengths);
            }
        }
    }
}