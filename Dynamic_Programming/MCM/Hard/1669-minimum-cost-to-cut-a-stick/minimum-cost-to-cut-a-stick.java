class Solution {
    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(M x M x M) + O(M x log(M)) ~ O(M x M x M)
     * SC: O(M) + O(M x M)
     *
     * Accepted (101 / 101 testcases passed)
     */
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        /**
         * To reduce the depemdency of cuts and since order does 
         * not matter so let's sort the cuts in ascending order
         */
        Arrays.sort(cuts); // TC: O(M x log(M))
        List<Integer> cutIndices = new ArrayList<Integer>();
        // we will insert 0 and n at the beginning and end
        cutIndices.add(0);
        for (int i = 0; i < m; i++) {
            cutIndices.add(cuts[i]);
        }
        cutIndices.add(n);
        int[][] memo = new int[m + 1][m + 1]; // SC: O(M x M)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(1, cutIndices.size() - 2, cutIndices, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x M x M)
     * SC: O(M)
     */
    private int solveMemoization(int i, int j, List<Integer> cutIndices, int[][] memo) {
        // Base Case
        if (i > j) {
            // length of stick is invalid
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            int currentCost = cutIndices.get(j + 1) - cutIndices.get(i - 1);
            minCost = Math.min(minCost, currentCost + 
                solveMemoization(i, k - 1, cutIndices, memo) + 
                solveMemoization(k + 1, j, cutIndices, memo)
            );
        }
        return memo[i][j] = minCost;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(Exponential)
     * SC: O(M)
     *
     * Time Limit Exceeded (12 / 101 testcases passed)
     */
    public int minCostRecursion(int n, int[] cuts) {
        int m = cuts.length;
        /**
         * To reduce the depemdency of cuts and since order does 
         * not matter so let's sort the cuts in ascending order
         */
        Arrays.sort(cuts); // TC: O(M x log(M))
        List<Integer> cutIndices = new ArrayList<Integer>();
        // we will insert 0 and n at the beginning and end
        cutIndices.add(0);
        for (int i = 0; i < m; i++) {
            cutIndices.add(cuts[i]);
        }
        cutIndices.add(n);
        return solveRecursion(1, cutIndices.size() - 2, cutIndices);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(Exponential)
     * SC: O(M)
     */
    private int solveRecursion(int i, int j, List<Integer> cutIndices) {
        // Base Case
        if (i > j) {
            // length of stick is invalid
            return 0;
        }
        // Recursion Calls
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            int currentCost = cutIndices.get(j + 1) - cutIndices.get(i - 1);
            minCost = Math.min(minCost, currentCost + 
                solveRecursion(i, k - 1, cutIndices) + 
                solveRecursion(k + 1, j, cutIndices)
            );
        }
        return minCost;
    }
}
