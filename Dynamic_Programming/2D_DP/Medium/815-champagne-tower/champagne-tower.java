class Solution {
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(Q²)
     * SC: O(Q²) + O(Q), where Q = Max(query_row, query_glass)
     */
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] memo = new double[101][101]; // SC: O(Q²)
        for (double[] mem : memo) {
            Arrays.fill(mem, -1d);
        }
        return Math.min(1d, solveMemoization((double) poured, query_row, query_glass, memo));
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(Q²)
     * SC: O(Q)
     */
    private double solveMemoization(double poured, int row, int col, double[][] memo) {
        // Base Case
        if (col < 0 || col > row) {
            // out of bounds of pyramid
            return 0d;
        }
        if (row == 0 && col == 0) {
            return poured;
        }
        // Memoization Check
        if (memo[row][col] != -1d) {
            return memo[row][col];
        }
        // Recursion Calls
        double leftParent = solveMemoization(poured, row - 1, col - 1, memo);
        double rightParent = solveMemoization(poured, row - 1, col, memo);
        double leftOverflow = Math.max(0d, leftParent - 1) / 2;
        double rightOverflow = Math.max(0d, rightParent - 1) / 2;
        return memo[row][col] = leftOverflow + rightOverflow;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ Q)
     * SC: O(Q)
     */
    public double champagneTowerRecursion(int poured, int query_row, int query_glass) {
        return Math.min(1d, solveRecursion((double) poured, query_row, query_glass));
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ Q)
     * SC: O(Q)
     */
    private double solveRecursion(double poured, int row, int col) {
        // Base Case
        if (col < 0 || col > row) {
            // out of bounds of pyramid
            return 0d;
        }
        if (row == 0 && col == 0) {
            return poured;
        }
        // Recursion Calls
        double leftParent = solveRecursion(poured, row - 1, col - 1);
        double rightParent = solveRecursion(poured, row - 1, col);
        double leftOverflow = Math.max(0d, leftParent - 1) / 2;
        double rightOverflow = Math.max(0d, rightParent - 1) / 2;
        return leftOverflow + rightOverflow;
    }
}
