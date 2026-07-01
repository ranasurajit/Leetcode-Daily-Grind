class Solution {
    private static final int[][] directions = {
        { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };

    /**
     * Approach : Using Binary Search on Answers + Graph Multi-source BFS Approach
     *
     * TC : O(n²) + O(n² x log(k)) ~ O(n² x log(k))
     * SC : O(n²)
     *
     * where k = Min (minDist[0][0], minDist[n - 1][n - 1])
     */
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        /**
         * we can perform multi-source BFS from every thief
         * cells to compute the minimum manhattan distance of
         * that cell from the nearest thief cell
         */
        int[][] minDist =
            runMultiSourceBFSGrid(grid, n); // TC : O(n²), SC : O(n²)
        /**
         * no need to check if thieves are even there 
         * as per constraints, it is mentioned that
         * 'There is at least one thief in the grid'
         * so we need to basically find the maximum of the
         * minimum safeness factor which gives a hint towards
         * usage of Binary Search on Answers
         *
         * lower bound of Binary search = 0 i.e. low = 0
         * upper bound of Binary search i.e. high =
         * Min(minDist[0][0], minDist[n - 1][n - 1]) as 
         * it cannot exceed that
         */
        int low = 0;
        int high = Math.min(minDist[0][0], minDist[n - 1][n - 1]);
        int maxSF = 0;
        while (low <= high) { // TC : O(log(k))
            int mid = low + (high - low) / 2;
            /**
             * now we need to maximize the mid value
             * if mid can be the maximum of the minimum
             * safeness factor that can be possible to 
             * reach any paths from (0, 0) to (n - 1, n - 1)
             */
            if (isPathPossible(grid, n, mid, minDist)) { // TC : O(n²)
                maxSF = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return maxSF;
    }

    /**
     * Using Graph BFS Approach
     *
     * TC : O(n²)
     * SC : O(n²)
     */
    private boolean isPathPossible(List<List<Integer>> grid, int n,
        int minSF, int[][] minDist) {
        if (minDist[0][0] < minSF) {
            // safeness factor minimum of 'minSF' cannot be reached
            return false;
        }
        boolean[][] visited = new boolean[n][n]; // SC : O(n²)
        Queue<int[]> queue = new LinkedList<>(); // SC : O(n²)
        queue.offer(new int[] { 0, 0 });
        visited[0][0] = true;
        while (!queue.isEmpty()) { // TC : O(n²)
            int[] current = queue.poll();
            int i = current[0];
            int j = current[1];
            if (i == n - 1 && j == n - 1) {
                return true;
            }
            for (int[] dir : directions) { // TC : O(4)
                int i_ = i + dir[0];
                int j_ = j + dir[1];
                if (i_ < 0 || i_ >= n ||
                    j_ < 0 || j_ >= n || visited[i_][j_] ||
                    minDist[i_][j_] < minSF) {
                    // invalid cells to step in for path
                    continue;
                }
                visited[i_][j_] = true;
                queue.offer(new int[] { i_, j_ });
            }
        }
        return false;
    }

    /**
     * Using Graph Multi-source BFS Approach
     *
     * TC : O(n²)
     * SC : O(n²)
     */
    private int[][] runMultiSourceBFSGrid(List<List<Integer>> grid,
        int n) {
        int[][] minDist = new int[n][n]; // SC : O(n²)
        Queue<int[]> queue = new LinkedList<>(); // SC : O(n²)
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) { // TC : O(n)
            for (int j = 0; j < n; j++) { // TC : O(n)
                if (grid.get(i).get(j) == 1) {
                    minDist[i][j] = 0; // distance 0 from thief
                    queue.offer(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) { // TC : O(n²)
            int[] current = queue.poll();
            int i = current[0];
            int j = current[1];
            for (int[] dir : directions) { // TC : O(4)
                int i_ = i + dir[0];
                int j_ = j + dir[1];
                if (i_ < 0 || i_ >= n || j_ < 0 || j_ >= n || visited[i_][j_]) {
                    continue;
                }
                int reach = minDist[i][j] + 1;
                visited[i_][j_] = true;
                minDist[i_][j_] = reach;
                queue.offer(new int[] { i_, j_ });
            }
        }
        return minDist;
    }
}
