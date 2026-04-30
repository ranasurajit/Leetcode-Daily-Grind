class Solution {
    private int m;
    private int n;
    private int[][] grid;

    /**
     * Approach II : Using Memoization Approach
     *
     * TC : O(m x n x k)
     * SC : O(m x n x k) + O(m + n)
     * - O(m x n x k) - memoization memory
     * - O(m + n) - recursion stack
     */
    public int maxPathScore(int[][] grid, int k) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        int[][][] memo = new int[m][n][k + 1]; // SC : O(m x n x k)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        int maxScore = solveMemoization(0, 0, k, memo);
        return maxScore == Integer.MIN_VALUE ? -1 : maxScore;
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(m x n x k)
     * SC : O(m + n)
     */
    private int solveMemoization(int i, int j, int k, int[][][] memo) {
        // Base Case
        if (k < 0) {
            // Invalid paths
            return Integer.MIN_VALUE;
        }
        if (i >= m || j >= n) {
            // Invalid paths
            return Integer.MIN_VALUE;
        }
        if (i == m - 1 && j == n - 1) {
            int cost = grid[i][j] == 0 ? 0 : 1;
            return k - cost >= 0 ? grid[i][j] : Integer.MIN_VALUE;
        }
        // Memoization Check
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        // Recursion Calls
        int currentCost = grid[i][j] == 0 ? 0 : 1;
        int downScore = Integer.MIN_VALUE;
        int rightScore = Integer.MIN_VALUE;
        downScore = solveMemoization(i + 1, j, k - currentCost, memo);
        rightScore = solveMemoization(i, j + 1, k - currentCost, memo);
        if (downScore == Integer.MIN_VALUE && rightScore == Integer.MIN_VALUE) {
            return memo[i][j][k] = Integer.MIN_VALUE;
        }
        return memo[i][j][k] = grid[i][j] + Math.max(downScore, rightScore);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(2 ^ (m + n))
     * SC : O(m + n)
     * - O(m + n) - recursion stack
     */
    public int maxPathScoreRecursion(int[][] grid, int k) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        int maxScore = solveRecursion(0, 0, k);
        return maxScore == Integer.MIN_VALUE ? -1 : maxScore;
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(2 ^ (m + n))
     * SC : O(m + n)
     */
    private int solveRecursion(int i, int j, int k) {
        // Base Case
        if (k < 0) {
            // Invalid paths
            return Integer.MIN_VALUE;
        }
        if (i >= m || j >= n) {
            // Invalid paths
            return Integer.MIN_VALUE;
        }
        if (i == m - 1 && j == n - 1) {
            int cost = grid[i][j] == 0 ? 0 : 1;
            return k - cost >= 0 ? grid[i][j] : Integer.MIN_VALUE;
        }
        // Recursion Calls
        int currentCost = grid[i][j] == 0 ? 0 : 1;
        int downScore = Integer.MIN_VALUE;
        int rightScore = Integer.MIN_VALUE;
        downScore = solveRecursion(i + 1, j, k - currentCost);
        rightScore = solveRecursion(i, j + 1, k - currentCost);
        if (downScore == Integer.MIN_VALUE && rightScore == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return grid[i][j] + Math.max(downScore, rightScore);
    }
}
