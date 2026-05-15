class Solution {
    /**
     * Approach II : Using Binary Search Approach
     *
     * TC : O(log(n))
     * SC : O(1)
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low < high) { // TC : O(log(n))
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                // left portion is sorted, so answer lies in right portion
                low = mid + 1;
            } else {
                // right portion is sorted, so answer lies in left portion
                high = mid;
            }
        }
        return nums[low];
    }

    /**
     * Approach I : Using Binary Search Approach
     *
     * TC : O(log(n))
     * SC : O(1)
     */
    public int findMinBruteForce(int[] nums) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC : O(log(n))
            if (nums[low] <= nums[high]) {
                return nums[low];
            }
            int mid = low + (high - low) / 2;
            int prev = (mid - 1 + n) % n;
            int next = (mid + 1) % n;
            if (nums[mid] <= nums[prev] && nums[mid] <= nums[next]) {
                return nums[mid];
            } else if (nums[low] <= nums[mid]) {
                // left portion is sorted, so answer lies in right portion
                low = mid + 1;
            } else if (nums[mid] <= nums[high]) {
                // right portion is sorted, so answer lies in left portion
                high = mid;
            }
        }
        return nums[0];
    }
}
