class Solution {
    private static final int[][] directions = {
        { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } 
    };

    private int m;
    private int n;
    private char[][] grid; 

    /**
     * Approach : Using Graph + DFS Approach
     *
     * TC : O((m x n)
     * SC : O(m x n) + O(m + n) ~ O(m x n)
     */
    public boolean containsCycle(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        boolean[][] visited = new boolean[m][n]; // SC : O(m x n)
        for (int i = 0; i < m; i++) {     // TC : O(m)
            for (int j = 0; j < n; j++) { // TC : O(n)
                if (!visited[i][j] && 
                    dfsGridHasCycle(i, j, -1, -1, visited)) {
                    // each cell is visited exactly once
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Using DFS Approach
     *
     * TC : O(m x n)
     * SC : O(m + n)
     */
    private boolean dfsGridHasCycle(int i, int j, int parentX, int parentY,
        boolean[][] visited) {
        visited[i][j] = true;
        char value = grid[i][j];
        for (int[] dir : directions) { // TC : O(4)
            int effX = i + dir[0];
            int effY = j + dir[1];
            if (effX >= 0 && effX < m && effY >= 0 && effY < n && 
                grid[effX][effY] == value) {
                if (!visited[effX][effY]) {
                    if (dfsGridHasCycle(effX, effY, i, j, visited)) {
                        return true;
                    }
                } else if (!(effX == parentX && effY == parentY)) {
                    return true;
                }
            }
        }
        return false;
    }
}
