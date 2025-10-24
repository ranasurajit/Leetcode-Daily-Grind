class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(N x log(M))
     * SC: O(1)
     */
    public int nextBeautifulNumber(int n) {
        int maxValue = 1224444; // as this is the balanced number for max n = 10 ^ 6 (from constraints)
        for (int i = n + 1; i <= maxValue; i++) { // TC: O(N)
            if (isBalanced(i)) { // TC: O(log(M) Base 10)
                return i;
            }
        }
        return -1;
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(log(M) Base 10)
     * SC: O(1)
     */
    private boolean isBalanced(int num) {
        int[] freq = new int[10];
        while (num > 0) { // TC: O(log(M) Base 10)
            int digit = num % 10;
            num = num / 10;
            freq[digit]++;
        }
        for (int digit = 0; digit <= 9; digit++) { // TC: O(9)
            if (freq[digit] != 0 && freq[digit] != digit) {
                return false;
            }
        }
        return true;
    }
}
