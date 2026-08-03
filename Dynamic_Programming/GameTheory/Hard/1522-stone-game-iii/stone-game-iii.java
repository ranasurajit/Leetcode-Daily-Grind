class Solution {
    private int n;

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(n)
     * SC : O(n)
     * - O(n) - recursion stack
     * - O(n) - memoization memory
     *
     * Accepted (185 / 185 testcases passed)
     */
    public String stoneGameIII(int[] stoneValue) {
        this.n = stoneValue.length;
        /**
         * Here solve function would return the 
         * score difference of Alice and Bob in the
         * range of [i...j]
         */
        int[] memo = new int[n + 1]; // SC : O(n)
        Arrays.fill(memo, -1);
        int diff = solveMemoization(0, stoneValue, memo);
        if (diff == 0) {
            return "Tie";
        } else if (diff > 0) {
            return "Alice";
        }
        return "Bob";
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    private int solveMemoization(int i, int[] stoneValue, int[] memo) {
        // Base Case
        if (i >= n) {
            return 0;
        }
        // Memoization Check
        if (memo[i] != -1) {
            return memo[i];
        }
        // Recursion Calls
        int diff = Integer.MIN_VALUE;
        int take_1 = stoneValue[i];
        int take_2 = 0;
        int take_3 = 0;
        diff = Math.max(diff,
            take_1 - solveMemoization(i + 1, stoneValue, memo));
        if (i + 1 < n) {
            take_2 = take_1 + stoneValue[i + 1];
            diff = Math.max(diff, 
                take_2 - solveMemoization(i + 2, stoneValue, memo));
        }
        if (i + 2 < n) {
            take_3 = take_2 + stoneValue[i + 2];
            diff = Math.max(diff,
                take_3 - solveMemoization(i + 3, stoneValue, memo));
        }
        return memo[i] = diff;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(3ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     *
     * Time Limit Exceeded (160 / 185 testcases passed)
     */
    public String stoneGameIIIRecursion(int[] stoneValue) {
        this.n = stoneValue.length;
        /**
         * Here solve function would return the 
         * score difference of Alice and Bob in the
         * range of [i...j]
         */
        int diff = solveRecursion(0, stoneValue);
        if (diff == 0) {
            return "Tie";
        } else if (diff > 0) {
            return "Alice";
        }
        return "Bob";
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(3ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int i, int[] stoneValue) {
        // Base Case
        if (i >= n) {
            return 0;
        }
        // Recursion Calls
        int diff = Integer.MIN_VALUE;
        int take_1 = stoneValue[i];
        int take_2 = 0;
        int take_3 = 0;
        diff = Math.max(diff, take_1 - solveRecursion(i + 1, stoneValue));
        if (i + 1 < n) {
            take_2 = take_1 + stoneValue[i + 1];
            diff = Math.max(diff, take_2 - solveRecursion(i + 2, stoneValue));
        }
        if (i + 2 < n) {
            take_3 = take_2 + stoneValue[i + 2];
            diff = Math.max(diff, take_3 - solveRecursion(i + 3, stoneValue));
        }
        return diff;
    }
}
