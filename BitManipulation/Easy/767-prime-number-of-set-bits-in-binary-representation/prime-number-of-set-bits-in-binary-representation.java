class Solution {
    private int[] sieve;
    /**
     * Approach II : Using Bit-Manipulation + Sieve of Eratosthenes Approach
     *
     * TC: O(R) + O(32) + O(32 x log(log(32))) ~ O(R)
     * SC: O(32) ~ O(1)
     *
     * where R = right - left + 1 ~ (Max - 10^4 as per constraint)
     */
    public int countPrimeSetBits(int left, int right) {
        // we need to compute Sieve of primes numbers from 1 to 32
        sieve = new int[33]; // TC: O(32)
        computePrimeSieve(); // TC: O(32 x log(log(32)))
        int count = 0;
        for (int i = left; i <= right; i++) { // TC: O(R)
            if (hasPrimeSetBitsOptimal(i)) {  // TC: O(1), SC: O(1)
                count++;
            }
        }
        return count;
    }

    /**
     * Using Sieve of Eratosthenes Approach
     *
     * TC: O(32 x log(log(32)))
     * SC: O(1)
     */
    private void computePrimeSieve() {
        Arrays.fill(sieve, 1);
        sieve[0] = 0;
        sieve[1] = 0;
        for (int i = 2; i * i <= 32; i++) { // TC: O(Sqrt(32))
            if (sieve[i] == 1) {
                for (int j = i * i; j <= 32; j += i) {
                    sieve[j] = 0;
                }
            }
        }
    }

    /**
     * Using Math + Bit-Manipulation Approach
     *
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    private boolean hasPrimeSetBitsOptimal(int num) {
        int countSetBits = 0;
        for (int i = 31; i >= 0; i--) { // TC: O(32)
            int bitValue = ((num >> i) & 1);
            if (bitValue == 1) {
                countSetBits++;
            }
        }
        return sieve[countSetBits] == 1;
    }

    /**
     * Approach I : Using Brute-Force (Bit-Manipulation + Math) Approach
     *
     * TC: O(R)
     * SC: O(1)
     *
     * where R = right - left + 1 ~ (Max - 10^4 as per constraint)
     */
    public int countPrimeSetBitsBruteForce(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) { // TC: O(R)
            if (hasPrimeSetBits(i)) { // TC: O(1), SC: O(1)
                count++;
            }
        }
        return count;
    }

    /**
     * Using Math + Bit-Manipulation Approach
     *
     * TC: O(32) + O(Sqrt(32)) ~ O(1)
     * SC: O(1)
     */
    private boolean hasPrimeSetBits(int num) {
        int countSetBits = 0;
        for (int i = 31; i >= 0; i--) { // TC: O(32)
            int bitValue = ((num >> i) & 1);
            if (bitValue == 1) {
                countSetBits++;
            }
        }
        // K is maximum 32 size (32 bits)
        return isPrime(countSetBits); // TC: O(Sqrt(K)), SC: O(1)
    }

    /**
     * Using Math + Simulation Approach
     *
     * TC: O(Sqrt(K))
     * SC: O(1)
     */
    private boolean isPrime(int count) {
        if (count == 1) {
            return false;
        }
        for (int i = 2; i * i <= count; i++) { // TC: O(Sqrt(K))
            if (count % i == 0) {
                return false;
            }
        }
        return true;
    }
}
