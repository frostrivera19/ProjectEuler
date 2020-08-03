# Python 3.8
# frostrivera19
# Project Euler Problem 2 : Even Fibonacci numbers
# https://projecteuler.net/archives

def fib(n):
    if n == 0:
        return 1
    elif n == 1:
        return 1
    else:
        return fib(n - 1) + fib(n - 2)

def fibSum(n):
    sum = 0
    for i in range(1, n):
        fibx = fib(i)
        if fibx > n:
            break
        if fibx % 2 == 0:
            print(sum)
            sum += fibx
    return sum

print(fibSum(4000000))