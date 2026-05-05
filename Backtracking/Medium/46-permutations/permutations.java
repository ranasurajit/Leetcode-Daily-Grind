class Solution {
    /**
     * Approach : Using Backtracking Approach
     *
     * TC : O(n x n!)
     * SC : O(n!) + O(n)
     *
     * - O(n!) - current arraylist memory
     * - O(n) - recursion stack 
     */
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>(); // SC : O(n!)
        Set<Integer> hs = new HashSet<>(); // SC : O(n)
        solve(n, nums, current, hs, result);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC : O(n x n!)
     * SC : O(n)
     */
    private void solve(int n, int[] nums, List<Integer> current,
        Set<Integer> hs, List<List<Integer>> result) {
        // Base Case
        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }
        // Recursion Calls
        for (int j = 0; j < n; j++) { // TC : O(n)
            if (!hs.contains(nums[j])) {
                current.add(nums[j]); // modify
                hs.add(nums[j]); // modify
                solve(n, nums, current, hs, result); // explore
                current.remove(current.size() - 1); // backtrack
                hs.remove(nums[j]); // backtrack
            }
        }
    }
}
