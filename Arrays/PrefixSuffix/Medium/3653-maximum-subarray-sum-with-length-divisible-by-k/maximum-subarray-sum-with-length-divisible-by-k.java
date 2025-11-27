class Solution {
    /**
     * Approach II : Using Array Prefix-Sum Approach
     *
     * TC: O(N)
     * SC: O(K)
     *
     * Accepted (661 / 661 testcases passed)
     */
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        /**
         * sub-array length = (r - l + 1) ~ ((r + 1) - l)
         * so for sub-array length divisible by k, so remainder should be same
         * ((r + 1) - l) % k should be same i.e. (r + 1) % k == l % k
         * so, indices must have the same remainder modulo k
         */
        // remainders can range from [0...(k - 1)]
        long[] minPrefix = new long[k]; // SC: O(K)
        Arrays.fill(minPrefix, Long.MAX_VALUE);
        minPrefix[0] = 0L;
        long prefixSum = 0L;
        long maxSum = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(N)
            prefixSum += (long) nums[i];
            int rem = (i + 1) % k;
            // track minimum prefix sum till index i
            if (minPrefix[rem] != Long.MAX_VALUE) {
                maxSum = Math.max(maxSum, prefixSum - minPrefix[rem]);
            }
            minPrefix[rem] = Math.min(minPrefix[rem], prefixSum);
        }
        return maxSum;
    }

    /**
     * Approach I : Using Sliding Window (Fixed Size) Approach
     *
     * TC: O(N) + O(N ^ 2 x K) ~ O(N ^ 2 x K)
     * SC: O(1)
     *
     * Time Limit Exceeded (655 / 661 testcases passed)
     */
    public long maxSubarraySumBruteForce(int[] nums, int k) {
        int n = nums.length;
        /**
         * we need to compare and find the maximum sum of sub-array
         * of length that is divisible by k, so we need to check for
         * all lengths in the range of [k ... (n - (n % k))]
         */
        int low = k;
        int high = n - (n % k);
        long maxSum = Long.MIN_VALUE;
        for (int len = low; len <= high; len += k) { // TC: O(N / K)
            maxSum = Math.max(maxSum, maxSubArraySumOfLength(nums, n, len)); // TC: O(N)
        }
        return maxSum;
    }

    /**
     * Using Sliding Window (Fixed Size) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private long maxSubArraySumOfLength(int[] nums, int n, int targetLength) {
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        long maxSum = Long.MIN_VALUE;
        long sum = 0L;
        while (j < n) { // TC: O(N)
            sum += nums[j];
            if (j - i + 1 == targetLength) {
                // size of sub-array is 'targetLength'
                maxSum = Math.max(maxSum, sum);
                // remove computation from index 'i'
                sum -= nums[i];
                // slide the window
                i++;
            }
            j++;
        }
        return maxSum;
    }
}
