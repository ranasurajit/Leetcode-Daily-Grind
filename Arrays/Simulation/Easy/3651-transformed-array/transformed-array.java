class Solution {

    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(N)
            int idx = i; // idx = i default if nums[i] = 0
            if (nums[i] > 0) {
                // move right by nums[i] steps with circular movement
                idx = (i + nums[i]) % n;
            } else if (nums[i] < 0) {
                // move left by nums[i] steps with circular movement
                idx = (n + (i - Math.abs(nums[i])) % n) % n;
            }
            result[i] = nums[idx];
        }
        return result;
    }
}
