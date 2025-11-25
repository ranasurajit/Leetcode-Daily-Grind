class Solution {
    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N) + O(N)
     * - O(N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (214 / 214 testcases passed)
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // states are index, canBuy and allowed
        int[][][] memo = new int[n][2][3]; // SC: O(6 x N) ~ O(N)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m , -1);
            }
        }
        return solveMemoization(0, 1, 2, n, prices, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x 2 x 3) ~ O(N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int canBuy, int allowed, int n, int[] prices, int[][][] memo) {
        // Base Case
        if (idx == n || allowed == 0) {
            // it is out of bounds or total transaction of 2 is achieved
            return 0;
        }
        // Memoization Check
        if (memo[idx][canBuy][allowed] != -1) {
            return memo[idx][canBuy][allowed];
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // we may buy(pick) or skip it
            // skip
            skip = solveMemoization(idx + 1, 1, allowed, n, prices, memo);
            // pick
            pick = -1 * prices[idx] + solveMemoization(idx + 1, 0, allowed, n, prices, memo);
        } else {
            // we may sell(pick) or skip it
            // skip
            skip = solveMemoization(idx + 1, 0, allowed, n, prices, memo);
            // pick
            pick = prices[idx] + solveMemoization(idx + 1, 1, allowed - 1, n, prices, memo);
        }
        return memo[idx][canBuy][allowed] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (201 / 214 testcases passed)
     */
    public int maxProfitRecursion(int[] prices) {
        int n = prices.length;
        return solveRecursion(0, 1, 2, n, prices);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int canBuy, int allowed, int n, int[] prices) {
        // Base Case
        if (idx == n || allowed == 0) {
            // it is out of bounds or total transaction of 2 is achieved
            return 0;
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // we may buy(pick) or skip it
            // skip
            skip = solveRecursion(idx + 1, 1, allowed, n, prices);
            // pick
            pick = -1 * prices[idx] + solveRecursion(idx + 1, 0, allowed, n, prices);
        } else {
            // we may sell(pick) or skip it
            // skip
            skip = solveRecursion(idx + 1, 0, allowed, n, prices);
            // pick
            pick = prices[idx] + solveRecursion(idx + 1, 1, allowed - 1, n, prices);
        }
        return Math.max(pick, skip);
    }
}
