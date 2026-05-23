class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC : O(n)
     * SC : O(1) 
     */
    public boolean check(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return true;
        }
        int countFalls = 0;
        int offset = 0;
        for (int i = 1; i < n; i++) { // TC : O(n)
            if (nums[i] < nums[i - 1]) {
                offset = i;
                countFalls++;
            }
        }
        /**
         * if we find a sudden fall and up in data and 
         * if its count is 0, then array is sorted and
         * non-rotated, while if count is 1, then array
         * is sorted and rotated
         */
        if (countFalls > 1) {
            return false;
        }
        for (int i = offset + 1; i < n + offset; i++) { // TC : O(n)
            if (nums[i % n] < nums[(i - 1 + n) % n]) {
                return false;
            }
        }
        return true;
    }
}
