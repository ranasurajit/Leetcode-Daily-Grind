class Solution {
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC: O(M x N)
     * SC: O(M x N)
     * - O(M x N) - dp array memory
     *
     * Accepted (64 / 64 testcases passed)
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];       // SC: O(M x N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int downWays = j > 0 ? dp[i][j - 1] : 0;
                int rightWays = i > 0 ? dp[i - 1][j] : 0;
                dp[i][j] = downWays + rightWays;
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(M x N)
     * SC: O(M x N) + O(M + N)
     * - O(M x N) - memoization memory
     * - O(M + N) - recursion stack space
     *
     * Accepted (64 / 64 testcases passed)
     */
    public int uniquePathsMemoization(int m, int n) {
        int[][] memo = new int[m][n]; // SC: O(M x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(m - 1, n - 1, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M + N) - recursion stack space
     */
    private int solveMemoization(int i, int j, int[][] memo) {
        // Base Case
        if (i < 0 || j < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            // when robot reaches cell (0, 0), we get 1 unique path
            return 1;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        // here we are moving in opposite direction bottom-right to top-left
        int topWays = solveMemoization(i, j - 1, memo);
        int leftWays = solveMemoization(i - 1, j, memo);
        return memo[i][j] = topWays + leftWays;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     * O(M + N) - recursion stack space
     *
     * Time Limit Exceeded (39 / 64 testcases passed)
     */
    public int uniquePathsRecursion(int m, int n) {
        return solveRecursion(m - 1, n - 1);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N) - recursion stack space
     */
    private int solveRecursion(int i, int j) {
        // Base Case
        if (i < 0 || j < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            // when robot reaches cell (0, 0), we get 1 unique path
            return 1;
        }
        // Recursion Calls
        // here we are moving in opposite direction bottom-right to top-left
        int topWays = solveRecursion(i, j - 1);
        int leftWays = solveRecursion(i - 1, j);
        return topWays + leftWays;
    }
}
