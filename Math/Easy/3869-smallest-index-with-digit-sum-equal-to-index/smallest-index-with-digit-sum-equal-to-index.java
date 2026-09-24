class Solution {
    /**
     * Approach : Using Math Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int smallestIndex(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) { // TC : O(n)
            int num = nums[i];
            int digitSum = 0;
            while (num > 0) { // TC : O(4) as, 0 <= nums[i] <= 1000
                digitSum += num % 10;
                num = num / 10;
            }
            if (digitSum == i) {
                // early exit to return smallest index
                return i;
            }
        }
        return -1;
    }
}
