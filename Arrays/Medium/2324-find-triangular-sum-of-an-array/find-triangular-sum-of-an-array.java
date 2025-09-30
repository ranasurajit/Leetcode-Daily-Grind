class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    public int triangularSum(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {         // TC: O(N)
            int[] sum = new int[n];           // SC: O(N)
            for (int j = 0; j < n - 1; j++) { // TC: O(N)
                sum[j] = (nums[j] + nums[j + 1]) % 10;
            }
            nums = sum.clone();
        }
        return nums[0];
    }
}
