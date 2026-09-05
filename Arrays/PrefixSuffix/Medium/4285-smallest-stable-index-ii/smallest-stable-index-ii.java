class Solution {
    /**
     * Approach : Using Prefix Array Approach
     *
     * TC : O(n) + O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        /**
         * we will first pre-compute maximum values
         * till index 'i' from left to right
         */
        int[] maxima = new int[n]; // SC : O(n)
        maxima[0] = nums[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            maxima[i] = Math.max(maxima[i - 1], nums[i]);
        }
        /**
         * we will then pre-compute minimum values
         * till index 'i' from right to left
         */
        int[] minima = new int[n]; // SC : O(n)
        minima[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) { // TC : O(n)
            minima[i] = Math.min(minima[i + 1], nums[i]);
        }
        /**
         * now we will move from left to right to
         * compute the instability score at each index
         * such that score <= k and return the smallest
         * index
         */
        for (int i = 0; i < n; i++) { // TC : O(n)
            int score = maxima[i] - minima[i];
            if (score <= k) {
                return i;
            }
        }
        return -1;
    }
}
