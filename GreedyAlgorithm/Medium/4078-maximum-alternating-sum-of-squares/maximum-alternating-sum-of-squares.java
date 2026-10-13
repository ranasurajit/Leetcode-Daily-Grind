class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     *
     * TC : O(n) + O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(n)
     */
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] numSquares = new long[n]; // SC : O(n)
        for (int i = 0; i < n; i++) {    // TC : O(n)
            numSquares[i] = (long) nums[i] * (long) nums[i];
        }
        Arrays.sort(numSquares); // TC : O(n x log(n))
        long sumMax = 0L;
        long sumMin = 0L;
        for (int i = n - 1; i >= 0; i--) { // TC : O(n)
            if (i >= (n / 2)) {
                sumMax += numSquares[i];
            } else {
                sumMin += numSquares[i];
            }
        }
        return sumMax - sumMin;
    }
}
