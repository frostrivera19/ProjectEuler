// Java 14.0.1
// frostrivera19
// Project Euler Problem 25: 1000-digit Fibonacci number
// What is the index of the first term in the Fibonacci sequence to contain
// 1000 digits?
// https://projecteuler.net/archives
// Solved 28 May 2020

import java.math.BigInteger;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int ans = fibDigits(1000);
        System.out.println(ans);
    }

    private static int fibDigits(int digits) {
        ArrayList<BigInteger> fibSequence = new ArrayList<>();
        fibSequence.add(0, BigInteger.ZERO);
        fibSequence.add(1, BigInteger.ONE);
        fibSequence.add(2, BigInteger.ONE);

        int index = 2;
        BigInteger ans;
        do {
            ans = fib(index, fibSequence);
            index++;
        } while (ans.toString().length() < digits);

        index -= 1;

        return index;
    }

    private static BigInteger fib(int index, ArrayList<BigInteger> array) {
        try {
            return array.get(index);
        } catch (IndexOutOfBoundsException aoe) {
            BigInteger first = fib(index - 1, array);

            BigInteger second = fib(index - 2, array);

            BigInteger fibThisIndex = first.add(second);
            array.add(index, fibThisIndex);
            return fibThisIndex;
        }
    }
}