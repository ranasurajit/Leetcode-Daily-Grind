class Solution {
    /**
     * Approach : Using Sliding Window Approach
     *
     * TC : O((r - l + 1) x n)
     * SC : O(1) 
     */
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int minSum = Integer.MAX_VALUE;
        int n = nums.size();
        for (int k = l; k <= r; k++) { // TC : O(r - l + 1)
            minSum = Math.min(minSum, getMinimumSubArraySum(nums, n, k)); // TC : O(n)
        }
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }

    /**
     * Using Sliding Window Approach
     *
     * TC : O(n)
     * SC : O(1) 
     */
    private int getMinimumSubArraySum(List<Integer> nums, int n, int k) {
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int currentSum = 0;
        int minPositiveSum = Integer.MAX_VALUE;
        while (j < n) { // TC : O(n)
            currentSum += nums.get(j);
            if (j - i + 1 == k) {
                if (currentSum > 0) {
                    minPositiveSum = Math.min(minPositiveSum, currentSum);
                }
                // remove computation from index 'i'
                currentSum -= nums.get(i);
                i++;
            }
            j++;
        }
        return minPositiveSum;
    }
}
