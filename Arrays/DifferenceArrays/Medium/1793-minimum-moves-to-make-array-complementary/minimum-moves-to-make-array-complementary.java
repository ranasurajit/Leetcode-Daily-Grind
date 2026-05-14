class Solution {
    /**
     * Approach II : Using Optimal (Difference Array) Approach
     *
     * TC : O(n / 2) + O(2 x limit) ~ O(n + limit)
     * SC : O(2 x limit + 1) ~ O(limit)
     *
     * Accepted (113 / 113 testcases passed)
     */
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        /**
         * The sum can be in range of [2, 2 * limit] as
         * we can change both pairs to a minimum
         * value as 1, 1 so, sum = 2 or we can
         * change both pairs to a maximum value as limit
         * so, sum = 2 * limit
         *
         * we can have either 0, 1 or 2 moves
         * 0 moves when pair-sum = sum
         */
        int minMoves = Integer.MAX_VALUE;
        int[] diff = new int[2 * limit + 2]; // SC : O(2 x limit + 1)
        // setting the range moves to 2 by default
        diff[2] += 2 * (n / 2);
        diff[2 * limit + 1] -= 2 * (n / 2);
        for (int i = 0; i < n / 2; i++) {  // TC : O(n / 2)
            int a = nums[i];
            int b = nums[n - 1 - i];
            int minSumRange = 1 + Math.min(a, b);
            int maxSumRange = limit + Math.max(a, b);
            // negating for 1 moves
            diff[minSumRange] -= 1;
            diff[maxSumRange + 1] += 1;
            // negating for 0 moves
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
        }
        for (int sum = 2; sum <= 2 * limit; sum++) { // TC : O(2 x limit)
            diff[sum] += diff[sum - 1];
            minMoves = Math.min(minMoves, diff[sum]);
        }
        return minMoves;
    }

    /**
     * Approach I : Using Brute-Force (Simulation) Approach
     *
     * TC : O(2 x limit x (n / 2)) ~ O(limit x n)
     * SC : O(1)
     *
     * Time Limit Exceeded (107 / 113 testcases passed)
     */
    public int minMovesBruteForce(int[] nums, int limit) {
        int n = nums.length;
        /**
         * The sum can be in range of [2, 2 * limit] as
         * we can change both pairs to a minimum
         * value as 1, 1 so, sum = 2 or we can
         * change both pairs to a maximum value as limit
         * so, sum = 2 * limit
         *
         * we can have either 0, 1 or 2 moves
         * 0 moves when pair-sum = sum
         */
        int minMoves = Integer.MAX_VALUE;
        for (int sum = 2; sum <= 2 * limit; sum++) { // TC : O(2 x limit)
            int moves = 0;
            for (int i = 0; i < n / 2; i++) {        // TC : O(n / 2)
                int a = nums[i];
                int b = nums[n - 1 - i];
                // 0 moves
                if (a + b == sum) {
                    continue;
                }
                // 1 moves
                // replace larger value with 1
                int minSumRange = Math.min(a, b) + 1;
                // replace smaller value with limit
                int maxSumRange = Math.max(a, b) + limit;
                if (sum >= minSumRange && sum <= maxSumRange) {
                    moves += 1;
                } else {
                    // we have only 2 moves as options for remaining pairs
                    moves += 2;
                }
            }
            minMoves = Math.min(minMoves, moves);
        }
        return minMoves;
    }
}
