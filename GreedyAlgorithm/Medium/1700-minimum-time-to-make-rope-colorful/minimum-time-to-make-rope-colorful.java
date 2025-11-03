class Solution {
    /**
     * Approach : Using Greedy Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minCost(String colors, int[] neededTime) {
        int n = neededTime.length;
        char prevColor = colors.charAt(0);
        int carryForward = neededTime[0];
        int minTime = 0;
        for (int i = 1; i < n; i++) { // TC: O(N)
            char currentColor = colors.charAt(i);
            if (currentColor == prevColor) {
                minTime += Math.min(neededTime[i], carryForward);
                carryForward = Math.max(neededTime[i], carryForward);
            } else {
                carryForward = neededTime[i];
            }
            prevColor = currentColor;
        }
        return minTime;
    }
}
