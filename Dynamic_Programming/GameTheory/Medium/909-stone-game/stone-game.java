class Solution {
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(n) + O(n²) ~ O(n²)
     * SC : O(n²) + O(n) ~ O(n²)
     * - O(n) - recursion stack
     * - O(n²) - memoization memory
     *
     * Accepted (46 / 46 testcases passed)
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int totalScore = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            totalScore += piles[i];
        }
        Integer[][] memo = new Integer[n + 1][n + 1];
        int aliceScore =
            solveMemoization(0, n - 1, piles, memo); // TC : O(n²), SC : O(n)
        int bobScore = totalScore - aliceScore;
        return aliceScore - bobScore > 0;
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(n²)
     * SC : O(n)
     */
    private int solveMemoization(int i, int j, int[] piles, Integer[][] memo) {
        // Base Case
        if (i > j) {
            // out of bounds
            return 0;
        }
        if (i == j) {
            return piles[i];
        }
        // Memoization Check
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        // Recursion Calls
        /**
         * Alice has two choices:
         * 1. if Alice chooses index 'i', then Bob can choose
         * index [i + 1, j] then Alice can get Minimum of 
         * [i + 2, j] and [i + 1, j - 1]
         *
         * 2. if Alice chooses index 'j', then Bob can choose
         * index [i, j - 1] then Alice can get Minimum of 
         * [i + 1, j - 1] and [i, j - 2]
         */
        int take_i = piles[i] + 
            Math.min(solveMemoization(i + 2, j, piles, memo),
                     solveMemoization(i + 1, j - 1, piles, memo));
        int take_j = piles[j] + 
            Math.min(solveMemoization(i + 1, j - 1, piles, memo),
                     solveMemoization(i, j - 2, piles, memo));
        return memo[i][j] = Math.max(take_i, take_j);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(n) + O(2ⁿ) ~ O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     *
     * Time Limit Exceeded (26 / 46 testcases passed)
     */
    public boolean stoneGameRecursion(int[] piles) {
        int n = piles.length;
        int totalScore = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            totalScore += piles[i];
        }
        int aliceScore = solveRecursion(0, n - 1, piles); // TC : O(2ⁿ), SC : O(n)
        int bobScore = totalScore - aliceScore;
        return aliceScore - bobScore > 0;
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int i, int j, int[] piles) {
        // Base Case
        if (i > j) {
            // out of bounds
            return 0;
        }
        if (i == j) {
            return piles[i];
        }
        // Recursion Calls
        /**
         * Alice has two choices:
         * 1. if Alice chooses index 'i', then Bob can choose
         * index [i + 1, j] then Alice can get Minimum of 
         * [i + 2, j] and [i + 1, j - 1]
         *
         * 2. if Alice chooses index 'j', then Bob can choose
         * index [i, j - 1] then Alice can get Minimum of 
         * [i + 1, j - 1] and [i, j - 2]
         */
        int take_i = piles[i] + 
            Math.min(solveRecursion(i + 2, j, piles),
                     solveRecursion(i + 1, j - 1, piles));
        int take_j = piles[j] + 
            Math.min(solveRecursion(i + 1, j - 1, piles),
                     solveRecursion(i, j - 2, piles));
        return Math.max(take_i, take_j);
    }
}
