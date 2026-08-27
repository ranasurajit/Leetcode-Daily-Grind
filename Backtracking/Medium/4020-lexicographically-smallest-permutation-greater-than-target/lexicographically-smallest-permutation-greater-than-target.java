class Solution {
    private int n;
    private String result = "";

    /**
     * Approach : Using Backtracking Approach
     *
     * TC : O(26 x n)
     * SC : O(n)
     * - O(n) - recursion stack
     */
    public String lexGreaterPermutation(String s, String target) {
        this.n = s.length();
        int[] freq = new int[26];     // SC : O(26)
        for (int i = 0; i < n; i++) { // TC : O(n)
            freq[s.charAt(i) - 'a']++;
        }
        /**
         * we will try to build the lexicographically smallest
         * string greater than target so we will start looping
         * from right to left to bump the minimum possible index
         * from right to give the desired String
         */
        StringBuilder sb = new StringBuilder();
        solve(0, sb, freq, target, false);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC : O(26 x n)
     * SC : O(n)
     */
    private boolean solve(int i, StringBuilder sb, 
        int[] freq, String target, boolean isGreater) {
        // Base Case
        if (i == n) {
            if (isGreater) {
                result = sb.toString();
                return true;
            }
            return false;
        }
        // Recursion Calls
        for (char ch = 'a'; ch <= 'z'; ch++) { // TC : O(26)
            // we will try to use substitute char 'ch' at index 'i'
            if (freq[ch - 'a'] == 0) {
                continue;
            }
            if (!isGreater && ch < target.charAt(i)) {
                continue;
            }
            boolean greater = isGreater || ch > target.charAt(i);
            sb.append(ch);    // modify
            freq[ch - 'a']--; // modify
            if (solve(i + 1, sb, freq, target, greater)) { // explore
                return true;
            }
            sb.setLength(sb.length() - 1); // backtrack
            freq[ch - 'a']++; // backtrack
        }
        return false;
    }
}
