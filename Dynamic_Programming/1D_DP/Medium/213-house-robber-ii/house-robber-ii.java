class Solution {
    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     *
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     * - O(N) - nums1 and nums2 array space
     *
     * Accepted (75 / 75 testcases passed)
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return nums[0];
        }
        int[] nums1 = new int[n - 1]; // SC: O(N)
        int[] nums2 = new int[n - 1]; // SC: O(N)
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            nums1[i] = nums[i];
            nums2[i] = nums[i + 1];
        }
        return Math.max(
            solveOptimization(nums1, n - 1),
            solveOptimization(nums2, n - 1)
        );
    }

    /**
     * Using Space Optimization Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private int solveOptimization(int[] nums, int n) {
        int prev1 = 0;
        int prev2 = 0;
        int skip = Integer.MIN_VALUE;
        int pick = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            pick = nums[i];
            if (i > 1) {
                pick += prev2;
            }
            if (i > 0) {
                skip = prev1;
            }
            int current = Math.max(pick, skip);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) + O(N) ~ O(N)
     * - O(N) - dp array and nums1 and nums2 array space
     *
     * Accepted (75 / 75 testcases passed)
     */
    public int robTabulation(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return nums[0];
        }
        int[] nums1 = new int[n - 1]; // SC: O(N)
        int[] nums2 = new int[n - 1]; // SC: O(N)
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            nums1[i] = nums[i];
            nums2[i] = nums[i + 1];
        }
        return Math.max(
            solveTabulation(nums1, n - 1),
            solveTabulation(nums2, n - 1)
        );
    }

    /**
     * Using Tabulation Approach
     *
     * TC: O(N)
     * SC: O(N)
     * - O(N) - dp array space
     */
    private int solveTabulation(int[] nums, int n) {
        int[] dp = new int[n];        // SC: O(N)
        int skip = Integer.MIN_VALUE;
        int pick = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            pick = nums[i];
            if (i > 1) {
                pick += dp[i - 2];
            }
            if (i > 0) {
                skip = dp[i - 1];
            }
            dp[i] = Math.max(pick, skip);
        }
        return dp[n - 1];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) + O(N) + O(N) ~ O(N) + O(N)
     * - O(N) - memoization space
     * - O(N) - recursion stack space
     *
     * Accepted (75 / 75 testcases passed)
     */
    public int robMemoization(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return nums[0];
        }
        int[] nums1 = new int[n - 1]; // SC: O(N)
        int[] nums2 = new int[n - 1]; // SC: O(N)
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            nums1[i] = nums[i];
            nums2[i] = nums[i + 1];
        }
        int[] memo1 = new int[n - 1]; // SC: O(N)
        int[] memo2 = new int[n - 1]; // SC: O(N)
        Arrays.fill(memo1, -1);
        Arrays.fill(memo2, -1);
        return Math.max(
            solveMemoization(nums1, n - 2, memo1),
            solveMemoization(nums2, n - 2, memo2)
        );
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N) - recursion stack space
     */
    private int solveMemoization(int[] nums, int idx, int[] memo) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        // if index 'idx' is skipped
        int skip = Integer.MIN_VALUE;
        if (idx > 0) {
            skip = solveMemoization(nums, idx - 1, memo);
        }
        // if index 'idx' is picked
        int pick = nums[idx];
        if (idx > 1) {
            pick += solveMemoization(nums, idx - 2, memo);
        }
        return memo[idx] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(N) + O(2 ^ N) ~ O(2 ^ N)
     * SC: O(N) + O(N) + O(N) - recursion stack space
     * - O(N) - recursion stack space
     *
     * Time Limit Exceeded (62 / 75 testcases passed)
     */
    public int robRecursion(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return nums[0];
        }
        int[] nums1 = new int[n - 1]; // SC: O(N)
        int[] nums2 = new int[n - 1]; // SC: O(N)
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            nums1[i] = nums[i];
            nums2[i] = nums[i + 1];
        }
        return Math.max(
            solveRecursion(nums1, n - 2),
            solveRecursion(nums2, n - 2)
        );
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) - recursion stack space
     */
    private int solveRecursion(int[] nums, int idx) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        // Recursion Calls
        // if index 'idx' is skipped
        int skip = Integer.MIN_VALUE;
        if (idx > 0) {
            skip = solveRecursion(nums, idx - 1);
        }
        // if index 'idx' is picked
        int pick = nums[idx];
        if (idx > 1) {
            pick += solveRecursion(nums, idx - 2);
        }
        return Math.max(pick, skip);
    }
}
