// C Program (C99)
// frostrivera19
// Project Euler Problem 24: Lexicographic permutations
// Q24: What is the millionth lexicographic permutation of the digits 0, 1, 2,
// 3, 4, 5, 6, 7, 8 and 9?
// https://projecteuler.net/archives
// Solved 29 May 2020


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

long process(long index, int digits);
long fact(int i);
bool contains(const int *arr, int toFind, int blocks);
void sort(char **arr, int blocks);
void swap(char **str1, char **str2);

int main() {
    long ans = process(1000000, 10);
    printf("%ld", ans);
    return 0;
}

long process(long index, int digits) {
    long ans = 0;
    if (digits != 10) {
        return -1;
    }

    long currentIndex = 0;

    char **lastTwo = malloc(2 * sizeof(char *));

    int factDivisor = digits - 1;
    int addCount = 0;
    long toAdd = fact(factDivisor);
    int visited[digits];

    for (int i = 0; i < digits; i++) {
        visited[i] = 0;
    }

    while (currentIndex < index) {
        while (currentIndex + toAdd < index) {
            currentIndex += toAdd;
            do {
                addCount++;
            } while (contains(visited, addCount, digits));
        }
        if (index - currentIndex == 1) {
            int unvisited[2] = {0, 0};
            int ind = 0;
            for (int i = 1; i < digits; i++) {
                if (!contains(visited, i, digits)) {
                    unvisited[ind] = i;
                    ind++;
                }
            }

            long first = ans + unvisited[0] * (int) pow(10, factDivisor);
            long second = ans + unvisited[0] * (int) pow(10, factDivisor - 1);
            char *firstStr = malloc((digits + 5) * sizeof(char));
            char *secondStr = malloc((digits + 5) * sizeof(char));


            sprintf(firstStr, "%ld", first);
            sprintf(secondStr, "%ld", second);

            lastTwo[0] = firstStr;
            lastTwo[1] = secondStr;

            sort(lastTwo, 2);
            char *end;

            long finalAns = strtol(lastTwo[1], &end, 10);
            free(lastTwo[0]);
            free(lastTwo[1]);
            free(lastTwo);
            return finalAns;

        } else {
            ans += addCount * (long) pow(10, factDivisor);

            visited[digits - factDivisor - 1] = addCount;

            factDivisor--;
            toAdd = fact(factDivisor);
            addCount = 0;
        }
    }

    return 10;
}

void swap(char **str1, char **str2) {
    char *temp = *str1;
    *str1 = *str2;
    *str2 = temp;
}

void sort(char **arr, int blocks) {
    for (int i = 0; i < blocks; i++) {
        for (int j = 0; j < (blocks - i - 1); j++) {
            if (strcmp(arr[j], arr[j + 1]) > 0) {
                swap(&(arr[j]), &(arr[j + 1]));
            }
        }
    }
}

long fact(int i) {
    if (i == 1) {
        return 1;
    } else {
        return i * fact(i - 1);
    }
}

bool contains(const int *arr, int toFind, int blocks) {
    for (int i = 0; i < blocks; i++) {
        if (arr[i] == toFind) {
            return true;
        }
    }
    return false;
}