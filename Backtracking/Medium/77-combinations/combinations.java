class Solution {
    private int k;

    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) + O(2 ^ N)
     * - O(N) - recursion stack
     * - O(2 ^ N) - current array memory
     */
    public List<List<Integer>> combine(int n, int k) {
        this.k = k;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<Integer>(); // SC: O(2 ^ N)
        solve(1, n, current, result); // TC: O(2 ^ N), SC: O(N)
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) - recursion stack
     */
    private void solve(int i, int n, List<Integer> current, List<List<Integer>> result) {
        // Base Case
        if (i > n) {
            if (current.size() == k) {
                result.add(new ArrayList<Integer>(current));
            }
            return;
        }
        // Recursion
        current.add(i); // modify - pick
        solve(i + 1, n, current, result); // explore - pick
        current.remove(current.size() - 1); // backtrack
        solve(i + 1, n, current, result); // explore - skip
    }
}
