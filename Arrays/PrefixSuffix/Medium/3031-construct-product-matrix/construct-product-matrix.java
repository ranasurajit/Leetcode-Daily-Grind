class Solution {
    private static final int MOD = 12345;
    /**
     * Approach II : Using Optimal (Prefix-Suffix Array) Approach
     *
     * TC: O(N x M) + O(N x M) ~ O(N x M)
     * SC: O(1)
     */
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        long prefix = 1L;
        long suffix = 1L;
        int[][] p = new int[n][m];
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = m - 1; j >= 0; j--) { // TC: O(M)
                p[i][j] = (int) suffix;
                suffix = (suffix * (long) grid[i][j]) % MOD;
            }
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < m; j++) { // TC: O(M)
                // multiplying with prefix suffix at (i, j) i.e. p[i][j]
                p[i][j] = (int) ((prefix * p[i][j]) % MOD);
                prefix = (prefix * grid[i][j]) % MOD;
            }
        }
        return p;
    }

    /**
     * Approach I : Using Brute-Force(Prefix-Suffix Array) Approach
     *
     * TC: O(N x M) + O(N x M) + O(N x M) ~ O(N x M)
     * SC: O(N x M) + O(N x M) ~ O(N x M)
     */
    public int[][] constructProductMatrixBruteForce(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        long[][] prefixProduct = new long[n][m]; // SC: O(N x M)
        long[][] suffixProduct = new long[n][m]; // SC: O(N x M)
        long prefix = 1L;
        long suffix = 1L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < m; j++) { // TC: O(M)
                prefixProduct[i][j] = prefix;
                prefix = (prefix * (long) grid[i][j]) % MOD;
            }
        }
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = m - 1; j >= 0; j--) { // TC: O(M)
                suffixProduct[i][j] = suffix;
                suffix = (suffix * (long) grid[i][j]) % MOD;
            }
        }
        int[][] p = new int[n][m];
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < m; j++) { // TC: O(M)
                p[i][j] = (int) ((prefixProduct[i][j] * suffixProduct[i][j]) % MOD);
            }
        }
        return p;
    }
}
