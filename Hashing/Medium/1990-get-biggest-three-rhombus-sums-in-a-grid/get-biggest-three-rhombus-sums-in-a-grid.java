class Solution {
    /**
     * Approach : Using Brute-Force Approach
     *
     * TC: O(M x N x Min(M, N)²)
     * SC: O(M x N x Min(M, N)²)
     */
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * we need to traverse via every cell and 
         * check how much distance it can go down,
         * left and right to form the rhombus
         *
         * From any cell, we can go with radius 
         * Min(j, n - 1 - j, (m - 1 - i) / 2)
         */
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                int r = Math.min(Math.min(j, n - 1 - j), ((m - 1 - i) / 2));
                for (int k = 0; k <= r; k++) { // TC: O(Min(M, N))
                    int sum = 0;
                    if (k == 0) {
                        sum = grid[i][j];
                    } else {
                        // compute right-top diagonal
                        for (int l = 0; l < k; l++) { // TC: O(Min(M, N))
                            sum += grid[i + l][j + l];
                        }
                        // compute right-bottom diagonal
                        for (int l = 0; l < k; l++) {
                            sum += grid[i + k + l][j + k - l];
                        }
                        // compute left-top diagonal
                        for (int l = 0; l < k; l++) {
                            sum += grid[i + k - l][j - k + l];
                        }
                        // compute left-bottom diagonal
                        for (int l = 0; l < k; l++) {
                            sum += grid[i + 2 * k - l][j - l];
                        }
                    }
                    set.add(sum);
                    if (set.size() > 3) {
                        set.pollFirst();
                    }
                }
            }
        }
        int[] ans = new int[set.size()];
        int index = set.size() - 1;
        for (int key : set) {
            ans[index--] = key;
        }
        return ans;
    }
}
