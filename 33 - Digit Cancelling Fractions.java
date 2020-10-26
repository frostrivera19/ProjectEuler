package transport;

/*
* Python 3.8
frostrivera19
Project Euler Problem 33 : Digit cancelling fractions
https://projecteuler.net/archives
Solved 22 Sep 2020
* */

/*
* The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.

We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.

If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
* */

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        run();
    }

    private static int run() {

        int max = 99;

        double[][] table = new double[max][max];

        ArrayList<int[]> arr = new ArrayList<>();

        for (int i = 10; i <= max; i++) {
            for (int j = 10; j <= max; j++) {
                int a = Integer.parseInt(String.valueOf(i).substring(0, 1));
                int b = Integer.parseInt(String.valueOf(i).substring(1, 2));
                int c = Integer.parseInt(String.valueOf(j).substring(0, 1));
                int d = Integer.parseInt(String.valueOf(j).substring(1, 2));

                if (i % 10 == 0 && j % 10 == 0) {
                    continue;
                } else if (i % 11 == 0 && j % 11 == 0) {
                    continue;
                } else if (i == j) {
                    continue;
                } else if ((double) i / j > 1) {
                    continue;
                }

                if (a == c) {
                    fillUpTable(table, b, d);
                    if ((double) i / j == table[b][d]) {
                        arr.add(new int[]{i, j});
                    }
                }
                else if (a == d) {
                    fillUpTable(table, b, c);
                    if ((double) i / j == table[b][c]) {
                        arr.add(new int[]{i, j});
                    }
                }
                else if (b == c) {
                    fillUpTable(table, a, d);
                    if ((double) i / j == table[a][d]) {
                        arr.add(new int[]{i, j});
                    }
                }
                else if (b == d) {
                    fillUpTable(table, a, c);
                    if ((double) i / j == table[a][c]) {
                        arr.add(new int[]{i, j});
                    }
                }
            }
        }

        int multNum = 1;
        int multDen = 1;

        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));

            int num = ints[0];
            int den = ints[1];

            multNum *= num;
            multDen *= den;
        }

        int gcd = gcd(multNum, multDen);

        System.out.println(multDen / gcd);

        return multDen / gcd;


    }

    private static void fillUpTable(double[][] table, int x, int y) {
        if (table[x][y] == 0) {
            table[x][y] = (double) x / y;
        }
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }



}