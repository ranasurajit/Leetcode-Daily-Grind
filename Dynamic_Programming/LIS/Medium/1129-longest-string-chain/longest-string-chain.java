class Solution {
    /**
     * Approach V : Using Optimized Tabulation Approach
     *
     * TC: O(L x N x N) + O(N x log(N)) ~ O(L x N x N)
     * SC: O(N)
     * - O(N) - 'dp' array memory
     *
     * Accepted (86 / 86 testcases passed)
     */
    public int longestStrChain(String[] words) {
        int n = words.length;
        /**
         * Order does not matter here and we should sort the String[] words
         * so that for any current word we can check if previous word is a
         * predecessor or not
         */
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        }); // TC: O(N x log(N))
        int[] dp = new int[n]; // SC: O(N)
        Arrays.fill(dp, 1); // each index will be a String of LIS of size 1
        int maxLength = 1;
        for (int idx = 1; idx < n; idx++) { // TC: O(N)
            for (int prevIdx = 0; prevIdx < idx; prevIdx++) { // TC: O(N)
                if (isPredecessor(words[prevIdx], words[idx]) && 
                    dp[idx] < 1 + dp[prevIdx]) { // TC: O(L)
                    dp[idx] = 1 + dp[prevIdx];
                }
            }
            maxLength = Math.max(maxLength, dp[idx]);
        }
        return maxLength;
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(L x N x N) + O(N x log(N)) ~ O(L x N x N)
     * SC: O(N) + O(N) ~ O(N)
     * - O(N) - 'next' array memory
     * - O(N) - 'current' array memory
     *
     * Accepted (86 / 86 testcases passed)
     */
    public int longestStrChainSpaceOptimization(String[] words) {
        int n = words.length;
        /**
         * Order does not matter here and we should sort the String[] words
         * so that for any current word we can check if previous word is a
         * predecessor or not
         */
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        }); // TC: O(N x log(N))
        int[] next = new int[n + 1]; // SC: O(N)
        for (int idx = n - 1; idx >= 0; idx--) { // TC: O(N)
            int[] current = new int[n + 1]; // SC: O(N)
            for (int prevIdx = idx - 1; prevIdx >= -1; prevIdx--) { // TC: O(N)
                int skip = next[prevIdx + 1];
                int pick = 0;
                if (prevIdx == -1 || isPredecessor(words[prevIdx], words[idx])) { // TC: O(L)
                    // pick
                    pick = 1 + next[idx + 1];
                }
                current[prevIdx + 1] = Math.max(skip, pick);
            }
            next = current;
        }
        return next[-1 + 1];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(L x N x N) + O(N x log(N)) ~ O(L x N x N)
     * SC: O(N x N)
     * - O(N x N) - memoization memory
     *
     * Accepted (86 / 86 testcases passed)
     */
    public int longestStrChainTabulation(String[] words) {
        int n = words.length;
        /**
         * Order does not matter here and we should sort the String[] words
         * so that for any current word we can check if previous word is a
         * predecessor or not
         */
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        }); // TC: O(N x log(N))
        int[][] dp = new int[n + 1][n + 1]; // SC: O(N x N)
        for (int idx = n - 1; idx >= 0; idx--) { // TC: O(N)
            for (int prevIdx = idx - 1; prevIdx >= -1; prevIdx--) { // TC: O(N)
                int skip = dp[idx + 1][prevIdx + 1];
                int pick = 0;
                if (prevIdx == -1 || isPredecessor(words[prevIdx], words[idx])) { // TC: O(L)
                    // pick
                    pick = 1 + dp[idx + 1][idx + 1];
                }
                dp[idx][prevIdx + 1] = Math.max(skip, pick);
            }
        }
        return dp[0][-1 + 1];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(L x N x N) + O(N x log(N)) ~ O(L x N x N)
     * SC: O(N x N) + O(N)
     * - O(N x N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (86 / 86 testcases passed)
     */
    public int longestStrChainMemoization(String[] words) {
        int n = words.length;
        /**
         * Order does not matter here and we should sort the String[] words
         * so that for any current word we can check if previous word is a
         * predecessor or not
         */
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        }); // TC: O(N x log(N))
        int[][] memo = new int[n][n + 1]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, -1, n, words, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(L x N x N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int prevIdx, int n, String[] words, int[][] memo) {
        // Base Case
        if (idx >= n) {
            return 0;
        }
        // Recursion Calls
        // Memoization Check
        if (memo[idx][prevIdx + 1] != -1) {
            // coordinate shift as prevIdx starts from -1
            return memo[idx][prevIdx + 1];
        }
        // skip
        int skip = solveMemoization(idx + 1, prevIdx, n, words, memo);
        int pick = 0;
        if (prevIdx == -1 || isPredecessor(words[prevIdx], words[idx])) { // TC: O(L)
            // pick
            pick = 1 + solveMemoization(idx + 1, idx, n, words, memo);
        }
        return memo[idx][prevIdx + 1] = Math.max(skip, pick); // return max (longest chain)
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(L x 2 ^ N) + O(N x log(N)) ~ O(L x 2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (78 / 86 testcases passed)
     */
    public int longestStrChainRecursion(String[] words) {
        int n = words.length;
        /**
         * Order does not matter here and we should sort the String[] words
         * so that for any current word we can check if previous word is a
         * predecessor or not
         */
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        }); // TC: O(N x log(N))
        return solveRecursion(0, -1, n, words);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(L x 2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int prevIdx, int n, String[] words) {
        // Base Case
        if (idx >= n) {
            return 0;
        }
        // Recursion Calls
        // skip
        int skip = solveRecursion(idx + 1, prevIdx, n, words);
        int pick = 0;
        if (prevIdx == -1 || isPredecessor(words[prevIdx], words[idx])) { // TC: O(L)
            // pick
            pick = 1 + solveRecursion(idx + 1, idx, n, words);
        }
        return Math.max(skip, pick); // as we need to return longest String chain
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(Min(M, N)) ~ O(L), where L = average length of parameter
     * SC: O(1)
     */
    private boolean isPredecessor(String prev, String current) {
        int m = prev.length();
        int n = current.length();
        if (m + 1 != n) {
            // predecessor should be 1 character less than current word
            return false;
        }
        int p = 0; // start pointer at String 'prev'
        int q = 0; // start pointer at String 'current'
        int skipped = 0;
        while (p < m && q < n) {
            if (prev.charAt(p) == current.charAt(q)) {
                p++;
                q++;
            } else {
                q++;
                skipped++;
                if (skipped > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
