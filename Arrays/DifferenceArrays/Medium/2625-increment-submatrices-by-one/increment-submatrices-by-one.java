class Solution {
    /**
     * Approach : Using Difference Array Approach
     *
     * TC: O(Q) + O(N x N) + O(N x N) ~ O(Q + N x N)
     * SC: O(1)
     */
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n][n];
        for (int[] query : queries) { // TC: O(Q)
            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];
            diff[r1][c1] += 1;
            if (c2 + 1 < n) {
                diff[r1][c2 + 1] -= 1;
            }
            if (r2 + 1 < n) {
                diff[r2 + 1][c1] -= 1;
            }
            if (r2 + 1 < n && c2 + 1 < n) {
                diff[r2 + 1][c2 + 1] += 1;
            }
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 1; j < n; j++) { // TC: O(N)
                diff[i][j] += diff[i][j - 1];
            }
        }
        for (int j = 0; j < n; j++) { // TC: O(N)
            for (int i = 1; i < n; i++) { // TC: O(N)
                diff[i][j] += diff[i - 1][j];
            }
        }
        return diff;
    }
}
