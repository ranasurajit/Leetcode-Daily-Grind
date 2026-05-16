class Solution {
    /**
     * Approach : Using Binary Search Approach
     *
     * TC : O(log(n)) (for no duplicates)
     *      O(n) (for duplicates)
     * SC : O(1)
     */
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] < nums[high]) {
                high = mid;
            } else if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high--; // handle duplicates
            }
        }
        return nums[low];
    }
}
