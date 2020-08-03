# Python 3.8
# frostrivera19
# Project Euler Problem 1 : Multiples of 3 and 5
# https://projecteuler.net/archives

def count(n):
    sum = 0
    for i in range(1, n):
        if i % 3 == 0:
            sum += i
        elif i % 5 == 0:
            sum += i
    return sum