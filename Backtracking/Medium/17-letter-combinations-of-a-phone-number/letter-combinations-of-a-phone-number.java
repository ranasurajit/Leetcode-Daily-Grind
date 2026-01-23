class Solution {
    private static final String[] keys = 
        { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    private List<String> result = new ArrayList<String>();

    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O(N x 4 ^ N) 
     *
     * as for a keypad key maximum 4 loops happen
     * For example keypad 9 has 4 options (w, x, y and z)
     *
     * SC: O(N)
     */
    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        StringBuilder sb = new StringBuilder();
        solve(0, n, digits, sb);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(N x 4 ^ N) 
     *
     * as for a keypad key maximum 4 loops happen
     * For example keypad 9 has 4 options (w, x, y and z)
     *
     * SC: O(N)
     */
    private void solve(int idx, int n, String digits, StringBuilder sb) {
        // Base Case
        if (idx == n) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        char c = digits.charAt(idx);
        String currentKey = keys[c - '0'];
        for (int i = 0; i < currentKey.length(); i++) { // TC: O(4)
            // pick
            char ch = currentKey.charAt(i);
            int size = sb.length();
            sb.append(ch);
            solve(idx + 1, n, digits, sb); // explore
            sb.setLength(size); // backtrack
        }    
    }
}
