class Solution {
    /**
     * Approach : Using Backtracking Approach
     *
     * TC : O(n x 2ⁿ)
     * SC : O(n x 2ⁿ) + O(n x 2ⁿ)
     *
     * - O(n x 2ⁿ) - recursion stack
     */
    public List<List<String>> partition(String s) {
        int n = s.length();
        List<List<String>> result = new ArrayList<>();
        List<String> current = new ArrayList<>(); // TC : O(n x 2ⁿ)
        solve(0, n, s, current, result);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC : O(n x 2ⁿ)
     * SC : O(n x 2ⁿ)
     */
    private void solve(int idx, int n, String s, List<String> current,
        List<List<String>> result) {
        // Base Case
        if (idx == n) {
            result.add(new ArrayList<>(current));
            return;
        }
        // Recursion Calls
        for (int i = idx; i < n; i++) { // TC : O(n)
            if (isPalindrome(s, idx, i)) { // TC : O(n)
                current.add(s.substring(idx, i + 1)); // modify
                solve(i + 1, n, s, current, result); // explore
                current.remove(current.size() - 1);
            }
        }
    }

    /**
     * Using Two Pointers Approach
     *
     * TC : O(L)
     * SC : O(1)
     */
    private boolean isPalindrome(String str, int i, int j) {
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
