# Python 3.8
# frostrivera19
# Project Euler Problem 6 : Sum square difference
# https://projecteuler.net/archives

def diff(n):
    return allSquared(n) - eachSquared(n)

def eachSquared(n):
    sum = 0
    for i in range(1, n + 1):
        sum += i * i
    return sum

def allSquared(n):
    sum = 0
    for i in range(1, n + 1):
        sum += i
    return sum * sum

print(diff(100))