class Solution {
    /**
     * Approach II : Using Math Approach
     *
     * TC : O(k)
     * SC : O(1)
     *
     * where k = Sum(n odd numbers)
     */
    public int gcdOfOddEvenSums(int n) {
        /**
         * Odd and Even n numbers will form an AP series
         *
         * Sum = (n / 2) * (2 * a + (n - 1) * d)
         */
        int sumOdd = (n * (2 * 1 + (n - 1) * 2)) / 2;
        int sumEven = (n * (2 * 2 + (n - 1) * 2)) / 2;
        return gcd(sumEven, sumOdd); // TC : O(sumOdd)
    }

    /**
     * Approach I : Using Math + Simulation Approach
     *
     * TC : O(n) + O(k)
     * SC : O(1)
     *
     * where k = Sum(n odd numbers)
     */
    public int gcdOfOddEvenSumsSimulation(int n) {
        int sumOdd = 0;
        int sumEven = 0;
        int oddNum = 1;
        int evenNum = 2;
        for (int i = 0; i < n; i++) { // TC : O(n)
            sumOdd += oddNum;
            sumEven += evenNum;
            oddNum += 2;
            evenNum += 2;
        }
        return gcd(sumEven, sumOdd); // TC : O(sumOdd)
    }

    /**
     * Using Math Approach
     *
     * TC : O(log(b))
     * SC : O(1)
     */
    private int gcd(int a, int b) {
        while (b != 0) { // TC : O(log(b))
            int rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }
}
