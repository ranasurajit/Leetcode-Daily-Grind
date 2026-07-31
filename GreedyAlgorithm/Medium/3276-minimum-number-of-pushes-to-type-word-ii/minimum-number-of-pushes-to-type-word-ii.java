class Solution {
    /**
     * Approach : Using Greedy + Hashing Approach
     *
     * TC : O(n) + O(26) ~ O(n)
     * SC : O(52) ~ O(1)
     */
    public int minimumPushes(String word) {
        int n = word.length();
        /**
         * we need to store the frequencies of each
         * letter in String 'word' so that we can
         * assign the letter having highest frequencies
         * to be mapped to the keys so that minimum
         * key push is needed to type the same
         */
        int[] freq = new int[26];     // SC : O(26)
        for (int i = 0; i < n; i++) { // TC : O(n)
            freq[word.charAt(i) - 'a']++;
        }
        /**
         * now we need to sort the 'freq' array so
         * that we can map the highest frequencies first
         */
        Arrays.sort(freq); // TC : O(26 x log(26)) ~ O(1)
        int step = 1;
        int pushes = 0;
        int alloc = 0;
        for (int i = 25; i >= 0; i--) { // TC : O(26)
            pushes += freq[i] * step;
            alloc++;
            if (alloc % 8 == 0) {
                /*
                 * keys (2-9) can only be mapped 
                 * so, maximum key allocation = 8
                 */
                step++;
                alloc = 0;
            }
        }
        return pushes;
    }
}
