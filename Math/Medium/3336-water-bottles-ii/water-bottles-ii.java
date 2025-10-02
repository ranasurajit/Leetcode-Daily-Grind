class Solution {
    /**
     * Approach : Using Math + Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     *
     * where N = numBottles
     */
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int fullBottles = numBottles;
        int emptyBottles = 0;
        int bottlesDrunk = 0;
        while (fullBottles > 0) { // TC: O(N)
            bottlesDrunk += fullBottles;
            emptyBottles += fullBottles;
            fullBottles = 0;
            if (emptyBottles >= numExchange) {
                fullBottles = 1;
                emptyBottles -= numExchange;
                numExchange++;
            }
        }
        return bottlesDrunk;
    }
}
