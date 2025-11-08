class Solution {
    /**
     * Approach : Using Recursion + Bit-Manipulation Approach
     *
     * TC: O(31) + O(30) ~ O(1)
     * SC: O(32) ~ O(1)
     */
    public int minimumOneBitOperations(int n) {
        if (n == 0) {
            return 0;
        }
        long[] function = new long[32]; // SC: O(32)
        // Number of operations to convert n to 0 if ith bit is set
        function[0] = 1; // here 0th bit is 1 and it will take 1 operation to convert it to 0
        for (int i = 1; i <= 31; i++) { // TC: O(31)
            function[i] = 2 * function[i - 1] + 1;
        }
        int result = 0;
        int sign = 1;
        for (int i = 30; i >= 0; i--) { // TC: O(30)
            int ithBit = (1 << i) & n;
            if (ithBit == 0) {
                continue;
            }
            if (sign > 0) {
                result += function[i];
            } else {
                result -= function[i];
            }
            sign = sign * -1;
        }
        return result;
    }
}
