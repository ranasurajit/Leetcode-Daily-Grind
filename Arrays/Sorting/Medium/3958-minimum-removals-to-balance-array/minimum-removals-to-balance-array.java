class Solution {
    /**
     * Approach : Using Sorting + Two Pointers Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            // array of size 1 is balanced
            return 0;
        }
        Arrays.sort(nums); // TC: O(N x log(N))
        int p = 0; // start pointer
        int q = 1; // end pointer
        int maxWindowSize = 1;
        while (q < n) { // TC: O(N)
            while (nums[q] > (long) k * nums[p]) {
                // increase start index
                p++;
            }
            maxWindowSize = Math.max(maxWindowSize, q - p + 1);
            q++;
        }
        return n - maxWindowSize;
    }
}
