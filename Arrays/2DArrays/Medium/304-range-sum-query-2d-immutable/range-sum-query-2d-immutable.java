class NumMatrix {
    private int m;
    private int n;
    private int[][] prefixSum;

    /**
     * Approach : Using 2-D Array Simulation + Array Preprocessing (Prefix Sum) Approach
     *
     * TC: O(M x N)
     * SC: O(M x N)
     */
    public NumMatrix(int[][] matrix) {
        this.m = matrix.length;
        this.n = matrix[0].length;
        prefixSum = new int[m][n]; // SC: O(M x N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                /**
                 * prefixSum[i - 1][j - 1] i deducted once as it was added 
                 * twice while adding prefixSum[i - 1][j] and prefixSum[i][j - 1]
                 */
                prefixSum[i][j] = matrix[i][j] + 
                    (i > 0 ? prefixSum[i - 1][j] : 0) + 
                    (j > 0 ? prefixSum[i][j - 1] : 0) -
                    (i > 0 && j > 0 ? prefixSum[i - 1][j - 1] : 0);
            }
        }
    }

    /**
     * Using 2-D Array Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        /**
         * prefixSum[row1 - 1][col1 - 1] is added once as it was deducted 
         * twice while subtracting prefixSum[row1 - 1][col2] and 
         * prefixSum[row2][col1 - 1]
         */
        return prefixSum[row2][col2] - 
            (row1 > 0 ? prefixSum[row1 - 1][col2] : 0) - 
            (col1 > 0 ? prefixSum[row2][col1 - 1] : 0) +
            (row1 > 0 && col1 > 0 ? prefixSum[row1 - 1][col1 - 1] : 0);
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
