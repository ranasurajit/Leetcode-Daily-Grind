class Solution {
    private int n;
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    /**
     * Approach : Using Dijkstra's Algorithm Approach
     *
     * TC: O(4 x N x N x log(N x N)) ~ O(N ^ 2 x log(N))
     * SC: O(N x N) + O(N x N) ~ O(N x N)
     */
    public int swimInWater(int[][] grid) {
        this.n = grid.length;
        int[][] minTime = new int[n][n]; // SC: O(N x N)
        for (int[] t : minTime) {              // TC: O(N)
            Arrays.fill(t, Integer.MAX_VALUE); // TC: O(N)
        }
        // we will be using Min-Heap to store { row, col, time } in order of time
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((p, q) -> p.time - q.time); // SC: O(N x N)
        minTime[0][0] = grid[0][0];
        pq.offer(new Pair(0, 0, grid[0][0]));
        while (!pq.isEmpty()) { // TC: O(N x N)
            Pair current = pq.poll();
            int row = current.row;
            int col = current.col;
            int time = current.time;
            if (row == n - 1 && col == n - 1) {
                return time;
            }
            for (int[] dir : directions) { // TC: O(4)
                int effRow = row + dir[0];
                int effCol = col + dir[1];
                if (effRow >= 0 && effRow < n && effCol >= 0 && effCol < n) {
                    /**
                     * calculate new time to wait until the current cells 
                     * elevation becomes same as adjacent cell's elevation
                     */
                    int newTime = Math.max(grid[effRow][effCol], time);
                    if (minTime[effRow][effCol] > newTime) {
                        minTime[effRow][effCol] = newTime;
                        pq.offer(new Pair(effRow, effCol, newTime)); // TC: O(log(N x N))
                    }
                }
            }
        }
        return -1;
    }

    private static class Pair {
        int row;
        int col;
        int time;

        public Pair (int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
