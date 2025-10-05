class Solution {
    private int m;
    private int n;
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    /**
     * Using DFS Approach
     *
     * TC: O(M x N) + O(M x N) ~ O(M x N), each cell is visited exactly once
     * SC: O(M x N) + O(M x N) + O(M x N) ~ O(M x N)
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacificVisited = new boolean[m][n];  // SC: O(M x N)
        boolean[][] atlanticVisited = new boolean[m][n]; // SC: O(M x N)
        // checking reverse path - pacific to reachable cells
        // left
        for (int i = 0; i < m; i++) { // TC: O(M)
            if (!pacificVisited[i][0]) {
                dfsGraph(i, 0, pacificVisited, heights); // TC: O(M x N), SC: O(M x N)
            }
        }
        // top
        for (int j = 0; j < n; j++) { // TC: O(N)
            if (!pacificVisited[0][j]) {
                dfsGraph(0, j, pacificVisited, heights); // TC: O(M x N), SC: O(M x N)
            }
        }
        // checking reverse path - atlantic to reachable cells
        // right
        for (int i = 0; i < m; i++) { // TC: O(M)
            if (!atlanticVisited[i][n - 1]) {
                dfsGraph(i, n - 1, atlanticVisited, heights); // TC: O(M x N), SC: O(M x N)
            }
        }
        // bottom
        for (int j = 0; j < n; j++) { // TC: O(N)
            if (!atlanticVisited[m - 1][j]) {
                dfsGraph(m - 1, j, atlanticVisited, heights); // TC: O(M x N), SC: O(M x N)
            }
        }
        /**
         * finding the common cells which is marked visited in both 
         * visited arrays 'pacificVisited' and 'atlanticVisited'
         */
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    /**
     * Using DFS Approach
     *
     * TC: O(M x N), each cell is visited exactly once
     * SC: O(M x N)
     */
    private void dfsGraph(int r, int c, boolean[][] visited, int[][] heights) {
        if (visited[r][c]) {
            // already visited
            return;
        }
        visited[r][c] = true;
        for (int[] dir : directions) { // TC: O(4)
            int effRow = r + dir[0];
            int effCol = c + dir[1];
            if (effRow < 0 || effRow >= m || effCol < 0 || effCol >= n) {
                continue;
            }
            if (heights[effRow][effCol] >= heights[r][c]) {
                dfsGraph(effRow, effCol, visited, heights);
            }
        }
    }
}
