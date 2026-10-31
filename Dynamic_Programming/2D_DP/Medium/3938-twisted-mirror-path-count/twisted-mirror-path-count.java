class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private int m;
    private int n;

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(m x n x 2) ~ O(m x n)
     * SC : O(m x n x 2) + O(m + n) ~ O(m x n) + O(m + n)
     * - O(m x n) - memoization memory
     * - O(m + n) - recursion stack
     * Accepted (521 / 521 testcases passed)
     */
    public int uniquePaths(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        long[][][] memo = new long[m][n][2]; // SC : O(m x n x 2)
        for (long[][] mem : memo) {
            for (long[] m : mem) {
                Arrays.fill(m, -1L);
            }
        }
        return (int) solveMemoization(0, 0, 0, grid, memo) % MOD;
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(m x n)
     * SC : O(m + n)
     */
    private long solveMemoization(int i, int j, int dir,
        int[][] grid, long[][][] memo) {
        // Base Case
        if (i == m - 1 && j == n - 1) {
            // reached the destination cell
            return 1L;
        }
        if (i >= m || j >= n) {
            // invalid cell
            return 0L;
        }
        // Memoization Check
        if (memo[i][j][dir] != -1L) {
            return memo[i][j][dir];
        }
        // Recursion Calls
        // check if grid[i][j] is a mirror cell
        long right = 0L;
        long down = 0L;
        if (grid[i][j] == 0) {
            // normal cell then the robot can just move right or down
            right = solveMemoization(i, j + 1, 0, grid, memo);
            down = solveMemoization(i + 1, j, 1, grid, memo);
        } else {
            // mirror cell
            if (dir == 0) {
                // entered from right so reflected down
                down = solveMemoization(i + 1, j, 1, grid, memo);
            } else {
                // entered from down so reflected right
                right = solveMemoization(i, j + 1, 0, grid, memo);
            }
        }
        return memo[i][j][dir] = (down + right) % MOD;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(2 ^ (m + n))
     * SC : O(m + n)
     * - O(m + n) - recursion stack
     * Time Limit Exceeded (406 / 521 testcases passed)
     */
    public int uniquePathsRecursion(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        return (int) solveRecursion(0, 0, 0, grid) % MOD;
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(2 ^ (m + n))
     * SC : O(m + n)
     */
    private long solveRecursion(int i, int j, int dir, int[][] grid) {
        // Base Case
        if (i == m - 1 && j == n - 1) {
            // reached the destination cell
            return 1L;
        }
        if (i >= m || j >= n) {
            // invalid cell
            return 0L;
        }
        // Recursion Calls
        // check if grid[i][j] is a mirror cell
        long right = 0L;
        long down = 0L;
        if (grid[i][j] == 0) {
            // normal cell then the robot can just move right or down
            right = solveRecursion(i, j + 1, 0, grid);
            down = solveRecursion(i + 1, j, 1, grid);
        } else {
            // mirror cell
            if (dir == 0) {
                // entered from right so reflected down
                down = solveRecursion(i + 1, j, 1, grid);
            } else {
                // entered from down so reflected right
                right = solveRecursion(i, j + 1, 0, grid);
            }
        }
        return down + right;
    }
}
