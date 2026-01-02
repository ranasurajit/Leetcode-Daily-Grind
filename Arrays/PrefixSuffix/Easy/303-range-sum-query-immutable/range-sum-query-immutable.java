/**
 * Approach : Using Prefix-Sum Approach
 *
 * TC: O(N) to form prefixSums array and O(Q) to run all queries
 * SC: O(N) - to form prefixSums array
 */
class NumArray {
    private int n;
    private int[] prefixSums;

    /**
     * Using Prefix-Sum Approach
     *
     * TC: O(N)
     * SC: O(N) - to form prefixSums array
     */
    public NumArray(int[] nums) {
        this.n = nums.length;
        this.prefixSums = new int[n];
        // pre-computing prefix sums
        for (int i = 0; i < n; i++) { // TC: O(N)
            prefixSums[i] = (i > 0 ? prefixSums[i - 1] : 0) + nums[i];
        }
    }
    
    /**
     * Using Prefix-Sum Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int sumRange(int left, int right) {
        /**
         * as per constraints, 0 <= left <= right < nums.length
         * so need to validate left and right input
         */
        return prefixSums[right] - (left > 0 ? prefixSums[left - 1] : 0);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
