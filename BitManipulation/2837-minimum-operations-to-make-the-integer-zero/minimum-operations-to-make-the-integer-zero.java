class Solution {
    /**
     * Approach : Using Bit-Manipulation + Math Approach
     *
     * TC: O(60) ~ O(1)
     * SC: O(1)
     */
    public int makeTheIntegerZero(int num1, int num2) {
        /**
         * To make num1 = 0 we need to subtract 2 ^ i + num2 k times
         *
         * num1 = (2 ^ i1 + num2) + (2 ^ i2 + num2) .... + (2 ^ ik + num2) 
         * = k * num2 + (2 ^ i1 + 2 ^ i2 + .... + 2 ^ ik)
         * 
         * (num1 - k * num2) = (2 ^ i1 + 2 ^ i2 + .... + 2 ^ ik)
         */
        for (int k = 0; k <= 60; k++) { // TC: O(60)
            long diff = (long) num1 - ((long) k * (long) num2);
            if (diff < 0) {
                continue;
            }
            int bitCounts = countSetBits(diff); // TC: O(64)
            if (bitCounts <= k && k <= diff) {
                /**
                 * bitCounts <= k, If we want to use k operations, then at 
                 * least bitCounts operations are needed.
                 * If k > diff then k operations would sum more than diff
                 */
                return k;
            }
        }
        return -1;
    }

    /**
     * Using Bit-Manipulation Approach
     *
     * TC: O(64)
     * SC: O(1)
     */
    private int countSetBits(long diff) {
        int count = 0;
        for (int i = 63; i >= 0; i--) { // TC: O(64)
            if (((diff >> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }
}
