class Solution {
    /**
     * Approach : Using DP on Squares (Tabulation) Approach
     *
     * TC: O(M x N) + O(M x N) + O(M) + O(N) ~ O(M x N)
     * SC: O(M x N)
     */
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // Initialization
        int[][] dp = new int[m][n]; // SC: O(M x N)
        // dp[i][j] denotes the number of square sub-matrices ending at (i, j)
        for (int j = 0; j < n; j++) { // TC: O(N)
            dp[0][j] = matrix[0][j];
        }
        for (int i = 0; i < m; i++) { // TC: O(M)
            dp[i][0] = matrix[i][0];
        }
        // Iterative Calls
        for (int i = 1; i < m; i++) { // TC: O(M)
            for (int j = 1; j < n; j++) { // TC: O(N)
                dp[i][j] = matrix[i][j] == 1 ?
                    1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) : 0;
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                count += dp[i][j];
            }
        }
        return count;
    }
}
