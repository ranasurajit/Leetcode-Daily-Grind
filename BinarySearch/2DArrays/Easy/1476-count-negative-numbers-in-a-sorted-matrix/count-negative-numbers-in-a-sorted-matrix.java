class Solution {
    /**
     * Approach I : Using 2D Array Simulation Approach
     *
     * TC: O(M x N)
     * SC: O(1)
     */
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {          // TC: O(M)
            for (int j = n - 1; j >= 0; j--) { // TC: O(N)
                if (grid[i][j] >= 0) {
                    // early break - as no elements would be negative towards left
                    break;
                }
                count++;
            }
        }
        return count;
    }
}
