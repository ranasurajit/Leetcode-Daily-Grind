class Solution {
    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x N x N) + O(N x log(N)) ~ O(N³)
     * SC: O(N²) + O(N)
     * - O(N²) - memoization memory
     * - O(N) - recursion stack
     * 
     * Accepted (49 / 49 testcases passed)
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); // TC: O(N x log(N))
        ArrayList<Integer>[][] memo = new ArrayList[n + 1][n + 1]; // SC: O(N x N)
        return solveMemoization(0, -1, n, nums, memo);
    }
    
    /**
     * Using Recursion Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(N)
     */
    private ArrayList<Integer> solveMemoization(int idx, int prev, int n, 
        int[] nums, ArrayList<Integer>[][] memo) {
        // Base Case
        if (idx == n) {
            return new ArrayList<Integer>();
        }
        // Memoization Check
        if (memo[idx][prev + 1] != null) {
            return memo[idx][prev + 1];
        }
        // Recursion Calls
        // pick or skip
        ArrayList<Integer> skip = solveMemoization(idx + 1, prev, n, nums, memo);
        ArrayList<Integer> pick = new ArrayList<Integer>();
        if (prev == -1 || nums[idx] % nums[prev] == 0) {
            pick.add(nums[idx]);
            List<Integer> next = solveMemoization(idx + 1, idx, n, nums, memo);
            pick.addAll(next);
        }
        return memo[idx][prev + 1] = pick.size() > skip.size() ? pick : skip;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(N x 2 ^ N) + O(N x log(N)) ~ O(N x 2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (47 / 49 testcases passed)
     */
    public List<Integer> largestDivisibleSubsetRecursion(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); // TC: O(N x log(N))
        return solveRecursion(0, -1, n, nums);
    }
    
    /**
     * Using Recursion Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(N)
     */
    private ArrayList<Integer> solveRecursion(int idx, int prev, int n, int[] nums) {
        // Base Case
        if (idx == n) {
            return new ArrayList<Integer>();
        }
        // Recursion Calls
        // pick or skip
        ArrayList<Integer> skip = solveRecursion(idx + 1, prev, n, nums);
        ArrayList<Integer> pick = new ArrayList<Integer>();
        if (prev == -1 || nums[idx] % nums[prev] == 0) {
            pick.add(nums[idx]);
            List<Integer> next = solveRecursion(idx + 1, idx, n, nums);
            pick.addAll(next);
        }
        return pick.size() > skip.size() ? pick : skip;
    }
}
