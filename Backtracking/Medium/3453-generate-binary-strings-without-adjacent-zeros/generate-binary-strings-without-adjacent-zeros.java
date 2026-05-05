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
     * TC : O(2 x 2ⁿ) ~ O(2ⁿ)
     * SC : O(n)
     */
    private void solve(int n, StringBuilder sb, List<String> result) {
        // Base Case
        if (sb.length() == n) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        for (int i = 0; i <= 1; i++) { // TC : O(2)
            // choice for 0
            if (i == 0) {
                if (sb.isEmpty() || sb.charAt(sb.length() - 1) != '0') {
                    sb.append(i); // modify
                    solve(n, sb, result); // explore
                    sb.setLength(sb.length() - 1); // backtrack
                }
            } else {
                sb.append(i); // modify
                solve(n, sb, result); // explore
                sb.setLength(sb.length() - 1); // backtrack
            }
        }
    }
}
