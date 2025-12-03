class Solution {
    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N x N x N)
     * SC: O(N x N)
     *
     * Accepted (73 / 73 testcases passed)
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        List<Integer> numsList = new ArrayList<Integer>();
        numsList.add(1);
        for (int num : nums) { // TC: O(N)
            numsList.add(num);
        }
        numsList.add(1);
        int[][] dp = new int[n + 2][n + 2]; // SC: O(N x N)
        for (int i = n; i >= 1; i--) { // TC: O(N)
            for (int j = 1; j <= n; j++) { // TC: O(N)
                if (i > j) {
                    continue;
                }
                int maximumVal = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) { // TC: O(N)
                    // kth index can be considered to be the last balloon to be bursted
                    int currentCost = numsList.get(k) * numsList.get(i - 1) * numsList.get(j + 1);
                    maximumVal = Math.max(maximumVal, 
                        currentCost + dp[i][k - 1] + dp[k + 1][j]);
                }
                dp[i][j] = Math.max(dp[i][j], maximumVal);
            }
        }
        return dp[1][n];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x N x N)
     * SC: O(N x N) + O(N)
     *
     * Accepted (73 / 73 testcases passed)
     */
    public int maxCoinsMemoization(int[] nums) {
        int n = nums.length;
        List<Integer> numsList = new ArrayList<Integer>();
        numsList.add(1);
        for (int num : nums) { // TC: O(N)
            numsList.add(num);
        }
        numsList.add(1);
        int[][] memo = new int[n + 2][n + 2]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(1, n, numsList, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N x N)
     * SC: O(N)
     */
    private int solveMemoization(int i, int j, List<Integer> numsList, int[][] memo) {
        // Base Case
        if (i > j) {
            // if i == j we still have a balloon to burst which will contribute to coins
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        int maximumVal = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) { // TC: O(N)
            // kth index can be considered to be the last balloon to be bursted
            int currentCost = numsList.get(k) * numsList.get(i - 1) * numsList.get(j + 1);
            maximumVal = Math.max(maximumVal, 
                currentCost + solveMemoization(i, k - 1, numsList, memo) + 
                solveMemoization(k + 1, j, numsList, memo)
            );
        }
        return memo[i][j] = maximumVal;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: Exponential
     * SC: O(N)
     *
     * Time Limit Exceeded (30 / 73 testcases passed)
     */
    public int maxCoinsRecursion(int[] nums) {
        int n = nums.length;
        List<Integer> numsList = new ArrayList<Integer>();
        numsList.add(1);
        for (int num : nums) { // TC: O(N)
            numsList.add(num);
        }
        numsList.add(1);
        return solveRecursion(1, n, numsList);
    }

    /**
     * Using Recursion Approach
     *
     * TC: Exponential
     * SC: O(N)
     */
    private int solveRecursion(int i, int j, List<Integer> numsList) {
        // Base Case
        if (i > j) {
            // if i == j we still have a balloon to burst which will contribute to coins
            return 0;
        }
        // Recursion Calls
        int maximumVal = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) { // TC: O(N)
            // kth index can be considered to be the last balloon to be bursted
            int currentCost = numsList.get(k) * numsList.get(i - 1) * numsList.get(j + 1);
            maximumVal = Math.max(maximumVal, 
                currentCost + solveRecursion(i, k - 1, numsList) + 
                solveRecursion(k + 1, j, numsList)
            );
        }
        return maximumVal;
    }
}
