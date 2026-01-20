class Solution {
    private int m;
    private int n;
    private int totalToVisit = 0;
    private static final int[][] directions = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O (M x N) + O(3 ^ (M x N)) ~ O(3 ^ (M x N))
     *     each cell can explore 3 directions
     * SC: O(M x N)
     */
    public int uniquePathsIII(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        int x = 0;
        int y = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (grid[i][j] == 1) {
                    x = i;
                    y = j;
                    this.totalToVisit++;
                } else if (grid[i][j] == 0) {
                    this.totalToVisit++;
                }
            }
        }
        return backtrack(x, y, 0, grid); // TC: O(3 ^ (M x N)), SC: O(M x N)
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(4 x 3 ^ (M x N)) ~ O(3 ^ (M x N)), each cell can explore 3 directions
     * SC: O(M x N)
     */
    private int backtrack(int i, int j, int countCells, int[][] grid) {
        // Base Case
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == -1) {
            // invalid cells to walk through
            return 0;
        }
        if (grid[i][j] == 2 && countCells == totalToVisit) {
            return 1;
        }
        // Recursion Calls
        int temp = grid[i][j]; // storing it for backtrack
        grid[i][j] = -1; // marking cell { i, j } as visited in current recursion
        int paths = 0;
        for (int[] dir : directions) { // TC: O(4)
            int effX = i + dir[0];
            int effY = j + dir[1];
            paths += backtrack(effX, effY, countCells + 1, grid);
        }
        grid[i][j] = temp; // backtrack
        return paths;
    }
}
