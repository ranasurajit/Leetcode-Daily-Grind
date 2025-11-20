class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        /**
         * we will try to minimize the buy price and try
         * to sell it on any next day and maximize sell
         * price so that the profit can be maximized
         */
        int minBuy = prices[0];
        int maximumProfit = 0;
        for (int i = 1; i < n; i++) { // TC: O(N)
            maximumProfit = Math.max(maximumProfit, prices[i] - minBuy);
            minBuy = Math.min(minBuy, prices[i]);
        }
        return maximumProfit;
    }
}
