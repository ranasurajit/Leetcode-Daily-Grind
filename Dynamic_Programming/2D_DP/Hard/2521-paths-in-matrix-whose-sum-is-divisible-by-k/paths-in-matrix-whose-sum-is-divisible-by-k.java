class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private int m;
    private int n;
    private int k;
    private int[][] grid;

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(M x N x K)
     * SC: O(N x K) + O(N x K) ~ O(N x K)
     * - O(N x K) - prev and current memory
     *
     * Accepted (88 / 88 testcases passed)
     */
    public int numberOfPaths(int[][] grid, int k) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.k = k;
        this.grid = grid;
        // Initialization
        int[][] prev = new int[n][k + 1]; // SC: O(N x K)
        prev[0][grid[0][0] % k] = 1; // 1 way
        // Iterative Calls
        for (int i = 0; i < m; i++) { // TC: O(M)
            int[][] current = new int[n][k + 1]; // SC: O(N x K)
            current[0][grid[0][0] % k] = 1; // 1 way
            for (int j = 0; j < n; j++) { // TC: O(N)
                // remainder after dividing with k will range from [0 to (k - 1)]
                if (i == 0 && j == 0) {
                    continue;
                }
                for (int rem = 0; rem < k; rem++) { // TC: O(K)
                    int rightWays = 0;
                    int bottomWays = 0;
                    int prevRem = rem - (grid[i][j] % k);
                    if (prevRem < 0) {
                        prevRem += k;
                    }
                    // right movement
                    rightWays = j > 0 ? current[j - 1][prevRem] % MOD : 0;
                    // bottom movement
                    bottomWays = i > 0 ? prev[j][prevRem] % MOD : 0;
                    current[j][rem] = (rightWays + bottomWays) % MOD;
                }
            }
            prev = current;
        }
        return prev[n - 1][0];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(M x N x K)
     * SC: O(M x N x K)
     * - O(M x N x K) - memoization memory
     *
     * Accepted (88 / 88 testcases passed)
     */
    public int numberOfPathsTabulation(int[][] grid, int k) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.k = k;
        this.grid = grid;
        // Initialization
        int[][][] dp = new int[m][n][k + 1]; // SC: O(M x N x K)
        dp[0][0][grid[0][0] % k] = 1; // 1 way
        // Iterative Calls
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                // remainder after dividing with k will range from [0 to (k - 1)]
                if (i == 0 && j == 0) {
                    continue;
                }
                for (int rem = 0; rem < k; rem++) { // TC: O(K)
                    int rightWays = 0;
                    int bottomWays = 0;
                    int prevRem = rem - (grid[i][j] % k);
                    if (prevRem < 0) {
                        prevRem += k;
                    }
                    // right movement
                    rightWays = j > 0 ? dp[i][j - 1][prevRem] % MOD : 0;
                    // bottom movement
                    bottomWays = i > 0 ? dp[i - 1][j][prevRem] % MOD : 0;
                    dp[i][j][rem] = (rightWays + bottomWays) % MOD;
                }
            }
        }
        return dp[m - 1][n - 1][0];
    }

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
    public int numberOfPathsMemoization(int[][] grid, int k) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.k = k;
        this.grid = grid;
        int[][][] memo = new int[m][n][k]; // SC: O(M x N x K)
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
