class Solution {
    /**
     * Approach : Using Greedy + Matrix Simulation Approach
     * Intuition: By performing operations any number of times
     *            1. If countNegatives is even then it is possible 
     *               to convert all of them into positives
     *            2. If countNegatives is odd then it is not possible 
     *               to convert all of them into positives, then we
     *               should try to make the minimum absolute value to
     *               be negative to maximize the total sum
     * TC: O(N x N)
     * SC: O(1)
     */
    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        int countNegatives = 0;
        long minAbsolute = Long.MAX_VALUE;
        long totalAbsSum = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (matrix[i][j] < 0) {
                    countNegatives++;
                }
                totalAbsSum += Math.abs(matrix[i][j]);
                minAbsolute = Math.min(minAbsolute, Math.abs(matrix[i][j]));
            }
        }
        if ((countNegatives & 1) == 0) {
            // count of negative numbers are even, so we can transform all of them to positives
            return totalAbsSum;
        }
        return totalAbsSum - 2 * minAbsolute;
    }
}
