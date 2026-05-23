class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC : O(n)
     * SC : O(1) 
     */
    public boolean check(int[] nums) {
        int n = nums.length;
        int countFalls = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (nums[i] > nums[(i + 1) % n]) {
                countFalls++;
            }
        }
        /**
         * if we find a sudden fall and up in data and 
         * if its count is 0, then array is sorted and
         * non-rotated, while if count is 1, then array
         * is sorted and rotated
         */
        return countFalls <= 1;
    }
}
