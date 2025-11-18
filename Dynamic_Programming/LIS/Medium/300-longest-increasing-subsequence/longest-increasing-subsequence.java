class Solution {
    private int n;

    /**
     * Approach V : Using Binary Search Approach
     *
     * TC: O(N x log(N))
     * SC: O(N)
     * - O(N) - sorted ArrayList memory
     *
     * Accepted (56 / 56 testcases passed)
     */
    public int lengthOfLIS(int[] nums) {
        this.n = nums.length;
        List<Integer> sorted = new ArrayList<Integer>(); // SC: O(N)
        sorted.add(nums[0]);
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (nums[i] > sorted.get(sorted.size() - 1)) {
                sorted.add(nums[i]);
            } else {
                // search for the position to swap using Lower-bound (Binary Search) 
                int lbIndex = lowerBound(sorted, nums[i]); // TC: O(log(N))
                // setting the nums[i] in place
                sorted.set(lbIndex, nums[i]);
            }
        }
        return sorted.size();
    }

    /**
     * Using Binary Search (We need to return index such that sorted.get(index) >= num) Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    private int lowerBound(List<Integer> sorted, int num) {
        int low = 0;
        int high = sorted.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sorted.get(mid) >= num) {
                // we need to shrink it to find the lowest index
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Approach IV : Using Optimized Tabulation Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     * - O(N) - dp array memory
     *
     * Accepted (56 / 56 testcases passed)
     */
    public int lengthOfLISOptimizedTabulation(int[] nums) {
        this.n = nums.length;
        int[] dp = new int[n]; // SC: O(N)
        // each element can have length 1 as LIS starting from itself
        Arrays.fill(dp, 1);
        int maxLength = 1;
        for (int idx = 1; idx < n; idx++) { // TC: O(N)
            // prevIdx should always be before idx
            for (int prevIdx = 0; prevIdx < idx; prevIdx++) { // TC: O(N)
                if (nums[prevIdx] < nums[idx]) {
                    // we can include nums[idx] in the LIS so increase 1 to dp[prevIdx] 
                    dp[idx] = Math.max(dp[idx], 1 + dp[prevIdx]);
                }
            }
            // compare and store the maximum length here
            maxLength = Math.max(maxLength, dp[idx]);
        }
        return maxLength;
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(N x N)
     * SC: O(N) + O(N) ~ O(N)
     * - O(N) - current and next array memory
     *
     * Accepted (56 / 56 testcases passed)
     */
    public int lengthOfLISSpaceOptimization(int[] nums) {
        this.n = nums.length;
        // Intialization
        int[] next = new int[n + 1]; // SC: O(N)
        /**
         * Base Case Conversion -> (idx >= n) return 0 which 
         * is not needed to handle as dp is initialized with 0s
         */
        // Iterative Calls - converting Recursion Calls - (reversing indices of start)
        for (int idx = n - 1; idx >= 0; idx--) { // TC: O(N)
            int[] current = new int[n + 1]; // SC: O(N)
            // prevIdx cannot start ahead of index 'idx' (with shifts of 1)
            for (int prevIdx = idx - 1; prevIdx >= -1; prevIdx--) { // TC: O(N)
                int length = next[prevIdx + 1];
                if (prevIdx == -1 || nums[idx] > nums[prevIdx]) {
                    length = Math.max(length, 1 + next[idx + 1]);
                }
                current[prevIdx + 1] = length;
            }
            next = current;
        }
        return next[-1 + 1];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N x N)
     * SC: O(N x N)
     * - O(N x N) - dp array memory
     *
     * Accepted (56 / 56 testcases passed)
     */
    public int lengthOfLISTabulation(int[] nums) {
        this.n = nums.length;
        // Intialization
        int[][] dp = new int[n + 1][n + 1]; // SC: O(N x N)
        /**
         * Base Case Conversion -> (idx >= n) return 0 which 
         * is not needed to handle as dp is initialized with 0s
         */
        // Iterative Calls - converting Recursion Calls - (reversing indices of start)
        for (int idx = n - 1; idx >= 0; idx--) { // TC: O(N)
            // prevIdx cannot start ahead of index 'idx' (with shifts of 1)
            for (int prevIdx = idx - 1; prevIdx >= -1; prevIdx--) { // TC: O(N)
                int length = dp[idx + 1][prevIdx + 1];
                if (prevIdx == -1 || nums[idx] > nums[prevIdx]) {
                    length = Math.max(length, 1 + dp[idx + 1][idx + 1]);
                }
                dp[idx][prevIdx + 1] = length;
            }
        }
        return dp[0][-1 + 1];
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
