// Java 14.0.1
// frostrivera19
// Project Euler Problem 17 : Number letter counts
// https://projecteuler.net/archives
// Solved 19 May 2020

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(countLetters(1000));
    }

    private static int countLetters(int limit) {
        int[] letters = new int[limit + 1];
        try {
            letters[0] = 0;

            letters[1] = 3;
            letters[2] = 3;
            letters[3] = 5;
            letters[4] = 4;

            letters[5] = 4;
            letters[6] = 3;
            letters[7] = 5;
            letters[8] = 5;

            letters[9] = 4;
            letters[10] = 3;
            letters[11] = 6; //eleven
            letters[12] = 6; //twelve

            letters[13] = 8; //thirteen
            letters[14] = letters[4] + 4; // fourteen
            letters[15] = 7; //fifteen
            letters[16] = letters[6] + 4; //sixteen

            letters[17] = letters[7] + 4; //seventeen
            letters[18] = letters[8] + 3; // eight + een
            letters[19] = letters[9] + 4;
            letters[20] = 6; // twenty

            letters[30] = 6; // thirty
            letters[40] = 5; // forty
            letters[50] = 5; // fifty
            letters[60] = letters[6] + 2; // six + ty

            letters[70] = letters[7] + 2;
            letters[80] = letters[8] + 1; // eight + y
            letters[90] = letters[9] + 2;

            int and = 3;
            int hundred = 7;
            int thousand = 8;

            for (int i = 21; i <= 99; i++) {
                if (i % 10 != 0) { // not 30, 40, 50, ..., 90
                    letters[i] = letters[i % 10] + letters[i / 10 * 10];
                }
            }

            for (int i = 100; i <= 999; i++) {
                if (i % 100 == 0) {
                    letters[i] = letters[i / 100] + hundred;
                } else {
                    letters[i] = letters[i / 100] + hundred + letters[i % 100]
                            + and;
                }
            }

            letters[1000] = letters[1] + thousand;
        } catch (ArrayIndexOutOfBoundsException aoe) {
            // do nothing
        }

        int sum = 0;
        for (int i = 1; i <= limit; i++) {
            sum += letters[i];
        }
        return sum;

    }


}