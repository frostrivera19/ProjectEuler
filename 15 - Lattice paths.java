// Java 14.0.1
// frostrivera19
// Project Euler Problem 15 : Lattice paths
// https://projecteuler.net/archives
// Solved 19 May 2020

/*
    Starting in the top left corner of a 2×2 grid, and only being able to move
    to the right and down, there are exactly 6 routes to the bottom right
    corner.
    How many such routes are there through a 20×20 grid?

    Solution: factorial(2 * sideLength) /
                (long) Math.pow(factorial(sideLength), 2);
    */

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println(possibilities(20));
    }


    private static BigInteger possibilities(int sideLength) {
        BigInteger x = new BigInteger(String.valueOf(sideLength));
        BigInteger twoX = x.multiply(BigInteger.TWO);
        BigInteger twoXFactorial = factorial(twoX);
        BigInteger xFactorial = factorial(x);
        BigInteger xFactorialSquared = xFactorial.pow(2);
        return twoXFactorial.divide(xFactorialSquared);
    }

    private static BigInteger factorial(BigInteger x) {
        if (x.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        } else {
            BigInteger lessOne = x.add(new BigInteger("-1"));
            return x.multiply(factorial(lessOne));
        }
    }

}