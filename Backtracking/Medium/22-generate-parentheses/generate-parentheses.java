class Solution {
    /**
     * Approach : Using Backtracking Approach
     *
     * TC : O(4ⁿ)
     * SC : O(n)
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        solve(0, 0, n, sb, result);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC : O(4ⁿ)
     * SC : O(n)
     */
    private void solve(int open, int close, int n, StringBuilder sb,
        List<String> result) {
        // Base Case
        if (open == n && close == n) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        if (open < n) {
            // we can use open parenthesis if its count < n
            sb.append('('); // modify
            solve(open + 1, close, n, sb, result); // explore
            sb.setLength(sb.length() - 1); // backtrack
        }
        if (close < open) {
            // we can use close parenthesis if close < open
            sb.append(')'); // modify
            solve(open, close + 1, n, sb, result); // explore
            sb.setLength(sb.length() - 1);
        }
    }
}
