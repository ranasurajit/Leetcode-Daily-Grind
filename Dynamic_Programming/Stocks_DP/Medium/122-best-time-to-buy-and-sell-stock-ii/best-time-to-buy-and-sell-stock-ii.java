class Solution {
    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(2) + O(2) ~ O(1)
     *
     * Accepted (202 / 202 testcases passed)
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] next = new int[2]; // SC: O(2)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            int[] current = new int[2]; // SC: O(2)
            for (int j = 0; j < 2; j++) {  // TC: O(2)
                int pick = 0;
                int skip = 0;
                if (j == 1) {
                    // we can decide to pick or skip
                    // pick - so canBuy = 0 and substract prices[i]
                    pick = (-1 * prices[i]) + ((i + 1) < n ? next[0] : 0); // bought here
                    // skip - if we skip then we can still buy so canBuy = 1
                    skip = ((i + 1) < n ? next[1] : 0);
                } else {
                    // we can decide to pick or skip
                    // pick - if we pick then we can buy so canBuy = 1 and add prices[idx]
                    pick = prices[i] + ((i + 1) < n ? next[1] : 0); // sold here
                    // skip - if we skip then we cannot still buy so canBuy = 0
                    skip = ((i + 1) < n ? next[0] : 0);
                }
                next = current;
                current[j] = Math.max(pick, skip);
            }
        }
        return next[1]; // return next[buy = 1 as it is sold here to gain profits]
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     * - O(N) - dp array memory
     *
     * Accepted (202 / 202 testcases passed)
     */
    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2]; // SC: O(2 x N) ~ O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = 0; j < 2; j++) {  // TC: O(2)
                int pick = 0;
                int skip = 0;
                if (j == 1) {
                    // we can decide to pick or skip
                    // pick - so canBuy = 0 and substract prices[i]
                    pick = (-1 * prices[i]) + ((i + 1) < n ? dp[i + 1][0] : 0); // bought here
                    // skip - if we skip then we can still buy so canBuy = 1
                    skip = ((i + 1) < n ? dp[i + 1][1] : 0);
                } else {
                    // we can decide to pick or skip
                    // pick - if we pick then we can buy so canBuy = 1 and add prices[idx]
                    pick = prices[i] + ((i + 1) < n ? dp[i + 1][1] : 0); // sold here
                    // skip - if we skip then we cannot still buy so canBuy = 0
                    skip = ((i + 1) < n ? dp[i + 1][0] : 0);
                }
                dp[i][j] = Math.max(pick, skip);
            }
        }
        return dp[0][1]; // return dp[0][buy = 1 as it is sold here to gain profits]
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N) + O(N)
     * - O(N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (202 / 202 testcases passed)
     */
    public int maxProfitMemoization(int[] prices) {
        int n = prices.length;
        int[][] memo = new int[n][2]; // SC: O(2 x N) ~ O(N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, 1, n, prices, memo); // TC: O(N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int canBuy, int n, int[] prices, int[][] memo) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][canBuy] != -1) {
            return memo[idx][canBuy];
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // we can decide to pick or skip
            // pick - if we pick then we cannot buy so canBuy = 0 and substract prices[idx]
            pick = (-1 * prices[idx]) +
                solveMemoization(idx + 1, 0, n, prices, memo); // bought here
            // skip - if we skip then we can still buy so canBuy = 1
            skip = solveMemoization(idx + 1, 1, n, prices, memo);
        } else {
            // we can decide to pick or skip
            // pick - if we pick then we can buy so canBuy = 1 and add prices[idx]
            pick = prices[idx] +
                solveMemoization(idx + 1, 1, n, prices, memo); // sold here
            // skip - if we skip then we cannot still buy so canBuy = 0
            skip = solveMemoization(idx + 1, 0, n, prices, memo);
        }
        // return maximum profits
        return memo[idx][canBuy] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (198 / 202 testcases passed)
     */
    public int maxProfitRecursion(int[] prices) {
        int n = prices.length;
        return solveRecursion(0, 1, n, prices); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int canBuy, int n, int[] prices) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // we can decide to pick or skip
            // pick - if we pick then we cannot buy so canBuy = 0 and substract prices[idx]
            pick = (-1 * prices[idx]) + solveRecursion(idx + 1, 0, n, prices); // bought here
            // skip - if we skip then we can still buy so canBuy = 1
            skip = solveRecursion(idx + 1, 1, n, prices);
        } else {
            // we can decide to pick or skip
            // pick - if we pick then we can buy so canBuy = 1 and add prices[idx]
            pick = prices[idx] + solveRecursion(idx + 1, 1, n, prices); // sold here
            // skip - if we skip then we cannot still buy so canBuy = 0
            skip = solveRecursion(idx + 1, 0, n, prices);
        }
        // return maximum profits
        return Math.max(pick, skip);
    }
}
