class Solution {
    private int n;
    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     *
     * TC: O(N)
     * SC: O(1)
     *
     * Accepted (45 / 45 testcases passed)
     */
    public int climbStairs(int n) {
        this.n = n;
        // Initialization
        int prev2 = 0;
        int prev1 = 1;
        // Iterative Calls
        for (int i = 1; i <= n; i++) { // TC: O(N)
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    /**
     * Approach III : Using Tabulation (Bottom Up) Approach
     *
     * TC: O(N)
     * SC: O(N)
     * - O(N) - dp array memory
     *
     * Accepted (45 / 45 testcases passed)
     */
    public int climbStairsTabulation(int n) {
        this.n = n;
        // Initialization
        int[] dp = new int[n + 1]; // SC: O(N)
        dp[0] = 1;
        // Iterative Calls
        for (int i = 1; i <= n; i++) { // TC: O(N)
            dp[i] = dp[i - 1] + (i > 1 ? dp[i - 2] : 0);
        }
        return dp[n];
    }

    /**
     * Approach II : Using Memoization (Top Down) Approach
     *
     * TC: O(N)
     * SC: O(N) + O(N)
     * - O(N) - memoization array memory
     * - O(N) - recursion stack
     *
     * Accepted (45 / 45 testcases passed)
     */
    public int climbStairsMemoization(int n) {
        this.n = n;
        int[] memo = new int[n + 1]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveMemoization(n, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N) - recursion stack
     */
    private int solveMemoization(int idx, int[] memo) {
        // Base Case
        if (idx < 0) {
            // invalid start point
            return 0;
        }
        if (idx == 0) {
            // we reached top by 1 way
            return 1;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        // number of ways to reach index 'idx' taking 1 step
        int oneStepWays = solveMemoization(idx - 1, memo);
        // number of ways to reach index 'idx' taking 2 steps
        int twoStepWays = solveMemoization(idx - 2, memo);
        // we need to return all possible ways
        return memo[idx] = oneStepWays + twoStepWays;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (21 / 45 testcases passed)
     */
    public int climbStairsRecursion(int n) {
        this.n = n;
        return solveRecursion(n);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx) {
        // Base Case
        if (idx < 0) {
            // invalid start point
            return 0;
        }
        if (idx == 0) {
            // we reached top by 1 way
            return 1;
        }
        // Recursion Calls
        // number of ways to reach index 'idx' taking 1 step
        int oneStepWays = solveRecursion(idx - 1);
        // number of ways to reach index 'idx' taking 2 steps
        int twoStepWays = solveRecursion(idx - 2);
        // we need to return all possible ways
        return oneStepWays + twoStepWays;
    }
}
