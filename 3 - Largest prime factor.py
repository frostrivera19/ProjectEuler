# Python 3.8
# frostrivera19
# Project Euler Problem 3 : Largest prime factor
# https://projecteuler.net/archives

import math

def largestPrimeF(n):
    largest = 1
    divisor = 1
    number = n
    pFactors = []
    if number % 2 == 0:
        largest = 2
        number /= 2
        pFactors.append(largest)
        while (number % largest == 0):
            number = int(number / largest)
    while number > 1:
        if number % (divisor + 2) == 0:
            if isPrime(divisor + 2):
                divisor += 2
                largest = divisor
                number = int(number / largest)
                pFactors.append(largest)
                while (number % largest == 0):
                    number = int(number / largest)
        else:
            divisor += 2
    print("Prime Factors = " + str(pFactors))
    return largest


def isPrime(n):
    n = int(n // 1)
    if n < 10:
        return n == 2 or n == 3 or n == 5 or n == 7
    else:
        for i in range(2, int(math.sqrt(n) + 1)):
            if (n / i) % 1 == 0:
                return False
    return True

print(largestPrimeF(4096))