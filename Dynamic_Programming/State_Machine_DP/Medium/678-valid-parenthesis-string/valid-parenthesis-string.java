class Solution {
    /**
     * Approach II : Using Memoization on States (Top-Down) Approach
     *
     * TC : O(n²)
     * SC : O(n) + O(n²)
     * - O(n) - recursion stack
     * - O(n²) - memoization memory
     *
     * Accepted (84 / 84 testcases passed)
     */
    public boolean checkValidString(String s) {
        int n = s.length();
        int[][] memo = new int[n][n + 1]; // SC : O(n²)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n, s, 0, memo);
    }

    /**
     * Using Memoization on States Approach
     *
     * TC : O(n²)
     * SC : O(n)
     */
    private boolean solveMemoization(int i, int n, String s,
        int balance, int[][] memo) {
        // Base Case
        if (balance < 0) {
            return false;
        }
        if (i == n) {
            return balance == 0;
        }
        // Memoization Check
        if (memo[i][balance] != -1) {
            return memo[i][balance] == 1;
        }
        // Recursion Calls
        char ch = s.charAt(i);
        boolean option1 = false;
        boolean option2 = false;
        boolean option3 = false;
        if (ch == '(') {
            option1 = solveMemoization(i + 1, n, s, balance + 1, memo);
        } else if (ch == ')') {
            option2 = solveMemoization(i + 1, n, s, balance - 1, memo);
        } else {
            // ch is '*'
            option3 = solveMemoization(i + 1, n, s, balance + 1, memo) ||
                solveMemoization(i + 1, n, s, balance - 1, memo) ||
                solveMemoization(i + 1, n, s, balance, memo);
        }
        boolean result = option1 || option2 || option3;
        memo[i][balance] = result ? 1 : 0;
        return result;
    }

    /**
     * Approach I : Using Recursion on States Approach
     *
     * TC : O(3ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     *
     * Time Limit Exceeded (81 / 84 testcases passed)
     */
    public boolean checkValidStringRecursion(String s) {
        int n = s.length();
        return solve(0, n, s, 0);
    }

    /**
     * Using Recursion on States Approach
     *
     * TC : O(3ⁿ)
     * SC : O(n)
     */
    private boolean solve(int i, int n, String s, int balance) {
        // Base Case
        if (balance < 0) {
            return false;
        }
        if (i == n) {
            return balance == 0;
        }
        // Recursion Calls
        char ch = s.charAt(i);
        boolean option1 = false;
        boolean option2 = false;
        boolean option3 = false;
        if (ch == '(') {
            option1 = solve(i + 1, n, s, balance + 1);
        } else if (ch == ')') {
            option2 = solve(i + 1, n, s, balance - 1);
        } else {
            // ch is '*'
            option3 = solve(i + 1, n, s, balance + 1) ||
                solve(i + 1, n, s, balance - 1) ||
                solve(i + 1, n, s, balance);
        }
        return option1 || option2 || option3;
    }
}
