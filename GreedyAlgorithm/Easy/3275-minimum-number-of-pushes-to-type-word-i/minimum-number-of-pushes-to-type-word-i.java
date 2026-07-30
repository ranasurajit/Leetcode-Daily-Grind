class Solution {
    /**
     * Approach : Using Greedy + Hashing Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int minimumPushes(String word) {
        int n = word.length();
        /**
         * Greedily we can try to place any distinct
         * letters of String 'word' in each keys so
         * that the pushes needed is at least as 
         * possible.
         */
        int pushes = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            // as we can remap any distinct 8 characters to 8 keys (2-9) only
            pushes += (i / 8) + 1;
        }
        return pushes;
    }
}
