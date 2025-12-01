class Solution {
    /**
     * Approach IV : Using Optimal Memoization (Front Partition DP) Approach
     *
     * TC: O(N²)
     * SC: O(N) + O(N) + O(N)
     *
     * Accepted (37 / 37 testcases passed)
     */
    public int minCut(String s) {
        int n = s.length();
        if (isPalindrome(s, 0, n - 1)) { // TC: O(N)
            // no need to partition if s is already palindrome
            return 0;
        }
        int[] memo = new int[n]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveOptimalMemoization(0, n, s, memo); // returns the number of partitions needed
    }

    /**
     * Using Optimal Memoization (Front Partition DP) Approach
     *
     * TC: O(N²)
     * SC: O(N) + O(N)
     */
    private int solveOptimalMemoization(int idx, int n, String s, int[] memo) {
        // Base Case
        if (idx == n) {
            // no more partitions can be done
            return 0;
        }
        if (isPalindrome(s, idx, n - 1)) {
            return 0;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        int minPartitions = n - 1;
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        for (int k = idx; k < n; k++) { // TC: O(N)
            sb.append(s.charAt(k));
            int m = sb.length();
            if (m == 1 || isPalindrome(sb.toString(), 0, m - 1)) {
                minPartitions = Math.min(minPartitions, 1 + solveOptimalMemoization(k + 1, n, s, memo));
            }
        }
        return memo[idx] = minPartitions;
    }

    /**
     * Approach III : Using Optimal Recursion (Front Partition DP) Approach
     *
     * TC: Exponential
     * SC: O(N)
     *
     * Time Limit Exceeded (25 / 37 testcases passed)
     */
    public int minCutOptimalRecursion(String s) {
        int n = s.length();
        if (isPalindrome(s, 0, n - 1)) { // TC: O(N)
            // no need to partition if s is already palindrome
            return 0;
        }
        return solveOptimalRecursion(0, n, s); // returns the number of partitions needed
    }

    /**
     * Using Optimal Recursion (Front Partition DP) Approach
     *
     * TC: Exponential
     * SC: O(N)
     */
    private int solveOptimalRecursion(int idx, int n, String s) {
        // Base Case
        if (idx == n) {
            // no more partitions can be done
            return 0;
        }
        if (isPalindrome(s, idx, n - 1)) {
            return 0;
        }
        // Recursion Calls
        int minPartitions = n - 1;
        StringBuilder sb = new StringBuilder();
        for (int k = idx; k < n; k++) {
            sb.append(s.charAt(k));
            int m = sb.length();
            if (m == 1 || isPalindrome(sb.toString(), 0, m - 1)) {
                minPartitions = Math.min(minPartitions, 1 + solveOptimalRecursion(k + 1, n, s));
            }
        }
        return minPartitions;
    }

    /**
     * Approach II : Using Memoization (Partition DP) Approach
     *
     * TC: O(N x N x N) ~ O(N³)
     * SC: O(N x N) + O(N) ~ O(N²) + O(N)
     *
     * Time Limit Exceeded (28 / 37 testcases passed)
     */
    public int minCutMemoization(String s) {
        int n = s.length();
        if (isPalindrome(s, 0, n - 1)) { // TC: O(N)
            // no need to partition if s is already palindrome
            return 0;
        }
        int[][] memo = new int[n][n]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(s, 0, n - 1, memo); // returns the number of partitions needed
    }

    /**
     * Using Memoization (Partition DP) Approach
     *
     * TC: O(N x N x N) ~ O(N³)
     * SC: O(N)
     */
    private int solveMemoization(String s, int i, int j, int[][] memo) {
        // Base Case
        if (i >= j) {
            /**
             * if i > j, then String is invalid, 
             * if i == j, then it is already palindrome 
             * so, no partitions needed
             */
            return 0;
        }
        if (isPalindrome(s, i, j)) { // TC: O(N)
            // no need to partition if s is already palindrome
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int minPartitions = s.length() - 1;
        for (int k = i; k <= j - 1; k++) { // TC: O(N)
            // k cannot be j as we partition (i , k) and (k + 1) to j so k + 1 cannot be > j
            int leftPartitions = memo[i][k] != -1 ? 
                memo[i][k] : solveMemoization(s, i, k, memo);
            int rightPartitions = memo[k + 1][j] != -1 ? 
                memo[k + 1][j] : solveMemoization(s, k + 1, j, memo);
            minPartitions = Math.min(minPartitions, 1 + leftPartitions + rightPartitions);
        }
        return memo[i][j] = minPartitions;
    }

    /**
     * Approach I : Using Recursion (Partition DP) Approach
     *
     * TC: Exponential
     * SC: O(N)
     *
     * Time Limit Exceeded (21 / 37 testcases passed)
     */
    public int minCutRecursion(String s) {
        int n = s.length();
        if (isPalindrome(s, 0, n - 1)) { // TC: O(N)
            // no need to partition if s is already palindrome
            return 0;
        }
        return solveRecursion(s, 0, n - 1); // returns the number of partitions needed
    }

    /**
     * Using Recursion (Partition DP) Approach
     *
     * TC: Exponential
     * SC: O(N)
     */
    private int solveRecursion(String s, int i, int j) {
        // Base Case
        if (i >= j) {
            /**
             * if i > j, then String is invalid, 
             * if i == j, then it is already palindrome 
             * so, no partitions needed
             */
            return 0;
        }
        if (isPalindrome(s, i, j)) { // TC: O(N)
            // no need to partition if s is already palindrome
            return 0;
        }
        int minPartitions = s.length() - 1;
        for (int k = i; k <= j - 1; k++) { // TC: O(N)
            // k cannot be j as we partition (i , k) and (k + 1) to j so k + 1 cannot be > j
            minPartitions = Math.min(minPartitions,
                1 + solveRecursion(s, i, k) + solveRecursion(s, k + 1, j));
        }
        return minPartitions;
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N / 2) ~ O(N)
     * SC: O(1)
     */
    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) { // TC: O(N / 2)
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
