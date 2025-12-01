class Solution {
    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public List<List<String>> partition(String s) {
        int n = s.length();
        List<List<String>> result = new ArrayList<List<String>>();
        backtrack(0, n, s, new ArrayList<String>(), result);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(N ^ 2 x 2 ^ N) ~ O(N x 2 ^ N) due to pruning
     * SC: O(N)
     */
    private void backtrack(int idx, int n, String s, List<String> current, List<List<String>> result) {
        // Base Case
        if (idx == n) {
            result.add(new ArrayList<String>(current));
            return;
        }
        // Recursion Calls
        for (int i = idx; i < n; i++) { // TC: O(N)
            String temp = s.substring(idx, i + 1);
            int m = temp.length();
            if (m == 1 || isPalindrome(temp, m)) { // TC: O(N) - pruning here
                current.add(temp); // modify
                backtrack(i + 1, n, s, current, result); // explore
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
    private boolean isPalindrome(String temp, int size) {
        int low = 0;
        int high = size - 1;
        while (low < high) {
            if (temp.charAt(low) != temp.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}
