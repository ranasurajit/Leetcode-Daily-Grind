class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int operations = 0;
        long total = 0L;
        for (int num : nums) { // TC : O(n)
            total += (long) num;
        }
        /**
         * in this problem we need to find the 
         * longest length of sub-array with
         * window sum = (total - x) so that
         * we can use the elements not in 
         * that sub-array to have the minimum
         * operations to reduce X to Zero 
         */
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        long sumNeeded = total - x;
        long sum = 0L;
        int maxLength = -1;
        while (j < n) { // TC : O(n)
            sum += (long) nums[j];
            while (i < n && sum > sumNeeded) {
                // we can remove the computation from index 'i'
                sum -= (long) nums[i];
                // shift the window
                i++;
            }
            if (sum == sumNeeded) {
                // here the sum = sumNeeded
                maxLength = Math.max(maxLength, j - i + 1);
            }
            j++; 
        }
        if (maxLength == -1) {
            // not possible to make x = 0
            return -1;
        }
        return n - maxLength;
    }
}
