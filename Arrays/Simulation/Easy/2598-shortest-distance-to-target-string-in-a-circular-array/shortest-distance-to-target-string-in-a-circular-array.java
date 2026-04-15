class Solution {
    /**
     * Approach II : Using Array Simulation Approach
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(n)
            if (words[i].equals(target)) {
                int right = Math.abs(i - startIndex);
                int left = n - right;
                minDist = Math.min(minDist, Math.min(left, right));
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    /**
     * Approach I : Using Array Simulation Approach
     *
     * TC: O(n / 2) ~ O(n)
     * SC: O(1)
     */
    public int closestTargetArraySimulation(String[] words, String target,
        int startIndex) {
        int n = words.length;
        /**
         * we can offset a distance either side of the startIndex
         * and if we encounter words[i] = target then that guarantees
         * the shortest distance to the target String
         */
        int maxOffset = n / 2;
        for (int offset = 0; offset <= maxOffset; offset++) { // TC: O(n / 2)
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
