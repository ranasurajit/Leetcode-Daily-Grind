class Solution {
    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     * - O(N) - dp array memory
     *
     * Accepted (44 / 44 testcases passed)
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // Initialization
        int[][] dp = new int[n + 1][2]; // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = 0; j < 2; j++) {
                int pick = 0;
                int skip = 0;
                if (j == 1) {
                    // we may skip buying the stock or pick (buy) it
                    skip = dp[i + 1][1];
                    pick = -1 * prices[i] + dp[i + 1][0];
                } else {
                    // we may skip selling the stock or pick (sell) it
                    skip = dp[i + 1][0];
                    pick = prices[i] - fee + dp[i + 1][1];
                }
                dp[i][j] = Math.max(pick, skip);
            }
        }
        return dp[0][1];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N)
     * - O(N) - recursion stack
     * - O(N) - memoization memory
     *
     * Accepted (44 / 44 testcases passed)
     */
    public int maxProfitMemoization(int[] prices, int fee) {
        int n = prices.length;
        int[][] memo = new int[n][2];
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, 1, n, fee, prices, memo); // TC: O(N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x 2) ~ O(N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int canBuy, int n, int fee, int[] prices, int[][] memo) {
        // Base Case
        if (idx == n) {
            // out of bounds
            return 0;
        }
        // Memoization Check
        if (memo[idx][canBuy] != -1) {
            return memo[idx][canBuy];
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // we may skip buying the stock or pick (buy) it
            skip = solveMemoization(idx + 1, 1, n, fee, prices, memo);
            pick = -1 * prices[idx] + solveMemoization(idx + 1, 0, n, fee, prices, memo);
        } else {
            // we may skip selling the stock or pick (sell) it
            skip = solveMemoization(idx + 1, 0, n, fee, prices, memo);
            pick = prices[idx] - fee + solveMemoization(idx + 1, 1, n, fee, prices, memo);
        }
        return memo[idx][canBuy] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (19 / 44 testcases passed)
     */
    public int maxProfitRecursion(int[] prices, int fee) {
        int n = prices.length;
        return solveRecursion(0, 1, n, fee, prices); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int canBuy, int n, int fee, int[] prices) {
        // Base Case
        if (idx == n) {
            // out of bounds
            return 0;
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // we may skip buying the stock or pick (buy) it
            skip = solveRecursion(idx + 1, 1, n, fee, prices);
            pick = -1 * prices[idx] + solveRecursion(idx + 1, 0, n, fee, prices);
        } else {
            // we may skip selling the stock or pick (sell) it
            skip = solveRecursion(idx + 1, 0, n, fee, prices);
            pick = prices[idx] - fee + solveRecursion(idx + 1, 1, n, fee, prices);
        }
        return Math.max(pick, skip);
    }
}
