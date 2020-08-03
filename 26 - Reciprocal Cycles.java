// Java 14.0.1
// frostrivera19
// Project Euler Problem 26 : Reciprocal Cycles
// Find the value of d < 1000 for which 1/d contains the longest recurring cycle
// in its decimal fraction part.
// https://projecteuler.net/archives
// Solved 10 Jun 2020

package transport;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        int ans = longestRecurringCycle(1000);
        System.out.println("\n------\nans = " + ans);
    }

    private static int longestRecurringCycle(int limit) {
        int maxRecurLength = 0;
        int maxD = -1;
        final BigInteger BIG_POWER_OF_10 = BigInteger.TEN.pow(10000);

        for (int i = 2; i <= limit; i++) {
            BigInteger limitBI = new BigInteger(String.valueOf(i));
            BigInteger inverse = BIG_POWER_OF_10.divide(limitBI);
            String inverseStr = inverse.toString();
            int recurLength = recurringLength(inverseStr);
            if (recurLength > maxRecurLength) {
//                System.out.println("\ndivisor=" + i + " recurLength="
//                        + recurLength + "\n");
                maxRecurLength = recurLength;
                maxD = i;
            }
        }
        return maxD;
    }

    private static int recurringLength(String value) {

        for (int i = 1; i <= value.length() / 2; i++) {
            if (checkRecurring(value, i)) {
//                System.out.println(value + " " + i);
                return i;
            }
        }

        return 0;
    }

    private static boolean checkRecurring(String value, int lengthEquiv) {
        int multiplier = 0;
        while ((multiplier + 2) * lengthEquiv <= value.length()) {
            String first = value.substring(multiplier * lengthEquiv,
                    (multiplier + 1) * lengthEquiv);
            String second = value.substring((multiplier + 1) * lengthEquiv,
                    (multiplier + 2) * lengthEquiv);
            if (!first.equals(second)) {
                return false;
            }
            multiplier++;
        }
        return true;
    }

}
