class Solution {
    /**
     * Approach : Using Prefix-Array Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        long[] min = new long[n]; // SC : O(n)
        long[] max = new long[n]; // SC : O(n)
        min[0] = (long) nums[0];
        max[0] = (long) nums[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            min[i] = Math.min(min[i - 1], nums[i]);
            max[i] = Math.max(max[i - 1], nums[i]);
        }
        long maxDiff = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) { // TC : O(n)
            maxDiff = Math.max(maxDiff, max[i] - min[i]);
        }
        return k * maxDiff;
    }
}
