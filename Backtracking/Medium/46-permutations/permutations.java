class Solution {
    /**
     * Approach II : Using Backtracking (Template 1) Approach
     *
     * TC: O(N x N!)
     * SC: O(N!)
     */
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        solveBetter(0, n, nums, result);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(N x N!)
     * SC: O(N!)
     */
    private void solveBetter(int idx, int n, int[] nums, List<List<Integer>> result) {
        // Base Case
        if (idx == n) {
            List<Integer> current = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) { // TC: O(N)
                current.add(nums[i]);
            }
            result.add(current);
            return;
        }
        // Recursion Calls
        for (int i = idx; i < n; i++) { // TC: O(N)
            swap(nums, idx, i); // modify
            solveBetter(idx + 1, n, nums, result); // explore
            swap(nums, idx, i); // backtrack
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * Approach I : Using Backtracking (Template 1) Approach
     *
     * TC: O(N x N!)
     * SC: O(N!)
     */
    public List<List<Integer>> permuteTemplate1(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<Integer>();
        Set<Integer> hs = new HashSet<Integer>();
        solve(n, nums, current, result, hs);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(N x N!)
     * SC: O(N!)
     */
    private void solve(int n, int[] nums, 
        List<Integer> current, List<List<Integer>> result, Set<Integer> hs) {
        // Base Case
        if (current.size() == n) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        // Recursion Calls
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (hs.isEmpty() || !hs.contains(nums[i])) {
                current.add(nums[i]); // modify
                hs.add(nums[i]);      // modify
                solve(n, nums, current, result, hs); // explore
                current.remove(current.size() - 1); // backtrack
                hs.remove(nums[i]);                 // backtrack
            }
        }
    }
}
