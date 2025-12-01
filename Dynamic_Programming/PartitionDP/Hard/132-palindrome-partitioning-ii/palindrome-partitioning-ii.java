class Solution {
    /**
     * Approach V : Using Tabulation (Partition Front DP) Approach
     *
     * TC: O(N²)
     * SC: O(N)
     * - O(N) - dp array memory
     *
     * Accepted (37 / 37 testcases passed)
     */
    public int minCut(String s) {
        int n = s.length();
        if (isPalindrome(s, 0, n - 1)) { // TC: O(N)
            return 0;
        }
        // Initialization
        int[] dp = new int[n + 1]; // SC: O(N)
        dp[n] = -1; // no cuts possible
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            int minLength = n - 1;
            for (int j = i; j < n; j++) { // TC: O(N)
                if (isPalindrome(s, i, j)) { // TC: O(N)
                    // pruned
                    minLength = Math.min(minLength, 1 + dp[j + 1]);
                }
            }
            dp[i] = minLength;
        }
        return dp[0];
    }

    /**
     * Approach IV : Using Memoization (Partition Front DP) Approach
     *
     * TC: O(N²)
     * SC: O(N) + O(N)
     * - O(N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (37 / 37 testcases passed)
     */
    public int minCutFrontMemoization(String s) {
        int n = s.length();
        if (isPalindrome(s, 0, n - 1)) { // TC: O(N)
            return 0;
        }
        int[] memo = new int[n]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveFrontMemoization(0, n, s, memo); // returns minimum partitions
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    private int solveFrontMemoization(int idx, int n, String s, int[] memo) {
        // Base Case
        if (idx == n || isPalindrome(s, idx, n - 1)) {
            return 0;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        int minPartitions = n - 1;
        StringBuilder sb = new StringBuilder();
        for (int j = idx; j < n; j++) { // TC: O(N)
            sb.append(s.charAt(j));
            int m = sb.length();
            if (m == 1 || isPalindrome(sb.toString(), 0, m - 1)) { // TC: O(N)
                // pruned
                minPartitions = Math.min(minPartitions,
                    1 + solveFrontMemoization(j + 1, n, s, memo)
                );
            }
        }
        return memo[idx] = minPartitions;
    }

    /**
     * Approach III : Using Recursion (Partition Front DP) Approach
     *
     * TC: Exponential
     * SC: O(N)
     *
     * Time Limit Exceeded (25 / 37 testcases passed)
     */
    public int minCutFrontRecursion(String s) {
        int n = s.length();
        if (isPalindrome(s, 0, n - 1)) { // TC: O(N)
            return 0;
        }
        return solveFrontRecursion(0, n, s); // returns minimum partitions
    }

    /**
     * Using Recursion Approach
     *
     * TC: Exponential
     * SC: O(N)
     */
    private int solveFrontRecursion(int idx, int n, String s) {
        // Base Case
        if (idx == n || isPalindrome(s, idx, n - 1)) {
            return 0;
        }
        // Recursion Calls
        int minPartitions = n - 1;
        StringBuilder sb = new StringBuilder();
        for (int j = idx; j < n; j++) { // TC: O(N)
            sb.append(s.charAt(j));
            int m = sb.length();
            if (m == 1 || isPalindrome(sb.toString(), 0, m - 1)) { // TC: O(N)
                minPartitions = Math.min(minPartitions,
                    1 + solveFrontRecursion(j + 1, n, s)
                );
            }
        }
        return minPartitions;
    }

    /**
     * Approach II : Using Memoization (Partition DP) Approach
     *
     * TC: O(N³)
     * SC: O(N²) + O(N)
     * - O(N²) - memoization memory
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (28 / 37 testcases passed)
     */
    public int minCutMemoization(String s) {
        int n = s.length();
        if (isPalindrome(s, 0, n - 1)) { // TC: O(N)
            return 0;
        }
        int[][] memo = new int[n][n]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n - 1, s, memo); // returns minimum partitions
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N x N)
     * SC: O(N)
     */
    private int solveMemoization(int i, int j, String s, int[][] memo) {
        // Base Case
        if (i >= j || isPalindrome(s, i, j)) {
            /**
             * return 0 partitions if String length is invalid
             * or 1 or if String is palindrome
             */
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        int minLength = s.length() - 1;
        for (int k = i; k <= j - 1; k++) { // TC: O(N)
            /**
             * partition at index k can happen till (j - 1) else
             * (i, k) and (k + 1, j) <= partition would go invalid
             */
            int leftPartitions = memo[i][k] != -1 ? 
                memo[i][k] : solveMemoization(i, k, s, memo);
            int rightPartitions = memo[k + 1][j] != -1 ?
                memo[k + 1][j] : solveMemoization(k + 1, j, s, memo);
            minLength = Math.min(minLength, 1 + leftPartitions + rightPartitions);
        }
        return memo[i][j] = minLength;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: Exponential
     * SC: O(N)
     *
     * Time Limit Exceeded (21 / 37 testcases passed)
     */
    public int minCutRecursion(String s) {
        int n = s.length();
        if (isPalindrome(s, 0, n - 1)) { // TC: O(N)
            return 0;
        }
        return solveRecursion(0, n - 1, s); // returns minimum partitions
    }

    /**
     * Using Recursion Approach
     *
     * TC: Exponential
     * SC: O(N)
     */
    private int solveRecursion(int i, int j, String s) {
        // Base Case
        if (i >= j || isPalindrome(s, i, j)) {
            /**
             * return 0 partitions if String length is invalid
             * or 1 or if String is palindrome
             */
            return 0;
        }
        // Recursion Calls
        int minLength = s.length() - 1;
        for (int k = i; k <= j - 1; k++) { // TC: O(N)
            /**
             * partition at index k can happen till (j - 1) else
             * (i, k) and (k + 1, j) <= partition would go invalid
             */
            minLength = Math.min(minLength,
                    1 + solveRecursion(i, k, s) + solveRecursion(k + 1, j, s)
                );
        }
        return minLength;
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N / 2) ~ O(N)
     * SC: O(1)
     */
    private boolean isPalindrome(String s, int p, int q) {
        while (p < q) {
            if (s.charAt(p) != s.charAt(q)) {
                return false;
            }
            p++;
            q--;
        }
        return true;
    }
}
