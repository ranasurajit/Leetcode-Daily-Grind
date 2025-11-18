class Solution {
    private int n;
    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N²) + O(N x log(N)) + O(N) + O(K) ~ O(N²)
     * SC: O(N) + O(N) + O(K), where K = size(Largest Divisible Subset)
     * 
     * Accepted (49 / 49 testcases passed)
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        this.n = nums.length;
        /**
         * since we need every pair so order does not matter so it 
         * is better to sort 'nums' so that we can only check
         * condition such that nums[idx] >= nums[prevIdx] such that
         * nums[idx] % nums[prevIdx] == 0
         */
        Arrays.sort(nums); // TC: O(N x log(N))
        int[] dp = new int[n]; // SC: O(N)
        Arrays.fill(dp, 1);
        int[] track = new int[n]; // SC: O(N)
        int maxLength = 1;
        int maxIndex = 0;
        for (int idx = 1; idx < n; idx++) { // TC: O(N)
            track[idx] = idx; // set element to its own index (parent)
            for (int prevIdx = 0; prevIdx < idx; prevIdx++) { // TC: O(N)
                if (nums[idx] % nums[prevIdx] == 0 && dp[idx] < dp[prevIdx] + 1) {
                    dp[idx] = dp[prevIdx] + 1;
                    track[idx] = prevIdx;
                }
            }
            if (maxLength < dp[idx]) {
                maxLength = dp[idx];
                maxIndex = idx;
            }
        }
        // now we need to backtrack the LIS i.e. Largest Divisible Subset
        int[] lds = new int[maxLength]; // SC: O(K)
        int index = 0;
        while (track[maxIndex] != maxIndex) { // TC: O(N)
            lds[index] = nums[maxIndex];
            maxIndex = track[maxIndex];
            index++;
        }
        lds[index] = nums[maxIndex];
        List<Integer> result = new ArrayList<Integer>();
        for (int i = maxLength - 1; i >= 0; i--) { // TC: O(K)
            result.add(lds[i]);
        }
        return result;
    }

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
    public List<Integer> largestDivisibleSubsetMemoization(int[] nums) {
        this.n = nums.length;
        /**
         * since we need every pair so order does not matter so it 
         * is better to sort 'nums' so that we can only check
         * condition such that nums[idx] >= nums[prevIdx] such that
         * nums[idx] % nums[prevIdx] == 0
         */
        Arrays.sort(nums); // TC: O(N x log(N))
        List<Integer>[][] memo = new ArrayList[n + 1][n + 1]; // SC: O(N x N)
        return solveMemoization(0, -1, nums, memo); // TC: O(N x N x N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N x N)
     * SC: O(N)
     */
    private List<Integer> solveMemoization(int idx, int prevIdx, int[] nums, List<Integer>[][] memo) {
        // Base Case
        if (idx >= n) {
            return new ArrayList<Integer>();
        }
        // Memoization Check
        if (memo[idx][prevIdx + 1] != null) {
            // index shift for prevIdx as it starts from -1
            return memo[idx][prevIdx + 1];
        }
        // Recursion Calls
        List<Integer> skip = solveMemoization(idx + 1, prevIdx, nums, memo);
        List<Integer> pick = new ArrayList<Integer>();
        if (prevIdx == -1 || nums[idx] % nums[prevIdx] == 0) {
            pick.add(nums[idx]);
            List<Integer> next = solveMemoization(idx + 1, idx, nums, memo);
            pick.addAll(next); // TC: O(N)
        }
        return memo[idx][prevIdx + 1] = pick.size() > skip.size() ? pick : skip;
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
        this.n = nums.length;
        /**
         * since we need every pair so order does not matter so it 
         * is better to sort 'nums' so that we can only check
         * condition such that nums[idx] >= nums[prevIdx] such that
         * nums[idx] % nums[prevIdx] == 0
         */
        Arrays.sort(nums); // TC: O(N x log(N))
        return solveRecursion(0, -1, nums); // TC: O(N x 2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(N)
     */
    private List<Integer> solveRecursion(int idx, int prevIdx, int[] nums) {
        // Base Case
        if (idx >= n) {
            return new ArrayList<Integer>();
        }
        // Recursion Calls
        List<Integer> skip = solveRecursion(idx + 1, prevIdx, nums);
        List<Integer> pick = new ArrayList<Integer>();
        if (prevIdx == -1 || nums[idx] % nums[prevIdx] == 0) {
            pick.add(nums[idx]);
            List<Integer> next = solveRecursion(idx + 1, idx, nums);
            pick.addAll(next); // TC: O(N)
        }
        return pick.size() > skip.size() ? pick : skip;
    }
}
