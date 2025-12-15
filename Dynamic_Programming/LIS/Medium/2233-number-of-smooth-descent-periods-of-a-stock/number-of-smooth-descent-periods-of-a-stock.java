class Solution {
    /**
     * Approach III : Using Dynamic Programming (Tabulation) Approach
     *
     * TC: O(N)
     * SC: O(N)
     * - O(N) - dp array memory
     *
     * Accepted (168 / 168 testcases passed)
     */
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long[] dp = new long[n]; // SC: O(N)
        // dp[i] - represents number of smooth descent periods ending at index 'i'
        dp[0] = 1;
        long count = dp[0];
        for (int i = 1; i < n; i++) { // TC: O(N)
            dp[i] = 1;
            if (prices[i] == prices[i - 1] - 1) {
                dp[i] = 1 + dp[i - 1];
            }
            count += dp[i];
        }
        return count;
    }

    /**
     * Approach II : Using Dynamic Programming (Memoization) Approach
     *
     * TC: O(N)
     * SC: O(N) + O(N)
     * - O(N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (168 / 168 testcases passed)
     */
    public long getDescentPeriodsMemoization(int[] prices) {
        int n = prices.length;
        long count = 0;
        long[] memo = new long[n]; // SC: O(N)
        Arrays.fill(memo, -1);
        for (int i = 0; i < n; i++) { // TC: O(N)
            // solveMemoization(i, ...) - represents number of smooth descent periods ending at index 'i'
            count += solveMemoization(i, prices, memo); // TC: O(N), SC: O(N)
        }
        return count;
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private long solveMemoization(int idx, int[] prices, long[] memo) {
        // Base Case
        if (idx == 0) {
            return 1;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        long count = 1L;
        if (prices[idx] == prices[idx - 1] - 1) {
            count += solveMemoization(idx - 1, prices, memo);
        }
        return memo[idx] = count;
    }

    /**
     * Approach I : Using Dynamic Programming (Recursion) Approach
     *
     * TC: O(N ^ 2)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (166 / 168 testcases passed)
     */
    public long getDescentPeriodsRecursion(int[] prices) {
        int n = prices.length;
        long count = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            // solveRecursion(i, ...) - represents number of smooth descent periods ending at index 'i'
            count += solveRecursion(i, prices); // TC: O(N), SC: O(N)
        }
        return count;
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private long solveRecursion(int idx, int[] prices) {
        // Base Case
        if (idx == 0) {
            return 1;
        }
        // Recursion Calls
        long count = 1L;
        if (prices[idx] == prices[idx - 1] - 1) {
            count += solveRecursion(idx - 1, prices);
        }
        return count;
    }
}
