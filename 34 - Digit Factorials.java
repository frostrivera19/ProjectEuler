package transport;

/*
* Java 14.0.1
frostrivera19
Project Euler Problem 34 : Digit factorials
https://projecteuler.net/archives
Solved 23 Sep 2020
* */

/*
145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

Find the sum of all numbers which are equal to the sum of the factorial of their digits.
* */


import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        ArrayList<Integer> fact = new ArrayList<>();
        fact.add(1); // 0!
        fact.add(1); // 1!

        HashSet<Integer> current = new HashSet<>();
        ArrayList<Integer> valid = new ArrayList<>();

        int adds = 11;
        int copy = adds;
        int sumFact = 0;

        A: while (true) {
            if (adds % 1000 == 0) {
                System.out.println("now = " + adds);
            }
            while (copy > 0) {
                int thisDigit = copy % 10;
                current.add(thisDigit);
                sumFact += factorial(thisDigit, fact);
                if (Math.abs(len(sumFact) - len(adds)) > 5) {
                    break A;
                }
                copy /= 10;
            }
            if (adds == sumFact) {
                System.out.println("valid = " + adds);
                valid.add(adds);
            }
            adds++;
            copy = adds;
            sumFact = 0;
            current.clear();
        }

        System.out.println(valid);



    }

    private static int factorial(int n, ArrayList<Integer> fact) {
        if (fact.size() < n + 1) {
            // does not exist in table
            fact.add(n, n * factorial(n - 1, fact));
        }
        return fact.get(n);
    }

    private static int len(int number) {
        if (number < 100000) {
            if (number < 100) {
                if (number < 10) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                if (number < 1000) {
                    return 3;
                } else {
                    if (number < 10000) {
                        return 4;
                    } else {
                        return 5;
                    }
                }
            }
        } else {
            if (number < 10000000) {
                if (number < 1000000) {
                    return 6;
                } else {
                    return 7;
                }
            } else {
                if (number < 100000000) {
                    return 8;
                } else {
                    if (number < 1000000000) {
                        return 9;
                    } else {
                        return 10;
                    }
                }
            }
        }
    }



}