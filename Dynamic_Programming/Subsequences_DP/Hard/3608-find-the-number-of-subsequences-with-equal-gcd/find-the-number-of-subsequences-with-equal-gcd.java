class Solution {
    private int n;
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(n x m x m)
     * SC : O(n x m x m) + O(n)
     *
     * Accepted (622 / 622 testcases passed)
     */
    public int subsequencePairCount(int[] nums) {
        this.n = nums.length;
        long[][][] memo = new long[n][201][201]; // SC : O(n x m x m)
        for (long[][] mem : memo) {
            for (long[] m : mem) {
                Arrays.fill(m, -1L);
            }
        }
        return (int) (solveMemoization(0, nums, 0, 0, memo) % MOD);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(n x m x m)
     * SC : O(n)
     */
    private long solveMemoization(int idx, int[] nums,
        int gcd1, int gcd2, long[][][] memo) {
        // Base Case
        if (idx == n) {
            boolean nonEmpty = (gcd1 != 0 && gcd2 != 0);
            boolean gcdsEqual = gcd1 == gcd2;
            return nonEmpty && gcdsEqual ? 1 : 0;
        }
        // Memoization Check
        if (memo[idx][gcd1][gcd2] != -1L) {
            return memo[idx][gcd1][gcd2];
        }
        // Recursion Calls
        // skip
        long skip =
            solveMemoization(idx + 1, nums, gcd1, gcd2, memo) % MOD;
        // take nums[idx] in 1st sequence gcd1
        long take1 =
            solveMemoization(idx + 1, nums, gcd(gcd1, nums[idx]), gcd2, memo) % MOD;
        // take nums[idx] in 2nd sequence gcd2
        long take2 =
            solveMemoization(idx + 1, nums, gcd1, gcd(gcd2, nums[idx]), memo) % MOD;
        return memo[idx][gcd1][gcd2] = (skip + take1 + take2) % MOD;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(3 ^ n)
     * SC : O(n)
     *
     * Time Limit Exceeded (588 / 622 testcases passed)
     */
    public int subsequencePairCountRecursion(int[] nums) {
        this.n = nums.length;
        return (int) (solveRecursion(0, nums, 0, 0) % MOD);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(3 ^ n)
     * SC : O(n)
     */
    private long solveRecursion(int idx, int[] nums, int gcd1, int gcd2) {
        // Base Case
        if (idx == n) {
            boolean nonEmpty = (gcd1 != 0 && gcd2 != 0);
            boolean gcdsEqual = gcd1 == gcd2;
            return nonEmpty && gcdsEqual ? 1 : 0;
        }
        // Recursion Calls
        // skip
        long skip = solveRecursion(idx + 1, nums, gcd1, gcd2);
        // take nums[idx] in 1st sequence gcd1
        long take1 = solveRecursion(idx + 1, nums, gcd(gcd1, nums[idx]), gcd2);
        // take nums[idx] in 2nd sequence gcd2
        long take2 = solveRecursion(idx + 1, nums, gcd1, gcd(gcd2, nums[idx]));
        return skip + take1 + take2;
    }

    /**
     * Using Math Approach
     *
     * TC : O(log(a) base b)
     * SC : O(log(a) base b)
     */
    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
