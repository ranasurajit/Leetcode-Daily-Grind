class Solution {
    private int n;
    private int k;
    private int[] prices;

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x 3 x K) ~ O(N x K)
     * SC: O(N x 3 x K) + O(N) ~ O(N x K) + O(N)
     * - O(N x K) - memoization array
     * - O(N) - recursion stack
     *
     * Accepted (776 / 776 testcases passed)
     */
    public long maximumProfit(int[] prices, int k) {
        this.n = prices.length;
        this.k = k;
        this.prices = prices;
        // holdingState -> 0: no positions, 1: bought, 2: short-sold
        long[][][] memo = new long[n][3][k + 1];
        for (long[][] mem : memo) {
            for (long[] m : mem) {
                Arrays.fill(m, -1L);
            }
        }
        return solveMemoization(0, 0, 0, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x 3 x K) ~ O(N x K)
     * SC: O(N)
     */
    private long solveMemoization(int idx, int holdingState, int countTransaction, long[][][] memo) {
        // Base Case
        if (idx == n) {
            if (holdingState == 0) {
                // if no positions at index n then no profit can be earned
                return 0L;
            } else {
                // if we have positions at index n then it is an invalid state
                return Long.MIN_VALUE / 2;
            }
        }
        if (countTransaction > k) {
            return Long.MIN_VALUE / 2;
        }
        // Memoization Check
        if (memo[idx][holdingState][countTransaction] != -1L) {
            return memo[idx][holdingState][countTransaction];
        }
        // Recursion Calls
        // skip buy/sell or short-sell/short-buy
        long skip = solveMemoization(idx + 1, holdingState, countTransaction, memo);
        long maxProfit = skip;
        if (holdingState == 0) {
            if (countTransaction < k) {
                // pick buy
                long pickBuy = -1 * prices[idx] + 
                    solveMemoization(idx + 1, 1, countTransaction, memo);
                // pick short sell
                long pickShortSell = prices[idx] +
                    solveMemoization(idx + 1, 2, countTransaction, memo);
                maxProfit = Math.max(skip, Math.max(pickBuy, pickShortSell));
            }
        } else if (holdingState == 1) {
            // pick sell
            if (countTransaction + 1 <= k) {
                long pickSell = prices[idx] +
                    solveMemoization(idx + 1, 0, countTransaction + 1, memo);
                maxProfit = Math.max(skip, pickSell);
            }
        } else {
            // it was short-sold so we can skip to short-buy or pick short-buy
            // pick short-buy
            if (countTransaction + 1 <= k) {
                long pickShortBuy = -1 * prices[idx] + 
                    solveMemoization(idx + 1, 0, countTransaction + 1, memo);
                maxProfit = Math.max(skip, pickShortBuy);
            }
        }
        return memo[idx][holdingState][countTransaction] = maxProfit;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(3 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (552 / 776 testcases passed)
     */
    public long maximumProfitRecursion(int[] prices, int k) {
        this.n = prices.length;
        this.k = k;
        this.prices = prices;
        // holdingState -> 0: no positions, 1: bought, 2: short-sold
        return solveRecursion(0, 0, 0);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(3 ^ N)
     * SC: O(N)
     */
    private long solveRecursion(int idx, int holdingState, int countTransaction) {
        // Base Case
        if (idx == n) {
            if (holdingState == 0) {
                // if no positions at index n then no profit can be earned
                return 0L;
            } else {
                // if we have positions at index n then it is an invalid state
                return Long.MIN_VALUE / 2;
            }
        }
        if (countTransaction > k) {
            return Long.MIN_VALUE / 2;
        }
        // Recursion Calls
        // skip buy/sell or short-sell/short-buy
        long skip = solveRecursion(idx + 1, holdingState, countTransaction);
        long maxProfit = skip;
        if (holdingState == 0) {
            if (countTransaction < k) {
                // pick buy
                long pickBuy = -1 * prices[idx] + solveRecursion(idx + 1, 1, countTransaction);
                // pick short sell
                long pickShortSell = prices[idx] + solveRecursion(idx + 1, 2, countTransaction);
                maxProfit = Math.max(skip, Math.max(pickBuy, pickShortSell));
            }
        } else if (holdingState == 1) {
            // pick sell
            if (countTransaction + 1 <= k) {
                long pickSell = prices[idx] + solveRecursion(idx + 1, 0, countTransaction + 1);
                maxProfit = Math.max(skip, pickSell);
            }
        } else {
            // it was short-sold so we can skip to short-buy or pick short-buy
            // pick short-buy
            if (countTransaction + 1 <= k) {
                long pickShortBuy = -1 * prices[idx] + solveRecursion(idx + 1, 0, countTransaction + 1);
                maxProfit = Math.max(skip, pickShortBuy);
            }
        }
        return maxProfit;
    }
}
