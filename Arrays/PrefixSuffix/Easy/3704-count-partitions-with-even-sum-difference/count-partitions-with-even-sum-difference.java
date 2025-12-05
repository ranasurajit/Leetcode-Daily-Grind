class Solution {
    /**
     * Approach II : Using Simulation (No Extra Space) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(1)
     */
    public int countPartitions(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) { // TC: O(N)
            sum += num;
        }
        int partitions = 0;
        int leftSum = 0;
        int rightSum = sum;
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            leftSum += nums[i];
            rightSum -= nums[i];
            if (((rightSum - leftSum) & 1) == 0) {
                partitions++;
            }
        }
        return partitions;
    }

    /**
     * Approach I : Using Array Pre-Processing (Prefix-Suffix) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int countPartitionsUsingSpace(int[] nums) {
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
