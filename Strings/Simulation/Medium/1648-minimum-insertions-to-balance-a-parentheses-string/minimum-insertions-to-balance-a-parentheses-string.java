class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                // Handle the first ')' of a required '))' pair.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++; // Consume the second ')'
                } else {
                    // Insert the missing ')'
                    insertions++;
                }

                if (open > 0) {
                    open--;
                } else {
                    // No matching '(' exists, so insert one.
                    insertions++;
                }
            }
        }

        // Every remaining '(' needs two closing parentheses.
        insertions += open * 2;

        return insertions;
    }
}
