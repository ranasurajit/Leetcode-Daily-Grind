class Solution {
    /**
     * Approach II : Using Sliding Window (Variable Size) Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    public int maximumLengthSubstring(String s) {
        int n = s.length();
        /**
         * as per constraints, 's' consists only of 
         * lowercase English letters. So, we can 
         * consider 'freqMap' as an array to store
         * only 26 characters' index
         */
        int[] freqMap = new int[26]; // SC : O(26)
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int maxLength = 0;
        while (j < n) { // TC : O(n)
            int chIdx = s.charAt(j) - 'a';
            freqMap[chIdx]++;
            // the change is frequency will be caused by character at index 'j'
            while (freqMap[chIdx] > 2) {
                // remove computation from index 'i'
                int cIdx = s.charAt(i) - 'a';
                freqMap[cIdx]--;
                i++;
            }
            /**
             * at this point all the characters in the sub-string
             * [i...j] has atmost 2 occurences
             */
            maxLength = Math.max(maxLength, j - i + 1);
            j++;
        }
        return maxLength;
    }

    /**
     * Approach I : Using Sliding Window (Variable Size) + HashMap Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    public int maximumLengthSubstringUsingHashMap(String s) {
        int n = s.length();
        Map<Integer, Integer> freqMap = new HashMap<>(); // SC : O(n)
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int maxLength = 0;
        while (j < n) { // TC : O(n)
            int chIdx = s.charAt(j) - 'a';
            freqMap.put(chIdx, freqMap.getOrDefault(chIdx, 0) + 1);
            // the change is frequency will be caused by character at index 'j'
            while (freqMap.get(chIdx) > 2) {
                // remove computation from index 'i'
                int cIdx = s.charAt(i) - 'a';
                int freq = freqMap.get(cIdx);
                if (freq == 1) {
                    // we can remove the key from Map
                    freqMap.remove(cIdx);
                } else {
                    freqMap.put(cIdx, freq - 1);
                }
                i++;
            }
            /**
             * at this point all the characters in the sub-string
             * [i...j] has atmost 2 occurences
             */
            maxLength = Math.max(maxLength, j - i + 1);
            j++;
        }
        return maxLength;
    }
}
