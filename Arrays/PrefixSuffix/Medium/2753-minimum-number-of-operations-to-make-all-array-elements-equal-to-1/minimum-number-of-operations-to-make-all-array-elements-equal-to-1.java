class Solution {
    /**
     * Approach : Using Euclidean algorithms and Prefix Array Approach
     *
     * TC: O(N x log(Max(nums))) + O(N x N x log(Max(nums))) ~ O(N x N x log(Max(nums)))
     * SC: O(log(Max(nums)))
     */
    public int minOperations(int[] nums) {
        int n = nums.length;
        int count1s = 0;
        int cummGCD = nums[0];
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (i > 0) {
                cummGCD = gcd(cummGCD, nums[i]); // TC: O(log(Max(nums)))
            }
            if (nums[i] == 1) {
                count1s++;
            }
        }
        if (cummGCD > 1) {
            // not possible to make all elements 1
            return -1;
        }
        if (count1s > 0) {
            // 1's in the array can be chosen either as (i)th or (i + 1)th elemnt to form GCD
            return n - count1s;
        }
        int operations = 0;
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            int prefixGCD = nums[i];
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                prefixGCD = gcd(prefixGCD, nums[j]); // TC: O(log(Max(nums)))
                if (prefixGCD == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }
        return (minLen - 1) + (n - 1);
    }

    /**
     * Using Euclidean algorithms for GCD
     *
     * TC: O(log(Max(nums)))
     * SC: O(log(Max(nums)))
     */
    private int gcd(int a, int b) {
        if (a < b) {
            // keeping always a > b
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
