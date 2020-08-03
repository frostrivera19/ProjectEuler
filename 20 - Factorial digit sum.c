// C Program (C99)
// frostrivera19
// Project Euler Problem 20: Factorial digit sum
// Q20: Find the sum of the digits in the number 100!
// https://projecteuler.net/archives
// Solved 23 May 2020

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

int * doubleStorage(int ** array, int initialBlocks);
void printArray(int ** array, int blocks);
int sumFactorialDigitSum(int limit);
int multiply2(int **array, int multiplier, int blocks);

int main() {

    int ans = sumFactorialDigitSum(100);
    printf("%d", ans);

    return 0;
}

int sumFactorialDigitSum(int limit) {

    if (limit < 0) {
        return -1;
    } else if (limit == 0 || limit == 1) {
        return 1;
    }

    int blocks = 2;
    int * digitsOfFactorial = malloc(blocks * sizeof (int));
    digitsOfFactorial[0] = 0;
    digitsOfFactorial[1] = 1;
    for (int i = 2; i <= limit; i++) {
        blocks = multiply2(&digitsOfFactorial, i, blocks);
    }

    int sum = 0;
    for (int i = 0; i < blocks; i++) {
        sum += digitsOfFactorial[i];
    }

    printArray(&digitsOfFactorial, blocks);
    free(digitsOfFactorial);
    return sum;
}


bool firstFewDigits0(int **array, int blocks) {
    int fewDigits = 5;
    if (blocks < fewDigits) {
        return false;
    }

    for (int i = 0; i < fewDigits; i++) {
        if ((*array)[i] != 0) {
            return false;
        }
    }
    return true;
}


int multiply2(int **array, int multiplier, int blocks) {
    if (!firstFewDigits0(array, blocks)) {
        *array = doubleStorage(array, blocks);
        return multiply2(array, multiplier, 2 * blocks);
    }

    int *additionArray = calloc(blocks, sizeof(int));
    for (int i = blocks - 1; i >= 0; i--) {
        if ((*array)[i] * multiplier >= 10) {
            (additionArray)[i - 1] += (*array)[i] * multiplier / 10;
            (*array)[i] = (*array)[i] * multiplier % 10;
        } else {
            (*array)[i] = (*array)[i] * multiplier;
        }
        (*array)[i] += (additionArray)[i];
        (additionArray)[i] = 0;
        if ((*array)[i] >= 10) {
            (additionArray)[i - 1] += (*array)[i] / 10;
            (*array)[i] = (*array)[i] % 10;
        }
//        printArray(array, blocks);
//        printArray(&additionArray, blocks);
//        printf("\n");
    }

    free(additionArray);
    return blocks;
}

int * doubleStorage(int ** array, int initialBlocks) {
    int * more = realloc(*array, 2 * initialBlocks * sizeof(int));
    if (more == NULL) {
        free(*array);
    } else {
        *array = more;
        for (int i = 0; i < initialBlocks; i++) {
            int num = (*array)[i];
            (*array)[i + initialBlocks] = num;
            (*array)[i] = 0;
        }
    }
    return *array;
}

void printArray(int ** array, int blocks) {
    printf("Array: ");
    for (int i = 0; i < blocks; i++) {
        printf("%d ", (*array)[i]);
    }
    printf("\n");
}