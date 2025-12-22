class Solution {
    private int m;
    private int n;
    /**
     * Approach III : Using Optimized Tabulation Approach
     *
     * TC: O(N x N x M)
     * SC: O(N)
     * - O(N) - dp array memory
     *
     * Accepted (131 / 131 testcases passed)
     */
    public int minDeletionSize(String[] strs) {
        this.m = strs.length;
        this.n = strs[0].length();
        int[] dp = new int[n]; // SC: O(N)
        Arrays.fill(dp, 1); // all elements are LIS of length 1 by-default
        int maxLIS = 1;
        for (int idx = 1; idx < n; idx++) { // TC: O(N)
            for (int prevIdx = 0; prevIdx < idx; prevIdx++) { // TC: O(N)
                if (canBeIncluded(idx, prevIdx, strs) && dp[idx] < dp[prevIdx] + 1) { // TC: O(M)
                    dp[idx] = dp[prevIdx] + 1;
                }
            }
            maxLIS = Math.max(maxLIS, dp[idx]);
        }
        return n - maxLIS;
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x N x M)
     * SC: O(N x N) + O(N)
     * - O(N x N) - memoization memory
     * - O(N) - recursion stack memory (reused)
     *
     * Accepted (131 / 131 testcases passed)
     */
    public int minDeletionSizeMemoization(String[] strs) {
        this.m = strs.length;
        this.n = strs[0].length();
        int[][] memo = new int[n][n + 1]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return n - lisMemoization(0, -1, strs, memo); // TC: O(N x N x M), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N x M)
     * SC: O(N)
     */
    private int lisMemoization(int idx, int prevIdx, String[] strs, int[][] memo) {
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
        int skip = lisMemoization(idx + 1, prevIdx, strs, memo);
        int pick = 0;
        if (prevIdx == -1 || canBeIncluded(idx, prevIdx, strs)) { // TC: O(M)
            pick = 1 + lisMemoization(idx + 1, idx, strs, memo);
        }
        return memo[idx][prevIdx + 1] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(Exponential)
     * SC: O(N)
     * - O(N) - recursion stack memory (reused)
     *
     * Time Limit Exceeded (122 / 131 testcases passed)
     */
    public int minDeletionSizeRecursion(String[] strs) {
        this.m = strs.length;
        this.n = strs[0].length();
        return n - lisRecursion(0, -1, strs); // TC: O(Exponential), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(Exponential)
     * SC: O(N)
     */
    private int lisRecursion(int idx, int prevIdx, String[] strs) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Recursion Calls
        // pick or skip
        int skip = lisRecursion(idx + 1, prevIdx, strs);
        int pick = 0;
        if (prevIdx == -1 || canBeIncluded(idx, prevIdx, strs)) { // TC: O(M)
            pick = 1 + lisRecursion(idx + 1, idx, strs);
        }
        return Math.max(pick, skip);
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(M)
     * SC: O(1)
     */
    private boolean canBeIncluded(int idx, int prevIdx, String[] strs) {
        for (int i = 0; i < m; i++) { // TC: O(M)
            // check for all rows if strs[idx] can be taken when strs[prevIdx] was taken previously
            if (strs[i].charAt(idx) < strs[i].charAt(prevIdx)) {
                return false;
            }
        }
        return true;
    }
}
