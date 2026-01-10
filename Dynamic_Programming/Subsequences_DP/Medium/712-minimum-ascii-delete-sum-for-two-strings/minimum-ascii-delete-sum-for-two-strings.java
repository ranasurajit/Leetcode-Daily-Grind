class Solution {
    private int m;
    private int n;

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(M x N) + O(M) + O(N) ~ O(M x N)
     * SC: O(M x N) + O(M + N)
     * - O(M x N) - memoization memory
     * - O(M + N) - recursion stack space
     *
     * Accepted (93 / 93 testcases passed)
     */
    public int minimumDeleteSum(String s1, String s2) {
        this.m = s1.length();
        this.n = s2.length();
        int[][] memo = new int[m][n]; // SC: O(M x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        int maxASCIISumLCS = solveMemoization(0, 0, s1, s2, memo); // TC: O(M x N), SC: O(M + N)
        int totalASCIISum = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            totalASCIISum += (int) s1.charAt(i);
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            totalASCIISum += (int) s2.charAt(i);
        }
        return totalASCIISum - 2 * maxASCIISumLCS;
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M + N)
     */
    private int solveMemoization(int i, int j, String s1, String s2, int[][] memo) {
        // Base Case
        if (i == m || j == n) {
            // if any of the pointers is exhausted
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        // pick or skip
        int pick = 0;
        int skip1 = solveMemoization(i + 1, j, s1, s2, memo);
        int skip2 = solveMemoization(i, j + 1, s1, s2, memo);
        // we can pick only if the characters at index i and j matches
        if (s1.charAt(i) == s2.charAt(j)) {
            pick = (int) s1.charAt(i) + solveMemoization(i + 1, j + 1, s1, s2, memo);
        }
        return memo[i][j] = Math.max(pick, Math.max(skip1, skip2));
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M + N)) + O(M) + O(N) ~ O(2 ^ (M + N))
     * SC: O(M + N)
     * - O(M + N) - recursion stack space
     *
     * Time Limit Exceeded (58 / 93 testcases passed)
     */
    public int minimumDeleteSumRecursion(String s1, String s2) {
        this.m = s1.length();
        this.n = s2.length();
        int maxASCIISumLCS = solveRecursion(0, 0, s1, s2); // TC: O(2 ^ (M + N)), SC: O(M + N)
        int totalASCIISum = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            totalASCIISum += (int) s1.charAt(i);
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            totalASCIISum += (int) s2.charAt(i);
        }
        return totalASCIISum - 2 * maxASCIISumLCS;
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     */
    private int solveRecursion(int i, int j, String s1, String s2) {
        // Base Case
        if (i == m || j == n) {
            // if any of the pointers is exhausted
            return 0;
        }
        // Recursion Calls
        // pick or skip
        int pick = 0;
        int skip1 = solveRecursion(i + 1, j, s1, s2);
        int skip2 = solveRecursion(i, j + 1, s1, s2);
        // we can pick only if the characters at index i and j matches
        if (s1.charAt(i) == s2.charAt(j)) {
            pick = (int) s1.charAt(i) + solveRecursion(i + 1, j + 1, s1, s2);
        }
        return Math.max(pick, Math.max(skip1, skip2));
    }
}
