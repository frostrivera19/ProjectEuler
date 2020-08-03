function deliberateAllPrimes(limit) {
    let allPrimes = new Array(limit + 1).fill(true);
    allPrimes[0] = false;
    allPrimes[1] = false;

    let evenMultiplier = 2;
    let evenIndex = 2 * evenMultiplier;
    while (evenIndex <= limit) {
        allPrimes[evenIndex] = false;
        evenMultiplier++;
        evenIndex = 2 * evenMultiplier;
    }

    var even = false;

    for (let i = 3; i <= limit; i++) {
        if (even) {
            allPrimes[i] = false;
            even = false;
            continue;
        }
        let multiplier = 2;
        let index = i * multiplier;
        while (index <= limit) {
            allPrimes[index] = false;
            index = i * ++multiplier;
        }
        even = true;
    }
    return allPrimes;
}