class Solution {
    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(N x 2 ^ N) - due to pruning
     * SC: O(N) + O(N) ~ O(N)
     */
    public List<List<String>> partition(String s) {
        int n = s.length();
        List<List<String>> result = new ArrayList<List<String>>(); // SC: O(N)
        List<String> current = new ArrayList<String>(); // SC: O(N)
        backtrack(0, n, s, current, result); // TC: O(N x 2 ^ N), SC: O(N)
        return result;
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N² x 2 ^ N) ~ O(N x 2 ^ N) - due to pruning
     * SC: O(N)
     */
    private void backtrack(int idx, int n, String s, 
        List<String> current, List<List<String>> result) {
        // Base Case
        if (idx == n) {
            result.add(new ArrayList<String>(current));
            return;
        }
        // Recursion Calls
        for (int i = idx + 1; i <= n; i++) { // TC: O(N)
            String subStr = s.substring(idx, i);
            if (subStr.length() == 1 || isPalindrome(subStr)) { // TC: O(N)
                // if above condition satisfies then only we can explore further in this path
                current.add(subStr); // modify
                backtrack(i, n, s, current, result); // explore
                current.remove(current.size() - 1);
            }
        }
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private boolean isPalindrome(String s) {
        int low = 0;
        int high = s.length() - 1;
        while (low < high) { // TC: O(N / 2)
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}
