class Solution {
    private int n;
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
    public int lengthOfLIS(int[] nums) {
        this.n = nums.length;
        int[][] memo = new int[n][n + 1]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, -1, nums, memo); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int prevIdx, int[] nums, int[][] memo) {
        // Base Case
        if (idx >= n) {
            // nums is exhaused at index 'idx' here
            return 0;
        }
        // Memoization Check - prevIdx starts from -1 so we shifted coordinate by 1
        if (memo[idx][prevIdx + 1] != -1) {
            return memo[idx][prevIdx + 1];
        }
        // Recursion Calls
        int maxLength = solveMemoization(idx + 1, prevIdx, nums, memo); // skip
        if (prevIdx == -1 || nums[idx] > nums[prevIdx]) {
            maxLength = Math.max(maxLength, 1 + solveMemoization(idx + 1, idx, nums, memo));
        }
        return memo[idx][prevIdx + 1] = maxLength;
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
        this.n = nums.length;
        return solveRecursion(0, -1, nums); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int prevIdx, int[] nums) {
        // Base Case
        if (idx >= n) {
            // nums is exhaused at index 'idx' here
            return 0;
        }
        // Recursion Calls
        int maxLength = solveRecursion(idx + 1, prevIdx, nums); // skip
        if (prevIdx == -1 || nums[idx] > nums[prevIdx]) {
            maxLength = Math.max(maxLength, 1 + solveRecursion(idx + 1, idx, nums));
        }
        return maxLength;
    }
}
