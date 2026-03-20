class Solution {
    /**
     * Approach : Using 2D-Array Simulation Approach
     *
     * TC: O(M x N x K² x log(K))
     * SC: O(K²) + O(K²) ~ O(K²)
     */
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] result = new int[m - k + 1][n - k + 1];
        if (k == 1) {
            /**
             * if k = 1, so all elements in sub-matrix will be same
             * If all elements in the submatrix have the same value,
             * the answer will be 0.
             */
            return result;
        }
        for (int i = 0; i < m - k + 1; i++) { // TC: O(M - K + 1)
            for (int j = 0; j < n - k + 1; j++) { // TC: O(M - K + 1)
                TreeSet<Integer> set = new TreeSet<>(); // SC: O(K x K)
                for (int p = i; p < i + k; p++) { // TC: O(K)
                    for (int q = j; q < j + k; q++) { // TC: O(K)
                        set.add(grid[p][q]); // TC: O(log(K x K)) ~ O(log(K))
                    }
                }
                if (set.size() == 1) {
                    result[i][j] = 0;
                } else {
                    int minDiff = Integer.MAX_VALUE;
                    List<Integer> uniqueList = new ArrayList<>(set); // SC: O(K x K)
                    for (int p = 1; p < uniqueList.size(); p++) {    // TC: O(K x K)
                        minDiff = Math.min(minDiff,
                            uniqueList.get(p) - uniqueList.get(p - 1));
                    }
                    result[i][j] = minDiff;
                }
            }
        }
        return result;
    }
}
