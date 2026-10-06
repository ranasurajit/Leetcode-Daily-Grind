class Solution {
    /**
     * Approach II : Using String Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minAddToMakeValid(String s) {
        int n = s.length();
        int open = 0;
        int close = 0;
        int i = 0;
        while (i < n) { // TC: O(N)
            char ch = s.charAt(i);
            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
            i++;
        }
        return open + close;
    }
}
