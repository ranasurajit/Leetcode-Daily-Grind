class Solution {
    /**
     * Approach II : Using Backtracking Approach
     *
     * TC : O(n x n!)
     * SC : O(n!)
     *
     * - O(n!) - current arraylist memory
     * - O(n!) - recursion stack 
     */
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        solveAgain(0, n, nums, result); // TC : O(n x n!), SC : O(n!)
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC : O(n x n!)
     * SC : O(n)
     */
    private void solveAgain(int idx, int n, int[] nums,
        List<List<Integer>> result) {
        // Base Case
        if (idx == n) {
            List<Integer> current = new ArrayList<>();
            for (int i = 0; i < n; i++) { // TC : O(n)
                current.add(nums[i]);
            }
            result.add(current);
            return;
        }
        // Recursion Calls
        for (int j = idx; j < n; j++) { // TC : O(n)
            // swap element nums[idx] with nums[j]
            swap(nums, idx, j); // 
            solveAgain(idx + 1, n, nums, result);
            swap(nums, idx, j);
        }
    }

    /**
     * Using Enumeration Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    /**
     * Approach I : Using Backtracking Approach
     *
     * TC : O(n x n!)
     * SC : O(n!) + O(n)
     *
     * - O(n!) - current arraylist memory
     * - O(n) - recursion stack 
     */
    public List<List<Integer>> permuteApproachI(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>(); // SC : O(n!)
        Set<Integer> hs = new HashSet<>(); // SC : O(n)
        solve(n, nums, current, hs, result); // TC : O(n x n!), SC : O(n)
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
