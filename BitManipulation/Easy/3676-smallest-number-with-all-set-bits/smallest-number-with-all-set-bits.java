class Solution {
    /**
     * Approach : Using Bit-Manipulation Approach
     *
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    public int smallestNumber(int n) {
        int result = 0;
        boolean foundSetBit = false;
        for (int i = 31; i >= 0; i--) { // TC: O(32)
            int bitValue = ((n >> i) & 1);
            if (bitValue == 1 && !foundSetBit) {
                foundSetBit = true;
            }
            if (foundSetBit) {
                result += (1 << i);
            }
        }
        return result;
    }
}
