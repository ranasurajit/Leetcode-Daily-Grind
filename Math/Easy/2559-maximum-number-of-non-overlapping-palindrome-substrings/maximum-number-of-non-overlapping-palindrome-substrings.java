class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        // pal[i][j] = true if s[i...j] is a palindrome
        boolean[][] pal = new boolean[n][n];

        // Build palindrome DP
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)
                        && (len <= 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        // dp[i] = maximum number of non-overlapping
        // valid palindromes in s[0...i-1]
        int[] dp = new int[n + 1];

        for (int end = 1; end <= n; end++) {

            // Don't select a palindrome ending at 'end - 1'
            dp[end] = dp[end - 1];

            // Try every possible starting position
            for (int start = 0; start <= end - k; start++) {
                if (pal[start][end - 1]) {
                    dp[end] = Math.max(
                        dp[end],
                        dp[start] + 1
                    );
                }
            }
        }

        return dp[n];
    }
}
