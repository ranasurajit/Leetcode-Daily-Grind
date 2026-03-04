class Solution {
    private int m;
    private int n;

    /**
     * Approach II : Using Optimal(2D-Array Simulation) Approach
     *
     * TC: O(M x N) + O(M x N) ~ O(M x N)
     * SC: O(M + N)
     */
    public int numSpecial(int[][] mat) {
        this.m = mat.length;
        this.n = mat[0].length;
        int count = 0;
        int[] row1Counts = new int[m]; // SC: O(M)
        int[] col1Counts = new int[n]; // SC: O(N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (mat[i][j] == 1) {
                    row1Counts[i]++;
                    col1Counts[j]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (mat[i][j] == 1 && row1Counts[i] == 1 && col1Counts[j] == 1) {
                    // { i, j } is special cell
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Approach I : Using Brute-Force(2D-Array Simulation) Approach
     *
     * TC: O(M x N x (M + N))
     * SC: O(1)
     */
    public int numSpecialBruteForce(int[][] mat) {
        this.m = mat.length;
        this.n = mat[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (mat[i][j] == 1 && isSpecial(mat, i, j)) { // TC: O(M + N)
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Using 2D-Array Simulation Approach
     *
     * TC: O(M + N)
     * SC: O(1)
     */
    private boolean isSpecial(int[][] mat, int r, int c) {
        for (int i = 0; i < m; i++) { // TC: O(M)
            if (i != r && mat[i][c] == 1) {
                return false;
            }
        }
        for (int j = 0; j < n; j++) { // TC: O(N)
            if (j != c && mat[r][j] == 1) {
                return false;
            }
        }
        return true;
    }
}
