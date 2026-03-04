class Solution {
    private int m;
    private int n;

    /**
     * Approach II : Using Optimal(2D-Array Simulation) Approach
     *
     * TC: O(M x N) + O(M + N) ~ O(M x N)
     * SC: O(Max(M, N))
     */
    public int numSpecial(int[][] mat) {
        this.m = mat.length;
        this.n = mat[0].length;
        int count = 0;
        Set<Integer> rowSet = new HashSet<Integer>(); // SC: O(M)
        Set<Integer> colSet = new HashSet<Integer>(); // SC: O(N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (mat[i][j] == 1) {
                    if (rowSet.contains(i) || colSet.contains(j)) {
                        // early pruning
                        continue;
                    }
                    if (isSpecial(mat, i, j)) { // TC: O(M + N)
                        count++;
                    } else {
                        /**
                         * if the cell { i, j } is 1 and not special 
                         * so any other cells with value 1 in that 
                         * row and column cannot be special
                         */
                        rowSet.add(i);
                        colSet.add(j);
                    }
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
