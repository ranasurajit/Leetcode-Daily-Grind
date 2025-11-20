class Solution {
    /**
     * Approach : Using Optimized DP (LIS) Approach
     *
     * TC: O(N x N) + O(N) ~ O(N x N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];    // SC: O(N)
        int[] count = new int[n]; // SC: O(N)
        int maxLength = 1;
        for (int idx = 0; idx < n; idx++) { // TC: O(N)
            dp[idx] = 1;
            count[idx] = 1;
            for (int prevIdx = 0; prevIdx < idx; prevIdx++) { // TC: O(N)
                if (nums[prevIdx] < nums[idx]) {
                    if (dp[idx] < 1 + dp[prevIdx]) {
                        dp[idx] = 1 + dp[prevIdx];
                        count[idx] = count[prevIdx];
                    } else if (dp[idx] == 1 + dp[prevIdx]) {
                        count[idx] += count[prevIdx];
                    }
                }
            }
            maxLength = Math.max(maxLength, dp[idx]);
        }
        int numLIS = 0;
        for (int idx = 0; idx < n; idx++) { // TC: O(N)
            if (dp[idx] == maxLength) {
                numLIS += count[idx];
            }
        }
        return numLIS;
    }
}
