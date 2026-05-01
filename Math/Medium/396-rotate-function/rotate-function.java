class Solution {
    /**
     * Approach II : Using Optimal (Math + Array Simulation) Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     *
     * Accepted (58 / 58 testcases passed)
     */
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int maxFn = Integer.MIN_VALUE;
        int currentFn = 0;
        /**
         * Firstly we can get the sum of F(0)
         * so, currentFn = F(0)
         */
        int total = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            currentFn += (nums[i] * i);
            total += nums[i];
        }
        maxFn = Math.max(maxFn, currentFn);
        for (int i = n - 1; i > 0; i--) { // TC : O(n)
            // here element at index 'i' is the start of rotated array
            currentFn += total - (n * nums[i]);
            maxFn = Math.max(maxFn, currentFn);
        }
        return maxFn == Integer.MIN_VALUE ? 0 : maxFn;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC : O(n²)
     * SC : O(1)
     *
     * Time Limit Exceeded (45 / 58 testcases passed)
     */
    public int maxRotateFunctionBruteForce(int[] nums) {
        int n = nums.length;
        int maxFn = Integer.MIN_VALUE;
        for (int start = 0; start < n; start++) {     // TC : O(n)
            int mult = 0;
            int currentFn = 0;
            for (int i = start; i < n + start; i++) { // TC : O(n)
                currentFn += nums[i % n] * mult;
                mult++;
            }
            maxFn = Math.max(maxFn, currentFn);
        }
        return maxFn;
    }
}
