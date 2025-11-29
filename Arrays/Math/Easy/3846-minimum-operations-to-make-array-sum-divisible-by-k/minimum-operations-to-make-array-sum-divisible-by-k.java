class Solution {
    /**
     * Approach : Using Array + Math Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minOperations(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // remainder of entire sum will be the minimum operations
        return sum % k;
    }
}
