class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int index = 0;
        while (index < n - 1) { // TC: O(N)
            if (bits[index] == 0) {
                // we can consider this as 1 bit 0 so, move 1 step
                index++;
            } else {
                index += 2;
                // we can consider this as 2 bits 10 or 11 so, move 2 steps
            }
        }
        // if it landed at index = n then last character is a part of 2 bits
        return index == n - 1 && bits[n - 1] == 0;
    }
}
