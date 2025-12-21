class Solution {
    /**
     * Approach : Using String Simulation Approach
     * 
     * TC: O(M x N)
     * SC: O(N)
     */
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int deletion = 0;
        boolean[] isSorted = new boolean[n - 1]; // SC: O(N)
        for (int j = 0; j < m; j++) {         // TC: O(M)
            boolean isColDeleted = false;
            for (int i = 0; i < n - 1; i++) { // TC: O(N)
                if (!isSorted[i] && strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    // not sorted
                    deletion++;
                    isColDeleted = true;
                    break;
                }
            }
            if (isColDeleted) {
                continue;
            }
            for (int i = 0; i < n - 1; i++) { // TC: O(N)
                isSorted[i] = (isSorted[i] | strs[i].charAt(j) < strs[i + 1].charAt(j));
            }
        }
        return deletion;
    }
}
