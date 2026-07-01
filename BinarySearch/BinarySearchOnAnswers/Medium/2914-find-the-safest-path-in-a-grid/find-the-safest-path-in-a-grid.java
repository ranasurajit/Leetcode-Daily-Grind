class Solution {
    private static final int[][] directions = {
        { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };

    /**
     * Approach : Using Binary Search on Answers + Graph Multi-source BFS Approach
     *
     * TC : O(n x n) + O(n x n x log(n)) ~ O(n x n x log(n))
     * SC : O(n x n)
     */
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        /**
         * we can perform multi-source BFS from every thief
         * cells to compute the minimum manhattan distance of
         * that cell from the nearest thief cell
         */
        int[][] minDist =
            runMultiSourceBFSGrid(grid, n); // TC : O(n x n), SC : O(n x n)
        /**
         * no need to check if thieves are even there 
         * as per constraints, it is mentioned that
         * 'There is at least one thief in the grid'
         * so we need to basically find the maximum of the
         * minimum safeness factor which gives a hint towards
         * usage of Binary Search on Answers
         *
         * we have a range [0, 2 * n] as maximum value
         * of safeness factor can be 2 * n
         */
        int low = 0;
        int high = 2 * n;
        int maxSF = 0;
        while (low <= high) { // TC : O(log(n))
            int mid = low + (high - low) / 2;
            /**
             * now we need to maximize the mid value
             * if mid can be the maximum of the minimum
             * safeness factor that can be possible to 
             * reach any paths from (0, 0) to (n - 1, n - 1)
             */
            if (isPathPossible(grid, n, mid, minDist)) { // TC : O(n x n)
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
     * TC : O(n x n)
     * SC : O(n x n)
     */
    private boolean isPathPossible(List<List<Integer>> grid, int n,
        int minSF, int[][] minDist) {
        if (minDist[0][0] < minSF) {
            // safeness factor minimum of 'minSF' cannot be reached
            return false;
        }
        boolean[][] visited = new boolean[n][n]; // SC : O(n x n)
        Queue<int[]> queue = new LinkedList<>(); // SC : O(n x n)
        queue.offer(new int[] { 0, 0 });
        visited[0][0] = true;
        while (!queue.isEmpty()) { // TC : O(n x n)
            int[] current = queue.poll();
            int i = current[0];
            int j = current[1];
            if (minDist[i][j] < minSF) {
                // invalid cells to step in for path
                continue;
            }
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
     * TC : O(n x n)
     * SC : O(n x n)
     */
    private int[][] runMultiSourceBFSGrid(List<List<Integer>> grid,
        int n) {
        int[][] minDist = new int[n][n]; // SC : O(n x n)
        Queue<int[]> queue = new LinkedList<>(); // SC : O(n x n)
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
        while (!queue.isEmpty()) { // TC : O(n x n)
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
                queue.offer(new int[] { i_, j_, reach });
            }
        }
        return minDist;
    }
}
