class Solution {
    /**
     * Using Backtracking Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n) + O(n) ~ O(n)
     *
     * - O(n) - recursion stack
     * - O(n) - StringBuilder memory
     */
    public List<String> validStrings(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder(); // SC : O(n)
        solve(n, sb, result); // TC : O(2ⁿ), SC : O(n)
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private void solve(int n, StringBuilder sb, List<String> result) {
        // Base Case
        if (sb.length() == n) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        // try using '1'
        sb.append(1); // modify
        solve(n, sb, result); // explore
        sb.setLength(sb.length() - 1); // backtrack
        if (sb.isEmpty() || sb.charAt(sb.length() - 1) != '0') {
            sb.append(0); // modify
            solve(n, sb, result); // explore
            sb.setLength(sb.length() - 1); // backtrack
        }
    }
}
