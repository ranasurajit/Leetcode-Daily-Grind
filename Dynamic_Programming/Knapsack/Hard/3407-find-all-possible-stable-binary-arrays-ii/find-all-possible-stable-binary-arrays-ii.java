class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach IV : Using Tabulation (Bottom-Up) + Prefix-Sum Approach
     *
     * TC: O(M x N) + O(M) + O(N) ~ O(M x N)
     * SC: O(M x N)
     * - O(M x N) - dp array
     *
     * Accepted (582 / 582 testcases passed)
     */
    public int numberOfStableArrays(int zero, int one, int limit) {
        /**
         * To form Stable Binary Arrays, the contiguous occurence 
         * of 1 or 0 cannot go beyond limit to satisfy all conditions
         */
        int[][][] dp = new int[zero + 1][one + 1][2]; // SC: O(M x N x 2) ~ O(M x N)
        // compute prefix sums
        for (int i = 1; i <= zero && i <= limit; i++) { // TC: O(M)
            dp[i][0][0] = 1;
        }
        for (int j = 1; j <= one && j <= limit; j++) { // TC: O(N)
            dp[0][j][1] = 1;
        }
        for (int i = 0; i <= zero; i++) {    // TC: O(M)
            for (int j = 0; j <= one; j++) { // TC: O(N)
                if (i > 0 && j > 0) {
                    // when we place a '0'
                    long val0 = ((long) dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                    // we need to substract invalid sequences
                    if (i - limit - 1 >= 0) {
                        val0 = (val0 - dp[i - limit - 1][j][1] + MOD) % MOD;
                    }
                    dp[i][j][0] = (int) val0;
                    // when we place a '1'
                    long val1 = ((long) dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                    // we need to substract invalid sequences
                    if (j - limit - 1 >= 0) {
                        val1 = (val1 - dp[i][j - limit - 1][0] + MOD) % MOD;
                    }
                    dp[i][j][1] = (int) val1;
                }
            }
        }
        return (dp[zero][one][0] + dp[zero][one][1]) % MOD;
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC: O(L x M x N)
     * SC: O(M x N)
     * - O(M x N) - dp array
     *
     * Time Limit Exceeded (575 / 582 testcases passed)
     */
    public int numberOfStableArraysTabulation(int zero, int one, int limit) {
        /**
         * To form Stable Binary Arrays, the contiguous occurence 
         * of 1 or 0 cannot go beyond limit to satisfy all conditions
         */
        int[][][] dp = new int[zero + 1][one + 1][2]; // SC: O(M x N x 2) ~ O(M x N)
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        for (int i = 0; i <= zero; i++) {    // TC: O(M)
            for (int j = 0; j <= one; j++) { // TC: O(N)
                if (i == 0 && j == 0) {
                    continue;
                }
                for (int k = 1; k <= limit && k <= i; k++) { // TC: O(Min(L, M))
                    dp[i][j][1] = (dp[i][j][1] + dp[i - k][j][0]) % MOD;
                }
                for (int k = 1; k <= limit && k <= j; k++) { // TC: O(Min(L, N))
                    dp[i][j][0] = (dp[i][j][0] + dp[i][j - k][1]) % MOD;
                }
            }
        }
        return (dp[zero][one][0] + dp[zero][one][1]) % MOD;
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(L x M x N)
     * SC: O(M x N) + O(M + N)
     * - O(M x N) - memoization array
     * - O(M + N) - recursion stack
     *
     * Time Limit Exceeded (572 / 582 testcases passed)
     */
    public int numberOfStableArraysMemoization(int zero, int one, int limit) {
        /**
         * To form Stable Binary Arrays, the contiguous occurence 
         * of 1 or 0 cannot go beyond limit to satisfy all conditions
         */
        int[][][] memo = new int[zero + 1][one + 1][3]; // SC: O(M x N x 3) ~ O(M x N)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        return solveMemoization(zero, one, limit, -1, memo); // TC: O(L x M x N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(L x M x N x 3) ~ O(L x M x N)
     * SC: O(M + N)
     */
    private int solveMemoization(int zero, int one, int limit, int last, int[][][] memo) {
        // Base Case
        if (zero == 0 && one == 0) {
            // 1 Stable Binary Array is formed
            return 1;
        }
        // Memoization Check
        if (memo[zero][one][last + 1] != -1) {
            return memo[zero][one][last + 1];
        }
        // Recursion Calls
        int ways = 0;
        for (int i = 1; i <= limit && i <= zero; i++) { // TC: O(Min(L, M))
            if (last != 0) {
                ways = (ways + solveMemoization(zero - i, one, limit, 0, memo)) % MOD;
            }
        }
        for (int i = 1; i <= limit && i <= one; i++) { // TC: O(Min(L, N))
            if (last != 1) {
                ways = (ways + solveMemoization(zero, one - i, limit, 1, memo)) % MOD;
            }
        }
        return memo[zero][one][last + 1] = ways % MOD;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(L ^ (M + N))
     * SC: O(M + N)
     * - O(M + N) - recursion stack
     *
     * Time Limit Exceeded (518 / 582 testcases passed)
     */
    public int numberOfStableArraysRecursion(int zero, int one, int limit) {
        /**
         * To form Stable Binary Arrays, the contiguous occurence 
         * of 1 or 0 cannot go beyond limit to satisfy all conditions
         */
        return solveRecursion(zero, one, limit, -1);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(L ^ (M + N))
     * SC: O(M + N)
     */
    private int solveRecursion(int zero, int one, int limit, int last) {
        // Base Case
        if (zero == 0 && one == 0) {
            // 1 Stable Binary Array is formed
            return 1;
        }
        // Recursion Calls
        int ways = 0;
        for (int i = 1; i <= limit && i <= zero; i++) { // TC: O(Min(L, M))
            if (last != 0) {
                ways += solveRecursion(zero - i, one, limit, 0);
            }
        }
        for (int i = 1; i <= limit && i <= one; i++) { // TC: O(Min(L, N))
            if (last != 1) {
                ways += solveRecursion(zero, one - i, limit, 1);
            }
        }
        return ways;
    }
}
