class Solution {
    /**
     * Approach II : Using Optimal (Array Prefix/Suffix) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int minimumCost(int[] nums) {
        int n = nums.length;
        /*
         * we need not think about index of the 1st partition and cost as
         * it will start from index 0, so cost = nums[0]
         *
         * now we can do array pre-processing to find minimum (suffix)
         * array from right to left
         */
        int[] rightMin = new int[n]; // SC: O(N)
        rightMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            // pick the minimum from right to left
            rightMin[i] = Math.min(rightMin[i + 1], nums[i]);
        }
        // compute minimum cost
        int minCost = Integer.MAX_VALUE;
        for (int j = 1; j < n - 1; j++) {  // TC: O(N)
            minCost = Math.min(minCost, nums[j] + rightMin[j + 1]);
        }
        return nums[0] + minCost;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(N²)
     * SC: O(1)
     */
    public int minimumCostBruteForce(int[] nums) {
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
