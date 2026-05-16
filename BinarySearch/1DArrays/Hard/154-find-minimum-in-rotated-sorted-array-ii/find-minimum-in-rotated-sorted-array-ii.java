class Solution {
    /**
     * Approach : Using Binary Search Approach
     *
     * TC : O(log(n)) (for no duplicates)
     *      O(n) (for duplicates)
     * SC : O(1)
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        int resultIdx = 0;
        while (low <= high) { // TC : O(log(n))
            // skipping duplicates from left
            while (low < high && nums[low] == nums[low + 1]) {
                low++;
            }
            // skipping duplicates from right
            while (low < high && nums[high] == nums[high - 1]) {
                high--;
            }
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[resultIdx]) {
                resultIdx = mid;
            } else if (nums[mid] > nums[high]) {
                // answer lies in right portion
                low = mid + 1;
            } else {
                // answer lies in left portion
                high = mid - 1;
            }
        }
        return nums[resultIdx];
    }
}
