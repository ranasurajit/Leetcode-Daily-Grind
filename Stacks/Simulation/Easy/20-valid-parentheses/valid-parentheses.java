class Solution {
    /**
     * Approach : Using Stack Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public boolean isValid(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<Character>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            if (st.isEmpty() && !isOpenBracket(ch)) {
                return false;
            }
            if (isOpenBracket(ch)) {
                st.push(ch);
            } else {
                char open = getOpenBracket(ch);
                if (open == st.peek()) {
                    st.pop();
                } else {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }

    /**
     * Using Enumeration Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isOpenBracket(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    /**
     * Using Enumeration Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    private char getOpenBracket(char ch) {
        char open = '(';
        switch (ch) {
            case '}':
                open = '{';
                break;
            case ']':
                open = '[';
                break;
            default:
                open = '(';
        }
        return open;
    }
}
