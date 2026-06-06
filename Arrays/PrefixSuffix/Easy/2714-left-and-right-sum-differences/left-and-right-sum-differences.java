class Solution {
    /**
     * Approach II : Using Running Prefix-Suffix Array Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] diff = new int[n];
        int leftSum = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            diff[i] = leftSum;
            leftSum += nums[i];
        }
        int rightSum = 0;
        for (int i = n - 1; i >= 0; i--) { // TC : O(n)
            diff[i] = Math.abs(diff[i] - rightSum);
            rightSum += nums[i];
        }
        return diff;
    }

    /**
     * Approach I : Using Brute-Force (Prefix-Suffix Array) Approach
     *
     * TC : O(n) + O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    public int[] leftRightDifferenceBruteForce(int[] nums) {
        int n = nums.length;
        int[] leftSum = new int[n];   // SC : O(n)
        leftSum[0] = nums[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            leftSum[i] = leftSum[i - 1] + nums[i];
        }
        int[] rightSum = new int[n];  // SC : O(n)
        rightSum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) { // TC : O(n)
            rightSum[i] = rightSum[i + 1] + nums[i];
        }
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) { // TC : O(n)
            int left = i > 0 ? leftSum[i - 1] : 0;
            int right = i < n - 1 ? rightSum[i + 1] : 0;
            diff[i] = Math.abs(left - right);
        }
        return diff;
    }
}
