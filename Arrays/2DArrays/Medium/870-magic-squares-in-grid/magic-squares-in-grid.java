class Solution {
    /**
     * Approach : Using Simulation Approach
     *
     * TC: O(M x N)
     * SC: O(1)
     */
    public int numMagicSquaresInside(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 2; i < m; i++) {     // TC: O(M)
            for (int j = 2; j < n; j++) { // TC: O(N)
                if (isMagicSquare(grid, i, j)) { // TC: O(1)
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(9) + O(9) ~ O(1)
     * SC: O(9) ~ O(1)
     */
    private boolean isMagicSquare(int[][] grid, int r, int c) {
        int dlsum = grid[r][c - 2] + grid[r - 1][c - 1] + grid[r - 2][c];
        int drsum = grid[r - 2][c - 2] + grid[r - 1][c - 1] + grid[r][c];
        Set<Integer> hs = new HashSet<Integer>(); // SC: O(9)
        // check if both diagonals sum are same
        if (dlsum != drsum) {
            return false;
        }
        // check if all rows sum are same
        for (int i = r - 2; i <= r; i++) { // TC: O(3)
            int rsum = 0;
            for (int j = c - 2; j <= c; j++) { // TC: O(3)
                rsum += grid[i][j];
                // check if numbers are in range [1...9]
                if (grid[i][j] < 1 || grid[i][j] > 9) {
                    return false;
                }
                hs.add(grid[i][j]);
            }
            if (rsum != dlsum) {
                return false;
            }
        }
        // check if all columns sum are same
        for (int j = c - 2; j <= c; j++) { // TC: O(3)
            int csum = 0;
            for (int i = r - 2; i <= r; i++) { // TC: O(3)
                csum += grid[i][j];
            }
            if (csum != dlsum) {
                return false;
            }
        }
        // check if Set has all numbers unique
        return hs.size() == 9;
    }
}
