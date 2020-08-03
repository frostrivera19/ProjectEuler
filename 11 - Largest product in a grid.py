# Python 3.8
# frostrivera19
# Project Euler Problem 11 : Largest product in a grid
# https://projecteuler.net/archives
# Solved 16 May 2020

from functools import reduce

def largestProduct(bigArr, numCount):
    biggest = 1
    biggestH = []
    biggestV = []
    biggestD = []
    biggestDU = []
    biggestHPending = []
    biggestVPending = []
    biggestDPending = []
    biggestDUPending = []
    for line in range(len(bigArr) - numCount):
        for num in range(len(bigArr[line]) - numCount):
            horizontal = 1
            for countH in range(numCount):
                horizontal *= bigArr[line][num + countH]
                biggestHPending.append(bigArr[line][num + countH])

            vertical = 1
            for countV in range(numCount):
                vertical *= bigArr[line + countV][num]
                biggestVPending.append(bigArr[line + countV][num])

            diagonal = 1
            for countD in range(numCount):
                diagonal *= bigArr[line + countD][num + countD]
                biggestDPending.append(bigArr[line + countD][num + countD])

            diagonalUp = 1
            if line >= numCount - 1:
                for countDU in range(numCount):
                    diagonalUp *= bigArr[line + countDU][num - countDU]
                    biggestDUPending.append(
                        bigArr[line + countDU][num - countDU]
                    )


            thisNumbersBiggest = max(horizontal, vertical, diagonal, diagonalUp)
            if thisNumbersBiggest > biggest:
                biggest = thisNumbersBiggest
                biggestV = biggestVPending
                biggestH = biggestHPending
                biggestD = biggestDPending
                biggestDU = biggestDUPending
            biggestVPending = []
            biggestHPending = []
            biggestDPending = []
            biggestDUPending = []

        for numContinued in range(len(bigArr[line]) - numCount,
                                  len(bigArr[line])):
            vertical = 1
            for countV in range(numCount):
                vertical *= bigArr[line + countV][numContinued]
                biggestVPending.append(bigArr[line + countV][numContinued])
            if vertical > biggest:
                biggest = vertical
                biggestV = biggestVPending
            biggestVPending = []

    for lineContinued in range(len(bigArr) - numCount, len(bigArr)):
        for numCContinued in range(len(bigArr) - numCount):
            horizontal = 1
            for countH in range(numCount):
                horizontal *= bigArr[lineContinued][numCContinued + countH]
                biggestHPending.append(bigArr[lineContinued][numCContinued
                                                             + countH])
            if horizontal > biggest:
                biggest = horizontal
                biggestH = biggestHPending
            biggestHPending = []

    if reduce(lambda x, y: x * y, biggestV) == biggest:
        print("BiggestV = " + str(biggestV))
    elif reduce(lambda x, y: x * y, biggestH) == biggest:
        print("BiggestH = " + str(biggestH))
    elif reduce(lambda x, y: x * y, biggestD) == biggest:
        print("BiggestD = " + str(biggestD))
    elif reduce(lambda x, y: x * y, biggestDU) == biggest:
        print("BiggestDU = " + str(biggestDU))
    return biggest

bigArray = []
f = open("Question 11 Grid.txt", "r") # input.txt contains the 20 x 20 int grid
for line in f.readlines():
    if line == "\n":
        continue
    thisLine = []
    aNumber = ""
    for char in line:
        if char != " ":
            aNumber += str(char)
        else:
            thisLine.append(int(aNumber))
            aNumber = ""
    thisLine.append((int(aNumber)))
    bigArray.append(thisLine)

f.close()



print(largestProduct(bigArray, 4))
