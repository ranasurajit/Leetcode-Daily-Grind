class Solution {
    private int n;

    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     * - O(n) - dp array memory
     *
     * Accepted (80 / 80 testcases passed)
     */
    public int stoneGameVIII(int[] stones) {
        this.n = stones.length;
        int[] prefixSum = new int[n]; // SC : O(n)
        prefixSum[0] = stones[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        }
        /**
         * the below recursion will return the
         * difference of score(Alice - Bob)
         */
        int[] dp = new int[n + 1]; // SC : O(n)
        dp[n - 1] = prefixSum[n - 1];
        for (int i = n - 2; i >= 1; i--) { // TC : O(n)
            int skip = dp[i + 1];
            int pick = prefixSum[i] - dp[i + 1];
            // as both players are playing optimally
            dp[i] = Math.max(skip, pick);
        }
        return dp[1];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n)
     * - O(n) - recursion stack
     * - O(n) - memoization memory
     *
     * Time Limit Exceeded (79 / 80 testcases passed)
     */
    public int stoneGameVIIIMemoization(int[] stones) {
        this.n = stones.length;
        int[] prefixSum = new int[n]; // SC : O(n)
        prefixSum[0] = stones[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        }
        /**
         * the below recursion will return the
         * difference of score(Alice - Bob)
         */
        int[] memo = new int[n]; // SC : O(n)
        Arrays.fill(memo, -1);
        return solveMemoization(1, prefixSum, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    private int solveMemoization(int i, int[] prefixSum, int[] memo) {
        // Base Case
        if (i == n - 1) {
            return prefixSum[n - 1];
        }
        // Memoization Check
        if (memo[i] != -1) {
            return memo[i];
        }
        // Recursion Calls
        int skip = solveMemoization(i + 1, prefixSum, memo);
        int pick = prefixSum[i] - solveMemoization(i + 1, prefixSum, memo);
        // as both players are playing optimally
        return memo[i] = Math.max(skip, pick);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(2ⁿ) + O(n) ~ O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     *
     * Time Limit Exceeded (9 / 80 testcases passed)
     */
    public int stoneGameVIIIRecursion(int[] stones) {
        this.n = stones.length;
        int[] prefixSum = new int[n]; // SC : O(n)
        prefixSum[0] = stones[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        }
        /**
         * the below recursion will return the
         * difference of score(Alice - Bob)
         */
        return solveRecursion(1, prefixSum);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int i, int[] prefixSum) {
        // Base Case
        if (i == n - 1) {
            return prefixSum[n - 1];
        }
        // Recursion Calls
        int skip = solveRecursion(i + 1, prefixSum);
        int pick = prefixSum[i] - solveRecursion(i + 1, prefixSum);
        return Math.max(skip, pick); // as both players are playing optimally
    }
}
