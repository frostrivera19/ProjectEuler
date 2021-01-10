public class deliberateAllPrimes {
    public static void main(String[] args) {

    }

    private static HashSet<Integer> allFactors(int num,
                                               ArrayList<Boolean> primes) {

        ArrayList<Integer> primeFactors = primeFactors(num, primes);
        HashSet<Integer> allFactors = new HashSet<>(primeFactors.size());

        for (int i = 1; i < Math.pow(2, primeFactors.size()); i++) {
            StringBuilder binRepStr = new StringBuilder();
            binRepStr.append(Integer.toBinaryString(i));
            while (binRepStr.length() < primeFactors.size()) {
                binRepStr.insert(0, '0');
            }
            char[] binRep = new String(binRepStr).toCharArray();

            int currentFactor = 1;
            for (int j = 0; j < binRep.length; j++) {
                if (binRep[j] == '1') {
                    currentFactor *= primeFactors.get(j);
                }
            }
            allFactors.add(currentFactor);
        }

        allFactors.add(1);
        allFactors.add(num);

        return allFactors;
    }

    private static ArrayList<Integer> primeFactors(int num,
                                                   ArrayList<Boolean> primes) {
        ArrayList<Integer> primeFactors = new ArrayList<>();


        int number = num;
        while (number % 2 == 0) {
            primeFactors.add(2);
            number /= 2;
        }

        int divisor = 3;
        while (number != 1 && divisor < number) {
            while (number % divisor == 0) {
                primeFactors.add(divisor);
                number /= divisor;
            }
            do {
                divisor += 2;
            } while (!primes.get(divisor));
        }

        return primeFactors;
    }

    private static ArrayList<Boolean> deliberateAllPrimes(int higher) {
            ArrayList<Boolean> allPrimes =
                    new ArrayList<>(Arrays.asList(new Boolean [higher + 1]));
            Collections.fill(allPrimes, true);
            allPrimes.set(0, false); // 0 == not prime
            allPrimes.set(1, false);

            boolean even = false;
            for (int i = 3; i <= Math.sqrt(higher); i++) {
                if (even) {
                    allPrimes.set(i, false);
                    even = false;
                    continue;
                } else if (allPrimes.get(i)) { // if i is prime
                    int multiplier = 2;
                    while (i * multiplier <= higher) {
                        allPrimes.set(i * multiplier, false);
                        multiplier++;
                    }
                }
                even = true;
            }
            even = (int) Math.sqrt(higher) % 2 == 0;
            for (int i = (int) Math.sqrt(higher); i <= higher; i++) {
                if (even) {
                    allPrimes.set(i, false);
                    even = false;
                } else {
                    even = true;
                }
            }
            return allPrimes;
        }
    }
}