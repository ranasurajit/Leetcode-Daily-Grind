class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(n)
            if (nums[i] == target) {
                minDist = Math.min(minDist, Math.abs(i - start));
            }
        }
        return minDist;
    }
}
