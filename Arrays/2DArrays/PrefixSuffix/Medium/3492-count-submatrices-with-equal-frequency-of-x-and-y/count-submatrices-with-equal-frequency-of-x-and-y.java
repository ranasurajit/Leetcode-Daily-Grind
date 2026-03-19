class Solution {
    /**
     * Approach II : Using Optimal (Prefix-Array) Approach
     *
     * TC: O(M x N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * Intuition:
         * Since all valid submatrices must start from (0,0),
         * we don’t need a full 2D prefix matrix.
         * Instead, we compress the computation by maintaining
         * column-wise cumulative contributions of (X - Y) and
         * count of X up to the current row.
         * For each row, we accumulate these column values 
         * horizontally to build the result for submatrices ending
         * at (i, j). If the cumulative difference becomes 0 
         * and there is at least one 'X', we count it as a 
         * valid submatrix.
         */
        int[] colDiff = new int[n]; // SC: O(N)
        int[] colX = new int[n];    // SC: O(N)
        int count = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            int currentDiff = 0;
            int currentXSum = 0;
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (grid[i][j] == 'X') {
                    colDiff[j] += 1;
                    colX[j] += 1;
                } else if (grid[i][j] == 'Y') {
                    colDiff[j] -= 1;
                }
                // for sub-matrix (0, 0) to (i, j)
                currentDiff += colDiff[j];
                currentXSum += colX[j];
                if (currentDiff == 0 && currentXSum > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Approach I : Using Brute-Force (Prefix-Array) Approach
     *
     * TC: O(M x N)
     * SC: O(M x N) + O(M x N) ~ O(M x N)
     */
    public int numberOfSubmatricesBruteForce(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * Intuition here is to pre-process the grid to
         * find prefix counts of X's and Y's across cells
         * and then increase count if prefixX's = prefixY's 
         */
        int[][] prefixX = new int[m][n];  // SC: O(M x N)
        int[][] prefixY = new int[m][n];  // SC: O(M x N)
        int count = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                prefixX[i][j] = (grid[i][j] == 'X' ? 1 : 0) + 
                    (i > 0 ? prefixX[i - 1][j] : 0) + 
                    (j > 0 ? prefixX[i][j - 1] : 0) - 
                    (i > 0 && j > 0 ? prefixX[i - 1][j - 1] : 0);
                prefixY[i][j] = (grid[i][j] == 'Y' ? 1 : 0) + 
                    (i > 0 ? prefixY[i - 1][j] : 0) + 
                    (j > 0 ? prefixY[i][j - 1] : 0) - 
                    (i > 0 && j > 0 ? prefixY[i - 1][j - 1] : 0);
                if (prefixX[i][j] != 0 && prefixX[i][j] == prefixY[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
