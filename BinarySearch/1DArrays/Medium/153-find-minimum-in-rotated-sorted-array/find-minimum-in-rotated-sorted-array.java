class Solution {
    /**
     * Approach : Using Binary Search Approach
     *
     * TC : O(log(n))
     * SC : O(1)
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int low = 0;
        int high = n;
        int result = Integer.MAX_VALUE;
        while (low < high) { // TC : O(log(n))
            int mid = low + (high - low) / 2;
            if (nums[low] <= nums[mid]) {
                // nums[low] can be the candidate
                result = Math.min(result, nums[low]);
                // left portion is sorted, so answer lies in right portion
                low = mid + 1;
            } else {
                // nums[mid] can be the candidate
                result = Math.min(result, nums[mid]);
                // right portion is sorted, so answer lies in left portion
                high = mid;
            }
        }
        return result;
    }
}
