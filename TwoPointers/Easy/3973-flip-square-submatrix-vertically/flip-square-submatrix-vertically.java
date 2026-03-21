class Solution {
    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(K²)
     * SC: O(1)
     */
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        /**
         * As per constraints, 0 <= x < m, 0 <= y < n
         * 1 <= k <= min(m - x, n - y), so validation
         * on values of x, y and k is not needed
         */
        int top = x;
        int bottom = x + k - 1;
        int left = y;
        int right = y + k - 1;
        while (top < bottom) { // TC: O(K)
            for (int j = left; j <= right; j++) { // TC: O(K)
                // swap elements grid[top][j] with grid[bottom][j]
                int temp = grid[bottom][j];
                grid[bottom][j] = grid[top][j];
                grid[top][j] = temp;
            }
            top++;
            bottom--;
        }
        return grid;
    }
}
