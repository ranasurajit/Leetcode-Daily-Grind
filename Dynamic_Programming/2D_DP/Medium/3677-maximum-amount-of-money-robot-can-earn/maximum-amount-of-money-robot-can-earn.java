class Solution {
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC: O(m x n x 3) ~ O(m x n)
     * SC: O(m x n)
     * - O(m x n) - dp array memory
     *
     * Accepted (579 / 579 testcases passed)
     */
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        int[][][] dp = new int[m][n][3]; // SC: O(m x n x 3) ~ O(m x n)
        // Initialization
        dp[0][0][0] = coins[0][0];
        dp[0][0][1] = 0;
        dp[0][0][2] = 0;

        // Iterative Calls
        for (int i = 0; i < m; i++) { // TC: O(m)
            for (int j = 0; j < n; j++) { // TC: O(n)
                if (i == 0 && j == 0) {
                    continue;
                }
                for (int k = 2; k >= 0; k--) { // TC: O(3)
                    int skipUp = Integer.MIN_VALUE;
                    int skipLeft = Integer.MIN_VALUE;
                    int pick = Integer.MIN_VALUE;
                    if (coins[i][j] < 0 && k > 0) {
                        // try to neutralize
                        skipUp = (i > 0 && k > 0) ? 
                            dp[i - 1][j][k - 1] : Integer.MIN_VALUE;
                        skipLeft = (j > 0 && k > 0) ?
                            dp[i][j - 1][k - 1] : Integer.MIN_VALUE;
                        // pick and don't neutralize
                        pick = coins[i][j] + 
                            Math.max(
                                i > 0 ? dp[i - 1][j][k] : Integer.MIN_VALUE,
                                j > 0 ? dp[i][j - 1][k]: Integer.MIN_VALUE
                            );
                    } else {
                        pick = coins[i][j] + 
                            Math.max(
                                i > 0 ? dp[i - 1][j][k] : Integer.MIN_VALUE,
                                j > 0 ? dp[i][j - 1][k]: Integer.MIN_VALUE
                            );
                    }
                    int skip = Math.max(skipUp, skipLeft);
                    dp[i][j][k] = Math.max(skip, pick);
                }
            }
        }
        return Math.max(dp[m - 1][n - 1][0],
            Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(m x n x 3) ~ O(m x n)
     * SC: O(m x n) + O(m + n)
     * - O(m x n) - memoization memory
     * - O(m + n) - recursion stack
     *
     * Accepted (579 / 579 testcases passed)
     */
    public int maximumAmountMemoization(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        int[][][] memo = new int[m][n][3]; // SC: O(m x n x 3) ~ O(m x n)
        for (int[][] mem : memo) {
            for (int[] p : mem) {
                Arrays.fill(p, Integer.MIN_VALUE);
            }
        }
        return solveMemoization(m - 1, n - 1, 2, coins, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(4 ^ (m + n))
     * SC: O(m + n)
     */
    private int solveMemoization(int i, int j, int k,
        int[][] coins, int[][][] memo) {
        // Base Case
        if (i == 0 && j == 0) {
            return coins[i][j] < 0 && k > 0 ? 0 : coins[i][j]; 
        }
        if (i < 0 || j < 0) {
            return Integer.MIN_VALUE;
        }
        // Memoization Check
        if (memo[i][j][k] != Integer.MIN_VALUE) {
            return memo[i][j][k];
        }
        // Recursion Calls
        int skipUp = Integer.MIN_VALUE;
        int skipLeft = Integer.MIN_VALUE;
        int pick = Integer.MIN_VALUE;
        if (coins[i][j] < 0 && k > 0) {
            // try to neutralize
            skipUp = solveMemoization(i - 1, j, k - 1, coins, memo);
            skipLeft = solveMemoization(i, j - 1, k - 1, coins, memo);
            // pick and don't neutralize
            pick = coins[i][j] + 
                Math.max(
                    solveMemoization(i - 1, j, k, coins, memo),
                    solveMemoization(i, j - 1, k, coins, memo)
                );
        } else {
            pick = coins[i][j] + 
                Math.max(
                    solveMemoization(i - 1, j, k, coins, memo),
                    solveMemoization(i, j - 1, k, coins, memo)
                );
        }
        int skip = Math.max(skipUp, skipLeft);
        return memo[i][j][k] = Math.max(skip, pick);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(4 ^ (m + n))
     * SC: O(m + n)
     * - O(m + n) - recursion stack
     *
     * Time Limit Exceeded (502 / 579 testcases passed)
     */
    public int maximumAmountRecursion(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        return solveRecursion(m - 1, n - 1, 2, coins);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(4 ^ (m + n))
     * SC: O(m + n)
     */
    private int solveRecursion(int i, int j, int k, int[][] coins) {
        // Base Case
        if (i == 0 && j == 0) {
            return coins[i][j] < 0 && k > 0 ? 0 : coins[i][j]; 
        }
        if (i < 0 || j < 0) {
            return Integer.MIN_VALUE;
        }
        // Recursion Calls
        int skipUp = Integer.MIN_VALUE;
        int skipLeft = Integer.MIN_VALUE;
        int pick = Integer.MIN_VALUE;
        if (coins[i][j] < 0 && k > 0) {
            // try to neutralize
            skipUp = solveRecursion(i - 1, j, k - 1, coins);
            skipLeft = solveRecursion(i, j - 1, k - 1, coins);
            // pick and don't neutralize
            pick = coins[i][j] + 
                Math.max(
                    solveRecursion(i - 1, j, k, coins),
                    solveRecursion(i, j - 1, k, coins)
                );
        } else {
            pick = coins[i][j] + 
                Math.max(
                    solveRecursion(i - 1, j, k, coins),
                    solveRecursion(i, j - 1, k, coins)
                );
        }
        int skip = Math.max(skipUp, skipLeft);
        return Math.max(skip, pick);
    }
}
