class Solution {
    /**
     * Approach II : Using Matrix Chain Multiplication (Memoization) Approach
     *
     * TC: O(N x N x N)
     * SC: O(N x N) + O(N) ~ O(N x N)
     */
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] memo = new int[n][n]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(values, 0, n - 1, memo); // TC: O(N x N x N), SC: O(N)
    }

    /**
     * Using Matrix Chain Multiplication (Memoization) Approach
     *
     * TC: O(N x N x N)
     * SC: O(N)
     */
    private int solveMemoization(int[] values, int i, int j, int[][] memo) {
        // Base Case
        if (j - i + 1 < 3) {
            // vertices less to form a triangle
            return 0;
        }
        // Memoization Calls
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int result = Integer.MAX_VALUE;
        // Recursion Calls
        for (int k = i + 1; k < j; k++) { // TC: O(N)
            int currentScore = values[i] * values[j] * values[k];
            result = Math.min(result, currentScore + 
                solveMemoization(values, i, k, memo) + solveMemoization(values, k, j, memo));
        }
        return memo[i][j] = result;
    }

    /**
     * Approach I : Using Matrix Chain Multiplication (Recursion) Approach
     *
     * TC: O(Catalan(n)) ≈ O(4 ^ n / n ^ (3 / 2)) in worst case
     * SC: O(N)
     *
     * Time Limit Exceeded (76 / 94 testcases passed)
     */
    public int minScoreTriangulationRecursion(int[] values) {
        int n = values.length;
        return solveRecursion(values, 0, n - 1);
    }

    /**
     * Using Matrix Chain Multiplication (Recursion) Approach
     *
     * TC: O(Catalan(n)) ≈ O(4 ^ n / n ^ (3 / 2)) in worst case
     * SC: O(N)
     */
    private int solveRecursion(int[] values, int i, int j) {
        // Base Case
        if (j - i + 1 < 3) {
            // vertices less to form a triangle
            return 0;
        }
        int result = Integer.MAX_VALUE;
        // Recursion Calls
        for (int k = i + 1; k < j; k++) {
            int currentScore = values[i] * values[j] * values[k];
            result = Math.min(result, currentScore + 
                solveRecursion(values, i, k) + solveRecursion(values, k, j));
        }
        return result;
    }
}
