class Solution {
    private int k;
    private List<List<Integer>> result = new ArrayList<List<Integer>>();

    /**
     * Approach II : Using Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) + O(K)
     * - O(N) - recursion stack
     * - O(K) - current array memory
     */
    public List<List<Integer>> combine(int n, int k) {
        this.k = k;
        List<Integer> current = new ArrayList<Integer>(); // SC: O(K)
        solveBetter(1, n, k, current); // TC: O(2 ^ N), SC: O(N)
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) - recursion stack
     */
    private void solveBetter(int start, int n, int k, List<Integer> current) {
        // Base Case
        if (start > n) {
            if (k == 0) {
                result.add(new ArrayList<Integer>(current));
            }
            return;
        }
        // Recursion Calls
        current.add(start); // modify
        solveBetter(start + 1, n, k - 1, current); // explore - pick
        current.remove(current.size() - 1); // backtrack
        solveBetter(start + 1, n, k, current); // explore - skip
    }

    /**
     * Approach I : Using Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) + O(2 ^ N)
     * - O(N) - recursion stack
     * - O(2 ^ N) - current array memory
     */
    public List<List<Integer>> combineBruteForce(int n, int k) {
        this.k = k;
        List<Integer> current = new ArrayList<Integer>(); // SC: O(2 ^ N)
        solve(1, n, current); // TC: O(2 ^ N), SC: O(N)
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) - recursion stack
     */
    private void solve(int i, int n, List<Integer> current) {
        // Base Case
        if (i > n) {
            if (current.size() == k) {
                result.add(new ArrayList<Integer>(current));
            }
            return;
        }
        // Recursion
        current.add(i); // modify - pick
        solve(i + 1, n, current); // explore - pick
        current.remove(current.size() - 1); // backtrack
        solve(i + 1, n, current); // explore - skip
    }
}
