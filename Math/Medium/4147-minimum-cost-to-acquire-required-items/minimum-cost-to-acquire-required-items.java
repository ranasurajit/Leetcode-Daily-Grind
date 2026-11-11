class Solution {
    /**
     * Approach : Using Math + Greedy Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public long minimumCost(int cost1, int cost2, int costBoth, 
        int need1, int need2) {
        long commonNeed = Math.min(need1, need2);
        return commonNeed * Math.min((long) costBoth, 
            (long) cost1 + cost2) + 
            (need1 - commonNeed) * Math.min(cost1, costBoth) + 
            (need2 - commonNeed) * Math.min(cost2, costBoth);
    }
}
