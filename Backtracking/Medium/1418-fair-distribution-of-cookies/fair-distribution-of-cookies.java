class Solution {
    private int n;
    private int k;
    private int result = Integer.MAX_VALUE;

    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O(K ^ N)
     * SC: O(N) + O(N) ~ O(N)
     * - O(N) - dist array memory
     * - O(N) - recursion stack
     */
    public int distributeCookies(int[] cookies, int k) {
        this.n = cookies.length;
        this.k = k;
        int[] dist = new int[k]; // SC: O(K)
        backtrack(0, cookies, dist);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(K ^ N)
     * SC: O(N) - recursion stack
     */
    private void backtrack(int idx, int[] cookies, int[] dist) {
        // Base Case
        if (idx == n) {
            // cookies bag exhaused
            int unfairness = 0;
            for (int i = 0; i < k; i++) { // TC: O(K)
                unfairness = Math.max(unfairness, dist[i]);
            }
            result = Math.min(result, unfairness);
            return;
        }
        // Recursion Calls
        for (int j = 0; j < k; j++) { // TC: O(K)
            if (dist[j] + cookies[idx] >= result) {
                // pruning 1 : already worst than best answer
                continue;
            }
            dist[j] += cookies[idx]; // modify
            backtrack(idx + 1, cookies, dist); // explore
            dist[j] -= cookies[idx]; // backtrack
            // pruning 2 : symmetry breaking
            if (dist[j] == 0) {
                break;
            }
        }
    }
}
