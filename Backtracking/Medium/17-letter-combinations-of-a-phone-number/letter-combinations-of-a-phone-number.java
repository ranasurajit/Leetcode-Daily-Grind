class Solution {
    private static final char[][] keys = {
        { },
        { },
        { 'a', 'b', 'c' },
        { 'd', 'e', 'f' },
        { 'g', 'h', 'i' },
        { 'j', 'k', 'l' },
        { 'm', 'n', 'o' },
        { 'p', 'q', 'r', 's' },
        { 't', 'u', 'v' },
        { 'w', 'x', 'y', 'z' }
    };

    private Map<Integer, char[]> keysMap;

    /**
     * Using Backtracking Approach
     *
     * TC : O(4ⁿ)
     * SC : O(n) + O(n) ~ O(n)
     *
     * - O(n) - recursion stack
     */
    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        this.keysMap = new HashMap<>(); // SC : O(10)
        for (int i = 0; i < keys.length; i++) { // TC : O(10) ~ O(1)
            keysMap.put(i, keys[i]);
        }
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder(); // SC : O(n)
        solve(0, n, digits, sb, result); // TC : O(4ⁿ), SC : O(n)
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC : O(4ⁿ)
     * SC : O(n)
     */
    private void solve(int idx, int n, String digits, 
        StringBuilder sb, List<String> result) {
        // Base Case
        if (idx == n) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        int currentKey = digits.charAt(idx) - '0';
        for (char ch : keysMap.get(currentKey)) { // TC : O(4)
            sb.append(ch); // modify
            solve(idx + 1, n, digits, sb, result); // explore
            sb.setLength(sb.length() - 1); // backtrack
        }
    }
}
