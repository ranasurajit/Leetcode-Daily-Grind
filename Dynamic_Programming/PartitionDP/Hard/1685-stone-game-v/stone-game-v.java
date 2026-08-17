class Solution {
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC : O(n³)
     * SC : O(n²)
     * - O(n²) - dp array memory
     *
     * Accepted (132 / 132 testcases passed)
     */
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        long[] prefixSum = new long[n]; // SC : O(n)
        prefixSum[0] = stoneValue[0];
        for (int i = 1; i < n; i++) {   // TC : O(n)
            prefixSum[i] = prefixSum[i - 1] + stoneValue[i];
        }
        long[][] dp = new long[n + 1][n + 1]; // SC : O(n²)
        for (int l = n - 1; l >= 0; l--) {    // TC : O(n) 
            for (int r = l + 1; r < n; r++) { // TC : O(n)
                for (int k = l; k < r; k++) { // TC : O(n)
                    long leftSum = prefixSum[k] - 
                        (l > 0 ? prefixSum[l - 1] : 0);  // sum of [l...k]
                    long rightSum = prefixSum[r] - 
                        prefixSum[k];                    // sum of [r... (k + 1)]
                    if (leftSum < rightSum) {
                        // Bob will throw away right partition sub-array
                        dp[l][r] = Math.max(dp[l][r], leftSum + dp[l][k]);
                    } else if (leftSum > rightSum) {
                        // Bob will throw away left partition sub-array
                        dp[l][r] = Math.max(dp[l][r], rightSum + dp[k + 1][r]);
                    } else {
                        /**
                        * leftSum == rightSum
                        * Bob lets Alice decide which row will be thrown away
                        * so, Alice with try to maximize from both sub-arrays
                        */
                        dp[l][r] = Math.max(dp[l][r], Math.max(
                            leftSum + dp[l][k],
                            rightSum + dp[k + 1][r])
                        );
                    }
                }
            }
        }
        return (int) dp[0][n - 1];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(n³)
     * SC : O(n) + O(n²)
     * - O(n) - recursion stack
     * - O(n²) - memoization memory
     *
     * Accepted (132 / 132 testcases passed)
     */
    public int stoneGameVMemoization(int[] stoneValue) {
        int n = stoneValue.length;
        long[] prefixSum = new long[n]; // SC : O(n)
        prefixSum[0] = stoneValue[0];
        for (int i = 1; i < n; i++) {   // TC : O(n)
            prefixSum[i] = prefixSum[i - 1] + stoneValue[i];
        }
        long[][] memo = new long[n][n]; // SC : O(n²)
        for (long[] mem : memo) {
            Arrays.fill(mem, -1L);
        }
        return (int) solveMemoization(0, n - 1, prefixSum, memo);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(n³)
     * SC : O(n)
     */
    private long solveMemoization(int l, int r, long[] prefixSum, long[][] memo) {
        // Base Case
        if (l >= r) {
            return 0;
        }
        // Memoization Check
        if (memo[l][r] != -1L) {
            return memo[l][r];
        }
        // Recursion Calls
        long score = 0;
        for (int k = l; k < r; k++) { // TC : O(n)
            long leftSum = prefixSum[k] - 
                (l > 0 ? prefixSum[l - 1] : 0);         // sum of [l...k]
            long rightSum = prefixSum[r] - prefixSum[k]; // sum of [r... (k + 1)]
            if (leftSum < rightSum) {
                // Bob will throw away right partition sub-array
                score = Math.max(score, 
                    leftSum + solveMemoization(l, k, prefixSum, memo));
            } else if (leftSum > rightSum) {
                // Bob will throw away left partition sub-array
                score = Math.max(score, 
                    rightSum + solveMemoization(k + 1, r, prefixSum, memo));
            } else {
                /**
                 * leftSum == rightSum
                 * Bob lets Alice decide which row will be thrown away
                 * so, Alice with try to maximize from both sub-arrays
                 */
                score = Math.max(score, Math.max(
                    leftSum + solveMemoization(l, k, prefixSum, memo),
                    rightSum + solveMemoization(k + 1, r, prefixSum, memo)
                ));
            }
        }
        return memo[l][r] = score;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : Exponential
     * SC : O(n)
     * - O(n) - recursion stack
     *
     * Time Limit Exceeded (62 / 132 testcases passed)
     */
    public int stoneGameVRecursion(int[] stoneValue) {
        int n = stoneValue.length;
        long[] prefixSum = new long[n]; // SC : O(n)
        prefixSum[0] = stoneValue[0];
        for (int i = 1; i < n; i++) {   // TC : O(n)
            prefixSum[i] = prefixSum[i - 1] + stoneValue[i];
        }
        return (int) solveRecursion(0, n - 1, prefixSum);
    }

    /**
     * Using Recursion Approach
     *
     * TC : Exponential
     * SC : O(n)
     */
    private long solveRecursion(int l, int r, long[] prefixSum) {
        // Base Case
        if (l >= r) {
            return 0;
        }
        // Recursion Calls
        long score = 0;
        for (int k = l; k < r; k++) { // TC : O(n)
            long leftSum = prefixSum[k] - 
                (l > 0 ? prefixSum[l - 1] : 0);         // sum of [l...k]
            long rightSum = prefixSum[r] - prefixSum[k]; // sum of [r... (k + 1)]
            if (leftSum < rightSum) {
                // Bob will throw away right partition sub-array
                score = Math.max(score, 
                    leftSum + solveRecursion(l, k, prefixSum));
            } else if (leftSum > rightSum) {
                // Bob will throw away left partition sub-array
                score = Math.max(score, 
                    rightSum + solveRecursion(k + 1, r, prefixSum));
            } else {
                /**
                 * leftSum == rightSum
                 * Bob lets Alice decide which row will be thrown away
                 * so, Alice with try to maximize from both sub-arrays
                 */
                score = Math.max(score, Math.max(
                    leftSum + solveRecursion(l, k, prefixSum),
                    rightSum + solveRecursion(k + 1, r, prefixSum)
                ));
            }
        }
        return score;
    }
}
