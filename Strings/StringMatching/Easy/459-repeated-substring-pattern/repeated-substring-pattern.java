class Solution {
    /**
     * Approach : Using KMP Algorithm Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] lps = new int[n]; // SC : O(n)
        computeLPSArray(s, n, lps);   // TC : O(n), SC : O(1)
        // length of prefix which is also a suffix ending at index (n - 1)
        int len = lps[n - 1];
        return len > 0 && n % (n - len) == 0;
    }

    /**
     * Using KMP Algorithm for LPS Array Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    private void computeLPSArray(String s, int n, int[] lps) {
        lps[0] = 0;
        int len = 0;
        int i = 1;
        while (i < n) {
            if (s.charAt(len) == s.charAt(i)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    i++;
                }
            }
        }
    }
}
