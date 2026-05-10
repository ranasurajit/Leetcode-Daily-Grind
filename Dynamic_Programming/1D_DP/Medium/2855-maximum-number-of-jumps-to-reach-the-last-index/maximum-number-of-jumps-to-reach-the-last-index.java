class Solution {
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC : O(n²)
     * SC : O(n)
     * - O(n) - dp array memory
     *
     * Accepted (2639 / 2639 testcases passed)
     */
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n]; // SC : O(n)
        Arrays.fill(dp, -1); // marking indices non-reachable
        dp[0] = 0;
        for (int i = 0; i < n - 1; i++) { // TC : O(n)
            if (dp[i] == -1) {
                continue;
            }
            int steps = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++) { // TC : O(n)
                if (Math.abs(nums[j] - nums[i]) <= target) {
                    // we can then move to index 'j'
                    dp[j] = Math.max(dp[j], 1 + dp[i]);
                }
            }
        }
        return dp[n - 1];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(n²)
     * SC : O(n) + O(n)
     * - O(n) - recursion stack
     * - O(n) - memoization memory
     *
     * Accepted (2639 / 2639 testcases passed)
     */
    public int maximumJumpsMemoization(int[] nums, int target) {
        int n = nums.length;
        int[] memo = new int[n]; // SC : O(n)
        Arrays.fill(memo, -1);
        int steps = solveMemoization(0, n, nums, target, memo);
        return steps < 0 ? -1 : steps;
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(n²)
     * SC : O(n)
     */
    private int solveMemoization(int idx, int n, int[] nums, 
        int target, int[] memo) {
        // Base Case
        if (idx == n - 1) {
            return 0;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        int pick = Integer.MIN_VALUE;
        for (int i = idx + 1; i < n; i++) { // TC : O(n)
            if (Math.abs(nums[i] - nums[idx]) <= target) {
                // we can then move to index 'i'
                pick = Math.max(pick,
                    1 + solveMemoization(i, n, nums, target, memo));
            }
        }
        return memo[idx] = pick;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     *
     * Time Limit Exceeded (1876 / 2639 testcases passed)
     */
    public int maximumJumpsRecursion(int[] nums, int target) {
        int n = nums.length;
        int steps = solveRecursion(0, n, nums, target);
        return steps < 0 ? -1 : steps;
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int idx, int n, int[] nums, int target) {
        // Base Case
        if (idx == n - 1) {
            return 0;
        }
        // Recursion Calls
        int pick = Integer.MIN_VALUE;
        for (int i = idx + 1; i < n; i++) { // TC : O(n)
            if (Math.abs(nums[i] - nums[idx]) <= target) {
                // we can then move to index 'i'
                pick = Math.max(pick, 1 + solveRecursion(i, n, nums, target));
            }
        }
        return pick;
    }
}
