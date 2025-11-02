class Solution {
    /**
     * Approach : Using Matrix + Array Simulation + Array Pre-Processing Approach
     *
     * TC: O(G + W) + O(2 x M x N) + O(2 x M x N) + O(M x N) ~ O(M x N)
     * SC: O(M x N)
     *
     * O(G + W) ~ O(M x N) as per constraints: 2 <= guards.length + walls.length <= m * n
     */
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n]; // SC: O(M x N)
        for (int[] g : guards) { // TC: O(G)
            // using 2 as cells value where Guards are present
            grid[g[0]][g[1]] = 2;
        }
        for (int[] w : walls) { // TC: O(W)
            // using 1 as cells value where Walls are present
            grid[w[0]][w[1]] = 1;
        }
        // cells having value 0 are unguarded cells but we need to mark the cells in vicinity of guards
        // traversing row-wise in the grid
        for (int i = 0; i < m; i++) { // TC: O(M)
            boolean seen = false;
            // sweeping from left to right
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (grid[i][j] == 1) {
                    // wall found so cannot traverse more towards right
                    seen = false;
                } else if (grid[i][j] == 2) {
                    seen = true;
                }
                if (seen && grid[i][j] == 0) {
                    // guarded cell is marked 3
                    grid[i][j] = 3;
                }
            }
            seen = false;
            // sweeping from right to left
            for (int j = n - 1; j >= 0; j--) { // TC: O(N)
                if (grid[i][j] == 1) {
                    // wall found so cannot traverse more towards right
                    seen = false;
                } else if (grid[i][j] == 2) {
                    seen = true;
                }
                if (seen && grid[i][j] == 0) {
                    // guarded cell is marked 3
                    grid[i][j] = 3;
                }
            }
        }
        // traversing column-wise in the grid
        for (int j = 0; j < n; j++) { // TC: O(N)
            boolean seen = false;
            // sweeping from top to bottom
            for (int i = 0; i < m; i++) { // TC: O(M)
                if (grid[i][j] == 1) {
                    // wall found so cannot traverse more towards right
                    seen = false;
                } else if (grid[i][j] == 2) {
                    // guard found
                    seen = true;
                }
                if (seen && grid[i][j] == 0) {
                    // guarded cell is marked 3
                    grid[i][j] = 3;
                }
            }
            seen = false;
            // sweeping from bottom to top
            for (int i = m - 1; i >= 0; i--) { // TC: O(M)
                if (grid[i][j] == 1) {
                    // wall found so cannot traverse more towards right
                    seen = false;
                } else if (grid[i][j] == 2) {
                    // guard found
                    seen = true;
                }
                if (seen && grid[i][j] == 0) {
                    // guarded cell is marked 3
                    grid[i][j] = 3;
                }
            }
        }
        // now counting the unguarded cells
        int unGuardedCells = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (grid[i][j] == 0) {
                    unGuardedCells++;
                }
            }
        }
        return unGuardedCells;
    }
}
