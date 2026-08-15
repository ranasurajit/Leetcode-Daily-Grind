class Solution {
    /**
     * Approach : Using Bit-Manipulation + Simulation Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;
        boolean hasNonZero = false;
        for (int num : nums) { // TC : O(n)
            xor ^= num;
            if (!hasNonZero && num != 0) {
                hasNonZero = true;
            }
        }
        /**
         * if XOR of all elements of 'nums' != 0 then
         * we can take the entire elements in the 
         * subsequence, else we need to check id 
         * atleast 1 element is zero and rest all
         * elements XOR is zero, so we can remove 1 
         * element that effectively makes the XOR = 0
         */
        if (xor != 0) {
            return n;
        }
        if (hasNonZero) {
            return n - 1;
        }
        /**
         * since all elements are zero so we cannot
         * form any such subsequence with non-zero
         * Bitwise XOR
         */
        return 0;
    }
}
