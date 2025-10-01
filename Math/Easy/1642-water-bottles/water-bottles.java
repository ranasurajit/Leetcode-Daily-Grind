class Solution {
    /**
     * Approach : Using Math + Simulation Approach
     *
     * TC: O(N / E)
     * SC: O(1)
     *
     * where N = numBottles, E = numExchange
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        int filled = numBottles;
        int empty = 0;
        int bottlesUsed = 0;
        int lastEmpty = 0;
        while (filled > 0) {
            empty = lastEmpty + filled;
            bottlesUsed += filled;
            filled = empty / numExchange;
            empty = empty % numExchange;
            lastEmpty = empty;
        }
        return bottlesUsed;
    }
}
