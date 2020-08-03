// Java 14.0.1
// frostrivera19
// Project Euler Problem 28 : Number spiral diagonals
// What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral
// formed in the same way?
// https://projecteuler.net/archives
// Solved 31 May 2020

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int ans = sumDiagonal(1001);
        System.out.println("ans = " + ans);
    }

    private static int sumDiagonal(final int sideLength) {
        if (sideLength % 2 != 1) {
            return -1;
        }

        int[][] spiral = createSpiral(sideLength);
        assert spiral != null;

//        pArr(spiral);
        int center = sideLength / 2;
        int shift = 0; // how 'deep' to go in one row to find the diagonals
        int sum = 0;

        for (int i = 0; i < sideLength / 2; i++) { // not including center
            sum += spiral[i][i];
            sum += spiral[i][sideLength - shift - 1];
            sum += spiral[sideLength - i - 1][i];
            sum += spiral[sideLength - i - 1][sideLength - shift - 1];

            shift++;
        }
        sum += spiral[center][center];

        return sum;

    }

    private static int[][] createSpiral(final int sideLength) {

        if (sideLength % 2 != 1) {
            return null;
        }

        int[][] spiral = new int[sideLength][sideLength];

        int center = sideLength / 2;

        boolean rightDone = false;
        boolean downDone = false;
        boolean leftDone = false;
        boolean upDone = true;

        boolean cycleFirstDown = true;
        // the first down in the current cycle
        boolean firstEverDown = true;
        // first down of them all

        int x = center;
        int y = center;
        int thisBoxSide = 3;
        int consecutiveMoves = 3;
        spiral[x][y] = 1;
        boolean cycleComplete = true;

        for (int i = 2; i <= sideLength * sideLength; i++) {
            if (cycleComplete) {
                x++;
                upDone = false;
                rightDone = true;
                cycleComplete = false;
                consecutiveMoves = thisBoxSide - 2;
            } else {
                if (rightDone) {
                    y++;
                    if (firstEverDown) {
                        consecutiveMoves = thisBoxSide - 2;
                        firstEverDown = false;
                    } else if (cycleFirstDown) {
                        consecutiveMoves = 1;
                        cycleFirstDown = false;
                    }

                    if (consecutiveMoves == thisBoxSide - 2) {
                        rightDone = false;
                        downDone = true;
                        consecutiveMoves = 0;
                    } else {
                        consecutiveMoves++;
                    }
                } else if (downDone) {
                    x--;
                    if (consecutiveMoves == thisBoxSide - 2) {
                        downDone = false;
                        leftDone = true;
                        consecutiveMoves = 0;
                    } else {
                        consecutiveMoves++;
                    }
                } else if (leftDone) {
                    y--;
                    if (consecutiveMoves == thisBoxSide - 2) {
                        leftDone = false;
                        upDone = true;
                        consecutiveMoves = 0;
                    } else {
                        consecutiveMoves++;
                    }
                } else if (upDone) {
                    x++;
                    if (consecutiveMoves == thisBoxSide - 2) {
                        upDone = false;
                        rightDone = true;
                        cycleComplete = true;
                        thisBoxSide += 2;
                        cycleFirstDown = true;
                        consecutiveMoves = 0;
                    } else {
                        consecutiveMoves++;
                    }
                }
            }
            spiral[y][x] = i;
        }

        return spiral;
    }

    private static void pArr(int[][] spiral) {
        for (int[] row : spiral) {
            System.out.println(Arrays.toString(row));
        }
    }


}