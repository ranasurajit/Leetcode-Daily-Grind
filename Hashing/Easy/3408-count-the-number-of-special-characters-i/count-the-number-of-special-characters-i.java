class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC : O(n) + O(26) ~ O(n)
     * SC : O(26) + O(26) ~ O(26)
     */
    public int numberOfSpecialChars(String word) {
        int n = word.length();
        int[] capsFreq = new int[26];  // SC : O(26)
        int[] smallFreq = new int[26]; // SC : O(26)
        for (int i = 0; i < n; i++) {  // TC : O(n)
            char ch = word.charAt(i);
            if (ch - 'a' >= 0 && ch - 'a' < 26) {
                smallFreq[ch - 'a']++;
            } else {
                capsFreq[ch - 'A']++;
            }
        }
        int count = 0;
        for (int i = 0; i < 26; i++) { // TC : O(26)
            if (smallFreq[i] > 0 && capsFreq[i] > 0) {
                count++;
            }
        }
        return count;
    }
}
