class Solution {
    /**
     * Approach III : Using Tabulation (Forward Partition DP) Approach
     *
     * TC: O(K x N)
     * SC: O(N)
     * - O(N) - dp array memory
     *
     * Accepted (52 / 52 testcases passed)
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        // Initialization
        int[] dp = new int[n + 1]; // SC: O(N)
        dp[n] = 0;
        for (int idx = n - 1; idx >= 0; idx--) { // TC: O(N)
            int maximum = Integer.MIN_VALUE;
            int totalSum = 0;
            int maxSum = 0;
            for (int j = idx; j < Math.min(idx + k, n); j++) { // TC: O(K)
                maximum = Math.max(maximum, arr[j]);
                totalSum = maximum * (j - idx + 1) + dp[j + 1];
                maxSum = Math.max(maxSum, totalSum);
            }
            dp[idx] = maxSum;
        }
        return dp[0];
    }

    /**
     * Approach II : Using Memoization (Forward Partition DP) Approach
     *
     * TC: O(N x K)
     * SC: O(N) + O(N)
     * - O(N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (52 / 52 testcases passed)
     */
    public int maxSumAfterPartitioningMemoization(int[] arr, int k) {
        int n = arr.length;
        int[] memo = new int[n]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveMemoization(0, n, arr, k, memo); // returns maximum sum after partitions
    }

    /**
     * Using Memoization (Forward Partition DP) Approach
     *
     * TC: O(N x K)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int n, int[] arr, int k, int[] memo) {
        // Base Case
        if (idx == n) {
            // no sum can be accumulated
            return 0;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        int maximum = Integer.MIN_VALUE;
        int totalSum = 0;
        int maxSum = 0;
        for (int j = idx; j < Math.min(idx + k, n); j++) { // TC: O(K)
            maximum = Math.max(maximum, arr[j]);
            totalSum = maximum * (j - idx + 1) + solveMemoization(j + 1, n, arr, k, memo);
            maxSum = Math.max(maxSum, totalSum);
        }
        return memo[idx] = maxSum;
    }

    /**
     * Approach I : Using Recursion (Forward Partition DP) Approach
     *
     * TC: O(K ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (14 / 52 testcases passed)
     */
    public int maxSumAfterPartitioningRecursion(int[] arr, int k) {
        int n = arr.length;
        return solveRecursion(0, n, arr, k); // returns maximum sum after partitions
    }

    /**
     * Using Recursion (Forward Partition DP) Approach
     *
     * TC: O(K ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int n, int[] arr, int k) {
        // Base Case
        if (idx == n) {
            // no sum can be accumulated
            return 0;
        }
        // Recursion Calls
        int maximum = Integer.MIN_VALUE;
        int totalSum = 0;
        int maxSum = 0;
        for (int j = idx; j < Math.min(idx + k, n); j++) {
            maximum = Math.max(maximum, arr[j]);
            totalSum = maximum * (j - idx + 1) + solveRecursion(j + 1, n, arr, k);
            maxSum = Math.max(maxSum, totalSum);
        }
        return maxSum;
    }
}
