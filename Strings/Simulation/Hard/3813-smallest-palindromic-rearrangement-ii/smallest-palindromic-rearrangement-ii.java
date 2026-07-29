class Solution {
    private static final long LIMIT = (long) 1e6 + 1;

    /**
     * Approach : Using String Simulation + Math Approach
     *
     * TC : O(26² x m²) ~ O(n²)
     * SC : O(n)
     */
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        /**
         * as per constraints, 's' consists of lowercase English letters
         * and also 's' is guaranteed to be palindromic so to form the 
         * palindrome we need to see the lexographic order of what alphabets
         * can occur in 1st half which can be duplicated in 2nd half so we
         * need to know the frequencies of characters in String 's'
         */
        int[] freq = new int[26];     // SC : O(26)
        for (int i = 0; i < n; i++) { // TC : O(n)
            freq[s.charAt(i) - 'a']++;
        }
        // Convert frequencies into half frequencies
        int halfLen = n / 2;
        int oddCharIdx = -1;
        for (int i = 0; i < 26; i++) { // TC : O(26)
            if ((freq[i] & 1) == 1) {
                oddCharIdx = i;
            }
            freq[i] /= 2;
        }
        // Total palindromes
        if (countWays(freq, halfLen) < k) {
            return "";
        }
        StringBuilder left = new StringBuilder();
        for (int pos = 0; pos < halfLen; pos++) { // TC : O(m)
            for (int ch = 0; ch < 26; ch++) {     // TC : O(26)
                if (freq[ch] == 0) {
                    continue;
                }
                freq[ch]--;
                long ways = countWays(freq, halfLen - pos - 1); // TC : O(26 x m)
                if (ways >= k) {
                    left.append((char) ('a' + ch));
                    break;
                }
                k -= ways;
                freq[ch]++;
            }
        }
        StringBuilder result = new StringBuilder(left);
        if (oddCharIdx != -1) {
            char mid = (char) (oddCharIdx + 'a');
            result.append(mid);
        }
        result.append(left.reverse());
        return result.toString();
    }

    /**
     * Using Math Approach
     *
     * TC : O(26 x m)
     * SC : O(1)
     */
    private long countWays(int[] freq, int total) {
        long ans = 1;
        int remaining = total;
        for (int f : freq) { // TC : O(26)
            if (f == 0) {
                continue;
            }
            ans *= nCr(remaining, f);
            if (ans > LIMIT) {
                return LIMIT;
            }
            remaining -= f;
        }
        return ans;
    }

    /**
     * Using Math Approach
     *
     * TC : O(m)
     * SC : O(1)
     */
    private long nCr(int n, int r) {
        if (r > n) {
            return 0;
        }
        r = Math.min(r, n - r);
        long ans = 1;
        for (int i = 1; i <= r; i++) {
            ans = ans * (n - r + i) / i;
            if (ans > LIMIT) {
                return LIMIT;
            }
        }
        return ans;
    }
}
