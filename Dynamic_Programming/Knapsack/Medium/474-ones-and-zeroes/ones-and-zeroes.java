class Solution {
    /**
     * Approach : Using Multi-Knapsack (Bottom Up DP) Approach
     *
     * TC : O(p x l) + O(p x m x n) ~ O(p x m x n)
     * SC : O(p x m x n)
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int p = strs.length;
        /**
         * pre-computing count of zeroes and ones in String[] strs
         */
        int[][] counts = new int[p][2]; // SC : O(p)
        for (int i = 0; i < p; i++) {   // TC : O(p)
            counts[i] = getCountZeroOnes(strs[i]); // TC : O(l)
        }
        int[][][] dp = new int[p + 1][m + 1][n + 1]; // SC : O(p x m x n)
        /**
         * Here dp[i][j][k] represents the maximum size
         * that is computed if we consider first (i) Strings
         * with maximum j 0's and k 1's
         */
        for (int i = 1; i <= p; i++) {         // TC : O(p)
            int zeroes = counts[i - 1][0];
            int ones = counts[i - 1][1];
            for (int j = 0; j <= m; j++) {     // TC : O(m)
                for (int k = 0; k <= n; k++) { // TC : O(n)
                    // skip
                    int skip = dp[i - 1][j][k];
                    // pick
                    int pick = 0;
                    if (zeroes <= j && ones <= k) {
                        pick = 1 + dp[i - 1][j - zeroes][k - ones];
                    }
                    dp[i][j][k] = Math.max(skip, pick);
                }
            }
        }
        return dp[p][m][n];
    }

    /**
     * Using String Simulation Approach
     * returns int[] as [ zeroes, ones ]
     *
     * TC : O(l)
     * SC : O(1)
     */
    private int[] getCountZeroOnes(String s) {
        int n = s.length();
        int zeroes = 0;
        for (int i = 0; i < n; i++) { // TC : O(l)
            if (s.charAt(i) == '0') {
                zeroes++;
            }
        }
        return new int[] { zeroes, n - zeroes };
    }
}
