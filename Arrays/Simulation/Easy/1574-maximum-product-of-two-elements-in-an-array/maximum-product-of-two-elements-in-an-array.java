class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int firstMax = -1;
        int secondMax = -1;
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (firstMax < nums[i]) {
                secondMax = firstMax;
                firstMax = nums[i];
            } else if (secondMax < nums[i]) {
                secondMax = nums[i];
            }
        }
        return (firstMax - 1) * (secondMax - 1);
    }
}
