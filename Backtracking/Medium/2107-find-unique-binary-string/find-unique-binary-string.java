class Solution {
    private String result;

    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack space
     */
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        Set<String> set = new HashSet<>(); // SC: O(N)
        for (String num : nums) { // TC: O(N)
            set.add(num);
        }
        backtrack(0, n, new StringBuilder(), set); // TC: O(2 ^ N), SC: O(N)
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void backtrack(int idx, int n, StringBuilder sb, Set<String> set) {
        // Base Case
        if (result != null) {
            return;
        }
        if (idx == n) {
            // sb will have a String of length = n
            if (!set.contains(sb.toString())) {
                result = sb.toString();
            }
            return;
        }
        // Recursion Calls
        for (int j = 0; j <= 1; j++) {
            sb.append(j);
            backtrack(idx + 1, n, sb, set);
            sb.setLength(sb.length() - 1);
        }
    }
}
