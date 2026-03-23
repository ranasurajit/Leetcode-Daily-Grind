class Solution {
    private static final int MOD = (int) 1e9 + 7;
    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(M x N)
     * SC: O(N) + O(N) ~ O(N)
     * - O(N) - previous, current array memory
     *
     * Accepted (159 / 159 testcases passed)
     */
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Pair[] previous = new Pair[n];    // SC: O(N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            Pair[] current = new Pair[n]; // SC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (i == 0 && j == 0) {
                    current[j] = new Pair(grid[0][0], grid[0][0]);
                    continue;
                }
                long min = Long.MAX_VALUE;
                long max = Long.MIN_VALUE;
                if (j > 0) {
                    Pair left = current[j - 1];
                    long a = grid[i][j] * left.min;
                    long b = grid[i][j] * left.max;
                    min = Math.min(min, Math.min(a, b));
                    max = Math.max(max, Math.max(a, b));
                }
                if (i > 0) {
                    Pair up = previous[j];
                    long a = grid[i][j] * up.min;
                    long b = grid[i][j] * up.max;
                    min = Math.min(min, Math.min(a, b));
                    max = Math.max(max, Math.max(a, b));
                }
                current[j] = new Pair(min, max);
            }
            previous = current;
        }
        long result = previous[n - 1].max;
        if (result < 0) {
            return -1;
        }
        return (int) (result % MOD);
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(M x N)
     * SC: O(M x N)
     * - O(M x N) - dp memory
     *
     * Accepted (159 / 159 testcases passed)
     */
    public int maxProductPathTabulation(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Pair[][] dp = new Pair[m][n];     // SC: O(M x N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (i == 0 && j == 0) {
                    dp[i][j] = new Pair(grid[0][0], grid[0][0]);
                    continue;
                }
                long min = Long.MAX_VALUE;
                long max = Long.MIN_VALUE;
                if (j > 0) {
                    Pair left = dp[i][j - 1];
                    long a = grid[i][j] * left.min;
                    long b = grid[i][j] * left.max;
                    min = Math.min(min, Math.min(a, b));
                    max = Math.max(max, Math.max(a, b));
                }
                if (i > 0) {
                    Pair up = dp[i - 1][j];
                    long a = grid[i][j] * up.min;
                    long b = grid[i][j] * up.max;
                    min = Math.min(min, Math.min(a, b));
                    max = Math.max(max, Math.max(a, b));
                }
                dp[i][j] = new Pair(min, max);
            }
        }
        long result = dp[m - 1][n - 1].max;
        if (result < 0) {
            return -1;
        }
        return (int) (result % MOD);
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M x N) + O(M + N)
     * - O(M x N) - memoization memory
     * - O(M + N) - recursion stack
     *
     * Accepted (159 / 159 testcases passed)
     */
    public int maxProductPathMemoization(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Pair[][] memo = new Pair[m][n]; // SC: O(M x N)
        long result = solveMemoization(m - 1, n - 1, grid, memo).max;
        if (result < 0) {
            return -1;
        }
        return (int) (result % MOD);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M + N)
     */
    private Pair solveMemoization(int i, int j, int[][] grid, Pair[][] memo) {
        // Base Case
        if (i == 0 && j == 0) {
            return new Pair(grid[i][j], grid[i][j]);
        }
        // Memoization Check
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        if (j > 0) {
            Pair left = solveMemoization(i, j - 1, grid, memo);
            long a = grid[i][j] * left.min;
            long b = grid[i][j] * left.max;
            min = Math.min(min, Math.min(a, b));
            max = Math.max(max, Math.max(a, b));
        }
        if (i > 0) {
            Pair up = solveMemoization(i - 1, j, grid, memo);
            long a = grid[i][j] * up.min;
            long b = grid[i][j] * up.max;
            min = Math.min(min, Math.min(a, b));
            max = Math.max(max, Math.max(a, b));
        }
        return memo[i][j] = new Pair(min, max);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     * - O(M + N) - recursion stack
     *
     * Time Limit Exceeded (151 / 159 testcases passed)
     */
    public int maxProductPathRecursion(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long result = solveRecursion(m - 1, n - 1, grid).max;
        if (result < 0) {
            return -1;
        }
        return (int) (result % MOD);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     */
    private Pair solveRecursion(int i, int j, int[][] grid) {
        // Base Case
        if (i == 0 && j == 0) {
            return new Pair(grid[i][j], grid[i][j]);
        }
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        if (j > 0) {
            Pair left = solveRecursion(i, j - 1, grid);
            long a = grid[i][j] * left.min;
            long b = grid[i][j] * left.max;
            min = Math.min(min, Math.min(a, b));
            max = Math.max(max, Math.max(a, b));
        }
        if (i > 0) {
            Pair up = solveRecursion(i - 1, j, grid);
            long a = grid[i][j] * up.min;
            long b = grid[i][j] * up.max;
            min = Math.min(min, Math.min(a, b));
            max = Math.max(max, Math.max(a, b));
        }
        return new Pair(min, max);
    }
}

class Pair {
    long min;
    long max;

    public Pair(long min, long max) {
        this.min = min;
        this.max = max;
    }
}
