class Solution {
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC: O(M x N)
     * SC: O(M x N)
     * - O(M x N) - dp array memory
     *
     * Accepted (42 / 42 testcases passed)
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            // top-left and bottom-right cells are blocked by obstacles
            return 0;
        }
        // Initialization
        int[][] dp = new int[m][n];       // SC: O(M x N)
        // Iterative Calls
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                if (obstacleGrid[i][j] == 1) {
                    // obstacleGrid cell (i, j) has an obstacle so no path goes through this cell
                    dp[i][j] = 0;
                    continue;
                }
                int topWays = i > 0 ? dp[i - 1][j] : 0;
                int leftWays = j > 0 ? dp[i][j - 1] : 0;
                dp[i][j] = topWays + leftWays;
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
     * Accepted (42 / 42 testcases passed)
     */
    public int uniquePathsWithObstaclesMemoization(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            // top-left and bottom-right cells are blocked by obstacles
            return 0;
        }
        int[][] memo = new int[m][n];
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(m - 1, n - 1, obstacleGrid, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M + N) - recursion stack space
     */
    private int solveMemoization(int i, int j, int[][] grid, int[][] memo) {
        // Base Case
        if (i < 0 || j < 0) {
            return 0;
        }
        if (grid[i][j] == 1) {
            // grid cell (i, j) has an obstacle so no path goes through this cell
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
        int topWays = solveMemoization(i - 1, j, grid, memo);
        int leftWays = solveMemoization(i, j - 1, grid, memo);
        return memo[i][j] = topWays + leftWays;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     * O(M + N) - recursion stack space
     *
     * Time Limit Exceeded (30 / 42 testcases passed)
     */
    public int uniquePathsWithObstaclesRecursion(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            // top-left and bottom-right cells are blocked by obstacles
            return 0;
        }
        return solveRecursion(m - 1, n - 1, obstacleGrid);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N) - recursion stack space
     */
    private int solveRecursion(int i, int j, int[][] grid) {
        // Base Case
        if (i < 0 || j < 0) {
            return 0;
        }
        if (grid[i][j] == 1) {
            // grid cell (i, j) has an obstacle so no path goes through this cell
            return 0;
        }
        if (i == 0 && j == 0) {
            // when robot reaches cell (0, 0), we get 1 unique path
            return 1;
        }
        // Recursion Calls
        // here we are moving in opposite direction bottom-right to top-left
        int topWays = solveRecursion(i - 1, j, grid);
        int leftWays = solveRecursion(i, j - 1, grid);
        return topWays + leftWays;
    }
}
