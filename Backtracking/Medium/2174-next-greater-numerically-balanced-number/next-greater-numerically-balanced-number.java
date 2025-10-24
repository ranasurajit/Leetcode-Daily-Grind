class Solution {
    private final int[] digitCount = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    /**
     * Approach II : Using Backtracking Approach
     *
     * TC: O(9 ^ D), where D = number of digits in n
     * SC: O(1)
     */
    public int nextBeautifulNumber(int n) {
        int numDigits = String.valueOf(n).length();
        int result = backtrack(n, 0, numDigits); // TC: O(9 ^ D), SC: O(1)
        if (result == 0) {
            result = backtrack(n, 0, numDigits + 1); // TC: O(9 ^ D), SC: O(1)
        }
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(9 ^ D), where D = number of digits in n
     * SC: O(1)
     */
    private int backtrack(int n, int current, int digitsLeft) {
        // Base Case
        if (digitsLeft == 0) {
            for (int digit = 1; digit <= 9; digit++) { // TC: O(9)
                if (digitCount[digit] != 0 && digitCount[digit] != digit) {
                    return 0;
                }
            }
            return current > n ? current : 0;
        }
        // Recursion Calls
        int result = 0;
        for (int digit = 1; digit <= 9; digit++) { // TC: O(9)
            if (digitCount[digit] > 0 && digitCount[digit] <= digitsLeft) {
                digitCount[digit]--; // modify
                result = backtrack(n, current * 10 + digit, digitsLeft - 1); // explore
                digitCount[digit]++; // backtrack
            }
            if (result != 0) {
                break;
            }
        }
        return result;
    }

    /**
     * Approach I : Using Hashing Approach
     *
     * TC: O((L - N) x log10(M))
     * SC: O(1)
     */
    public int nextBeautifulNumberHashingApproach(int n) {
        int maxValue = 1224444; // as this is the balanced number for max n = 10 ^ 6 (from constraints)
        for (int i = n + 1; i <= maxValue; i++) { // TC: O(L - N)
            if (isBalanced(i)) { // TC: O(log10(M))
                return i;
            }
        }
        return -1;
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(log10(M))
     * SC: O(1)
     */
    private boolean isBalanced(int num) {
        int[] freq = new int[10];
        while (num > 0) { // TC: O(log10(M))
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
