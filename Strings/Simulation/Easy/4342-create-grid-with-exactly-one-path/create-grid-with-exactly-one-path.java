class Solution {
    /**
     * Approach : Using String Simulation Approach
     *
     * TC : O(m x n)
     * SC : O(n)
     */
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        /**
         * greedily we can make a path such that
         * 0th index row has all dots (.) and 
         * (n - 1)th column has all dots (.) only
         */
        for (int i = 0; i < m; i++) {         // TC : O(m)
            StringBuilder sb = new StringBuilder(); // SC : O(n)
            if (i == 0) {
                for (int j = 0; j < n; j++) { // TC : O(n)
                    sb.append(".");
                }
            } else {
                for (int j = 0; j < n; j++) {
                    if (j == n - 1) {
                        sb.append(".");
                    } else {
                        sb.append("#");
                    }
                }
            }
            grid[i] = sb.toString();
        }
        return grid;
    }
}
