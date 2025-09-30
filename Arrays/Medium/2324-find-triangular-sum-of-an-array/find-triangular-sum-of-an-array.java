class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(N x N)
     * SC: O(1)
     */
    public int triangularSum(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {         // TC: O(N)
            for (int j = 0; j < n - 1; j++) { // TC: O(N)
                nums[j] = (nums[j] + nums[j + 1]) % 10;
            }
        }
        return nums[0];
    }
}
