// Java 14.0.1
// frostrivera19
// Project Euler Problem 16 : Power Digit Sum
// https://projecteuler.net/archives
// Solved 19 May 2020

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sum Of Digits = " + sumOfDigits(1000));

    }

    private static int sumOfDigits(int pow) {
        BigInteger num = BigInteger.TWO.pow(pow);
        int sumOfDigits = 0;
        String numInString = num.toString();
        for (char digit : numInString.toCharArray()) {
            sumOfDigits += Integer.parseInt(Character.toString(digit));
        }
        return sumOfDigits;
    }


}