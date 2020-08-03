# Python 3.8
# frostrivera19
# Project Euler Problem 5 : Smallest multiple
# https://projecteuler.net/archives

def smallestDivisible(limit):
    current = 20
    while True:
        if allDivisible(current, limit):
            return current
        current += 20

def allDivisible(n, limit):
    for i in range(1, limit + 1):
        if n % i != 0:
            return False
    return True

print(smallestDivisible(20))