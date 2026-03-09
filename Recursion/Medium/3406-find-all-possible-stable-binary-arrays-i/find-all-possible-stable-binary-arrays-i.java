class Solution {
    private static final int MOD = (int) 1e9 + 7;
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(L x M x N)
     * SC: O(M + N) + O(L x M x N)
     * - O(M + N) - recursion stack space, where M = zero, N = one
     * - O(L x M x N) - memoization memory
     *
     * Accepted (670 / 670 testcases passed)
     */
    public int numberOfStableArrays(int zero, int one, int limit) {
        /**
         * To make the array as Stable Binary Array, we cannot use 
         * either 1 or 0 contiguous occurences more than limit 
         */
        int[][][] memo = new int[zero + 1][one + 1][3]; // SC: O(M x N x L)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        return solveMemoization(zero, one, limit, -1, memo); // TC: O(L x M x N), SC: O(M + N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(L x M x N)
     * SC: O(M + N)
     */
    private int solveMemoization(int zero, int one, int limit, int last, int[][][] memo) {
        // Base Case
        if (zero == 0 && one == 0) {
            // one possible stable binary array is formed
            return 1;
        }
        // Memoization Check
        if (memo[zero][one][last + 1] != -1) {
            return memo[zero][one][last + 1];
        }
        // Recursion Calls
        int ways = 0;
        for (int j = 1; j <= limit; j++) {
            // choose either 0 or 1
            if (last != 0 && zero >= j) {
                ways = (ways + solveMemoization(zero - j, one, limit, 0, memo)) % MOD;
            }
            if (last != 1 && one >= j) {
                ways = (ways + solveMemoization(zero, one - j, limit, 1, memo)) % MOD;
            }
        }
        return memo[zero][one][last + 1] = ways % MOD;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(L^(M + N))
     * SC: O(M + N)
     * - O(M + N) - recursion stack space, where M = zero, N = one
     *
     * Time Limit Exceeded (515 / 670 testcases passed)
     */
    public int numberOfStableArraysRecursion(int zero, int one, int limit) {
        /**
         * To make the array as Stable Binary Array, we cannot use 
         * either 1 or 0 contiguous occurences more than limit 
         */
        return solveRecursion(zero, one, limit, -1); // TC: O(L^(M + N)), SC: O(M + N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(L^(M + N))
     * SC: O(M + N)
     */
    private int solveRecursion(int zero, int one, int limit, int last) {
        // Base Case
        if (zero == 0 && one == 0) {
            // one possible stable binary array is formed
            return 1;
        }
        // Recursion Calls
        int zeroWays = 0;
        int oneWays = 0;
        for (int j = 1; j <= limit; j++) {
            // choose either 0 or 1
            if (last != 0 && zero >= j) {
                zeroWays += solveRecursion(zero - j, one, limit, 0);
            }
            if (last != 1 && one >= j) {
                oneWays += solveRecursion(zero, one - j, limit, 1);
            }
        }
        return zeroWays + oneWays;
    }
}
