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
        long commonCost = commonNeed * 
            Math.min((long) costBoth, (long) cost1 + cost2);
        long remain1 = need1 - commonNeed;
        long remain2 = need2 - commonNeed;
        long remainingCost = remain1 * Math.min(cost1, costBoth) + 
            remain2 * Math.min(cost2, costBoth);
        return commonCost + remainingCost;
    }
}
