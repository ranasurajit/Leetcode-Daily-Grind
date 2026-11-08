class Solution {
    /**
     * TC: O(2 x M x N) ~ O(M x N)
     * SC: O(M + N)
     */
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] rowCount = new int[m];      // SC: O(M)
        int[] colCount = new int[n];      // SC: O(N)
        int totalServers = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                    totalServers++;
                }
            }
        }
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                // remove idle servers
                if (grid[i][j] == 1 && rowCount[i] == 1 && colCount[j] == 1) {
                    totalServers--;
                }
            }
        }
        return totalServers;
    }
}
