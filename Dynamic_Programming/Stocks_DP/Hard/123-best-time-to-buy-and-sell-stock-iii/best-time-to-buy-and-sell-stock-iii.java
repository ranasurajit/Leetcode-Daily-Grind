class Solution {
    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(N)
     * SC: O(1)
     *
     * Accepted (214 / 214 testcases passed)
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // states are index, canBuy and allowed
        // Initialization
        int[][] next = new int[2][3]; // SC: O(6) ~ O(1)
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) {    // TC: O(N) - index
            int[][] current = new int[2][3]; // SC: O(6) ~ O(1)
            for (int j = 0; j < 2; j++) {     // TC: O(2) - canBuy
                for (int k = 0; k < 3; k++) { // TC: O(3) - allowed
                    if (k == 0) {
                        current[j][k] = 0;
                        continue;
                    }
                    int pick = 0;
                    int skip = 0;
                    if (j == 1) {
                        skip = next[1][k];
                        pick = -1 * prices[i] + next[0][k];
                    } else {
                        skip = next[0][k];
                        pick = prices[i] + next[1][k - 1];
                    }
                    current[j][k] = Math.max(pick, skip);
                }
            }
            next = current;
        }
        return next[1][2];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N)
     * SC: O(N)
     * - O(N) - dp memory
     *
     * Accepted (214 / 214 testcases passed)
     */
    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        // states are index, canBuy and allowed
        // Initialization
        int[][][] dp = new int[n + 1][2][3]; // SC: O(6 x N) ~ O(N)
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) {    // TC: O(N) - index
            for (int j = 0; j < 2; j++) {     // TC: O(2) - canBuy
                for (int k = 0; k < 3; k++) { // TC: O(3) - allowed
                    if (k == 0) {
                        dp[i][j][k] = 0;
                        continue;
                    }
                    int pick = 0;
                    int skip = 0;
                    if (j == 1) {
                        skip = dp[i + 1][1][k];
                        pick = -1 * prices[i] + dp[i + 1][0][k];
                    } else {
                        skip = dp[i + 1][0][k];
                        pick = prices[i] + dp[i + 1][1][k - 1];
                    }
                    dp[i][j][k] = Math.max(pick, skip);
                }
            }
        }
        return dp[0][1][2];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N) + O(N)
     * - O(N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (214 / 214 testcases passed)
     */
    public int maxProfitMemoization(int[] prices) {
        int n = prices.length;
        // states are index, canBuy and allowed
        int[][][] memo = new int[n][2][3]; // SC: O(6 x N) ~ O(N)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m , -1);
            }
        }
        return solveMemoization(0, 1, 2, n, prices, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x 2 x 3) ~ O(N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int canBuy, int allowed, int n, int[] prices, int[][][] memo) {
        // Base Case
        if (idx == n || allowed == 0) {
            // it is out of bounds or total transaction of 2 is achieved
            return 0;
        }
        // Memoization Check
        if (memo[idx][canBuy][allowed] != -1) {
            return memo[idx][canBuy][allowed];
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // we may buy(pick) or skip it
            // skip
            skip = solveMemoization(idx + 1, 1, allowed, n, prices, memo);
            // pick
            pick = -1 * prices[idx] + solveMemoization(idx + 1, 0, allowed, n, prices, memo);
        } else {
            // we may sell(pick) or skip it
            // skip
            skip = solveMemoization(idx + 1, 0, allowed, n, prices, memo);
            // pick
            pick = prices[idx] + solveMemoization(idx + 1, 1, allowed - 1, n, prices, memo);
        }
        return memo[idx][canBuy][allowed] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (201 / 214 testcases passed)
     */
    public int maxProfitRecursion(int[] prices) {
        int n = prices.length;
        return solveRecursion(0, 1, 2, n, prices);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int canBuy, int allowed, int n, int[] prices) {
        // Base Case
        if (idx == n || allowed == 0) {
            // it is out of bounds or total transaction of 2 is achieved
            return 0;
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // we may buy(pick) or skip it
            // skip
            skip = solveRecursion(idx + 1, 1, allowed, n, prices);
            // pick
            pick = -1 * prices[idx] + solveRecursion(idx + 1, 0, allowed, n, prices);
        } else {
            // we may sell(pick) or skip it
            // skip
            skip = solveRecursion(idx + 1, 0, allowed, n, prices);
            // pick
            pick = prices[idx] + solveRecursion(idx + 1, 1, allowed - 1, n, prices);
        }
        return Math.max(pick, skip);
    }
}
