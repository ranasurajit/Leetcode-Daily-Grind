class Solution {
    /**
     * Approach : Using 2D-Array Simulation Approach
     *
     * TC : O(Math.min(m, n) x (m + n))
     * SC : O(m + n)
     */
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * we need to take care of the rotation layer by layer
         * every layer we need compute perimeter count of cells
         * (p), so r = k % (p - 4), so every (p - 4) times rotation
         * brings the layer back to its normal position only
         *
         * layer 0, start from cell (0, 0)
         * layer 1, start from cell (1, 1) and layers can be in the 
         * range of [0, Min(m, n) / 2)
         */
        int maxLayer = (Math.min(m, n) / 2) - 1; // (index starts from 0)
        for (int l = 0; l <= maxLayer; l++) { // TC : O(Min(m, n))
            int top = l;
            int bottom = m - l - 1;
            int left = l;
            int right = n - l - 1;
            // rotate the cells here
            rotateLayers(grid, 
                top, bottom, left, right, k); // TC : O(m + n), SC : O(m + n)
        }
        return grid;
    }

    /**
     * Using 2D-Array Simulation Approach
     *
     * TC : O(m + n)
     * SC : O(m + n)
     */
    private void rotateLayers(int[][] grid, int top, int bottom,
        int left, int right, int k) {
        List<Integer> cList = new ArrayList<>(); // SC : O(2 * (m + n) - 4)
        // retrieve the values from grid
        // top to bottom
        for (int i = top; i <= bottom; i++) { // TC : O(m)
            cList.add(grid[i][left]);
        }
        // left to right
        for (int j = left + 1; j <= right - 1; j++) { // TC : O(n)
            cList.add(grid[bottom][j]);
        }
        // bottom to top
        for (int i = bottom; i >= top; i--) { // TC : O(m)
            cList.add(grid[i][right]);
        }
        // right to left
        for (int j = right - 1; j > left; j--) { // TC : O(n)
            cList.add(grid[top][j]);
        }
        List<Integer> rotatedList = new ArrayList<>(); // SC : O(2 * (m + n) - 4)
        int len = cList.size();
        k = k % len; // effective rotation
        for (int i = 0; i < len; i++) { // TC : O(2 * (m + n) - 4)
            rotatedList.add(cList.get((i + len - k) % len));
        }
        // now we need to set the rotatedList in the grid
        int index = 0;
        // top to bottom
        for (int i = top; i <= bottom; i++) { // TC : O(m)
            grid[i][left] = rotatedList.get(index++);
        }
        // left to right
        for (int j = left + 1; j <= right - 1; j++) { // TC : O(n)
            grid[bottom][j] = rotatedList.get(index++);
        }
        // bottom to top
        for (int i = bottom; i >= top; i--) { // TC : O(m)
            grid[i][right] = rotatedList.get(index++);
        }
        // right to left
        for (int j = right - 1; j > left; j--) { // TC : O(n)
            grid[top][j] = rotatedList.get(index++);
        }
    }
}
