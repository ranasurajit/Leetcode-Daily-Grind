class Solution {
    /**
     * Approach V : Using Mathematical Approach
     *
     * TC : O(1)
     * SC : O(1)
     *
     * Accepted (46 / 46 testcases passed)
     */
    public boolean stoneGame(int[] piles) {
        /**
         * since there are even elements in piles so 
         * mathematically Alice can win always
         */
        return true;
    }

    /**
     * Approach IV : Using Memoization (Top-Down) - II Approach
     *
     * TC : O(n²)
     * SC : O(n²) + O(n) ~ O(n²)
     * - O(n) - recursion stack
     * - O(n²) - memoization memory
     *
     * Accepted (46 / 46 testcases passed)
     */
    public boolean stoneGameMemoizationII(int[] piles) {
        int n = piles.length;
        /**
         * we need to maximize the difference of aliceScore - bobScore
         * and return true if aliceScore - bobScore > 0
         *
         * below solve(i, j) returns the maximum difference of scores
         * for Alice and Bob 
         */
        Integer[][] memo = new Integer[n + 1][n + 1];
        return solveMem(0, n - 1, piles, memo) > 0; // TC : O(n²), SC : O(n)
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(n²)
     * SC : O(n)
     */
    private int solveMem(int i, int j, int[] piles, Integer[][] memo) {
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
         * index [i + 1, j]
         *
         * 2. if Alice chooses index 'j', then Bob can choose
         * index [i, j - 1]
         */
        int take_i = piles[i] - solveMem(i + 1, j, piles, memo);
        int take_j = piles[j] - solveMem(i, j - 1, piles, memo);
        return memo[i][j] = Math.max(take_i, take_j);
    }

    /**
     * Approach III : Using Recursion - II Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     *
     * Time Limit Exceeded (26 / 46 testcases passed)
     */
    public boolean stoneGameRecursionII(int[] piles) {
        int n = piles.length;
        /**
         * we need to maximize the difference of aliceScore - bobScore
         * and return true if aliceScore - bobScore > 0
         *
         * below solve(i, j) returns the maximum difference of scores
         * for Alice and Bob 
         */
        return solve(0, n - 1, piles) > 0; // TC : O(2ⁿ), SC : O(n)
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solve(int i, int j, int[] piles) {
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
         * index [i + 1, j]
         *
         * 2. if Alice chooses index 'j', then Bob can choose
         * index [i, j - 1]
         */
        int take_i = piles[i] - solve(i + 1, j, piles);
        int take_j = piles[j] - solve(i, j - 1, piles);
        return Math.max(take_i, take_j);
    }

    /**
     * Approach II : Using Memoization (Top-Down) - I Approach
     *
     * TC : O(n) + O(n²) ~ O(n²)
     * SC : O(n²) + O(n) ~ O(n²)
     * - O(n) - recursion stack
     * - O(n²) - memoization memory
     *
     * Accepted (46 / 46 testcases passed)
     */
    public boolean stoneGameMemoizationI(int[] piles) {
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
     * Approach I : Using Recursion - I Approach
     *
     * TC : O(n) + O(2ⁿ) ~ O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     *
     * Time Limit Exceeded (26 / 46 testcases passed)
     */
    public boolean stoneGameRecursionI(int[] piles) {
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
