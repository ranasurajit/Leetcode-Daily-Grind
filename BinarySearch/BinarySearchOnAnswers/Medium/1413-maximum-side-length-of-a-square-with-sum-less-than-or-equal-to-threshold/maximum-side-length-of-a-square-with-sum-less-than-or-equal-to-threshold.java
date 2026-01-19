class Solution {
    private int m;
    private int n;

    /**
     * Approach II : Using Prefix-Sum + Binary Search on Answers Approach
     *
     * TC: O(M x N) + O(M x N x log(Min(M, N))) ~ O(M x N x log(Min(M, N)))
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
        int low = 0;
        int high = Math.min(m, n);
        while (low <= high) { // TC: O(log(M, N))
            int mid = low + (high - low) / 2;
            if (matrixHasSum(prefixSum, mid, threshold)) { // TC: O(M x N), SC: O(1)
                // we will try to increase it
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    /**
     * Using Prefix Sum + 2D Array Simulation Approach
     *
     * TC: O(M x N)
     * SC: O(1)
     */
    private boolean matrixHasSum(int[][] prefixSum, int k, int threshold) {
        for (int r = 0; r < m; r++) {     // TC: O(M)
            for (int c = 0; c < n; c++) { // TC: O(N)
                if (r + k <= m && c + k <= n) {
                    int subMatrixSum = prefixSum[r + k][c + k] - 
                        prefixSum[r][c + k] - prefixSum[r + k][c] + prefixSum[r][c];
                    if (subMatrixSum <= threshold) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Approach I : Using Prefix-Sum Approach
     *
     * TC: O(M x N) + O(M x N x Min(M, N)) ~ O(M x N x Min(M, N))
     * SC: O(M x N)
     */
    public int maxSideLengthUsingPrefixSum(int[][] mat, int threshold) {
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
