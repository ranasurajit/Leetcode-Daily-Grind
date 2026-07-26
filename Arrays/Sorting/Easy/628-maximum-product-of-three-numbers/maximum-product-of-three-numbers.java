class Solution {
    /**
     * Approach I : Using Sorting Approach
     *
     * TC : O(n x log(n))
     * SC : O(1)
     */
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); // TC : O(n x log(n))
        int firstMax = nums[n - 1];
        int secondMax = nums[n - 2];
        int thirdMax = nums[n - 3];
        int firstMin = nums[0];
        int secondMin = nums[1];
        int thirdMin = nums[2];
        return Math.max(
            firstMin * secondMin * firstMax,
            Math.max(
                secondMax * thirdMax * firstMax,
                firstMin * secondMin * thirdMin
            )
        );
    }
}
