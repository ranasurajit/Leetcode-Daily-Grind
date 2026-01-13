class Solution {
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC: O(M x N)
     * SC: O(M x N)
     * - O(M x N) - dp array memory
     *
     * Accepted (66 / 66 testcases passed)
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n]; // SC: O(M x N)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                    continue;
                } else {
                    int topMoveSum = grid[i][j] + (i > 0 ? dp[i - 1][j] : (int) 1e9);
                    int leftMoveSum = grid[i][j] + (j > 0 ? dp[i][j - 1] : (int) 1e9);
                    dp[i][j] = Math.min(topMoveSum, leftMoveSum);
                }
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
     * Accepted (66 / 66 testcases passed)
     */
    public int minPathSumMemoization(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n]; // SC: O(M x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(m - 1, n - 1, grid, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M + N)
     */
    private int solveMemoization(int i, int j, int[][] grid, int[][] memo) {
        // Base Case
        if (i < 0 || j < 0) {
            return (int) 1e9;
        }
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        int topMoveSum = grid[i][j] + solveMemoization(i - 1, j, grid, memo);
        int leftMoveSum = grid[i][j] + solveMemoization(i, j - 1, grid, memo);
        return memo[i][j] = Math.min(topMoveSum, leftMoveSum);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     * O(M + N) - recursion stack space
     *
     * Time Limit Exceeded (25 / 66 testcases passed)
     */
    public int minPathSumRecursion(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return solveRecursion(m - 1, n - 1, grid);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     */
    private int solveRecursion(int i, int j, int[][] grid) {
        // Base Case
        if (i < 0 || j < 0) {
            return (int) 1e9;
        }
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        // Recursion Calls
        int topMoveSum = grid[i][j] + solveRecursion(i - 1, j, grid);
        int leftMoveSum = grid[i][j] + solveRecursion(i, j - 1, grid);
        return Math.min(topMoveSum, leftMoveSum);
    }
}
