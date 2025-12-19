class Solution {
    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(N x M)
     * SC: O(1)
     */
    public int strStr(String haystack, String needle) {
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
