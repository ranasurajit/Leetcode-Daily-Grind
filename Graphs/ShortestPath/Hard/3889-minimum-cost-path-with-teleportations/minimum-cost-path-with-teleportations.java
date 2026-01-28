class Solution {
    private int m;
    private int n;
    private int[][] directions = { { 0, 1 }, { 1, 0 } };

    /**
     * Approach : Using Dijkstra's Algorithm Approach
     *
     * TC: O(M x N x K) + O(M x N) + O(M x N x log(M x N)) + 
     * O(M x N x K x log(M x N)) + O(M x N x K x log(M x N)) + O(K) ~ O(M x N x K x log(M x N))
     * 
     * SC: O(M x N x K) + O(M x N) ~ O(M x N x K)
     */
    public int minCost(int[][] grid, int k) {
        this.m = grid.length;
        this.n = grid[0].length;
        /**
         * we will store { grid[i][j], i, j, t }, where t = teleports done so far
         * in Min-Heap in ascending order of grid[i][j]
         */
        PriorityQueue<State> dijkstraHeap = new PriorityQueue<State>((p, q) -> {
            return Integer.compare(p.dist, q.dist);
        }); // SC: O(M x N)
        int[][][] minCost = new int[m][n][k + 1]; // SC: O(M x N x K)
        for (int[][] mc : minCost) { // TC: O(M x N x K)
            for (int[] m : mc) {
                Arrays.fill(m, (int) 1e9);
            }
        }
        /**
         * we will also store { grid[i][j], i, j } per layer of teleportation in 
         * in a List in ascending order of grid[i][j] so that we can use
         * the minimum value cells that can be compared against current cell's
         * value for teleport
         */
        // let's push all cells value to teleportHeap
        List<int[]> cells = new ArrayList<int[]>(); // SC: O(M x N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                cells.add(new int[] { grid[i][j], i, j });
            }
        }
        cells.sort((a, b) -> Integer.compare(a[0], b[0])); // TC: O(M x N x log(M x N))
        dijkstraHeap.offer(new State(0, 0, 0, 0));
        minCost[0][0][0] = 0;
        int[] teleportState = new int[k + 1];
        while (!dijkstraHeap.isEmpty()) { // TC: O(M x N x K)
            State current = dijkstraHeap.poll();
            int dist = current.dist;
            int x = current.x;
            int y = current.y;
            int t = current.t;
            if (dist > minCost[x][y][t]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                return dist;
            }
            for (int[] dir : directions) { // TC: O(2)
                int effX = x + dir[0];
                int effY = y + dir[1];
                if (effX < 0 || effX >= m || effY < 0 || effY >= n) {
                    // out of bounds
                    continue;
                }
                if (grid[effX][effY] + dist < minCost[effX][effY][t]) {
                    minCost[effX][effY][t] = grid[effX][effY] + dist;
                    dijkstraHeap.offer(
                        new State(grid[effX][effY] + dist, effX, effY, t)
                    ); // TC: O(log(M x N))
                }
            }
            if (t < k) {
                // can do more teleports
                while (teleportState[t + 1] < cells.size() && 
                    cells.get(teleportState[t + 1])[0] <= grid[x][y]) {
                    int[] ported = cells.get(teleportState[t + 1]++);
                    int nx = ported[1];
                    int ny = ported[2];
                    if (dist < minCost[nx][ny][t + 1]) {
                        minCost[nx][ny][t + 1] = dist;
                        dijkstraHeap.offer(
                            new State(dist, nx, ny, t + 1)
                        ); // TC: O(log(M x N))
                    }
                }
            }
        }
        int minValue = Integer.MAX_VALUE;
        for (int t = 0; t <= k; t++) { // TC: O(K)
            minValue = Math.min(minValue, minCost[m - 1][n - 1][t]);
        }
        return minValue;
    }

    private class State {
        int dist;
        int x;
        int y;
        int t;

        public State (int dist, int x, int y, int t) {
            this.dist = dist;
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
}
