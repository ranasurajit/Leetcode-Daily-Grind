class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach I : Using DP Tabulation Approach
     *
     * TC: O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int numOfWays(int n) {
        /**
         * we can arrange colors in two ways
         * 1. A B C - all three colors different in row - dpThree
         * 2. A B A - 1st and 3rd cell has same color - dpTwo
         */
        long[] dpThree = new long[n + 1]; // SC: O(N)
        long[] dpTwo = new long[n + 1]; // SC: O(N)
        dpThree[1] = 6L; // filling ways = 3 x 2 x 1
        dpTwo[1] = 6L; // filling ways = 3 x 2 x 1 (fixed)
        /**
         * filling a row depends on filling pattern of previous row
         * 1. if previous row is having 3 color pattern so we can have current
         * row filled up with 2 or 3 color pattern
         * Number of ways to fill current row if previous row is filled with:
         * 3 --> 3 = 2 ways, 3 --> 2 = 2 ways
         * 2 --> 3 = 2 ways, 2 --> 2 = 3 ways
         */
        for (int i = 2; i <= n; i++) { // TC: O(N)
            dpThree[i] = ((dpThree[i - 1] * 2) % MOD + (dpTwo[i - 1] * 2) % MOD) % MOD;
            dpTwo[i] = ((dpTwo[i - 1] * 3) % MOD + (dpThree[i - 1] * 2) % MOD) % MOD;
        }
        return (int) ((dpThree[n] + dpTwo[n]) % MOD);
    }
}
