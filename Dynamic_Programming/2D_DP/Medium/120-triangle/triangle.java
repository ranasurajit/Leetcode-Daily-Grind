class Solution {
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC: O(N x M) + O(N x M) ~ O(N x M)
     * SC: O(N x M), where M = size(triagle's last row)
     *      O(N x M) - dp array memory
     *
     * Accepted (46 / 46 testcases passed)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(n - 1).size();
        // Initialization
        int[][] dp = new int[n][m];   // SC: O(N x M)
        for (int j = 0; j < m; j++) { // TC: O(M)
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }
        // Iterative Calls
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            for (int j = 0; j < triangle.get(i).size(); j++) { // TC: O(M)
                int currentSum = triangle.get(i).get(j);
                dp[i][j] = currentSum + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        return dp[0][0];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(N x M) + O(N x M) ~ O(N x M)
     * SC: O(N x M) + O(N), where M = size(triagle's last row)
     *      O(N x M) - memoization array memory
     *      O(N) - recursion stack
     *
     * Time Limit Exceeded (27 / 46 testcases passed)
     */
    public int minimumTotalMemoization(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] memo = new int[n][]; // SC: O(N x M)
        for (int i = 0; i < n; i++) {  // TC: O(N)
            memo[i] = new int[triangle.get(i).size()];
            Arrays.fill(memo[i], -1); // TC: O(N)
        }
        return solveMemoization(0, 0, n - 1, triangle, memo); // TC: O(N x N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N) - recursion stack
     */
    private int solveMemoization(int row, int col, int n, List<List<Integer>> triangle, int[][] memo) {
        // Base Case
        if (row == n) {
            return triangle.get(row).get(col);
        }
        // Memoization Check
        if (memo[row][col] != -1) {
            return memo[row][col];
        }
        // Recursion
        int currentSum = triangle.get(row).get(col);
        int downSum = solveMemoization(row + 1, col, n, triangle, memo);
        int diagnonalSum = solveMemoization(row + 1, col + 1, n, triangle, memo);
        return memo[row][col] = currentSum + Math.min(downSum, diagnonalSum);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) - recursion stack
     *
     * Time Limit Exceeded (27 / 46 testcases passed)
     */
    public int minimumTotalRecursion(List<List<Integer>> triangle) {
        int n = triangle.size();
        return solveRecursion(0, 0, n - 1, triangle); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) - recursion stack
     */
    private int solveRecursion(int row, int col, int n, List<List<Integer>> triangle) {
        // Base Case
        if (row == n) {
            return triangle.get(row).get(col);
        }
        // Recursion
        int currentSum = triangle.get(row).get(col);
        int downSum = solveRecursion(row + 1, col, n, triangle);
        int diagnonalSum = solveRecursion(row + 1, col + 1, n, triangle);
        return currentSum + Math.min(downSum, diagnonalSum);
    }
}
