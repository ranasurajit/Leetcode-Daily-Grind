class Solution {
    /**
     * Approach : Using Array + Math Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minOperations(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // remainder of entire sum will be the minimum operations
        return sum % k;
    }
}
