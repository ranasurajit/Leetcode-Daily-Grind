class Solution {
    /**
     * Approach : Using String Simulation Approach
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int maxDepth(String s) {
        int n = s.length();
        int counter = 0;
        int i = 0;
        int maxDepth = 0;
        while (i < n) { // TC: O(N)
            char ch = s.charAt(i);
            if (ch == '(') {
                counter++;
            }
            maxDepth = Math.max(maxDepth, counter);
            if (ch == ')') {
                counter--;
            }
            i++;
        }
        return maxDepth;
    }
}
