/**
 * Approach : Using Segment Tree Approach
 *
 * TC: O(log(N)) + O(Q x log(N))
 * - O(log(N)) - to build segment tree
 * - O(Q x log(N)) - to execute Q queries
 * 
 * SC: O(4 x N) + O(log(N)) ~ O(N + log(N))
 * - O(4 x N) - to build segment tree
 * - O(log(N)) - recursion stack space
 */
class NumArray {
    private int n;
    private int[] nums;
    private int[] segTree;

    /**
     * Using Segment Tree Approach
     *
     * TC: O(log(N))
     * SC: O(4 x N) + O(log(N)) ~ O(N + log(N)) - where log(N) is recursion stack space
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
     * TC: O(log(N))
     * SC: O(log(N)) - recursion stack space
     */
    public void update(int index, int val) {
        updateSegmentTree(index, val, 0, 0, n - 1);
    }
    
    /**
     * Using Segment Tree Approach
     *
     * TC: O(2 x log(N)) ~ O(log(N))
     * SC: O(log(N)) - recursion stack space
     */
    public int sumRange(int left, int right) {
        return querySegmentTree(left, right, 0, 0, n - 1);
    }

    /**
     * Using Segment Tree Approach
     *
     * TC: O(2 x log(N)) - two times height was traversed
     * SC: O(log(N)) - recursion stack space
     */
    private int querySegmentTree(int left, int right, int idx, int low, int high) {
        // Base Case
        // case 1 : if there is no overlap
        if (right < low || left > high) {
            // no contribution
            return 0;
        }
        // case 2: if low and high is within range of (left, right)
        if (low >= left && high <= right) {
            // return current node's value at index 'idx'
            return segTree[idx];
        }
        // case 3: partial overlap so get the answer from both left and right of segment tree node
        // Recursion Calls
        int mid = low + (high - low) / 2;
        return querySegmentTree(left, right, 2 * idx + 1, low, mid) +
            querySegmentTree(left, right, 2 * idx + 2, mid + 1, high);
    }

    /**
     * Using Segment Tree Approach
     *
     * TC: O(log(N))
     * SC: O(log(N)) - recursion stack space
     */
    private void updateSegmentTree(int index, int val, int idx, int low, int high) {
        // Base Case
        if (low == high) {
            // we got to update segTree[idx] here with val
            segTree[idx] = val;
            return;
        }
        // Recursion Calls
        int mid = low + (high - low) / 2;
        if (index <= mid) {
            // update the value in the left side of Segment Tree
            updateSegmentTree(index, val, 2 * idx + 1, low, mid);
        } else {
            // update the value in the right side of Segment Tree
            updateSegmentTree(index, val, 2 * idx + 2, mid + 1, high);
        }
        segTree[idx] = segTree[2 * idx + 1] + segTree[2 * idx + 2];
    }

    /**
     * Using Segment Tree Approach
     *
     * TC: O(log(N))
     * SC: O(log(N)) - recursion stack space
     */
    private void buildSegmentTree(int idx, int low, int high) {
        // Base Case
        if (low == high) {
            segTree[idx] = nums[low];
            return;
        }
        // Recursion Calls
        int mid = low + (high - low) / 2;
        buildSegmentTree(2 * idx + 1, low, mid);
        buildSegmentTree(2 * idx + 2, mid + 1, high);
        segTree[idx] = segTree[2 * idx + 1] + segTree[2 * idx + 2];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
