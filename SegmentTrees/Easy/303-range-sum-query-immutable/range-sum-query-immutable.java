/**
 * Approach II : Using Segment Tree Approach
 *
 * TC: O(log(N)) to form segment tree array and O(Q x log(N)) to run all queries
 * SC: O(N + log(N)) - to form prefixSums array + recursion stack space
 */
class NumArray {
    private int n;
    private int[] nums;
    private int[] segTree;

    /**
     * Using Segment Tree Approach
     *
     * TC: O(1)
     * SC: O(4 x N) + O(log(N)) ~ O(N + log(N))
     */
    public NumArray(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        this.segTree = new int[4 * n];
        buildSegmentTree(0, 0, n - 1);
    }
    
    /**
     * Using Segment Tree Approach
     *
     * TC: O(2 x log(N)) ~ O(log(N))
     * SC: O(log(N))
     */
    public int sumRange(int left, int right) {
        return querySumRange(0, 0, n - 1, left, right);
    }

    /**
     * Using Segment Tree Approach
     *
     * TC: O(2 x log(N)) ~ O(log(N))
     * SC: O(log(N))
     */
    private int querySumRange(int index, int low, int high, int left, int right) {
        /**
         * as per constraints, 0 <= left <= right < nums.length
         * so need to validate left and right input
         */
        // Base Case
        // Case 1: if left and right are beyond low and high
        if (right < low || left > high) {
            return 0;
        }
        // Case 2: if left and right are exactly within low and high
        if (low >= left && high <= right) {
            return segTree[index];
        }
        // Recursion Calls
        // Case 3: partial overlap condition
        int mid = low + (high - low) / 2;
        int leftSum = querySumRange(2 * index + 1, low, mid, left, right);
        int rightSum = querySumRange(2 * index + 2, mid + 1, high, left, right);
        return leftSum + rightSum;
    }

    /**
     * Using Segment Tree Approach
     *
     * TC: O(log(N))
     * SC: O(log(N))
     */
    private void buildSegmentTree(int index, int low, int high) {
        // Base Case
        if (low == high) {
            // we need to set the value of index here
            segTree[index] = nums[low];
            return;
        }
        // Recursion Calls
        int mid = low + (high - low) / 2;
        buildSegmentTree(2 * index + 1, low, mid);
        buildSegmentTree(2 * index + 2, mid + 1, high);
        segTree[index] = segTree[2 * index + 1] + segTree[2 * index + 2];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */

/**
 * Approach I : Using Prefix-Sum Approach
 *
 * TC: O(N) to form prefixSums array and O(Q) to run all queries
 * SC: O(N) - to form prefixSums array
 */
class NumArrayPrefixSumApproach {
    private int n;
    private int[] prefixSums;

    /**
     * Using Prefix-Sum Approach
     *
     * TC: O(N)
     * SC: O(N) - to form prefixSums array
     */
    public NumArrayPrefixSumApproach(int[] nums) {
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
