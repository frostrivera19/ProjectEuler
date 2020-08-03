// Java 14.0.1
// frostrivera19
// Project Euler Problem 22 : Names scores
// What is the total of all the name scores in the file?
// https://projecteuler.net/archives
// Solved 29 May 2020

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        long ans = nameScore("Question 22 Text.txt");
        System.out.println(ans);
    }

    private static long nameScore(String filename) {

        ArrayList<String> records = getNames(filename);
        Collections.sort(records);
        final int ASCII_A = 65;

        long sum = 0;
        int index = 0;

        for (String name : records) {
            int thisName = 0;
            for (char c : name.toCharArray()) {
                thisName += (int) c - ASCII_A + 1;
            }
            sum += (index + 1) * thisName;

            if (name.equals("COLIN")) {
                System.out.println(index + 1);
                System.out.println(thisName);
            }
            index++;
        }

        return sum;
    }

    private static ArrayList<String> getNames(String filename) {
        File file = new File(filename);
        final String COMMA_DELIMITER = "\",\"";
        Scanner scan;
        ArrayList<String> records = new ArrayList<>();
        try {
            scan = new Scanner(file);
            scan.useDelimiter(COMMA_DELIMITER);
            while (scan.hasNext()) {
                records.add(scan.next());
            }
            scan.close();
        } catch (FileNotFoundException foe) {
            System.err.println("File Not Found.");
        }

        records.set(0, records.get(0).substring(1));
        int lastInd = records.size() - 1;
        String lastName = records.get(lastInd);
        records.set(lastInd, lastName.substring(0, lastName.length() - 1));

        return records;
    }
}