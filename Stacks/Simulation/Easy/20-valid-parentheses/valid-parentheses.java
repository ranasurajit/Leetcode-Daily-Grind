class Solution {
    /**
     * Approach : Using Stack Simulation Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    public boolean isValid(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<>(); // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            char ch = s.charAt(i);
            if (isOpen(ch)) {
                st.push(ch);
            } else {
                if (st.isEmpty()) {
                    /**
                     * if Stack is empty and we get a closed 
                     * parentheses so we cannot balance it
                     * so, String 's' is not valid
                     */ 
                    return false;
                } else {
                    if (hasOpenBracket(ch) == st.peek()) {
                        st.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        // String 's' will be valid if Stack has no open parentheses left
        return st.isEmpty();
    }

    /**
     * Using Enumeration Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    private boolean isOpen(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    /**
     * Using Enumeration Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    private char hasOpenBracket(char ch) {
        if (ch == ')') {
            return '(';
        } else if (ch == '}') {
            return '{';
        } else {
            return '[';
        }
    }
}
