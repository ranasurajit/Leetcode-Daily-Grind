class Solution {
    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) + O(2 ^ N)
     */
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<Integer>(); // SC: O(2 ^ N)
        solve(0, n, nums, current, result); // TC: O(2 ^ N), SC: O(N)
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void solve(int idx, int n, int[] nums,
        List<Integer> current, List<List<Integer>> result) {
        // Base Case
        if (idx == n) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        // Recursion Calls
        current.add(nums[idx]); // modify
        solve(idx + 1, n, nums, current, result); // explore - pick
        current.remove(current.size() - 1); // backtrack
        solve(idx + 1, n, nums, current, result); // explore - skip
    }
}
