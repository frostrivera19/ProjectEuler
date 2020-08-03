// Java 14.0.1
// frostrivera19
// Project Euler Problem 31 : Coin sums
// https://projecteuler.net/archives
// Solved 21 Jul 2020

package transport;

/*
* In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins in general circulation:

1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).
It is possible to make £2 in the following way:

1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
How many different ways can £2 be made using any number of coins?
* */

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(run());
    }

    private static int run() {
        int[] combo = new int[8];
        int possibles = 0;

        for (int i1 = 0; i1 <= 200; i1++) {
            for (int i2 = 0; i2 <= 100; i2++) {
                for (int i3 = 0; i3 <= 40; i3++) {
                    for (int i4 = 0; i4 <= 20; i4++) {
                        for (int i5 = 0; i5 <= 10; i5++) {
                            for (int i6 = 0; i6 <= 4; i6++) {
                                for (int i7 = 0; i7 <= 2; i7++) {
                                    for (int i8 = 0; i8 <= 1; i8++) {
                                        combo[0] = i1;
                                        combo[1] = i2;
                                        combo[2] = i3;
                                        combo[3] = i4;
                                        combo[4] = i5;
                                        combo[5] = i6;
                                        combo[6] = i7;
                                        combo[7] = i8;

                                        int thisCombo = sum(combo);
                                        if (thisCombo == 200) {
                                            System.out.println(
                                                    Arrays.toString(combo));
                                            possibles += 1;
                                        } else if (thisCombo > 200) {
                                            break;
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return possibles;



    }

    private static int sum(int[] combo) {
        int sum = 0;
        sum += combo[0];
        sum += combo[1] * 2;
        sum += combo[2] * 5;
        sum += combo[3] * 10;
        sum += combo[4] * 20;
        sum += combo[5] * 50;
        sum += combo[6] * 100;
        sum += combo[7] * 200;

        return sum;
    }
}