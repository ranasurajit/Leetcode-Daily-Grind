class Solution {
    /**
     * Approach : Using Array Pre-Processing (Prefix-Suffix) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int countPartitions(int[] nums) {
        int n = nums.length;
        int[] suffixSum = new int[n]; // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            suffixSum[i] = i == (n - 1) ? nums[i] : suffixSum[i + 1] + nums[i];
        }
        int prefixSum = 0;
        int partitions = 0;
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            prefixSum += nums[i];
            int diff = suffixSum[i + 1] - prefixSum;
            if ((diff & 1) == 0) {
                partitions++;
            }
        }
        return partitions;
    }
}
