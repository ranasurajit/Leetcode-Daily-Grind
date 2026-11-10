class Solution {
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC : O(n)
     * SC : O(n)
     * - O(n) - dp array memory
     * Accepted (1005 / 1005 testcases passed)
     */
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1]; // SC : O(n)
        dp[n] = 0;
        int option1 = Integer.MAX_VALUE;
        int option2 = Integer.MAX_VALUE;
        int option3 = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) { // TC : O(n)
            if (i + 1 <= n) {
                option1 = costs[i] + 1 + dp[i + 1];
            }
            // jumping (+2) step
            if (i + 2 <= n) {
                option2 = costs[i + 1] + 4 + dp[i + 2];
            }
            // jumping (+3) step
            if (i + 3 <= n) {
                option3 = costs[i + 2] + 9 + dp[i + 3];
            }
            dp[i] = Math.min(option1, Math.min(option2, option3));
        }
        return dp[0];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(n)
     * SC : O(n) + O(n)
     * - O(n) - memoization memory
     * - O(n) - recursion stack
     * Accepted (1005 / 1005 testcases passed)
     */
    public int climbStairsMemoization(int n, int[] costs) {
        int[] memo = new int[n]; // SC : O(n)
        Arrays.fill(memo, -1);
        return solveMemoization(0, n, costs, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(n)
     * SC : O(n) 
     */
    private int solveMemoization(int i, int n, int[] costs, int[] memo) {
        // Base Case
        if (i >= n) {
            return 0;
        }
        // Memoization Check
        if (memo[i] != -1) {
            return memo[i];
        }
        // Recursion Calls
        int option1 = Integer.MAX_VALUE;
        int option2 = Integer.MAX_VALUE;
        int option3 = Integer.MAX_VALUE;
        // jumping (+1) step
        if (i + 1 <= n) {
            option1 = costs[i] + 1 + solveMemoization(i + 1, n, costs, memo);
        }
        // jumping (+2) step
        if (i + 2 <= n) {
            option2 = costs[i + 1] + 4 + solveMemoization(i + 2, n, costs, memo);
        }
        // jumping (+3) step
        if (i + 3 <= n) {
            option3 = costs[i + 2] + 9 + solveMemoization(i + 3, n, costs, memo);
        }
        return memo[i] = Math.min(option1, Math.min(option2, option3));
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(3ⁿ)
     * SC : O(n) 
     * - O(n) - recursion stack
     * Time Limit Exceeded (505 / 1005 testcases passed)
     */
    public int climbStairsRecursion(int n, int[] costs) {
        return solveRecursion(0, n, costs);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(3ⁿ)
     * SC : O(n) 
     */
    private int solveRecursion(int i, int n, int[] costs) {
        // Base Case
        if (i >= n) {
            return 0;
        }
        // Recursion Calls
        int option1 = Integer.MAX_VALUE;
        int option2 = Integer.MAX_VALUE;
        int option3 = Integer.MAX_VALUE;
        // jumping (+1) step
        if (i + 1 <= n) {
            option1 = costs[i] + 1 + solveRecursion(i + 1, n, costs);
        }
        // jumping (+2) step
        if (i + 2 <= n) {
            option2 = costs[i + 1] + 4 + solveRecursion(i + 2, n, costs);
        }
        // jumping (+3) step
        if (i + 3 <= n) {
            option3 = costs[i + 2] + 9 + solveRecursion(i + 3, n, costs);
        }
        return Math.min(option1, Math.min(option2, option3));
    }
}
