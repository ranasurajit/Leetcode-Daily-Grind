class Solution {
    /**
     * Approach : Using Array Prefix Sum Approach
     *
     * TC: O(M x N) + O(M) + O(N) ~ O(M x N)
     * SC: O(M x N)
     */
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * Intuition is to find the prefix-sum of
         * 2D Matrix and then we can check for 
         * horizontal cuts at (n - 1)th column and
         * vertical cuts at (m - 1)th row
         */
        long[][] prefix = new long[m][n]; // SC: O(M x N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                prefix[i][j] = (long) grid[i][j] +
                    (i > 0 ? prefix[i - 1][j] : 0L) +
                    (j > 0 ? prefix[i][j - 1] : 0L) -
                    (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0L);
            }
        }
        // let's try to check for horizontal cut
        long previousSum = prefix[0][n - 1];
        for (int i = 1; i < m; i++) { // TC: O(M)
            if (prefix[m - 1][n - 1] == 2 * previousSum) {
                return true;
            }
            previousSum = prefix[i][n - 1];
        }
        // let's try to check for vertical cut
        previousSum = prefix[m - 1][0];
        for (int j = 1; j < n; j++) { // TC: O(N)
            if (prefix[m - 1][n - 1] == 2 * previousSum) {
                return true;
            }
            previousSum = prefix[m - 1][j];
        }
        // none of an cuts is possible
        return false;
    }
}
