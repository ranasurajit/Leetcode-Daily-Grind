class Solution {
    /**
     * Approach : Using 2D-Array Simulation + Two Pointers Approach
     *
     * TC : O(m x n) + O(m x n) ~ O(m x n)
     * SC : O(1)
     */
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        char[][] rGrid = new char[n][m];
        // First we can shift stone from left to right in spaces
        for (int i = 0; i < m; i++) {          // TC : O(m)
            // find space from right to left
            int lastSpace = n - 1;
            for (int j = n - 1; j >= 0; j--) { // TC : O(n)
                if (boxGrid[i][j] == '*') {
                    // reset lastSpace before this position to left
                    lastSpace = j - 1;
                } else if (boxGrid[i][j] == '#') {
                    // swap boxGrid[i][j] with boxGrid[i][lastSpace]
                    char temp = boxGrid[i][j];
                    boxGrid[i][j] = boxGrid[i][lastSpace];
                    boxGrid[i][lastSpace] = temp;
                    lastSpace -= 1;
                }
            }
        }
        // Rotation 90 degrees clockwise
        for (int i = 0; i < m; i++) {     // TC : O(m)
            for (int j = 0; j < n; j++) { // TC : O(n)
                rGrid[j][m - i - 1] = boxGrid[i][j];
            }
        }
        return rGrid;
    }
}
