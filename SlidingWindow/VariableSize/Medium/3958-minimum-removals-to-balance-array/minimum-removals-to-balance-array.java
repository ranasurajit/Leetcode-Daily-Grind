class Solution {
    /**
     * Approach : Using Sorting + Sliding Window (Variable Size) Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            // array of size 1 is balanced
            return 0;
        }
        Arrays.sort(nums); // TC: O(N x log(N))
        int i = 0; // start pointer
        int j = 1; // end pointer as array of size 1 is always balanced
        int maxWindowSize = 1;
        while (j < n) {    // TC: O(N)
            while (i <= j && nums[j] > (long) nums[i] * k) {
                // we need to shrink the ith index
                i++;
            }
            maxWindowSize = Math.max(maxWindowSize, j - i + 1);
            j++;
        }
        /**
         * the maximum window size of 'maxWindowSize' satisfies the 
         * Balanced Array condition, so minimum removals = n - maxWindowSize
         */
        return n - maxWindowSize;
    }
}
