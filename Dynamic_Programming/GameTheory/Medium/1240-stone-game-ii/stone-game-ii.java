class Solution {
    private int n;
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(n²)
     * SC : O(n²) + O(n)
     * - O(n) - recursion stack
     * - O(n²) - memoization stack
     *
     * Accepted (92 / 92 testcases passed)
     */
    public int stoneGameII(int[] piles) {
        this.n = piles.length;
        int m = 1;
        /**
         * for Alice, p = 1 and foe Bob, p = 0
         */
        int p = 1;
        int[][][] memo = new int[2][n][n + 1]; // SC : O(n²)
        for (int[][] mem : memo) {
            for (int[] em : mem) {
                Arrays.fill(em , -1);
            }
        }
        return solveMemoization(p, 0, m, piles, memo); // TC : O(n²), SC : O(n)
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(n²)
     * SC : O(n)
     */
    private int solveMemoization(int p, int i, int m,
        int[] piles, int[][][] memo) {
        // Base Case
        if (i >= n) {
            return 0;
        }
        // Memoization Check
        if (memo[p][i][m] != -1) {
            return memo[p][i][m];
        }
        // Recursion Calls
        int result = p == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int stones = 0;
        for (int x = 1; x <= Math.min(2 * m, n - i); x++) {
            /**
             * a player can pick element from index (i + x - 1) from piles
             */
            stones += piles[i + x - 1];
            if (p == 1) {
                // when it is Alice expect Alice to play best
                result = Math.max(result, 
                    stones + 
                    solveMemoization(0, i + x, Math.max(x, m), piles, memo)
                );
            } else {
                // when it is opponent i.e. Bob, expect the worse
                result = Math.min(result, 
                    solveMemoization(1, i + x, Math.max(x, m), piles, memo));
            }
        }
        return memo[p][i][m] = result;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(nⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     *
     * Time Limit Exceeded (25 / 92 testcases passed)
     */
    public int stoneGameIIRecursion(int[] piles) {
        this.n = piles.length;
        int m = 1;
        /**
         * for Alice, p = 1 and foe Bob, p = 0
         */
        int p = 1;
        return solve(p, 0, m, piles);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O((2M)ⁿ) ~ O(nⁿ) as M <= n
     * SC : O(n)
     */
    private int solve(int p, int i, int m, int[] piles) {
        // Base Case
        if (i >= n) {
            return 0;
        }
        // Recursion Calls
        int result = p == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int stones = 0;
        for (int x = 1; x <= Math.min(2 * m, n - i); x++) {
            /**
             * a player can pick element from index (i + x - 1) from piles
             */
            stones += piles[i + x - 1];
            if (p == 1) {
                // when it is Alice expect Alice to play best
                result = Math.max(result, 
                    stones + solve(0, i + x, Math.max(x, m), piles)
                );
            } else {
                // when it is opponent i.e. Bob, expect the worse
                result = Math.min(result, solve(1, i + x, Math.max(x, m), piles));
            }
        }
        return result;
    }
}
