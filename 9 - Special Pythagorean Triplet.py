# Python 3.8
# frostrivera19
# Project Euler Problem 9 : Special Pythagorean Triplet
# https://projecteuler.net/archives
# Solved 15 May 2020

def pythagTrip(n):
    origA = 1
    origB = 2
    origC = 3
    a = origA
    b = origB
    c = origC
    while a < n - 3:
        while b < c < n:
            while c < n:
                if a + b + c == 1000:
                    if a < b < c:
                        if a ** 2 + b ** 2 == c ** 2:
                            return a * b * c
                c += 1

            origC += 1
            c = origC
            b += 1
            print(str(a) + " " + str(b) + " " + str(c))

        origB += 1
        b = origB
        origC = origB + 1
        c = origC
        a += 1
    return None


print(pythagTrip(1000))