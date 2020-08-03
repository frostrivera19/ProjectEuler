// Java 14.0.1
// frostrivera19
// Project Euler Problem 30 : Digit fifth powers
// Find the sum of all the numbers that can be written as the sum of fifth
// powers of their digits.
// https://projecteuler.net/archives
// Solved 31 May 2020


public class Main {

    public static void main(String[] args) {
        int ans = sumCanPower(5);
        System.out.println("ans = " + ans);
    }

    private static int sumCanPower(final int power) {
        final int START = 2;
        final int FINAL = (int) Math.pow(10, power + 1);

        int sum = 0;
        for (int num = START; num <= FINAL; num++) {
            if (canPower(num, power)) {
                sum += num;
                System.out.println(num);
            }
        }
        return sum;
    }

    private static boolean canPower(final int num, final int power) {
        int sum = 0;
        for (int i : intToArray(num)) {
            sum += Math.pow(i, power);
        }
        return sum == num;
    }

    private static int[] intToArray(final int i) {
        int length = (String.valueOf(i)).length();
        int[] arr = new int[length];
        int number = i;
        for (int j = length - 1; j >= 0; j--) {
            arr[j] = number % 10;
            number /= 10;
        }
        return arr;
    }
}
