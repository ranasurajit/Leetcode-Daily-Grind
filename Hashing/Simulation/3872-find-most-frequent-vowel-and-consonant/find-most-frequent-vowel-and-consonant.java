class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(N) + O(26) ~ O(N)
     * SC: O(26) ~ O(1)
     */
    public int maxFreqSum(String s) {
        int n = s.length();
        int[] freq = new int[26]; // SC: O(26)
        for (int i = 0; i < n; i++) { // TC: O(N)
            freq[s.charAt(i) - 'a']++;
        }
        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;
        for (int i = 0; i < 26; i++) { // TC: O(26)
            char ch = (char) (i + 'a');
            if (isVowel(ch)) {
                maxVowelFreq = Math.max(maxVowelFreq, freq[i]);
            } else {
                maxConsonantFreq = Math.max(maxConsonantFreq, freq[i]);
            }
        }
        return maxVowelFreq + maxConsonantFreq;
    }

    /**
     * Utility to check if character is a vowel
     *
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
