class Solution {
    /**
     * Approach : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(M x N)
     * SC: O(N) + O(N) ~ O(N)
     * 
     * - O(N) - prev and current array memory
     *
     * Accepted (66 / 66 testcases passed)
     */
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        // Initialization
        int[] prev = new int[n + 1];           // SC: O(N)
        prev[0] = 1;
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) {      // TC: O(M)
            int[] current = new int[n + 1];    // SC: O(N)
            current[0] = 1;
            for (int j = 1; j < n + 1; j++) {  // TC: O(N)
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    current[j] = prev[j - 1] + prev[j];
                } else {
                    current[j] = prev[j];
                }
            }
            prev = current.clone();
        }
        return prev[n];
    }
}
