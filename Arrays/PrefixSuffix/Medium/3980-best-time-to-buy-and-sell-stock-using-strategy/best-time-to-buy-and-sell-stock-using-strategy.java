class Solution {
    /**
     * Approach : Using Array Pre-processing (Prefix Sum) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long[] profitSum = new long[n]; // SC: O(N)
        long[] priceSum = new long[n];  // SC: O(N)
        long baseProfit = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            long currentProfit = (long) prices[i] * (long) strategy[i];
            profitSum[i] = i > 0 ? profitSum[i - 1] + currentProfit : currentProfit;
            priceSum[i] = i > 0 ? priceSum[i - 1] + prices[i] : prices[i];
            baseProfit += currentProfit;
        }
        /**
         * max_profit = base_profit + delte and we need to maximize delta
         * so for any index i, 
         * first half = i to (i + k / 2 - 1), where l1 = i, r1 = (i + k / 2 - 1)
         * second half = (i + k / 2) to (i + k - 1), where l2 = (i + k / 2), r2 = (i + k - 1)
         * delta at index i = priceSum of right - (orig profitSum left + orig profitSum right)
         * where priceSum of right = (priceSum[r2] - priceSum[l2 - 1])
         * orig profitSum left = profitSum[r1] - profitSum[l1 - 1]
         * orig profitSum right = profitSum[r2] - profitSum[l2 - 1]
         */
        long maxDelta = 0L;
        for (int i = 0; i < n - k + 1; i++) { // TC: O(N)
            int l1 = i;
            int r1 = i + (k / 2) - 1;
            int l2 = r1 + 1;
            int r2 = i + k - 1;
            long currentPriceSum =  priceSum[r2] - (l2 > 0 ? priceSum[l2 - 1] : 0);
            long leftOrigProfitSum = profitSum[r1] - (l1 > 0 ? profitSum[l1 - 1] : 0);
            long rightOrigProfitSum = profitSum[r2] - (l2 > 0 ? profitSum[l2 - 1] : 0);
            long currentDelta = currentPriceSum - (leftOrigProfitSum + rightOrigProfitSum);
            maxDelta = Math.max(maxDelta, currentDelta);
        }
        return baseProfit + maxDelta;
    }
}
