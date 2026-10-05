class Solution {
    /**
     * Approach:
     * Each primitive "()" contributes 2^depth to the score,
     * where depth is the nesting depth after closing ')'.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int scoreOfParentheses(String s) {
        int depth = 0;
        int score = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                depth++;
            } else {
                depth--;

                // "()" found
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth;
                }
            }
        }

        return score;
    }
}
