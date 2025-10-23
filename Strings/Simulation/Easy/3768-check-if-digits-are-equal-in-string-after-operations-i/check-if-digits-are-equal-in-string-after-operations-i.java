class Solution {
    /**
     * Approach : Using String Simulation Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    public boolean hasSameDigits(String s) {
        while (s.length() > 2) { // TC: O(N - 2)
            StringBuilder sb = new StringBuilder(); // SC: O(N)
            for (int i = 1; i < s.length(); i++) { // TC: O(N)
                sb.append(((s.charAt(i - 1) - '0') + (s.charAt(i) - '0')) % 10);
            }
            s = sb.toString();
        }
        return s.charAt(0) == s.charAt(1);
    }
}
