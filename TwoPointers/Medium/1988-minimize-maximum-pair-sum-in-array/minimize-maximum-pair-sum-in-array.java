class Solution {
    /**
     * Approach II : Using Two Pointers Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     *
     * where R = Range (nums[n - 1] + nums[0], nums[n - 1] + nums[n - 2])
     */
    public int minPairSum(int[] nums) {
        int n = nums.length;
        /**
         * Since order does not matter here we can sort 'nums'
         * and then try to perform Binary Search on Answers
         */
        Arrays.sort(nums); // TC: O(N x log(N))
        int i = 0;     // start pointer
        int j = n - 1; // end pointer
        int maxSum = 0;
        while (i < j) { // TC: O(N)
            maxSum = Math.max(maxSum, nums[i] + nums[j]);
            i++;
            j--;
        }
        return maxSum;
    }

    /**
     * Approach I : Using Binary Search on Answers + Two Pointers Approach
     *
     * TC: O(N x log(N)) + O(N x log(R)) ~ O(N x log(N))
     * SC: O(1)
     *
     * where R = Range (nums[n - 1] + nums[0], nums[n - 1] + nums[n - 2])
     */
    public int minPairSumUsingBinarySearch(int[] nums) {
        int n = nums.length;
        /**
         * Since order does not matter here we can sort 'nums'
         * and then try to perform Binary Search on Answers
         */
        Arrays.sort(nums);                    // TC: O(N x log(N))
        int low = nums[0] + nums[n - 1];      // lowest possible bound
        int high = nums[n - 2] + nums[n - 1]; // highest possible bound
        while (low <= high) {                 // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (canFormSum(nums, n, mid)) {   // TC: O(N)
                // minimize the value
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private boolean canFormSum(int[] nums, int n, int target) {
        int maxSum = 0;
        int i = 0;
        int j = n - 1;
        while (i < j) { // TC: O(N)
            int currentSum = nums[i] + nums[j];
            maxSum = Math.max(maxSum, currentSum);
            i++;
            j--;
        }
        return maxSum <= target;
    }
}
