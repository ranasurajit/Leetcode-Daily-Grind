class Solution {
    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     * - O(N) - dp array memory
     *
     * Accepted (56 / 56 testcases passed)
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; // SC: O(N)
        Arrays.fill(dp, 1);
        int maxLength = 1;
        for (int idx = 1; idx < n; idx++) { // TC: O(N)
            for (int prevIdx = 0; prevIdx < idx; prevIdx++) { // TC: O(N)
                if (nums[prevIdx] < nums[idx] && dp[idx] < dp[prevIdx] + 1) {
                    dp[idx] = dp[prevIdx] + 1;
                }
            }
            maxLength = Math.max(maxLength, dp[idx]);
        }
        return maxLength;
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N x N) + O(N)
     * - O(N x N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (56 / 56 testcases passed)
     */
    public int lengthOfLISMemoization(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n + 1]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, -1, n, nums, memo); // TC: O(N x N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int prevIdx, int n, int[] nums, int[][] memo) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][prevIdx + 1] != -1) {
            return memo[idx][prevIdx + 1];
        }
        // Recursion Calls
        // pick or skip
        int skip = solveMemoization(idx + 1, prevIdx, n, nums, memo);
        int pick = 0;
        if (prevIdx == -1 || nums[prevIdx] < nums[idx]) {
            // pick
            pick = 1 + solveMemoization(idx + 1, idx, n, nums, memo);
        }
        return memo[idx][prevIdx + 1] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (23 / 56 testcases passed)
     */
    public int lengthOfLISRecursion(int[] nums) {
        int n = nums.length;
        return solveRecursion(0, -1, n, nums);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int prevIdx, int n, int[] nums) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Recursion Calls
        // pick or skip
        int skip = solveRecursion(idx + 1, prevIdx, n, nums);
        int pick = 0;
        if (prevIdx == -1 || nums[prevIdx] < nums[idx]) {
            // pick
            pick = 1 + solveRecursion(idx + 1, idx, n, nums);
        }
        return Math.max(pick, skip);
    }
}
