class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private int m;
    private int n;
    private int k;
    private int[][] grid;

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(M x N x K)
     * SC: O(M x N x K) + O(M + N)
     * - O(M x N x K) - memoization memory
     * - O(M + N) - recursion stack
     *
     * Accepted (88 / 88 testcases passed)
     */
    public int numberOfPaths(int[][] grid, int k) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.k = k;
        this.grid = grid;
        int[][][] memo = new int[m][n][k];
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m , -1);
            }
        }
        return solveMemoization(m - 1, n - 1, 0, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N x K)
     * SC: O((M - 1) + (N - 1)) ~ O(M + N)
     */
    private int solveMemoization(int i, int j, int remainder, int[][][] memo) {
        // Base Case
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return (remainder + grid[0][0]) % k == 0 ? 1 : 0;
        }
        // Memoization Check
        if (memo[i][j][remainder] != -1) {
            return memo[i][j][remainder];
        }
        // Recursion Calls
        // left movement
        int leftWays = solveMemoization(i, j - 1, (remainder + grid[i][j]) % k, memo) % MOD;
        // top movement
        int topWays = solveMemoization(i - 1, j, (remainder + grid[i][j]) % k, memo) % MOD;
        return memo[i][j][remainder] = (leftWays + topWays) % MOD;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     * - O(M + N) - recursion stack
     *
     * Time Limit Exceeded (73 / 88 testcases passed)
     */
    public int numberOfPathsRecursion(int[][] grid, int k) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.k = k;
        this.grid = grid;
        return solveRecursion(m - 1, n - 1, 0);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O((M - 1) + (N - 1)) ~ O(M + N)
     */
    private int solveRecursion(int i, int j, int remainder) {
        // Base Case
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return (remainder + grid[0][0]) % k == 0 ? 1 : 0;
        }
        // Recursion Calls
        // left movement
        int leftWays = solveRecursion(i, j - 1, (remainder + grid[i][j]) % k) % MOD;
        // top movement
        int topWays = solveRecursion(i - 1, j, (remainder + grid[i][j]) % k) % MOD;
        return (leftWays + topWays) % MOD;
    }
}
