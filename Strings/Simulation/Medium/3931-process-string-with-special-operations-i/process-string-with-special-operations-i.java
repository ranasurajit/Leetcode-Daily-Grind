class Solution {
    /**
     * Approach : Using String Simulation Approach
     *
     * TC : O(n²)
     * SC : O(n²)
     */
    public String processStr(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) { // TC : O(n)
            char ch = s.charAt(i);
            if (ch == '*') {
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1);
                }
            } else if (ch == '#') {
                sb.append(sb.toString());
            } else if (ch == '%') {
                sb = sb.reverse(); // TC : O(n)
            } else {
                // ch is lowercase letter
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
