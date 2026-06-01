class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     *
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(1)
     */
    public int minimumCost(int[] cost) {
        int n = cost.length;
        if (n == 1) {
            return cost[0];
        }
        /**
         * To minimize cost we will try to buy 
         * the candies with higher cost first
         * so that a relative high cost candy
         * would become free, so we need to sort
         */
        Arrays.sort(cost); // TC : O(n x log(n))
        int minCost = 0;
        for (int i = n - 1; i >= 0; i--) { // TC : O(n)
            minCost += cost[i];
            if (i > 0) {
                minCost += cost[i - 1];
            }
            i -= 2; // next lesser cost candy is free
        }
        return minCost;
    }
}
