class Solution {
    private int n;
    private String result = "";

    /**
     * Approach : Using Backtracking + Two Pointers Approach
     *
     * TC : O(n) + O(26) + O(26 x n) ~ O(n)
     * SC : O(n) + O(26) + O(n / 2) + O(n / 2) ~ O(n)
     * - O(n) - recursion stack
     */
    public String lexPalindromicPermutation(String s, String target) {
        this.n = s.length();
        int[] freq = new int[26];     // SC : O(26)
        for (int i = 0; i < n; i++) { // TC : O(n)
            freq[s.charAt(i) - 'a']++;
        }
        int oddCount = 0;
        for (int i = 0; i < 26; i++) { // TC : O(26)
            if ((freq[i] & 1) != 0) {
                oddCount++;
            }
        }
        if (oddCount > 1) {
            // not possible to form Palindromic String
            return "";
        }
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        solve(0, n - 1, left, right, target, freq, false);
        return result;
    }

    /**
     * Using Backtracking + Two Pointers Approach
     *
     * TC : O(26 x n) ~ O(n)
     * SC : O(n)
     */
    private boolean solve(int l, int r, StringBuilder left, StringBuilder right,
        String target, int[] freq, boolean isGreater) {
        // Base Case
        if (l == r) {
            for (char ch = 'a'; ch <= 'z'; ch++) { // TC : O(26)
                if (freq[ch - 'a'] == 0) {
                    continue;
                }
                String candidate = left.toString() + 
                    String.valueOf(ch) +
                    new StringBuilder(right).reverse().toString();
                if (candidate.compareTo(target) > 0) {
                    result = candidate;
                    return true;
                }
                return false;
            }
        }
        if (l > r) {
            String candidate = left.toString() + 
                new StringBuilder(right).reverse().toString();
            if (candidate.compareTo(target) > 0) {
                result = candidate;
                return true;
            }
            return false;
        }
        // Recursion Calls
        for (char ch = 'a'; ch <= 'z'; ch++) { // TC : O(26)
            if (freq[ch - 'a'] < 2) {
                continue;
            }
            if (!isGreater && ch < target.charAt(l)) {
                continue;
            }
            // choose char 'ch' for left and right
            left.append(ch);  // modify
            right.append(ch); // modify
            boolean greater = isGreater || ch > target.charAt(l);
            freq[ch - 'a'] -= 2;
            if (solve(l + 1, r - 1, left, 
                right, target, freq, greater)) { // explore
                return true;
            }
            left.setLength(left.length() - 1);  // backtrack
            right.setLength(right.length() - 1); // backtrack
            freq[ch - 'a'] += 2; // backtrack
        }
        return false;
    }
}
