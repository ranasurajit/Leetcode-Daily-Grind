class Solution {
    private int m;
    private int n;

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(m x n x 1024)
     * SC : O(m + n) + O(m x n x 1024)
     *
     * Accepted (999 / 999 testcases passed)
     */
    public int minCost(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        int[][][] memo = new int[m][n][1024]; // SC : O(m x n x 1024)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        return solveMemoization(0, 0, 0, grid, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(m x n x 1024)
     * SC : O(m + n)
     */
    private int solveMemoization(int i, int j, int xor,
        int[][] grid, int[][][] memo) {
        // Base Case
        if (i >= m || j >= n) {
            return Integer.MAX_VALUE;
        }
        xor = (xor ^ grid[i][j]);
        if (i == m - 1 && j == n - 1) {
            return xor;
        }
        // Memoization Check
        if (memo[i][j][xor] != -1) {
            return memo[i][j][xor];
        }
        // Recursion Calls
        int right = solveMemoization(i + 1, j, xor, grid, memo);
        int down = solveMemoization(i, j + 1, xor, grid, memo);
        return memo[i][j][xor] = Math.min(right, down);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(2 ^ (m + n))
     * SC : O(m + n) - recursion stack
     *
     * Time Limit Exceeded (830 / 999 testcases passed)
     */
    public int minCostRecursion(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        return solveRecursion(0, 0, 0, grid);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(2 ^ (m + n))
     * SC : O(m + n)
     */
    private int solveRecursion(int i, int j, int xor, int[][] grid) {
        // Base Case
        if (i >= m || j >= n) {
            return Integer.MAX_VALUE;
        }
        xor = (xor ^ grid[i][j]);
        if (i == m - 1 && j == n - 1) {
            return xor;
        }
        // Recursion Calls
        int right = solveRecursion(i + 1, j, xor, grid);
        int down = solveRecursion(i, j + 1, xor, grid);
        return Math.min(right, down);
    }
}
