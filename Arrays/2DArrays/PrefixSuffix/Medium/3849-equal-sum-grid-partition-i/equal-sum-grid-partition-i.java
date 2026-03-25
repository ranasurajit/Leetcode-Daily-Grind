class Solution {
    /**
     * Approach II : Using Array Prefix Sum Approach
     *
     * TC: O(M x N) + O(M x N) + O(M x N) ~ O(M x N)
     * SC: O(1)
     */
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * Intuition is to find the total sum,
         * if total sum is odd then it is not
         * possible to divide it in any cut
         *
         * then we can try for both horizontal 
         * and vertical cuts individually by 
         * computing prefix rowSums and colSums
         * respectively and comparing with total
         * sum / 2 
         */
        long totalSum = 0L;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                totalSum += (long) grid[i][j];
            }
        }
        if ((totalSum & 1) != 0) {
            // odd sum cannot be made two equal partitions
            return false;
        }
        long targetSum = totalSum / 2;
        long cumulativeSum = 0L;
        // let's try to check for horizontal cut
        for (int i = 0; i < m - 1; i++) { // TC: O(M)
            long rowSum = 0L;
            for (int j = 0; j < n; j++) { // TC: O(N)
                rowSum += (long) grid[i][j];
            }
            cumulativeSum += rowSum;
            if (cumulativeSum == targetSum) {
                // horizontal cut is possible
                return true;
            }
        }
        // let's try to check for vertical cut
        cumulativeSum = 0L;
        for (int j = 0; j < n - 1; j++) { // TC: O(M)
            long colSum = 0L;
            for (int i = 0; i < m; i++) { // TC: O(N)
                colSum += (long) grid[i][j];
            }
            cumulativeSum += colSum;
            if (cumulativeSum == targetSum) {
                // vertical cut is possible
                return true;
            }
        }
        // none of an cuts is possible
        return false;
    }

    /**
     * Approach I : Using 2D Prefix Computation Approach
     *
     * TC: O(M x N) + O(M) + O(N) ~ O(M x N)
     * SC: O(M x N)
     */
    public boolean canPartitionGridUsing2DPrefix(int[][] grid) {
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
