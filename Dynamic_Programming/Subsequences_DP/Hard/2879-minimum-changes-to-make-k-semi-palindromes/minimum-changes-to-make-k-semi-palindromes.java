class Solution {
    /**
     * Approach: Precomputation + Dynamic Programming
     *
     * 1. Precompute the minimum changes required to make
     *    every substring a semi-palindrome.
     *
     * 2. Use DP to partition the string into exactly k
     *    substrings, each having length >= 2.
     *
     * TC: O(n^3 * sqrt(n) + k * n^2)
     * SC: O(n^2 + k * n)
     */
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1_000_000_000;

        // cost[i][j] = minimum changes required to make
        // substring s[i...j-1] a semi-palindrome.
        int[][] cost = new int[n][n + 1];

        // Step 1: Precompute substring costs.
        for (int len = 2; len <= n; len++) {

            for (int start = 0; start + len <= n; start++) {

                int minCost = INF;

                // Consider every proper divisor of len.
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Divide substring into d groups.
                    for (int r = 0; r < d; r++) {

                        int groupLen = len / d;

                        // Compare symmetric characters
                        // within the current group.
                        for (int q = 0; q < groupLen / 2; q++) {

                            int left = start + r + q * d;

                            int right = start + r
                                    + (groupLen - 1 - q) * d;

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                        }
                    }

                    minCost = Math.min(minCost, changes);
                }

                cost[start][start + len] = minCost;
            }
        }

        // Step 2: DP.
        // dp[i][j] = minimum changes to partition
        // first i characters into exactly j substrings.
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for (int groups = 1; groups <= k; groups++) {

            for (int i = groups * 2; i <= n; i++) {

                // Last substring is s[start...i-1].
                for (int start = (groups - 1) * 2;
                     start <= i - 2;
                     start++) {

                    if (dp[start][groups - 1] == INF) {
                        continue;
                    }

                    dp[i][groups] = Math.min(
                        dp[i][groups],
                        dp[start][groups - 1] + cost[start][i]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
