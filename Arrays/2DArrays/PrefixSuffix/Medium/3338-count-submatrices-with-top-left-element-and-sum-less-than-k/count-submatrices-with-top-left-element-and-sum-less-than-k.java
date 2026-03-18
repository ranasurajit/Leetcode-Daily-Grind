class Solution {
    /**
     * Approach II : Using Better(Prefix-Sum - Single Pass) Approach
     *
     * TC: O(M x N)
     * SC: O(M x N)
     */
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * Intuition is to compute the prefix sum of
         * the 2D matrix grid and then count the 
         * number of cells greater than k)
         *
         * prefix[i][j] = grid[i][j] + prefix[i - 1][j] +
         * prefix[i][j - 1] - prefix[i - 1][j - 1]
         * compute count on-the-go
         */
        int[][] prefix = new int[m][n];   // SC: O(M x N)
        int count = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                prefix[i][j] = grid[i][j] + 
                    (i > 0 ? prefix[i - 1][j] : 0) + 
                    (j > 0 ? prefix[i][j - 1] : 0) - 
                    (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0);
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
         * number of cells greater than k)
         *
         * prefix[i][j] = grid[i][j] + prefix[i - 1][j] +
         * prefix[i][j - 1] - prefix[i - 1][j - 1]
         */
        int[][] prefix = new int[m][n];   // SC: O(M x N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                prefix[i][j] = grid[i][j] + 
                    (i > 0 ? prefix[i - 1][j] : 0) + 
                    (j > 0 ? prefix[i][j - 1] : 0) - 
                    (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0);
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
