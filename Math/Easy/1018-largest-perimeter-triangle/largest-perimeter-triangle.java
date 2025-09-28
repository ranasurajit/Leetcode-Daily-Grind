class Solution {
    /**
     * Approach : Using Math + Geometry + Sorting Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int largestPerimeter(int[] nums) {
        int n = nums.length;
        /**
         * largest perimeter is possible with maximum possible
         * lengths, so sorting the nums as order does not matter
         */
        Arrays.sort(nums); // TC: O(N x log(N))
        int p = n - 3;
        int q = n - 2;
        int r = n - 1;
        while (p >= 0) { // TC: O(N)
            // side1 + side2 > side3 
            if (nums[p] + nums[q] > nums[r]) {
                return nums[p] + nums[q] + nums[r];
            } else {
                p--;
                q--;
                r--;
            }
        }
        return 0;
    }
}
