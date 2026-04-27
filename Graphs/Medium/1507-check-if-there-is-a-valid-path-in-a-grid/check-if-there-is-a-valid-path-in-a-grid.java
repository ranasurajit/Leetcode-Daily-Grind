class Solution {
    private static final int[][][] directions = {
        {{ 0, -1 }, { 0, 1 }}, // street 1
        {{ -1, 0 }, { 1, 0 }}, // street 2
        {{ 0, -1 }, { 1, 0 }}, // street 3
        {{ 1, 0 }, { 0, 1 }}, // street 4
        {{ 0, -1 }, { -1, 0 }}, // street 5
        {{ -1, 0 }, { 0, 1 }} // street 6
    };

    private int m;
    private int n;
    private int[][] grid;

    /**
     * Approach : Using DFS Approach
     *
     * TC : O(m x n)
     * SC : O(m x n) + O(m x n) ~ O(m x n)
     *
     * O(m x n) - recursion stack
     * O(m x n) - visited array space
     */
    public boolean hasValidPath(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        boolean[][] visited = new boolean[m][n]; // SC : O(m x n)
        if (dfsGrid(0, 0, visited)) { // TC : O(m x n), SC : O(m x n) 
            return true;
        }
        return false;
    }

    /**
     * Using DFS Approach
     *
     * TC : O(4 x (m x n)) ~ O(m x n), worst case all cells are visited once
     * SC : O(m x n)
     */
    private boolean dfsGrid(int i, int j, boolean[][] visited) {
        if (i == m - 1 && j == n - 1) {
            // reached destination cell
            return true;
        }
        visited[i][j] = true;
        int streetType = grid[i][j] - 1;
        int[][] dir = directions[streetType];
        for (int[] d : dir) { // TC : O(2)
            int x = i + d[0];
            int y = j + d[1];
            if (x < 0 || x >= m || y < 0 || y >= n) {
                // invalid cells
                continue;
            }
            if (visited[x][y]) {
                // already visited
                continue;
            }
            int newStreetType = grid[x][y] - 1;
            int[][] newDir = directions[newStreetType];
            for (int[] nd : newDir) { // TC : O(2)
                /**
                 * If we can go back from (x, y) to previous cell 
                 * (i, j) from where we came then it forms a path
                 */
                if (nd[0] == -d[0] && nd[1] == -d[1] && dfsGrid(x, y, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}
