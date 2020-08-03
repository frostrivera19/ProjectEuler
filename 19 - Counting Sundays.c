// C Program (C99)
// frostrivera19
// Project Euler Problem 19: Counting Sundays
// Q19: How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
// https://projecteuler.net/archives
// Solved 23 May 2020

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

bool isLeap(int year);
int daysInYear(int year);
bool arrContains(const int arr[], int blocks, int toFind);
enum Days firstDay(int year);

int totalSunInYears(int startYear, int endYear);
int firstSunsInMonths(int startYear, int endYear);

enum Days {
    MON = 1, TUE = 2, WED = 3,
    THU = 4, FRI = 5, SAT = 6,
    SUN = 7
};

enum DaysOnMonths {
    JAN = 31, FEB_N = 28, FEB_L = 29,
    MAR = 31, APR = 30, MAY = 31,
    JUN = 30, JUL = 31, AUG = 31,
    SEP = 30, OCT = 31, NOV = 30,
    DEC = 31
};

enum Months {
    Jan, Feb,
    Mar, Apr, May,
    Jun, Jul, Aug,
    Sep, Oct, Nov,
    Dec
};

int daysSinceEpoch(enum Months month, int endYear);
const enum Days DAY_ON_EPOCH = MON;
int getDaysOnMonths(enum Months month, int year);
int numberOfSunInYear(enum Days firstDay, int year);

// begin algorithm================================================

int main() {

    int num = firstSunsInMonths(1901, 2000);
    printf("%d", num);

    return 0;
}

// How many Sundays fell on the first of the month from 01 Jan <startYear> to 31 Dec <endYear>?
int firstSunsInMonths(int startYear, int endYear) {
    if (startYear < 1900 || endYear < startYear) {
        return -1;
    }

    int daysSinceEpoch = 0;
    int firstSuns = 0;
    enum Days firstDayFirstYear = firstDay(startYear);

    for (int year = startYear; year <= endYear; year++) {
        for (enum Months month = Jan; month <= Dec; month++) {
            daysSinceEpoch += getDaysOnMonths(month, year);
            enum Days firstDayThisMonth = firstDayFirstYear + daysSinceEpoch % 7;
            if (firstDayThisMonth == SUN) {
                firstSuns++;
            }
        }
    }

    return firstSuns;
}

int getDaysOnMonths(enum Months month, int year) {
    enum DaysOnMonths daysOnMonths;
    switch (month) {
        case Jan:
            daysOnMonths = JAN;
            break;
        case Feb:
            if (isLeap(year)) {
                daysOnMonths = FEB_L;
            } else {
                daysOnMonths = FEB_N;
            }
            break;
        case Mar:
            daysOnMonths = MAR;
            break;
        case Apr:
            daysOnMonths = APR;
            break;
        case May:
            daysOnMonths = MAY;
            break;
        case Jun:
            daysOnMonths = JUN;
            break;
        case Jul:
            daysOnMonths = JUL;
            break;
        case Aug:
            daysOnMonths = AUG;
            break;
        case Sep:
            daysOnMonths = SEP;
            break;
        case Oct:
            daysOnMonths = OCT;
            break;
        case Nov:
            daysOnMonths = NOV;
            break;
        case Dec:
            daysOnMonths = DEC;
            break;
        default:
            break;
    }
    return (int) daysOnMonths;
}




// quick function
enum Days firstDay(int year) {
    if (year < 1900) {
        return -1;
    } else if (year == 1900) {
        return MON;
    }
    int daysSince1900 = daysSinceEpoch(Dec, year - 1);
    return (daysSince1900 % 7 + DAY_ON_EPOCH) % 7;
}

int daysSinceEpoch(enum Months month, int endYear) {
    int sum = 0;
    enum DaysOnMonths daysOnMonths;
    if (month == Dec) {
        for (int i = 1900; i <= endYear; i++) {
            sum += daysInYear(i);
        }
        return sum;
    } else {
        for (int i = 1900; i <= endYear - 1; i++) {
            sum += daysInYear(i);
        }
        switch (month) {
            // no breaks
            // case Dec:
                // daysOnMonths = DEC;
                // sum += (int) daysOnMonths;
            case Nov:
                daysOnMonths = NOV;
                sum += (int) daysOnMonths;
            case Oct:
                daysOnMonths = OCT;
                sum += (int) daysOnMonths;
            case Sep:
                daysOnMonths = SEP;
                sum += (int) daysOnMonths;
            case Aug:
                daysOnMonths = AUG;
                sum += (int) daysOnMonths;
            case Jul:
                daysOnMonths = JUL;
                sum += (int) daysOnMonths;
            case Jun:
                daysOnMonths = JUN;
                sum += (int) daysOnMonths;
            case May:
                daysOnMonths = MAY;
                sum += (int) daysOnMonths;
            case Apr:
                daysOnMonths = APR;
                sum += (int) daysOnMonths;
            case Mar:
                daysOnMonths = MAR;
                sum += (int) daysOnMonths;
            case Feb:
                if (isLeap(endYear)) {
                    daysOnMonths = FEB_L;
                } else {
                    daysOnMonths = FEB_N;
                }
                sum += (int) daysOnMonths;
            case Jan:
                daysOnMonths = JAN;
                sum += (int) daysOnMonths;
                break;
            default:
                break;
        }
    }
    return sum;
}

bool isLeap(int year) {
    if (year % 4 == 0) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return true;
            }
            return false;
        }
        return true;
    }
    return false;
}

int daysInYear(int year) {
    if (isLeap(year)) {
        return 366;
    }
    return 365;
}



int numberOfSunInYear(enum Days firstDay, int year) {
    int daysInThisYear = daysInYear(year);
    int suns = daysInThisYear / 7;
    enum Days addDays[7] = {0, 0, 0, 0, 0, 0, 0};
    switch (daysInThisYear % 7) {
        case 0:
            return suns;
        case 1:
            addDays[0] = SUN;
            if (arrContains((const int *) addDays, 7, firstDay)) {
                return suns + 1;
            }
            return suns;
        case 2:
            addDays[0] = SUN;
            addDays[1] = SAT;
            if (arrContains((const int *) addDays, 7, firstDay)) {
                return suns + 1;
            }
            return suns;
        case 3:
            addDays[0] = SUN;
            addDays[1] = SAT;
            addDays[2] = MON;
            if (arrContains((const int *) addDays, 7, firstDay)) {
                return suns + 1;
            }
            return suns;
        case 4:
            addDays[0] = SUN;
            addDays[1] = SAT;
            addDays[2] = FRI;
            addDays[3] = THU;
            if (arrContains((const int *) addDays, 7, firstDay)) {
                return suns + 1;
            }
            return suns;
        case 5:
            addDays[0] = SUN;
            addDays[1] = SAT;
            addDays[2] = FRI;
            addDays[3] = THU;
            addDays[4] = WED;
            if (arrContains((const int *) addDays, 7, firstDay)) {
                return suns + 1;
            }
            return suns;
        case 6:
            addDays[0] = SUN;
            addDays[1] = SAT;
            addDays[2] = FRI;
            addDays[3] = THU;
            addDays[4] = WED;
            addDays[5] = TUE;
            if (arrContains((const int *) addDays, 7, firstDay)) {
                return suns + 1;
            }
            return suns;
        default:
            return -1;
    }
}

bool arrContains(const int arr[], int blocks, int toFind) {
    for (int i = 0; i < blocks; i++) {
        if (arr[i] == toFind) {
            return true;
        }
    }
    return false;
}

// Unused function: calculates total number of Sundays from 01 Jan <startYear> to 31 Dec <endYear> inclusive
int totalSunInYears(int startYear, int endYear) {
    if (startYear < 1900 || endYear < startYear) {
        return -1;
    }

    int sum = 0;
    for (int year = startYear; year <= endYear; year++) {
        sum += numberOfSunInYear(firstDay(year), year);
    }

    return sum;
}