# Python 3.8
# frostrivera19
# Project Euler Problem 10 : Summation of primes
# https://projecteuler.net/archives
# Solved 15 May 2020

import math

def sumPrimes(n):
    if n < 2:
        return 0
    elif n == 2:
        return 2

    allNumberBool = [True for b in range(n + 1)]
    allNumberBool[0] = False
    allNumberBool[1] = False
    for i in range(4, n, 2):    # mark all even numbers > 2 False
        allNumberBool[i] = False
    currentPrime = 3
    while currentPrime <= math.sqrt(n):
        for i in range(currentPrime ** 2, n, currentPrime):
            if allNumberBool[i]:
                allNumberBool[i] = False
        currentPrime += 2
    sum = 2
    for index in range(3, n + 1, 2):
        if allNumberBool[index]:
            sum += index
    return sum



print(sumPrimes(2000000))
