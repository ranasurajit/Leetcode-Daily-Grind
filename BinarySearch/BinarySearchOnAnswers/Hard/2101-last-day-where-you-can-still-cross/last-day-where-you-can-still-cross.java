class Solution {
    private int n;
    private int row;
    private int col;
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    /**
     * Approach II : Using Disjoint Set Union Find Approach
     *
     * TC: O((M x N) x α(M x N))
     * SC: O(M x N) - reused for α(M x N) times
     */
    public int latestDayToCross(int row, int col, int[][] cells) {
        this.n = cells.length;
        this.row = row;
        this.col = col;
        int total = row * col;
        int TOP = total;
        int BOTTOM = total + 1;
        UnionFind uf = new UnionFind(total + 2); // include TOP and BOTTOM
        boolean[][] land = new boolean[row][col]; // SC: O(M x N)
        /**
         * To minimize operations we can think land is all covered with water and 
         * we can iterate on cells array backwards by removing water from cells[i] 
         */
        for (int i = n - 1; i >= 0; i--) { // TC: O(M x N)
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            land[r][c] = true;
            int id = r * col + c;
            for (int[] dir : directions) { // TC: O(4)
                int effRow = r + dir[0];
                int effCol = c + dir[1];
                if (effRow >= 0 && effRow < row && 
                    effCol >= 0 && effCol < col && 
                    land[effRow][effCol]) {
                    uf.unionByRank(id, effRow * col + effCol); // TC: O(α(M x N))
                }
            }
            /**
             * union top row and bottom row with TOP and BOTTOM virtual nodes as all top and 
             * bottom rows should act like a bridge where if land, one can walk through
             */
            if (r == 0) {
                uf.unionByRank(id, TOP); // TC: O(α(M x N))
            }
            if (r == row - 1) {
                uf.unionByRank(id, BOTTOM); // TC: O(α(M x N))
            }
            // check connectivity of the bridge
            if (uf.find(TOP) == uf.find(BOTTOM)) { // TC: O(α(M x N))
                return i;
            }
        }
        return 0;
    }

    /**
     * Using Disjoint Set Union Find Approach
     *
     * SC: O(M x N)
     */
    private class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
            this.rank = new int[n];
        }

        /**
         * Using Disjoint Set Union (Union By Rank) Approach
         *
         * TC: O(α(V))
         * SC: O(V)
         */
        public int find(int x) {
            if (x == parent[x]) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        /**
         * Using Disjoint Set Union (Union By Rank) Approach
         *
         * TC: O(α(V)) 
         * SC: O(1)
         */
        private void unionByRank(int x, int y) {
            int xParent = find(x); // TC: O(α(V))
            int yParent = find(y); // TC: O(α(V)) 
            if (xParent == yParent) {
                return;
            }
            if (rank[xParent] > rank[yParent]) {
                // make xParent as parent of yParent
                parent[yParent] = xParent;
            } else if (rank[xParent] < rank[yParent]) {
                // make yParent as parent of xParent
                parent[xParent] = yParent;
            } else {
                // make anyone as parent increasing its rank
                parent[yParent] = xParent;
                rank[xParent]++;
            }
        }
    }

    /**
     * Approach I : Using Binary Search on Answers + Multi-Source BFS Approach
     *
     * TC: O((M x N) x log(M x N))
     * SC: O(M x N) - reused for log(M x N) times
     */
    public int latestDayToCrossBinarySearchWithMultiSourceBFS(int row, int col, int[][] cells) {
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
