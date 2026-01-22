class Solution {
    private int m;
    private int n;
    private int maxTransfers = 0;

    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O(N x 2 ^ M)
     * SC: O(M + N)
     * - O(N) - movement array memory
     * - O(M) - recursion stack
     */
    public int maximumRequests(int n, int[][] requests) {
        m = requests.length;
        this.n = n;
        int[] movement = new int[n]; // SC: O(N)
        backtrack(0, 0, requests, movement); // TC: O(N x 2 ^ M), SC: O(M)
        return maxTransfers;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(N x 2 ^ M)
     * SC: O(M) - recursion stack
     */
    private void backtrack(int idx, int count, int[][] requests, int[] movement) {
        // Base Case
        if (idx == m) {
            for (int i = 0; i < n; i++) { // TC: O(N)
                if (movement[i] != 0) {
                    return;
                }
            }
            maxTransfers = Math.max(maxTransfers, count);
            return;
        }
        // Recursion Calls
        if (count + (m - idx) <= maxTransfers) {
            // early pruning - even if we take the requests it cannot beat maxTransfers
            return;
        }
        int from = requests[idx][0];
        int to = requests[idx][1];
        // modify
        movement[from]--;
        movement[to]++;
        backtrack(idx + 1, count + 1, requests, movement); // explore - pick
        // backtrack - undo
        movement[from]++;
        movement[to]--;
        backtrack(idx + 1, count, requests, movement); // explore - skip
    }
}
