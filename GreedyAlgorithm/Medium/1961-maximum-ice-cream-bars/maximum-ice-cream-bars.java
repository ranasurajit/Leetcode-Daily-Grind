class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     *
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(1)
     */
    public int maxIceCream(int[] costs, int coins) {
        int n = costs.length;
        /**
         * ice cream bars can be purchased in any order
         * so since we need to maximize ice cream bars
         * we can sort the 'costs' array and greedily 
         * try to buy as many ice cream bars as possible
         */
        Arrays.sort(costs); // TC : O(n x log(n))
        int count = 0;
        for (int i = 0; i < n && coins > 0; i++) { // TC : O(n)
            if (costs[i] <= coins) {
                count++;
                coins -= costs[i];
            }
        }
        return count;
    }
}
