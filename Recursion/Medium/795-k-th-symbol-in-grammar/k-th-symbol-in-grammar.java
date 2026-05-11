class Solution {
    /**
     * Approach : Using Recursion Approach
     *
     * TC : O(n)
     * SC : O(n)
     * - O(n) - recursion stack
     */
    public int kthGrammar(int n, int k) {
        return solve(n, k);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    private int solve(int n, int k) {
        // Base Case
        if (n == 1 && k == 1) {
            return 0;
        }
        // Recursion Calls
        int numElements = 1 << (n - 1);  // 2^(n - 1)
        int mid = numElements / 2; // 0-based index
        if (k <= mid) {
            // the values will be replication of previous row
            return solve(n - 1, k);
        } else {
            return solve(n - 1, k - mid) == 1 ? 0 : 1;
        }
    }
}
