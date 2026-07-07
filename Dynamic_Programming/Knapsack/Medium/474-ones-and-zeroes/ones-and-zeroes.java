class Solution {
    /**
     * Approach : Using Multi-Knapsack (Bottom Up DP) Approach
     *
     * TC : O(p x m x n)
     * SC : O(p x m x n)
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int p = strs.length;
        int[][][] dp = new int[p + 1][m + 1][n + 1]; // SC : O(p x m x n)
        /**
         * Here dp[i][j][k] represents the maximum size
         * that is computed if we consider first (i) Strings
         * with maximum j 0's and k 1's
         */
        for (int i = 1; i <= p; i++) {         // TC : O(p)
            int[] countZeroOnes = getCountZeroOnes(strs[i - 1]);
            int zeroes = countZeroOnes[0];
            int ones = countZeroOnes[1];
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
     * TC : O(n)
     * SC : O(1)
     */
    private int[] getCountZeroOnes(String s) {
        int n = s.length();
        int zeroes = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (s.charAt(i) == '0') {
                zeroes++;
            }
        }
        return new int[] { zeroes, n - zeroes };
    }
}
