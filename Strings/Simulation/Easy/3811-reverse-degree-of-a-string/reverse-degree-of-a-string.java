class Solution {
    /**
     * Approach : Using Simulation Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int reverseDegree(String s) {
        int n = s.length();
        int degree = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            int revIdx = 26 - (s.charAt(i) - 'a');
            degree += revIdx * (i + 1);
        }
        return degree;
    }
}
