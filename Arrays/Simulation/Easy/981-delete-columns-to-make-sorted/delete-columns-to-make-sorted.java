class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(M x N)
     * SC: O(1)
     */
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int count = 0;
        for (int j = 0; j < m; j++) {     // TC: O(M)
            for (int i = 1; i < n; i++) { // TC: O(N)
                if (strs[i].charAt(j) < strs[i - 1].charAt(j)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
