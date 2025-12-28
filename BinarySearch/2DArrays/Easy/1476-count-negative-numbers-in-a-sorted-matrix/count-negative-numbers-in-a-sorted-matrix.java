class Solution {
    /**
     * Approach III : Using Staircase Counting Approach
     *
     * TC: O(M + N)
     * SC: O(1)
     */
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int row = 0;
        int col = n - 1;
        while (row < m && col >= 0) { // TC: O(M + N)
            if (grid[row][col] < 0) {
                // all elements below this cell are also negative
                count += (m - row);
                col--;
            } else {
                row++;
            }
        }
        return count;
    }

    /**
     * Approach II : Using Binary Search Approach
     *
     * TC: O(M x log(N))
     * SC: O(1)
     */
    public int countNegativesBinarySearch(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {           // TC: O(M)
            count += lowerBound(grid[i], n, 0); // TC: O(log(N))
        }
        return count;
    }

    /**
     * Using Binary Search (To Find Lower Bound) Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    private int lowerBound(int[] row, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (row[mid] >= target) {
                low = mid + 1;
            } else {
                // move to the left to find least index with negative value
                high = mid - 1;
            }
        }
        return n - low;
    }

    /**
     * Approach I : Using Brute-Force (2D Array Simulation) Approach
     *
     * TC: O(M x N)
     * SC: O(1)
     */
    public int countNegativesBruteForce(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {          // TC: O(M)
            for (int j = n - 1; j >= 0; j--) { // TC: O(N)
                if (grid[i][j] >= 0) {
                    // early break - as no elements would be negative towards left
                    break;
                }
                count++;
            }
        }
        return count;
    }
}
