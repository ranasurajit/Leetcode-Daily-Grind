class Solution {
    private int m;
    private int n;

    /**
     * Approach I : Using 2D - Matrix Simulation Approach
     *
     * TC: O(M x N x Min(M, N) x K²)
     * SC: O(1)
     */
    public int largestMagicSquare(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        /**
         * Every 1 x 1 grid is a magic square so we should try to
         * find atleast 2 x 2 and above grid if it forms a magic square
         */
        int maxK = 1;
        for (int i = 0; i < m - 1; i++) { // TC: O(M)
            for (int j = 0; j < n - 1; j++) { // TC: O(N)
                // (i, j) is the start cell of the matrix
                int p = Math.min(n - j, m - i); // maximum size k till k = 2 can be checked
                for (int k = p; k >= 2; k--) { // TC: O(Min(M, N))
                    if (isValidMagicSquare(i, j, k, grid)) { // TC: O(K²)
                        maxK = Math.max(maxK, k);
                    }
                }
            }
        }
        return maxK;
    }

    /**
     * Using 2D - Matrix Simulation Approach
     *
     * TC: O(K) + O(K²) + O(K²) + O(K²) ~ O(K²)
     * SC: O(1)
     */
    private boolean isValidMagicSquare(int r, int c, int k, int[][] grid) {
        int target = 0;
        for (int j = c; j < Math.min(n, c + k); j++) { // TC: O(K)
            target += grid[r][j];
        }
        // check for diagonals
        int leftDiagSum = 0;
        int rightDiagSum = 0;
        for (int i = r; i < Math.min(m, r + k); i++) { // TC: O(K)
            for (int j = c; j < Math.min(n, c + k); j++) { // TC: O(K)
                if ((i - r) == (j - c)) {
                    leftDiagSum += grid[i][j];
                }
                if ((i - r) + (j - c) == k - 1) {
                    rightDiagSum += grid[i][j];
                }
            }
        }
        if (leftDiagSum != target || rightDiagSum != target) {
            return false;
        }
        // check for row-wise sums
        for (int i = r; i < Math.min(m, r + k); i++) { // TC: O(K)
            int rsum = 0;
            for (int j = c; j < Math.min(n, c + k); j++) { // TC: O(K)
                rsum += grid[i][j];
            }
            if (rsum != target) {
                return false;
            }
        }
        // check for column-wise sums
        for (int j = c; j < Math.min(n, c + k); j++) { // TC: O(K)
            int csum = 0;
            for (int i = r; i < Math.min(m, r + k); i++) { // TC: O(K)
                csum += grid[i][j];
            }
            if (csum != target) {
                return false;
            }
        }
        return true;
    }
}
