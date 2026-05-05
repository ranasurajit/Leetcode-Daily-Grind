class Solution {
    /**
     * Approach : Using Backtracking Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n) + O(n) ~ O(n)
     *
     * - O(n) - recursion stack
     * - O(n) - current arraylist memory
     */
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>(); // SC : O(n)
        solve(0, n, nums, current, result); // TC : O(2ⁿ), SC : O(n)
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private void solve(int idx, int n, int[] nums, List<Integer> current,
        List<List<Integer>> result) {
        // Base Case
        if (idx == n) {
            result.add(new ArrayList<>(current));
            return;
        }
        // Recursion Calls
        // skip element at index 'idx'
        solve(idx + 1, n, nums, current, result); // explore
        // pick element at index 'idx'
        current.add(nums[idx]); // modify
        solve(idx + 1, n, nums, current, result); // explore
        current.remove(current.size() - 1); // backtrack
    }
}
