class Solution {
    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(N x T) + O(N) ~ O(N x T)
     * SC: O(T) + O(T) ~ O(T)
     * - O(T) - next and current memory
     *
     * Accepted (147 / 147 testcases passed)
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += nums[i];
        }
        if ((sum & 1) != 0) {
            // odd sum cannot be divided into two equal sum subsets
            return false;
        }
        sum = sum / 2;
        boolean[] next = new boolean[sum + 1]; // SC: O(T)
        next[0] = true;
        for (int i = n - 1; i >= 0; i--) {   // TC: O(N)
            boolean[] current = new boolean[sum + 1]; // SC: O(T)
            current[0] = true;
            for (int j = 0; j <= sum; j++) { // TC: O(T)
                boolean skip = next[j];
                boolean pick = false;
                if (nums[i] <= j) {
                    pick = next[j - nums[i]];
                }
                current[j] = pick || skip;
            }
            next = current;
        }
        return next[sum];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N x T) + O(N) ~ O(N x T)
     * SC: O(N x T)
     * - O(N x T) - dp memory
     *
     * Accepted (147 / 147 testcases passed)
     */
    public boolean canPartitionTabulation(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += nums[i];
        }
        if ((sum & 1) != 0) {
            // odd sum cannot be divided into two equal sum subsets
            return false;
        }
        sum = sum / 2;
        boolean[][] dp = new boolean[n + 1][sum + 1]; // SC: O(N x T)
        dp[n][0] = true;
        for (int i = n - 1; i >= 0; i--) {   // TC: O(N)
            for (int j = 0; j <= sum; j++) { // TC: O(T)
                boolean skip = dp[i + 1][j];
                boolean pick = false;
                if (nums[i] <= j) {
                    pick = dp[i + 1][j - nums[i]];
                }
                dp[i][j] = pick || skip;
            }
        }
        return dp[0][sum];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x T) + O(N) ~ O(N x T)
     * SC: O(N x T) + O(N)
     * - O(N x T) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (147 / 147 testcases passed)
     */
    public boolean canPartitionMemoization(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += nums[i];
        }
        if ((sum & 1) != 0) {
            // odd sum cannot be divided into two equal sum subsets
            return false;
        }
        sum = sum / 2;
        int[][] memo = new int[n][sum + 1]; // SC: O(N x T)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        // we can try to get one subset with sum (original sum / 2)
        return solveMemoization(0, n, sum, nums, memo); // TC: O(N x T), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x T)
     * SC: O(N)
     */
    private boolean solveMemoization(int idx, int n, int sum, int[] nums, int[][] memo) {
        // Base Case
        if (idx == n) {
            return sum == 0;
        }
        // Memoization Check
        if (memo[idx][sum] != -1) {
            return memo[idx][sum] == 1;
        }
        // Recursion Calls
        // we can skip or pick element at index 'idx'
        // skip
        boolean skip = solveMemoization(idx + 1, n, sum, nums, memo);
        boolean pick = false;
        if (nums[idx] <= sum) {
            pick = solveMemoization(idx + 1, n, sum - nums[idx], nums, memo);
        }
        boolean result = pick || skip;
        memo[idx][sum] = result ? 1 : 0;
        return result;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N) + O(N) ~ O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (39 / 147 testcases passed)
     */
    public boolean canPartitionRecursion(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += nums[i];
        }
        if ((sum & 1) != 0) {
            // odd sum cannot be divided into two equal sum subsets
            return false;
        }
        sum = sum / 2;
        // we can try to get one subset with sum (original sum / 2)
        return solveRecursion(0, n, sum, nums); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private boolean solveRecursion(int idx, int n, int sum, int[] nums) {
        // Base Case
        if (idx == n) {
            return sum == 0;
        }
        // Recursion Calls
        // we can skip or pick element at index 'idx'
        // skip
        boolean skip = solveRecursion(idx + 1, n, sum, nums);
        boolean pick = false;
        if (nums[idx] <= sum) {
            pick = solveRecursion(idx + 1, n, sum - nums[idx], nums);
        }
        return pick || skip;
    }
}
