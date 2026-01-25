class Solution {
    /**
     * Approach I : Using Sorting + Array Simulation Approach
     *
     * TC: O((N - K) x K) + O(N x log(N)) ~ O(N x (K + log(N)))
     * SC: O(1)
     */
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        // sorting the nums so that we can compare it in k windows
        Arrays.sort(nums); // TC: O(N x log(N))
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - k + 1; i++) {     // TC: O(N - K)
            int minValue = Integer.MAX_VALUE;
            int maxValue = Integer.MIN_VALUE;
            for (int j = i; j < i + k ; j++) {    // TC: O(K)
                minValue = Math.min(minValue, nums[j]);
                maxValue = Math.max(maxValue, nums[j]);
            }
            minDiff = Math.min(minDiff, maxValue - minValue);
        }
        return minDiff;
    }
}
