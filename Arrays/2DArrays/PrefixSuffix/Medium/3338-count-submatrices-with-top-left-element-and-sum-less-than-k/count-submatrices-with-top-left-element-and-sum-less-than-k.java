class Solution {
    /**
     * Approach III : Using Optimal(Prefix-Sum - Single Pass, Inplace Replacement) Approach
     *
     * TC: O(M x N)
     * SC: O(1)
     */
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * Intuition is to compute the prefix sum of
         * the 2D matrix grid and then count the 
         * number of prefixSum cells <= k
         * as grid[i][j] >= 0)
         *
         * grid[i][j] = grid[i][j] + grid[i - 1][j] +
         * grid[i][j - 1] - grid[i - 1][j - 1]
         * compute count on-the-go
         */
        int count = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                long currentSum = (long) grid[i][j] + 
                    (i > 0 ? (long) grid[i - 1][j] : 0L) + 
                    (j > 0 ? (long) grid[i][j - 1] : 0L) - 
                    (i > 0 && j > 0 ? (long) grid[i - 1][j - 1] : 0L);
                if (currentSum <= k) {
                    count++;
                }
                grid[i][j] = (int) currentSum;
            }
        }
        return count;
    }

    /**
     * Approach II : Using Better(Prefix-Sum - Single Pass) Approach
     *
     * TC: O(M x N)
     * SC: O(M x N)
     */
    public int countSubmatricesBetter(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * Intuition is to compute the prefix sum of
         * the 2D matrix grid and then count the 
         * number of prefixSum cells <= k
         * as grid[i][j] >= 0)
         *
         * prefix[i][j] = grid[i][j] + prefix[i - 1][j] +
         * prefix[i][j - 1] - prefix[i - 1][j - 1]
         * compute count on-the-go
         */
        long[][] prefix = new long[m][n];   // SC: O(M x N)
        int count = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                prefix[i][j] = (long) grid[i][j] + 
                    (i > 0 ? prefix[i - 1][j] : 0L) + 
                    (j > 0 ? prefix[i][j - 1] : 0L) - 
                    (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0L);
                if (prefix[i][j] <= k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Approach I : Using Brute-Force(Prefix-Sum) Approach
     *
     * TC: O(M x N) + O(M x N) ~ O(M x N)
     * SC: O(M x N)
     */
    public int countSubmatricesBruteForce(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * Intuition is to compute the prefix sum of
         * the 2D matrix grid and then count the 
         * number of prefixSum cells <= k
         * as grid[i][j] >= 0)
         *
         * prefix[i][j] = grid[i][j] + prefix[i - 1][j] +
         * prefix[i][j - 1] - prefix[i - 1][j - 1]
         */
        long[][] prefix = new long[m][n];   // SC: O(M x N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                prefix[i][j] = (long) grid[i][j] + 
                    (i > 0 ? prefix[i - 1][j] : 0L) + 
                    (j > 0 ? prefix[i][j - 1] : 0L) - 
                    (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0L);
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (prefix[i][j] <= k) {
                    count++;
                }
            }
        }
        return count;
    }
}
