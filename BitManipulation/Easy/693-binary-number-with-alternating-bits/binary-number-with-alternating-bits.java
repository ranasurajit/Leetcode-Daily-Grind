class Solution {
    /**
     * Approach II : Using Bit-Manipulation Approach
     *
     * Idea:
     *
     * If bits alternate, then n ^ (n >> 1) becomes all 1s (111...111).
     * A number with all 1s satisfies: x & (x + 1) == 0
     *
     * TC: O(1)
     * SC: O(1)
     */
    public boolean hasAlternatingBits(int n) {
        // we need to check if n has ones if we do (n) ^ (n >> 1)
        int x = (n ^ (n >> 1));
        // if x has all 1111...1's so x & (x + 1) will certainly return 0
        return (x & (x + 1)) == 0;
    }

    /**
     * Approach I : Using Bit-Manipulation (Bit By Bit Comparison) Approach
     *
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    public boolean hasAlternatingBitsComparisonApproach(int n) {
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
