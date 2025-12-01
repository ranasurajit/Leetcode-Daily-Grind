class Solution {
    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(N)
     */
    public List<List<String>> partition(String s) {
        int n = s.length();
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> current = new ArrayList<String>();
        backtrack(0, n, s, current, result);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(N ^ 2 x  2 ^ N) ~ O(N x 2 ^ N) due to pruning
     * SC: O(N)
     */
    private void backtrack(int idx, int n, String s, List<String> current,
        List<List<String>> result) {
        // Base Case
        if (idx == n) {
            // no more partitions needed
            result.add(new ArrayList<String>(current));
            return;
        }
        // Recursion Calls
        for (int j = idx; j < n; j++) { // TC: O(N)
            String temp = s.substring(idx, j + 1);
            if (isPalindrome(temp)) {   // TC: O(N) - pruning happens here
                current.add(temp); // modify
                backtrack(j + 1, n, s, current, result); // backtrack
                current.remove(current.size() - 1); // backtrack
            }
        }
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N / 2) ~ O(N)
     * SC: O(1)
     */
    private boolean isPalindrome(String s) {
        int p = 0;
        int q = s.length() - 1;
        while (p < q) {
            if (s.charAt(p) != s.charAt(q)) {
                return false;
            }
            p++;
            q--;
        }
        return true;
    }
}
