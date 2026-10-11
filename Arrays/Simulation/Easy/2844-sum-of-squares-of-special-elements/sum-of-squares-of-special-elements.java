class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sumSquares = 0;
        for (int i = 1; i <= n; i++) { // TC : O(n)
            if (n % i == 0) {
                sumSquares += nums[i - 1] * nums[i - 1];
            }
        }
        return sumSquares;
    }
}
