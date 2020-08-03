from math import sqrt

def deliberateAllPrimes(limit):
    allPrimes = [True for i in range(limit + 1)]
    allPrimes[0] = False
    allPrimes[1] = False
    for i in range(2, int((sqrt(limit + 1) + 1))):
        multiplier = 2
        currentIndex = multiplier * i
        while currentIndex <= limit:
            allPrimes[currentIndex] = False
            multiplier += 1
            currentIndex = multiplier * i
    return allPrimes