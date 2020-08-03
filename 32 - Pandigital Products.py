# // Python 3.8
# // frostrivera19
# // Project Euler Problem 32 : Pandigital Products
# // https://projecteuler.net/archives
# // Solved 28 Jul 2020

# We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.

# The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

# Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.

def main():
    run()


def run():
    eligible = {0}
    digits = [[1, 4], [2, 3], [3, 3], [2, 4]] # got through running findDigits()
    unit = 0

    while unit < len(digits):
        for i in range(10 ** (digits[unit][0] - 1), 10 ** digits[unit][0]):
            for j in range(10 ** (digits[unit][1] - 1), 10 ** digits[unit][1]):
                product = i * j
                allDigits = {0}
                iDigits = digitsOfNum(i)
                jDigits = digitsOfNum(j)
                prodDigits = digitsOfNum(product)

                allDigits.update(iDigits)
                allDigits.update(jDigits)
                allDigits.update(prodDigits)
                allDigits.remove(0)

                if len(iDigits) + len(jDigits) + len(prodDigits) == 9 and \
                        len(allDigits) == 9:
                    print(str(i) + " " + str(j) + " " + str(product))
                    eligible.add(product)
        unit += 1

    print(sum(eligible))




def digitsOfNum(num):
    digits = []
    while num > 0:
        digits.append(num % 10)
        num //= 10
    return digits




def findDigits():
    a, b = 1, 1
    while a <= 9:
        while b <= 9:
            find(a, b)
            b += 1
        b = 1
        a += 1


def find(a, b):
    minDigits = max(a, b) + (a + b)
    maxDigits = a + b + (a + b)

    if minDigits <= 10 and maxDigits >= 10:
        print(str(a) + " " + str(b) + " digits: " + str(minDigits) + " "
              + str(maxDigits))




main()
