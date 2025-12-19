class Solution {
    /**
     * Approach III : Using BuiltIn Function Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * Approach II : Using KMP Algorithm + Two Pointers Approach
     *
     * TC: O(M + N)
     * SC: O(M)
     */
    public int strStrUsingKMP(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        // construct lps array from pattern i.e. needle
        int[] lps = new int[m]; // SC: O(M)
        constructLPS(needle, lps, m); // TC: O(M)
        int i = 0; // pointer at the start of String 'haystack'
        int j = 0; // pointer at the start of String 'needle'
        while (i < n) { // TC: O(N)
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    return i - j;
                }
            } else {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    /**
     * Using KMP Algorithm to compute LPS Approach
     *
     * TC: O(M)
     * SC: O(1)
     */
    private void constructLPS(String pattern, int[] lps, int m) {
        int i = 1;
        int len = 0;
        while (i < m) { // TC: O(M)
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    /**
     * Approach I : Using Two Pointers Approach
     *
     * TC: O(N x M)
     * SC: O(1)
     */
    public int strStrUsingTwoPointers(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        int i = 0; // pointer at the start of String 'haystack'
        int j = 0; // pointer at the start of String 'needle'
        while (i < n) { // TC: O(N X M)
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    return i - j;
                }
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        return -1;
    }
}
