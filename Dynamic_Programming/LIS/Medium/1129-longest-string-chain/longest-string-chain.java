class Solution {
    /**
     * Approach : Using Dynamic Programming Tabulation (LIS) Approach
     *
     * TC: O(L x N x N) + O(N x log(N))
     * SC: O(N)
     */
    public int longestStrChain(String[] words) {
        int n = words.length;
        /**
         * As we need to find predecessor so the order of the 
         * words does not matter, rather we should sort it
         * based upon its length
         */
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        }); // TC: O(N x log(N))
        int[] dp = new int[n]; // SC: O(N)
        Arrays.fill(dp, 1);
        int maxLength = 1;
        for (int idx = 1; idx < n; idx++) { // TC: O(N)
            for (int prevIdx = 0; prevIdx < idx; prevIdx++) { // TC: O(N)
                if (isAllowed(words[idx], words[prevIdx]) && 
                    dp[idx] < dp[prevIdx] + 1) { // TC: O(L)
                    dp[idx] = dp[prevIdx] + 1;
                }
                maxLength = Math.max(maxLength, dp[idx]);
            }
        }
        return maxLength;
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(Min(M, N)) ~ O(L), where L = average size(words[i])
     * SC: O(1)
     */
    private boolean isAllowed(String current, String prev) {
        int m = prev.length();
        int n = current.length();
        if (m + 1 != n) {
            return false;
        }
        int p = 0; // pointer at the start of String 'prev'
        int q = 0; // pointer at the start of String 'current'
        int mismatches = 0;
        while (p < m && q < n) {
            if (prev.charAt(p) == current.charAt(q)) {
                p++;
                q++;
            } else {
                mismatches++;
                q++;
                if (mismatches > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
