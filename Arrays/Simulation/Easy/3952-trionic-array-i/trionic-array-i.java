class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(N1 + N2 + N3) ~ O(N)
     * SC: O(1)
     */
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        // we will find if there is an index p < n which is strictly increasing
        int p = 0;
        for (int i = 1; i < n; i++) { // TC: O(N1)
            if (nums[i] > nums[i - 1]) {
                p = i;
            } else {
                break;
            }
        }
        if (p == 0 || p == n - 1) {
            return false;
        }
        // we will find if there is an index q < n which is strictly increasing
        int q = n - 1;
        for (int i = n - 2; i >= 0; i--) { // TC: O(N2)
            if (nums[i] < nums[i + 1]) {
                q = i;
            } else {
                break;
            }
        }
        if (p == 0 || q == n - 1) {
            return false;
        }
        // we will find if there is an index p < q < n which is strictly decreasing
        for (int i = p + 1; i <= q; i++) { // TC: O(N3)
            if (nums[i] >= nums[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
