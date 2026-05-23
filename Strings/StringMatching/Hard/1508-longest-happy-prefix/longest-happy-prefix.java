class Solution {
    /**
     * Approach : Using KMP Algorithm Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    public String longestPrefix(String s) {
        int n = s.length();
        int[] lps = new int[n]; // SC : O(n)
        computeLPSArray(s, n, lps); // TC : O(n), SC : O(1)
        int len = lps[n - 1];
        // lps of last index decides the lonest prefix which is also the suffix
        return s.substring(0, len);
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
        while (i < n) { // TC : O(n)
            if (s.charAt(len) == s.charAt(i)) {
                len += 1;
                lps[i] = len;
                i++;
            } else {
                // fallback 'len' to a better index
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    i++;
                }
            }
        }
    }
}
