// Java 14.0.1
// frostrivera19
// Project Euler Problem 12 : Large sum
// https://projecteuler.net/archives
// Solved 18 May 2020

package transport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        long ans = countSum("Question 13 Text.txt", 10);
        System.out.println(ans);
    }

    public static long countSum(String filename, int firstHowMany) {
        long firstDigitsSum = 0;
        try {
            File file = new File(filename);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String thisLine = scan.nextLine();
                int extension = 17 - firstHowMany;
                if (extension <= 0) {
                    System.out.println("Too Much Digits Requested.");
                    return -1;
                }
                if (thisLine.length() > firstHowMany + extension) {
                    thisLine = thisLine.substring(0, firstHowMany + extension);
                }
                long thisNum = Long.parseLong(thisLine);
                firstDigitsSum += thisNum;
            }
        } catch (FileNotFoundException foe) {
            System.out.println(foe);
        }

        String ans = String.valueOf(firstDigitsSum).substring(0, firstHowMany);
        return Long.parseLong(ans);

    }
}