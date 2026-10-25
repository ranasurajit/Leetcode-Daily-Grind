class Solution {
    private int rows;
    private int cols;

    public int closedIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        // Step 1: Remove all land connected to the boundary.
        for (int i = 0; i < rows; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, cols - 1);
        }

        for (int j = 0; j < cols; j++) {
            dfs(grid, 0, j);
            dfs(grid, rows - 1, j);
        }

        // Step 2: Count the remaining islands.
        int closedIslands = 0;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0) {
                    closedIslands++;
                    dfs(grid, i, j);
                }
            }
        }

        return closedIslands;
    }

    private void dfs(int[][] grid, int row, int col) {
        if (row < 0 || row >= rows ||
            col < 0 || col >= cols ||
            grid[row][col] == 1) {
            return;
        }

        // Mark this land cell as visited.
        grid[row][col] = 1;

        dfs(grid, row + 1, col);
        dfs(grid, row - 1, col);
        dfs(grid, row, col + 1);
        dfs(grid, row, col - 1);
    }
}
