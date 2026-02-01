class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(N²)
     * SC: O(1)
     */
    public int minimumCost(int[] nums) {
        int n = nums.length;
        int minCost = Integer.MAX_VALUE;
        /*
         * we need not think about index of the 1st partition and cost as
         * it will start from index 0, so cost = nums[0]
         */
        for (int j = 1; j < n - 1; j++) {     // TC: O(N)
            for (int k = j + 1; k < n; k++) { // TC: O(N)
                int currentCost = nums[j] + nums[k];
                minCost = Math.min(minCost, currentCost);
            }
        }
        return nums[0] + minCost;
    }
}
