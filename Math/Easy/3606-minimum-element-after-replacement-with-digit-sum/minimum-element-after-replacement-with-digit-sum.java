class Solution {
    /**
     * Approach : Using Math + Array Simulation Approach
     *
     * TC : O(n x d) ~ O(n x 5) ~ O(n) as 1 <= nums[i] <= 10⁴
     * SC : O(1)
     */
    public int minElement(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        for (int num : nums) { // TC : O(n)
            int replacedNum = getDigitSum(num); // TC : O(d)
            min = Math.min(min, replacedNum);
        }
        return min;
    }

    /**
     * Using Math Approach
     *
     * TC : O(d)
     * SC : O(1)
     */
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += (num % 10);
            num /= 10;
        }
        return sum;
    }
}
