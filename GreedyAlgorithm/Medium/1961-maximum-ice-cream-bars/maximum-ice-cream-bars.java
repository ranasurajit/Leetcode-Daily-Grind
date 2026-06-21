class Solution {
    /**
     * Approach II : Using Greedy + Sorting (Count Sort) Approach
     *
     * TC : O(n) + O(n) + O(Max(costs)) ~ O(n + Max(costs))
     * SC : O(Max(costs))
     */
    public int maxIceCream(int[] costs, int coins) {
        int n = costs.length;
        int max = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            max = Math.max(max, costs[i]);
        }
        /**
         * ice cream bars can be purchased in any order
         * so since we need to maximize ice cream bars
         * we can sort the 'costs' array and greedily 
         * try to buy as many ice cream bars as possible
         * since here we know the range to optimize
         * sorting, we can go with Count Sort Algorithm
         */
        int[] freq = new int[max + 1]; // TC : O(Max(costs))
        for (int i = 0; i < n; i++) {  // TC : O(n)
            freq[costs[i]]++;
        }
        int count = 0;
        for (int cost = 1; cost <= max; cost++) { // TC : O(Max(costs))
            if (freq[cost] == 0) {
                continue;
            }
            int countBuyAtCost = Math.min(freq[cost], coins / cost);
            count += countBuyAtCost;
            coins -= countBuyAtCost * cost;
            if (coins < cost) {
                // early pruning
                break;
            }
        }
        return count;
    }

    /**
     * Approach I : Using Greedy + Sorting (In-Built Sort) Approach
     *
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(1)
     */
    public int maxIceCreamBruteForce(int[] costs, int coins) {
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
