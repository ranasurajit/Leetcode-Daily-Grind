class Solution {
    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(N x K)
     * SC: O(K) + O(K) ~ O(K)
     * - O(K) - current and next array memory
     *
     * Accepted (211 / 211 testcases passed)
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // Initialization
        int[][] next = new int[2][k + 1]; // SC: O(K x 2) ~ O(K)
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) {
            int[][] current = new int[2][k + 1]; // SC: O(K x 2) ~ O(K)
            for (int j = 0; j < 2; j++) {
                for (int p = 0; p <= k; p++) {
                    if (p == 0) {
                        current[j][p] = 0;
                        continue;
                    }
                    int pick = 0;
                    int skip = 0;
                    if (j == 1) {
                        // buy (pick) at index 'idx' or skip
                        pick = -1 * prices[i] + next[0][p];
                        skip = next[1][p];
                    } else {
                        // sell (pick) at index 'idx' or skip
                        pick = prices[i] + next[1][p - 1];
                        skip = next[0][p];
                    }
                    current[j][p] = Math.max(pick, skip);
                }
            }
            next = current;
        }
        return next[1][k];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N x K)
     * SC: O(N x K)
     * - O(N x K) - dp memory
     *
     * Accepted (211 / 211 testcases passed)
     */
    public int maxProfitTabulation(int k, int[] prices) {
        int n = prices.length;
        // Initialization
        int[][][] dp = new int[n + 1][2][k + 1]; // SC: O(N x K x 2) ~ O(N x K)
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 2; j++) {
                for (int p = 0; p <= k; p++) {
                    if (p == 0) {
                        dp[i][j][p] = 0;
                        continue;
                    }
                    int pick = 0;
                    int skip = 0;
                    if (j == 1) {
                        // buy (pick) at index 'idx' or skip
                        pick = -1 * prices[i] + dp[i + 1][0][p];
                        skip = dp[i + 1][1][p];
                    } else {
                        // sell (pick) at index 'idx' or skip
                        pick = prices[i] + dp[i + 1][1][p - 1];
                        skip = dp[i + 1][0][p];
                    }
                    dp[i][j][p] = Math.max(pick, skip);
                }
            }
        }
        return dp[0][1][k];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x K)
     * SC: O(N x K) + O(N)
     * - O(N x K) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (211 / 211 testcases passed)
     */
    public int maxProfitMemoization(int k, int[] prices) {
        int n = prices.length;
        int[][][] memo = new int[n][2][k + 1]; // SC: O(N x K x 2) ~ O(N x K)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m , -1);
            }
        } 
        return solveMemoization(0, n, 1, k, prices, memo); // TC: O(N x K), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x K x 2) ~ O(N x K)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int n, int canBuy, int k, 
        int[] prices, int[][][] memo) {
        // Base Case
        if (idx == n || k == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][canBuy][k] != -1) {
            return memo[idx][canBuy][k];
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // buy (pick) at index 'idx' or skip
            pick = -1 * prices[idx] + solveMemoization(idx + 1, n, 0, k, prices, memo);
            skip = solveMemoization(idx + 1, n, 1, k, prices, memo);
        } else {
            // sell (pick) at index 'idx' or skip
            pick = prices[idx] + solveMemoization(idx + 1, n, 1, k - 1, prices, memo);
            skip = solveMemoization(idx + 1, n, 0, k, prices, memo);
        }
        return memo[idx][canBuy][k] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (207 / 211 testcases passed)
     */
    public int maxProfitRecursion(int k, int[] prices) {
        int n = prices.length;
        return solveRecursion(0, n, 1, k, prices); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int n, int canBuy, int k, int[] prices) {
        // Base Case
        if (idx == n || k == 0) {
            return 0;
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // buy (pick) at index 'idx' or skip
            pick = -1 * prices[idx] + solveRecursion(idx + 1, n, 0, k, prices);
            skip = solveRecursion(idx + 1, n, 1, k, prices);
        } else {
            // sell (pick) at index 'idx' or skip
            pick = prices[idx] + solveRecursion(idx + 1, n, 1, k - 1, prices);
            skip = solveRecursion(idx + 1, n, 0, k, prices);
        }
        return Math.max(pick, skip);
    }
}
