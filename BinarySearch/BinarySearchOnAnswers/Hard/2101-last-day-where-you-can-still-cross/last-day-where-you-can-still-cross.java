class Solution {
    private int n;
    private int row;
    private int col;
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    /**
     * Approach : Using Binary Search on Answers + Multi-Source BFS Approach
     *
     * TC: O((M x N) x log(M x N))
     * SC: O(M x N) - reused for log(M x N) times
     */
    public int latestDayToCross(int row, int col, int[][] cells) {
        this.n = cells.length;
        this.row = row;
        this.col = col;
        int low = 1; // minimum day when first cell becomes flooded 
        int high = n; // maximum day when last cell becomes flooded
        while (low <= high) { // TC: O(log(M x N))
            int mid = low + (high - low) / 2;
            if (isPossibleToWalk(cells, mid)) { // TC: O(M x N), SC: O(M x N)
                /**
                 * if it is possible to cross on mid index day then it 
                 * will be possible to cross on (mid - 1)th day as well
                 */
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high; // returns the last day when walking from top to bottom is possible
    }

    /**
     * Using Multi-Source BFS Approach
     *
     * TC: O(M x N) + O(M x N) + O(N) ~ O(M x N)
     * SC: O(M x N) + O(M x N) + O(M x N) ~ O(M x N)
     */
    private boolean isPossibleToWalk(int[][] cells, int targetDay) {
        int[][] grid = new int[row][col]; // SC: O(M x N)
        for (int i = 0; i < targetDay; i++) { // TC: O(M x N)
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            grid[r][c] = 1;
        }
        // performing Multi-source BFS from all land cells in row index '0'
        boolean[][] visited = new boolean[row][col];  // SC: O(M x N)
        Queue<int[]> queue = new LinkedList<int[]>(); // SC: O(M x N)
        for (int j = 0; j < col; j++) { // TC: O(N)
            if (grid[0][j] == 0) {
                queue.offer(new int[] { 0, j });
                visited[0][j] = true;
            }
        }
        while (!queue.isEmpty()) { // TC: O(M x N)
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            if (r == row - 1) {
                // reached bottom already
                return true;
            }
            for (int[] dir : directions) { // TC: O(4)
                int effRow = r + dir[0];
                int effCol = c + dir[1];
                if (effRow < 0 || effRow >= row || effCol < 0 || effCol >= col || 
                    visited[effRow][effCol] || grid[effRow][effCol] == 1) {
                    continue;
                }
                visited[effRow][effCol] = true;
                queue.offer(new int[] { effRow, effCol });
            }
        }
        return false;
    }
}
