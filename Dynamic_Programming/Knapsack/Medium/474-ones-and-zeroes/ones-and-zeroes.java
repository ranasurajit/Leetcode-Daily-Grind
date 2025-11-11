class Solution {
    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(K x P x M x N)
     * SC: O(2 x M x N) ~ O(M x N)
     *
     * - O(M x N) - prev and current memory
     *
     * Accepted (77 / 77 testcases passed)
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int P = strs.length;
        int[][] prev = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int i = 0; i < P; i++) { // TC: O(P)
            int[] zerosOnes = getZerosAndOnes(strs[i]); // TC: O(K)
            int[][] current = new int[m + 1][n + 1]; // SC: O(M x N)
            for (int j = 0; j <= m; j++) { // TC: O(M)
                for (int k = 0; k <= n; k++) { // TC: O(N)
                    int pick = 0;
                    if (j >= zerosOnes[0] && k >= zerosOnes[1]) {
                        pick = 1 + (i > 0 ? prev[j - zerosOnes[0]][k - zerosOnes[1]] : 0);
                    }
                    int skip = i > 0 ? prev[j][k] : 0;
                    current[j][k] = Math.max(pick, skip);
                }
            }
            prev = current.clone();
        }
        return prev[m][n];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(K x P x M x N)
     * SC: O(P x M x N)
     *
     * - O(P x M x N) - dp memory
     *
     * Accepted (77 / 77 testcases passed)
     */
    public int findMaxFormTabulation(String[] strs, int m, int n) {
        int P = strs.length;
        int[][][] dp = new int[P][m + 1][n + 1]; // SC: O(P x M x N)
        for (int i = 0; i < P; i++) { // TC: O(P)
            int[] zerosOnes = getZerosAndOnes(strs[i]); // TC: O(K)
            for (int j = 0; j <= m; j++) { // TC: O(M)
                for (int k = 0; k <= n; k++) { // TC: O(N)
                    int pick = 0;
                    if (j >= zerosOnes[0] && k >= zerosOnes[1]) {
                        pick = 1 + (i > 0 ? dp[i - 1][j - zerosOnes[0]][k - zerosOnes[1]] : 0);
                    }
                    int skip = i > 0 ? dp[i - 1][j][k] : 0;
                    dp[i][j][k] = Math.max(pick, skip);
                }
            }
        }
        return dp[P - 1][m][n];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(K x P x M x N)
     * SC: O(P x M x N) + O(P x M x N)
     *
     * - O(P x M x N) - memoization memory
     * - O(P x M x N) - recursion stack
     *
     * Accepted (77 / 77 testcases passed)
     */
    public int findMaxFormMemoization(String[] strs, int m, int n) {
        int P = strs.length;
        int[][][] memo = new int[P][m + 1][n + 1]; // SC: O(P x M x N)
        for (int[][] mem : memo) {
            for (int[] me : mem) {
                Arrays.fill(me, -1);
            }
        }
        return solveMemoization(P - 1, strs, m, n, memo); // TC: O(K x P x M x N), SC: O(P x M x N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(K x P x M x N)
     * SC: O(P x M x N)
     */
    private int solveMemoization(int idx, String[] strs, int m, int n, int[][][] memo) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        if (m == 0 && n == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][m][n] != -1) {
            return memo[idx][m][n];
        }
        // Recursion Calls
        // pick or skip - returns the count of Strings added
        int[] zerosOnes = getZerosAndOnes(strs[idx]); // TC: O(K)
        int pick = 0;
        if (zerosOnes[0] <= m && zerosOnes[1] <= n) {
            // pick
            pick = 1 + solveMemoization(idx - 1, strs, m - zerosOnes[0], n - zerosOnes[1], memo);
        }
        int skip = solveMemoization(idx - 1, strs, m, n, memo);
        return memo[idx][m][n] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(K x 2 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (27 / 77 testcases passed)
     */
    public int findMaxFormRecursion(String[] strs, int m, int n) {
        return solveRecursion(strs.length - 1, strs, m, n); // TC: O(K x 2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(K x 2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, String[] strs, int m, int n) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        if (m == 0 && n == 0) {
            return 0;
        }
        // Recursion Calls
        // pick or skip - returns the count of Strings added
        int[] zerosOnes = getZerosAndOnes(strs[idx]); // TC: O(K)
        int pick = 0;
        if (zerosOnes[0] <= m && zerosOnes[1] <= n) {
            // pick
            pick = 1 + solveRecursion(idx - 1, strs, m - zerosOnes[0], n - zerosOnes[1]);
        }
        int skip = solveRecursion(idx - 1, strs, m, n);
        return Math.max(pick, skip);
    }

    /**
     * Using String Simulation Approach
     *
     * TC: O(K)
     * SC: O(1)
     */
    private int[] getZerosAndOnes(String s) {
        int len = s.length();
        int zeros = 0;
        int ones = 0;
        for (int i = 0; i < len; i++) { // TC: O(K)
            if (s.charAt(i) == '1') {
                ones++;
            }
        }
        return new int[] { len - ones , ones };
    }
}
