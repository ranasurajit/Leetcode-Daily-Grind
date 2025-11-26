class Solution {
    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N)
     * SC: O(N)
     * - O(N) - dp memory
     *
     * Accepted (210 / 210 testcases passed)
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2]; // SC: O(N x 2) ~ O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = 0; j < 2; j++) { // TC: O(2)
                int pick = 0;
                int skip = 0;
                if (j == 1) {
                    // pick (buy) stock at index 'idx' or skip
                    skip = dp[i + 1][1];
                    pick = -1 * prices[i] + dp[i + 1][0];
                } else {
                    // pick (sell) stock at index 'idx' or skip
                    skip = dp[i + 1][0];
                    pick = prices[i] + dp[i + 2][1];
                }
                dp[i][j] = Math.max(pick, skip);
            }
        }
        return dp[0][1];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N) + O(N)
     * - O(N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (210 / 210 testcases passed)
     */
    public int maxProfitMemoization(int[] prices) {
        int n = prices.length;
        int[][] memo = new int[n][2]; // SC: O(N x 2) ~ O(N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n, 1, prices, memo); // TC: O(N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x 2) ~ O(N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int n, int canBuy, int[] prices, int[][] memo) {
        // Base Case
        if (idx >= n) {
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
            // pick (buy) stock at index 'idx' or skip
            skip = solveMemoization(idx + 1, n, 1, prices, memo);
            pick = -1 * prices[idx] + solveMemoization(idx + 1, n, 0, prices, memo);
        } else {
            // pick (sell) stock at index 'idx' or skip
            skip = solveMemoization(idx + 1, n, 0, prices, memo);
            pick = prices[idx] + solveMemoization(idx + 2, n, 1, prices, memo);
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
     * Time Limit Exceeded (208 / 210 testcases passed)
     */
    public int maxProfitRecursion(int[] prices) {
        int n = prices.length;
        return solveRecursion(0, n, 1, prices); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int n, int canBuy, int[] prices) {
        // Base Case
        if (idx >= n) {
            return 0;
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // pick (buy) stock at index 'idx' or skip
            skip = solveRecursion(idx + 1, n, 1, prices);
            pick = -1 * prices[idx] + solveRecursion(idx + 1, n, 0, prices);
        } else {
            // pick (sell) stock at index 'idx' or skip
            skip = solveRecursion(idx + 1, n, 0, prices);
            pick = prices[idx] + solveRecursion(idx + 2, n, 1, prices);
        }
        return Math.max(pick, skip);
    }
}
