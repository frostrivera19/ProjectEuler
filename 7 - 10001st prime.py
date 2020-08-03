# Python 3.8
# frostrivera19
# Project Euler Problem 7 : 10001st prime
# https://projecteuler.net/archives

import math

def primeAt(n):
    if n == 1:
        return 2
    elif n == 2:
        return 3
    count = 2
    i = 3
    while True:
        if count == n:
            if isPrime(i):
                return i
        else:
            if isPrime(i):
                if (count % 1000 == 0):
                    print(str(count) + " " + str(i))
                count += 1
        i += 2


def isPrime(n):
    if n < 10:
        return n == 2 or n == 3 or n == 5 or n == 7
    else:
        divisor = 2
        while divisor != int(math.sqrt(n)):
            if n % divisor == 0:
                return False
            else:
                divisor += 1
        return True


print(primeAt(10001))