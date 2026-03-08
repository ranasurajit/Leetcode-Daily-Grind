class Solution {
    /**
     * Approach II : Using Optimal(Cantor’s Diagonal Argument - Diagonal Flipping) Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            sb.append(nums[i].charAt(i) == '0' ? '1' : '0');
        }
        return sb.toString();
    }

    /**
     * Approach I : Using Brute-Force(Backtracking) Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack space
     */
    public String findDifferentBinaryStringBruteForce(String[] nums) {
        int n = nums.length;
        Set<String> set = new HashSet<>(); // SC: O(N)
        for (String num : nums) { // TC: O(N)
            set.add(num);
        }
        String[] result = { null };
        backtrack(0, n, new StringBuilder(), set, result); // TC: O(2 ^ N), SC: O(N)
        return result[0];
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void backtrack(int idx, int n, StringBuilder sb, Set<String> set, String[] result) {
        // Base Case
        if (result[0] != null) {
            return;
        }
        if (idx == n) {
            // sb will have a String of length = n
            if (!set.contains(sb.toString())) {
                result[0] = sb.toString();
            }
            return;
        }
        // Recursion Calls
        for (int j = 0; j <= 1; j++) {
            sb.append(j);
            backtrack(idx + 1, n, sb, set, result);
            sb.setLength(sb.length() - 1);
        }
    }
}
