class Solution {
    /**
     * Approach : Using Math + Array Simulation Approach
     *
     * TC : O(n x log10(Max(nums)))
     * SC : O(1)
     */
    public int minElement(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        for (int num : nums) { // TC : O(n)
            int len = (int) Math.log10(num) + 1;
            int replacedNum = len > 1 ? getDigitSum(num) : num; // TC : O(log10(num))
            min = Math.min(min, replacedNum);
        }
        return min;
    }

    /**
     * Using Math Approach
     *
     * TC : O(log10(num))
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
