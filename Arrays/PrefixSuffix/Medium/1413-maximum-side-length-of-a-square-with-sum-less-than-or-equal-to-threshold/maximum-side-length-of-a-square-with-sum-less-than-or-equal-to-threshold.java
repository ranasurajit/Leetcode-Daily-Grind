class Solution {
    private int m;
    private int n;

    /**
     * Approach I : Using Prefix-Sum Approach
     *
     * TC: O(M x N) + O(M x N x Min(M, N)) ~ O(M x N x Min(M, N))
     * SC: O(M x N)
     */
    public int maxSideLength(int[][] mat, int threshold) {
        this.m = mat.length;
        this.n = mat[0].length;
        // pre-compute prefixSum from matrix 'mat' so that lookup is in O(1) time complexity
        int[][] prefixSum = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                prefixSum[i + 1][j + 1] = mat[i][j] + 
                    prefixSum[i + 1][j] + prefixSum[i][j + 1] - prefixSum[i][j];
            }
        }
        /**
         * Now doing a look-up for every starting cell r,c till the side k
         */
        int maxSide = 0;
        for (int r = 0; r < m; r++) {     // TC: O(M)
            for (int c = 0; c < n; c++) { // TC: O(N)
                // compute maximum square side range
                int p = Math.min(m - r, n - c);
                for (int k = p; k > maxSide; k--) { // TC: O(Min(M, N))
                    int subMatrixSum = prefixSum[r + k][c + k] - 
                        prefixSum[r][c + k] - prefixSum[r + k][c] + prefixSum[r][c];
                    if (subMatrixSum <= threshold) {
                        maxSide = k;
                        break;
                    }
                }
            }
        }
        return maxSide;
    }
}
