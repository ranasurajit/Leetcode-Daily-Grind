class Solution {
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC: O(N)
     * SC: O(N)
     * - O(N) - dp array space
     *
     * Accepted (70 / 70 testcases passed)
     */
    public int rob(int[] nums) {
        int n = nums.length;
        // Initialization
        int[] dp = new int[n + 1]; // SC: O(N)
        // Iterative Calls
        for (int idx = 0; idx < n; idx++) { // TC: O(N)
            int optionOne = nums[idx] + (idx > 1 ? dp[idx - 2] : 0);
            int optionTwo = idx > 0 ? dp[idx - 1] : 0;
            dp[idx] = Math.max(optionOne, optionTwo);
        }
        return dp[n - 1];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(N)
     * SC: O(N) + O(N) - recursion stack space
     * - O(N) - memoization stack space
     * - O(N) - recursion stack space
     *
     * Accepted (70 / 70 testcases passed)
     */
    public int robMemoization(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveMemoization(n - 1, nums, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N) - recursion stack space
     */
    private int solveMemoization(int idx, int[] nums, int[] memo) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        /**
         * If index 'idx' house is stolen, then 
         * we can steal index (idx - 2)th house
         * If index 'idx' house is skipped, then
         * we can steal index (idx - 1)th house
         */
        int optionOne = nums[idx];
        int optionTwo = Integer.MIN_VALUE;
        if (idx > 1) {
            optionOne += solveMemoization(idx - 2, nums, memo);
        }
        if (idx > 0) {
            optionTwo = solveMemoization(idx - 1, nums, memo);
        }
        return memo[idx] = Math.max(optionOne, optionTwo);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) - recursion stack space
     *
     * Time Limit Exceeded (55 / 70 testcases passed)
     */
    public int robRecursion(int[] nums) {
        int n = nums.length;
        return solveRecursion(n - 1, nums);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) - recursion stack space
     */
    private int solveRecursion(int idx, int[] nums) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        // Recursion Calls
        /**
         * If index 'idx' house is stolen, then 
         * we can steal index (idx - 2)th house
         * If index 'idx' house is skipped, then
         * we can steal index (idx - 1)th house
         */
        int optionOne = nums[idx];
        int optionTwo = Integer.MIN_VALUE;
        if (idx > 1) {
            optionOne += solveRecursion(idx - 2, nums);
        }
        if (idx > 0) {
            optionTwo = solveRecursion(idx - 1, nums);
        }
        return Math.max(optionOne, optionTwo);
    }
}
