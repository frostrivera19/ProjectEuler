# Ruby 2.6.4
# frostrivera19
# Project Euler Problem 27 : Quadratic Primes
# Find the product of the coefficients, a and b, for the quadratic expression
# that produces the maximum number of primes for consecutive values of n,
# starting with n=0.
# https://projecteuler.net/archives
# Solved 11 Jun 2020

def main()
    puts maxNumOfPrimes(1000)
end

def maxNumOfPrimes(limit)
    primeLimit = limit ** 2 + limit * limit + limit
    primes = deliberateAllPrimes(primeLimit)
    maxNumOfPrimes = 0
    maxA = 0
    maxB = 0
    negLimit = -1 * limit
    for a in negLimit..limit
        for b in negLimit..limit
            numOfPrimes = numOfPrimes(primes, a, b)
            if numOfPrimes >= maxNumOfPrimes
                puts ("a = " + a.to_s + " b = " + b.to_s + " numOfPrimes = " + numOfPrimes.to_s)
                maxNumOfPrimes = numOfPrimes
                maxA = a
                maxB = b
            end
        end
    end
    return maxA * maxB
end

def numOfPrimes(primes, a, b)
    numOfPrimes = 0
    n = 0
    product = n ** 2 + a * n + b
    while primes[product]
        n += 1
        product = n ** 2 + a *n + b
        numOfPrimes += 1
    end
    return numOfPrimes
end

def deliberateAllPrimes(limit)
    primes = Array.new(limit + 1, true)

    for i in 2..Math.sqrt(limit)
        if !primes[i]
            next
        end
        multiplier = 2
        primeMultiple = i * multiplier
        while primeMultiple <= limit
            primes[primeMultiple] = false
            multiplier += 1
            primeMultiple = i * multiplier
        end
    end

    return primes
end

main()
