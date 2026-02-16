class Solution {
    /**
     * Approach : Using Bit-Manipulation Approach
     *
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 31; i >= 0; i--) { // TC: O(32)
            int currentBit = ((n >> i) & 1); // get the current bit
            // multiply current bit with reversed (index)
            result += (1 << (31 - i)) * currentBit;
        }
        return result;
    }
}
