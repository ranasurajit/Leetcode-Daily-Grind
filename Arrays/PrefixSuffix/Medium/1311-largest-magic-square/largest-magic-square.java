class Solution {
    private int m;
    private int n;

    /**
     * Approach II : Using Optimal (2D - Matrix Prefix Sum) Approach
     *
     * TC: O(M x N x Min(M, N) x K)
     * SC: O(M x N)
     */
    public int largestMagicSquare(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        int[][] rowPrefix = new int[m][n + 1]; // SC: O(M x N)
        int[][] colPrefix = new int[m + 1][n]; // SC: O(M x N)
        int[][] leftDiagPrefix = new int[m + 1][n + 1]; // SC: O(M x N)
        int[][] rightDiagPrefix = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                rowPrefix[i][j + 1] = rowPrefix[i][j] + grid[i][j];
                colPrefix[i + 1][j] = colPrefix[i][j] + grid[i][j];
                leftDiagPrefix[i + 1][j + 1] = leftDiagPrefix[i][j] + grid[i][j];
                rightDiagPrefix[i + 1][j] = rightDiagPrefix[i][j + 1] + grid[i][j];
            }
        }
        int maxK = 1; // minimum magic square dimention
        for (int i = 0; i < m - 1; i++) { // TC: O(M)
            for (int j = 0; j < n - 1; j++) { // TC: O(N)
                // (i, j) is the start cell of the matrix
                int p = Math.min(n - j, m - i); // maximum size k till k = 2 can be checked
                for (int k = p; k > maxK; k--) { // TC: O(Min(M, N))
                    if (isValidMagicSquareOptimal(i, j, k, rowPrefix, 
                            colPrefix, leftDiagPrefix, rightDiagPrefix)) { // TC: O(K)
                        maxK = k;
                        break; // no need to shrink the dimension further
                    }
                }
            }
        }
        return maxK;
    }

    /**
     * Using 2D - Matrix Prefix Sum Approach
     *
     * TC: O(K) + O(K) ~ O(K)
     * SC: O(1)
     */
    private boolean isValidMagicSquareOptimal(int r, int c, int k, 
        int[][] rowPrefix, int[][] colPrefix, int[][] leftDiagPrefix, int[][] rightDiagPrefix) {
        int target = rowPrefix[r][c + k] - rowPrefix[r][c];
        // check for diagonals
        int leftDiagSum = leftDiagPrefix[r + k][c + k] - leftDiagPrefix[r][c];
        int rightDiagSum = rightDiagPrefix[r + k][c] - rightDiagPrefix[r][c + k];
        if (leftDiagSum != target || rightDiagSum != target) {
            return false;
        }
        // check for row-wise sums
        for (int i = r; i < Math.min(m, r + k); i++) { // TC: O(K)
            int rsum = rowPrefix[i][c + k] - rowPrefix[i][c];
            if (rsum != target) {
                return false;
            }
        }
        // check for column-wise sums
        for (int j = c; j < Math.min(n, c + k); j++) { // TC: O(K)
            int csum = colPrefix[r + k][j] - colPrefix[r][j];
            if (csum != target) {
                return false;
            }
        }
        return true;
    }

    /**
     * Approach I : Using Brute-Force (2D - Matrix Simulation) Approach
     *
     * TC: O(M x N x Min(M, N) x K²)
     * SC: O(1)
     */
    public int largestMagicSquareBruteForce(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        /**
         * Every 1 x 1 grid is a magic square so we should try to
         * find atleast 2 x 2 and above grid if it forms a magic square
         */
        int maxK = 1; // minimum magic square dimention
        for (int i = 0; i < m - 1; i++) { // TC: O(M)
            for (int j = 0; j < n - 1; j++) { // TC: O(N)
                // (i, j) is the start cell of the matrix
                int p = Math.min(n - j, m - i); // maximum size k till k = 2 can be checked
                for (int k = p; k >= 2; k--) { // TC: O(Min(M, N))
                    if (isValidMagicSquare(i, j, k, grid)) { // TC: O(K²)
                        maxK = Math.max(maxK, k);
                    }
                }
            }
        }
        return maxK;
    }

    /**
     * Using 2D - Matrix Simulation Approach
     *
     * TC: O(K) + O(K²) + O(K²) + O(K²) ~ O(K²)
     * SC: O(1)
     */
    private boolean isValidMagicSquare(int r, int c, int k, int[][] grid) {
        int target = 0;
        for (int j = c; j < Math.min(n, c + k); j++) { // TC: O(K)
            target += grid[r][j];
        }
        // check for diagonals
        int leftDiagSum = 0;
        int rightDiagSum = 0;
        for (int i = r; i < Math.min(m, r + k); i++) { // TC: O(K)
            for (int j = c; j < Math.min(n, c + k); j++) { // TC: O(K)
                if ((i - r) == (j - c)) {
                    leftDiagSum += grid[i][j];
                }
                if ((i - r) + (j - c) == k - 1) {
                    rightDiagSum += grid[i][j];
                }
            }
        }
        if (leftDiagSum != target || rightDiagSum != target) {
            return false;
        }
        // check for row-wise sums
        for (int i = r; i < Math.min(m, r + k); i++) { // TC: O(K)
            int rsum = 0;
            for (int j = c; j < Math.min(n, c + k); j++) { // TC: O(K)
                rsum += grid[i][j];
            }
            if (rsum != target) {
                return false;
            }
        }
        // check for column-wise sums
        for (int j = c; j < Math.min(n, c + k); j++) { // TC: O(K)
            int csum = 0;
            for (int i = r; i < Math.min(m, r + k); i++) { // TC: O(K)
                csum += grid[i][j];
            }
            if (csum != target) {
                return false;
            }
        }
        return true;
    }
}
