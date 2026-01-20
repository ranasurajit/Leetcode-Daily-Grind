class Solution {
    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        int n = nums.length;
        List<Integer> current = new ArrayList<Integer>(); // SC: O(N)
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backtrack(0, n, nums, current, result);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(N) + O(N) ~ O(N)
     */
    private void backtrack(int idx, int n, int[] nums, List<Integer> current, 
        List<List<Integer>> result) {
        // Base Case
        if (current.size() > 1) {
            result.add(new ArrayList<Integer>(current));
        }
        // Recursion Calls
        /**
         * we need a Set to check if nums[i] is already used 
         * in current Recursion so that duplicate list elements
         * are not added
         */
        Set<Integer> set = new HashSet<Integer>();
        for (int i = idx; i < n; i++) { // TC: O(N)
            if ((current.isEmpty() || nums[i] >= current.get(current.size() - 1)) && 
                !set.contains(nums[i])) {
                // in this condition only we can add nums[i] to current
                current.add(nums[i]); // modify
                set.add(nums[i]);     // modify
                backtrack(i + 1, n, nums, current, result); // explore
                current.remove(current.size() - 1); // backtrack
            }
        }
    }
}
