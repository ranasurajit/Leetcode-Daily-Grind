class Solution {
    /**
     * Approach II : Using Memoization Approach
     *
     * TC: TC: O(M x M x M) + O(M) + O(M x log(M)) ~ O(M³)
     * SC: O(M x M) + O(M) + O(M)
     * - O(M x M) - memoization memory
     * - O(M) - arraylist cutList memory
     * - O(M) - recursion stack
     *
     * Accepted (101 / 101 testcases passed)
     */
    public int minCost(int n, int[] cuts) {
        /**
         * As the cutting order does not matter we should sort it
         * so that while dividing it one portion is not dependent
         * on other as in MCM / Partition DP
         */
        int m = cuts.length;
        List<Integer> cutList = new ArrayList<Integer>(); // SC: O(M)
        for (int i = 0; i < m; i++) { // TC: O(M)
            cutList.add(cuts[i]);
        }
        cutList.add(0);
        cutList.add(n);
        // Sorting the cutList
        Collections.sort(cutList); // TC: O(M x log(M))
        int[][] memo = new int[m + 2][m + 2]; // SC: O(M x M)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(1, m, cutList, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x M x M) ~ O(M³)
     * SC: O(M)
     */
    private int solveMemoization(int i, int j, List<Integer> cutList, int[][] memo) {
        // Base Case
        if (i > j) {
            // invalid length of stick
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) { // TC: O(M)
            // current cost is length of stick portion before cutting at index k
            int currentCost = cutList.get(j + 1) - cutList.get(i - 1);
            minCost = Math.min(minCost, 
                solveMemoization(i, k - 1, cutList, memo) + 
                currentCost + 
                solveMemoization(k + 1, j, cutList, memo)
            );
        }
        return memo[i][j] = minCost;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: Exponential
     * SC: O(M) + O(M)
     * - O(M) - arraylist cutList memory
     * - O(M) - recursion stack
     *
     * Time Limit Exceeded (12 / 101 testcases passed)
     */
    public int minCostRecursion(int n, int[] cuts) {
        /**
         * As the cutting order does not matter we should sort it
         * so that while dividing it one portion is not dependent
         * on other as in MCM / Partition DP
         */
        int m = cuts.length;
        List<Integer> cutList = new ArrayList<Integer>(); // SC: O(M)
        for (int i = 0; i < m; i++) { // TC: O(M)
            cutList.add(cuts[i]);
        }
        cutList.add(0);
        cutList.add(n);
        // Sorting the cutList
        Collections.sort(cutList); // TC: O(M x log(M))
        return solveRecursion(1, m, cutList);
    }

    /**
     * Using Recursion Approach
     *
     * TC: Exponential
     * SC: O(M)
     */
    private int solveRecursion(int i, int j, List<Integer> cutList) {
        // Base Case
        if (i > j) {
            // invalid length of stick
            return 0;
        }
        // Recursion Calls
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            // current cost is length of stick portion before cutting at index k
            int currentCost = cutList.get(j + 1) - cutList.get(i - 1);
            minCost = Math.min(minCost, 
                solveRecursion(i, k - 1, cutList) + 
                currentCost + 
                solveRecursion(k + 1, j, cutList)
            );
        }
        return minCost;
    }
}
