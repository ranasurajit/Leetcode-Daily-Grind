class Solution {
    /**
     * Approach : Using Game Theory + Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public boolean doesAliceWin(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (isVowel(s.charAt(i))) {
                count++;
            }
        }
        if (count == 0) {
            // Alice cannot pick a non-empty sub-string, so Alice cannot win
            return false;
        }
        // In other scenarios Alice will end up winning
        return true;
    }

    /**
     * Using Enumeration Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
