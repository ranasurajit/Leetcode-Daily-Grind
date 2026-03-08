class Solution {
    /**
     * Approach II : Using Optimal(Cantor’s Diagonal Argument - Diagonal Flipping) Approach
     *
     * TC: O(n)
     * SC: O(n)
     */
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        StringBuilder sb = new StringBuilder(); // SC: O(n)
        for (int i = 0; i < n; i++) { // TC: O(n)
            sb.append(nums[i].charAt(i) == '0' ? '1' : '0');
        }
        return sb.toString();
    }

    /**
     * Approach I : Using Brute-Force(Backtracking) Approach
     *
     * TC: O(n x 2ⁿ)
     * SC: O(n)
     * - O(n) - recursion stack space
     */
    public String findDifferentBinaryStringBruteForce(String[] nums) {
        int n = nums.length;
        Set<String> set = new HashSet<>(); // SC: O(n)
        for (String num : nums) { // TC: O(n)
            set.add(num);
        }
        String[] result = { null };
        backtrack(0, n, new StringBuilder(), set, result); // TC: O(n x 2ⁿ), SC: O(n)
        return result[0];
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(n x 2ⁿ)
     * SC: O(n)
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
