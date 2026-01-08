class Solution {
    private int m;
    private int n;

    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC: O(M x N) + O(M) + O(N) ~ O(M x N)
     * SC: O(M x N)
     * - O(M x N) - dp array memory
     *
     * Accepted (69 / 69 testcases passed)
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        this.m = nums1.length;
        this.n = nums2.length;
        // Initialization
        int[][] dp = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int i1 = 0; i1 <= m; i1++) { // TC: O(M)
            dp[i1][n] = Integer.MIN_VALUE;
        }
        for (int i2 = 0; i2 <= n; i2++) { // TC: O(N)
            dp[m][i2] = Integer.MIN_VALUE;
        }
        // Iterative Calls
        for (int i1 = m - 1; i1 >= 0; i1--) { // TC: O(M)
            for (int i2 = n - 1; i2 >= 0; i2--) { // TC: O(N)
                int skip1 = dp[i1 + 1][i2];
                int skip2 = dp[i1][i2 + 1];
                int pick = nums1[i1] * nums2[i2] + Math.max(0, dp[i1 + 1][i2 + 1]);
                dp[i1][i2] = Math.max(pick, Math.max(skip1, skip2));
            }
        }
        return dp[0][0];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(M x N)
     * SC: O(M x N) + O(Min(M, N))
     * - O(M x N) - memoization memory
     * - O(Min(M, N)) - recursion stack space
     *
     * Accepted (69 / 69 testcases passed)
     */
    public int maxDotProductMemoization(int[] nums1, int[] nums2) {
        this.m = nums1.length;
        this.n = nums2.length;
        int[][] memo = new int[m][n];
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, 0, nums1, nums2, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(Min(M , N)) - recursion stack space
     */
    private int solveMemoization(int i1, int i2, int[] nums1, int[] nums2, int[][] memo) {
        // Base Case
        if (i1 == m || i2 == n) {
            // returning invalid value or impossible value
            return Integer.MIN_VALUE;
        }
        // Memoization Check
        if (memo[i1][i2] != -1) {
            return memo[i1][i2];
        }
        // Recursion Calls
        // we can decide to pick or skip
        // skipping can be done independently between indices
        // skip not picking from index 'i1'
        int skip1 = solveMemoization(i1 + 1, i2, nums1, nums2, memo);
        // skip not picking from index 'i2'
        int skip2 = solveMemoization(i1, i2 + 1, nums1, nums2, memo);
        // if we decide to pick then pick from both i1 and i2 to maintain the same pick lengths
        int pick = (nums1[i1] * nums2[i2]) +
            Math.max(0, solveMemoization(i1 + 1, i2 + 1, nums1, nums2, memo));
        return memo[i1][i2] = Math.max(pick, Math.max(skip1, skip2));
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ Min(M, N))
     * SC: O(Min(M, N))
     * - O(Min(M, N)) - recursion stack space
     *
     * Time Limit Exceeded (19 / 69 testcases passed)
     */
    public int maxDotProductRecursion(int[] nums1, int[] nums2) {
        this.m = nums1.length;
        this.n = nums2.length;
        return solveRecursion(0, 0, nums1, nums2);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ Min(M, N))
     * SC: O(Min(M, N)) - recursion stack space
     */
    private int solveRecursion(int i1, int i2, int[] nums1, int[] nums2) {
        // Base Case
        if (i1 == m || i2 == n) {
            // returning invalid value or impossible value
            return Integer.MIN_VALUE;
        }
        // Recursion Calls
        // we can decide to pick or skip
        // skipping can be done independently between indices
        // skip not picking from index 'i1'
        int skip1 = solveRecursion(i1 + 1, i2, nums1, nums2);
        // skip not picking from index 'i2'
        int skip2 = solveRecursion(i1, i2 + 1, nums1, nums2);
        // if we decide to pick then pick from both i1 and i2 to maintain the same pick lengths
        int pick = (nums1[i1] * nums2[i2]) + Math.max(0, solveRecursion(i1 + 1, i2 + 1, nums1, nums2));
        return Math.max(pick, Math.max(skip1, skip2));
    }
}
