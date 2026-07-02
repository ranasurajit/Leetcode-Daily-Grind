class Solution {
    private static final int[][] directions = {
        { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
    };

    /**
     * Approach : Using Graph BFS Approach
     *
     * TC : O(n x m)
     * SC : O(n x m)
     */
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        /**
         * from cell (0, 0) we can always start the journey as it can have
         * value 0 or 1 and 1 <= health <= m + n
         */
        int startHealth = health - grid.get(0).get(0);
        int[][] bestHealth = new int[m][n]; // SC : O(m x n)
        for (int[] h : bestHealth) {
            Arrays.fill(h, -1);
        }
        Queue<int[]> queue = new LinkedList<>(); // SC : O(n x m)
        queue.offer(new int[] { 0, 0, startHealth });
        bestHealth[0][0] = startHealth;
        while (!queue.isEmpty()) { // TC : O(n x m)
            int[] current = queue.poll();
            int i = current[0];
            int j = current[1];
            int h = current[2];
            if (i == m - 1 && j == n - 1 && bestHealth[i][j] >= 1) {
                return true;
            }
            for (int[] dir : directions) { // TC : O(4)
                int i_ = i + dir[0];
                int j_ = j + dir[1];
                if (i_ < 0 || i_ >= m || j_ < 0 || j_ >= n) {
                    // invalid cells
                    continue;
                }
                int newHealth = h - grid.get(i_).get(j_);
                if (bestHealth[i_][j_] >= newHealth) {
                    /**
                     * already we have reached cell (i_, j_)
                     * with better remaining health then we
                     * continue with that exploration only
                     */
                    continue;
                }
                bestHealth[i_][j_] = newHealth;
                queue.offer(new int[] { i_, j_, newHealth });
            }
        }
        return false;
    }
}
