class Solution {
    /**
     * Approach : Using DP + Math Approach
     *
     * TC : O(k x n)
     * SC : O(k)
     */
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] result = new long[k];    // SC : O(k)
        long[] prevCount = new long[k]; // SC : O(k)
        for (int i = 0; i < n; i++) {   // TC : O(n)
            long[] currCount = new long[k];
            int currRem = nums[i] % k;
            currCount[currRem]++;
            for (int oldRem = 0; oldRem <= k - 1; oldRem++) {
                int newRemain = (int) (((long) oldRem * nums[i] % k) % k);
                currCount[newRemain] += prevCount[oldRem];
            }
            prevCount = currCount;
            for (int x = 0; x < k; x++) {
                result[x] += prevCount[x];
            }
        }
        return result;
    }
}
