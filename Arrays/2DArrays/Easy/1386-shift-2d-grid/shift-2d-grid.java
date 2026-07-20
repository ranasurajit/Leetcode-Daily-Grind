class Solution {
    /**
     * Approach II : Using Array Simulation without Shifts Approach
     *
     * TC : O(m x n) + O(m x n) ~ O(m x n)
     * SC : O(m x n)
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * the result of rotation will repeat after every (m x n) shifts
         */
        int total = m * n;
        k = k % total;
        int[][] shifted = new int[m][n];  // SC : O(m x n)
        for (int i = 0; i < m; i++) {     // TC : O(m)
            for (int j = 0; j < n; j++) { // TC : O(n)
                int oldIndex = i * n + j;
                int newIndex = (oldIndex + k) % total;
                shifted[newIndex / n][newIndex % n] = grid[i][j];
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {     // TC : O(m)
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) { // TC : O(n)
                row.add(shifted[i][j]);
            }
            result.add(row);
        }
        return result;
    }

    /**
     * Approach I : Using Array Simulation with Shifts Approach
     *
     * TC : O(m x n x k) + O(m x n) ~ O(m x n x k)
     * SC : O(1)
     */
    public List<List<Integer>> shiftGridWithRealShifts(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * the result of rotation will repeat after every (m x n) shifts
         */
        k = k % (m * n);
        for (int shift = 0; shift < k; shift++) {  // TC : O(k)
            int firstCell = grid[m - 1][n - 1];
            for (int i = m - 1; i >= 0; i--) {     // TC : O(m)
                int temp = grid[i][n - 1];
                for (int j = n - 2; j >= 0; j--) { // TC : O(n)
                    grid[i][j + 1] = grid[i][j]; 
                }
                if (i + 1 < m) {
                    grid[i + 1][0] = temp;
                } else {
                    firstCell = temp;
                }
            }
            grid[0][0] = firstCell;
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {     // TC : O(m)
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) { // TC : O(n)
                row.add(grid[i][j]);
            }
            result.add(row);
        }
        return result;
    }
}
