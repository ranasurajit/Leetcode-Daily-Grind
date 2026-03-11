class Solution {
    /**
     * Approach IV : Using Bit-Manipulation + Bit-Masking Approach
     *
     * TC: O(log(N) Base 2)
     * SC: O(1)
     */
    public int bitwiseComplement(int n) {
        int result = 0;
        if (n == 0) {
            return 1; // flipped
        }
        int temp = n;
        int mask = 0;
        while (temp > 0) { // TC: O(log(N) Base 2)
            mask = ((mask << 1) | 1);
            temp = temp >> 1;
        }
        return (n ^ mask);
    }

    /**
     * Approach III : Using Bit By Bit Traverse Method + Bit-Manipulation Approach
     *
     * TC: O(30)
     * SC: O(1)
     */
    public int bitwiseComplementBitByBitComputation(int n) {
        int result = 0;
        /**
         * as per constraints, 0 <= n < 10^9, 
         * so n can have fit within 30 bits
         */
        int maxBit = 29;
        // find leftmost set bit
        int setBitPos = 0;
        for (int i = maxBit; i >= 0; i--) { // TC: O(30 - setBitPos)
            int currentBit = ((n >> i) & 1);
            if (currentBit == 1) {
                setBitPos = i;
                break;
            }
        }
        for (int i = setBitPos; i >= 0; i--) { // TC: O(setBitPos)
            int flippedBit = ((n >> i) & 1) == 1 ? 0 : 1;
            if (flippedBit == 1) {
                result += (1 << i);
            }
        }
        return result;
    }

    /**
     * Approach II : Using Inbuilt Method + Bit-Manipulation Approach
     *
     * TC: O(M)
     * SC: O(M), where M = leftmost set bit
     */
    public int bitwiseComplementUsingInbuiltMethod(int n) {
        int result = 0;
        String binaryStr = Integer.toBinaryString(n); // SC: O(M)
        int m = binaryStr.length();
        for (int i = m - 1; i >= 0; i--) { // TC: O(M)
            int flipped = binaryStr.charAt(i) == '0' ? 1 : 0;
            if (flipped == 1) {
                result += (1 << (m - 1 - i));
            }
        }
        return result;
    }

    /**
     * Approach I : Using Math + Bit-Manipulation Approach
     *
     * TC: O(log(N) Base 2)
     * SC: O(1)
     */
    public int bitwiseComplementUsingDivisionMethod(int n) {
        int result = 0;
        if (n == 0) {
            return 1; // flipped
        }
        int pow = 0;
        while (n > 0) { // TC: O(log(N) Base 2)
            int rem = n % 2;
            if (rem == 0) {
                result += (1 << pow);
            }
            n = n / 2;
            pow++;
        }
        return result;
    }
}
