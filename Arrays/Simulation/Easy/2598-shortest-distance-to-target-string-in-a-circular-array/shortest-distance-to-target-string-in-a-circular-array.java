class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(Max(s, n - s))
     * SC: O(1)
     * where s = startIndex and n = size(words)
     */
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        /**
         * we can offset a distance either side of the startIndex
         * and if we encounter words[i] = target then that guarantees
         * the shortest distance to the target String
         */
        int maxOffset = Math.max(startIndex, n - 1 - startIndex);
        for (int offset = 0; offset <= maxOffset; offset++) { // TC: O(Max(s, n - s))
            int leftIdx = (startIndex - offset + n) % n;
            int rightIdx = (startIndex + offset) % n;
            if (words[leftIdx].equals(target)) {
                return offset;
            }
            if (words[rightIdx].equals(target)) {
                return offset;
            }
        }
        return -1;
    }
}
