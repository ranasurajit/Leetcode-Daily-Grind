class Solution {
    /**
     * Approach : Using StringBuilder Approach
     *
     * TC: O(n)
     * SC: O(n)
     */
    public String removeOuterParentheses(String s) {
        int n = s.length();
        int counter = 0;
        StringBuilder sb = new StringBuilder(); // SC: O(n)
        for (int i = 0; i < n; i++) { // TC: O(n)
            char ch = s.charAt(i);
            if (ch == ')') {
                counter--;
            }
            if (counter != 0) {
                sb.append(ch);
            }
            if (ch == '(') {
                counter++;
            }
        }
        return sb.toString();
    }
}
