class Solution {
    /**
     * Approach II : Using Inbuilt Method + Bit-Manipulation Approach
     *
     * TC: O(M)
     * SC: O(M), where M = leftmost set bit
     */
    public int bitwiseComplement(int n) {
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
    public int bitwiseComplementDivisionMethod(int n) {
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
