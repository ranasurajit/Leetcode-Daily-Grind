/**
 * Approach : Using Fenwick Tree Approach
 *
 * TC : O(n) + O(q x log(n))
 * SC : O(n)
 */
class NumArray {
    private int n;
    private int[] nums;
    private FenwickTree tree;

    /**
     * Using Fenwick Tree Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    public NumArray(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        tree = new FenwickTree(n, nums); // TC : O(n), SC : O(n)
    }
    
    /**
     * Using Fenwick Tree Approach
     *
     * TC : O(log(n)) per update
     * SC : O(1)
     */
    public void update(int index, int val) {
        int delta = val - nums[index];
        nums[index] = val;
        // Fenwick Tree is having 1-based index
        tree.update(index + 1, delta);
    }

    /**
     * Using Fenwick Tree Approach
     *
     * TC : O(log(n)) per query
     * SC : O(1)
     */
    public int sumRange(int left, int right) {
        // Fenwick Tree is having 1-based index
        return tree.query(right + 1) - tree.query(left);
    }
}

class FenwickTree {
    private int n;
    private int[] bit;

    /**
     * Using Fenwick Tree Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    public FenwickTree(int n, int[] nums) {
        this.n = n;
        this.bit = new int[n + 1]; // SC : O(n)
        buildFenwickTree(nums); // TC : O(n)
    }

    /**
     * Using Fenwick Tree Approach
     *
     * TC : O(log(n))
     * SC : O(1)
     */
    public void update(int idx, int delta) {
        /**
         * when we update any element we
         * update idx and upward indices
         */
        while (idx <= n) {
            bit[idx] += delta;
            // we increase idx by its lowest set bit
            idx += (idx & (-idx));
        }
    }

    /**
     * Using Fenwick Tree Approach
     *
     * TC : O(log(n))
     * SC : O(1)
     */
    public int query(int idx) {
        /**
         * when we query any index we
         * query idx and downward indices
         */
        int sum = 0;
        // Fenwick Tree is having 1-based index
        while (idx > 0) {
            sum += bit[idx];
            // we decrease idx by its lowest set bit
            idx -= (idx & (-idx));
        }
        return sum;
    }

    /**
     * Using Fenwick Tree Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    private void buildFenwickTree(int[] nums) {
        for (int i = 1; i <= n; i++) { // TC : O(n)
            bit[i] += nums[i - 1];
            int j = i + (i & (-i));
            if (j <= n) {
                bit[j] += bit[i];
            }
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
