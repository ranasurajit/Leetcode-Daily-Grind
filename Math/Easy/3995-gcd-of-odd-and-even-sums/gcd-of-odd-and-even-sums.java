class Solution {
    /**
     * Approach III : Using Pure Math Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public int gcdOfOddEvenSums(int n) {
        /**
         * Odd and Even n numbers will form an AP series
         *
         * Sum = (n * (2 * a + (n - 1) * d)) / 2
         * sumOdd = (n * (2 * 1 + (n - 1) * 2)) / 2 = n * n
         * sumEven = (n * (2 * 2 + (n - 1) * 2)) / 2 = n * (n + 1)
         * so GCD (n², n * (n + 1) is n always)
         */
        return n;
    }

    /**
     * Approach II : Using Math Approach
     *
     * TC : O(log(k))
     * SC : O(1)
     *
     * where k = Sum(n odd numbers)
     */
    public int gcdOfOddEvenSumsMath(int n) {
        /**
         * Odd and Even n numbers will form an AP series
         *
         * Sum = (n * (2 * a + (n - 1) * d)) / 2
         * sumOdd = (n * (2 * 1 + (n - 1) * 2)) / 2 = n * n
         * sumEven = (n * (2 * 2 + (n - 1) * 2)) / 2 = n * (n + 1)
         */
        int sumOdd = n * n;
        int sumEven = n * (n + 1);
        return gcd(sumEven, sumOdd); // TC : O(log(k))
    }

    /**
     * Approach I : Using Math + Simulation Approach
     *
     * TC : O(n) + O(log(k))
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
        return gcd(sumEven, sumOdd); // TC : O(log(k))
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
