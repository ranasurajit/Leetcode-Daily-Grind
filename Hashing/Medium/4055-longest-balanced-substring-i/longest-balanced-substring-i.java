class Solution {
    /**
     * Approach : Using Brute-Force (Hashing + Simulation) Approach
     *
     * TC: O(N²)
     * SC: O(26) ~ O(1)
     */
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLength = 0;
        for (int i = 0; i < n; i++) {     // TC: O(N)
            int[] freq = new int[26];     // SC: O(26)
            int currentMax = 0;
            for (int j = i; j < n; j++) { // TC: O(N)
                int idx = s.charAt(j) - 'a';
                freq[idx]++;
                if (isBalanced(freq)) { // TC: O(1)
                    currentMax = Math.max(currentMax, j - i + 1);
                }
            }
            maxLength = Math.max(maxLength, currentMax);
        }
        return maxLength;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(26) ~ O(1)
     * SC: O(1)
     */
    private boolean isBalanced(int[] freq) {
        int value = -1;
        for (int f : freq) { // TC: O(26)
            if (f > 0) {
                if (value == -1) {
                    value = f;
                } else if (value != f) {
                    // not balanced
                    return false;
                }
            }
        }
        return true;
    }
}
