class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(N) + O(26) ~ O(N)
     * SC: O(26) + O(26) ~ O(1)
     */
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        /**
         * (j - i) even means, indices that can be swapped
         * is such that i and j are both odd or even
         */
        int[] evenFreq = new int[26]; // SC: O(26)
        int[] oddFreq = new int[26];  // SC: O(26)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if ((i & 1) == 0) {
                // even index
                evenFreq[s1.charAt(i) - 'a']++;
                evenFreq[s2.charAt(i) - 'a']--;
            } else {
                // odd index
                oddFreq[s1.charAt(i) - 'a']++;
                oddFreq[s2.charAt(i) - 'a']--;
            }
        }
        for (int i = 0; i < 26; i++) { // TC: O(26)
            if (evenFreq[i] != 0 || oddFreq[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
