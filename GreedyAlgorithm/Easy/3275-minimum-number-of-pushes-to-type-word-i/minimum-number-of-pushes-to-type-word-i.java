class Solution {
    /**
     * Approach : Using Greedy + Hashing Approach
     *
     * TC : O(n) + O(26) ~ O(n)
     * SC : O(26) ~ O(1)
     */
    public int minimumPushes(String word) {
        int n = word.length();
        /**
         * Greedily we can try to place the smallest
         * letters of String 'word' in each keys so
         * that the pushes needed is at least as 
         * possible.
         *
         * so we need to pre-compute the frequencies
         * of each letter that will contribute to the
         * push while typing the String 'word'
         */
        int[] freq = new int[26];     // SC : O(26)
        for (int i = 0; i < n; i++) { // TC : O(n)
            freq[word.charAt(i) - 'a']++;
        }
        int pushes = 0;
        int distinctKeyMapUsed = 0;
        int keyPushFactor = 1;
        for (int i = 0; i < 26; i++) { // TC : O(26)
            if (freq[i] > 0) {
                pushes += keyPushFactor * freq[i];
                distinctKeyMapUsed++;
            }
            if (distinctKeyMapUsed == 8) {
                // we can place mappings in keys (2-9) i.e. 8 keys
                // all distinct key has been used
                distinctKeyMapUsed = 0;
                keyPushFactor++;
            }
        }
        return pushes;
    }
}
