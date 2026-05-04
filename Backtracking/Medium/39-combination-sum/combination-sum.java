class Solution {
    /**
     * Approach : Using Backtracking Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n) + O(n)
     *
     * - O(n) - recursion stack
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int n = candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>(); // SC : O(n)
        solve(0, n, candidates, target, current, result); // TC : O(2ⁿ), SC : O(n)
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private void solve(int idx, int n, int[] candidates, int target,
        List<Integer> current, List<List<Integer>> result) {
        // Base Case
        if (idx == n) {
            if (target == 0) {
                result.add(new ArrayList<>(current));
            }
            return;
        }
        // Recursion Calls
        // skip
        // we can skip element at index 'idx'
        solve(idx + 1, n, candidates, target, current, result); // explore
        if (target >= candidates[idx]) {
            // take
            // we can use candidates[idx] if target - candidates[idx] >= 0
            int currentLeft = target - candidates[idx]; // modify
            current.add(candidates[idx]); // modify
            // we can use same element at index 'idx'
            solve(idx, n, candidates, currentLeft, current, result); // explore
            target += candidates[idx]; // backtrack
            current.remove(current.size() - 1); // backtrack
        }
    }
}
