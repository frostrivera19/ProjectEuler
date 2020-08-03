# Python 3.8
# frostrivera19
# Project Euler Problem 4 : Largest palindrome product
# https://projecteuler.net/archives
# Solved 15 May 2020

# @param digitNumber number of digits for
# each number to be multilpled
def largestPalindrome(digitNumber):
    lowerLimit = 10 ** (digitNumber - 1)
    higherLimit = 10 ** digitNumber
    largestMultipliers = [0, 0]
    max = 0
    for i in range(higherLimit, lowerLimit, -1):
        for j in range(higherLimit, lowerLimit, -1):
            if isPalindrome(i * j) and (i * j > max):
                largestMultipliers[0] = i
                largestMultipliers[1] = j
                max = i * j
    return max

def numberToArray(n):
    # e.g. numberToArray(321) -> [321]
    digits = []
    while (n > 0):
        digits.append(n % 10)
        n = n // 10
    digits.reverse()
    return digits

def isPalindrome(n):
    digits = numberToArray(n)
    for i in range(len(digits) // 2):
        if digits[i] != digits[len(digits) - i - 1]:
            return False
    return True

arr = largestPalindrome(3)
print(arr)