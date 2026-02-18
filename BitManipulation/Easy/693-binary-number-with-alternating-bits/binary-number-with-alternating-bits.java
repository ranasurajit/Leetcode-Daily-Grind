class Solution {
    /**
     * Approach I : Using Bit-Manipulation Approach
     *
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    public boolean hasAlternatingBits(int n) {
        int i = 31;
        while (((n >> i) & 1) != 1) { // TC: O(N1)
            i--;
        }
        int leftSetBit = i;
        int lastBitValue = 1;
        // here the leftSetBit has the position where left-most bit is set for n
        for (i = leftSetBit + 1; i >= 0; i--) { // TC: O(N2)
            int currentBit = ((n >> i) & 1);
            if (currentBit == lastBitValue) {
                return false;
            }
            lastBitValue = currentBit;
        }
        return true;
    }
}
