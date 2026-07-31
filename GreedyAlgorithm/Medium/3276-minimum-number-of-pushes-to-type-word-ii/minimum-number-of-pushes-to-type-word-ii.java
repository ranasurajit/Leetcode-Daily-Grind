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
         * we need to store the frequencies of each
         * letter in String 'word' so that we can
         * assign the letter having highest frequencies
         * to be mapped to the keys so that minimum
         * key push is needed to type the same
         */
        int[][] freq = new int[26][2];     // SC : O(26 x 2)
        for (int i = 0; i < n; i++) { // TC : O(n)
            int idx = word.charAt(i) - 'a';
            freq[idx][0] = idx;
            freq[idx][1]++;
        }
        /**
         * now we need to sort the 'freq' array so
         * that we can map the highest frequencies first
         */
        Arrays.sort(freq, (a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            return b[1] - a[1];
        }); // TC : O(52 x log(52)) ~ O(1)
        int step = 1;
        int pushes = 0;
        int alloc = 0;
        for (int i = 0; i < 26; i++) { // TC : O(26)
            pushes += freq[i][1] * step;
            alloc++;
            if (alloc % 8 == 0) {
                step++;
                alloc = 0;
            }
        }
        return pushes;
    }
}
