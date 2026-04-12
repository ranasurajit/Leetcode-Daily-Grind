class Solution {
    private String word;
    private int n;

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(n x 27 x 27) + O(n x 27 x 27) ~ O(n)
     * SC: O(n) + O(n)
     * - O(n) - recursion stack
     * - O(n) - memoization memory
     * Accepted (55 / 55 testcases passed)
     */
    public int minimumDistance(String word) {
        this.word = word;
        this.n = word.length();
        int[][][] memo = new int[n][27][27]; // SC: O(n x 27 x 27) ~ O(n)
        for (int[][] mem : memo) { // TC: O(n x 27 x 27) ~ O(n)
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        return solveMemoization(0, 26, 26, memo); // TC: O(n), SC: O(n)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(n x 27 x 27) ~ O(n)
     * SC: O(n)
     */
    private int solveMemoization(int i, int left, int right, int[][][] memo) {
        // Base Case
        if (i == n) {
            return 0;
        }
        // Memoization Check
        if (memo[i][left][right] != -1) {
            return memo[i][left][right];
        }
        // Recursion Calls
        // ith character typed with left finger
        int leftDist = computeDistance(word.charAt(i) - 'A', left) +
            solveMemoization(i + 1, word.charAt(i) - 'A', right, memo);
        // ith character typed with right finger
        int rightDist = computeDistance(word.charAt(i) - 'A', right) +
            solveMemoization(i + 1, left, word.charAt(i) - 'A', memo);
        return memo[i][left][right] = Math.min(leftDist, rightDist);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2ⁿ)
     * SC: O(n)
     * - O(n) - recursion stack
     * Time Limit Exceeded (24 / 55 testcases passed)
     */
    public int minimumDistanceRecursion(String word) {
        this.word = word;
        this.n = word.length();
        return solveRecursion(0, 26, 26); // TC: O(2ⁿ), SC: O(n)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2ⁿ)
     * SC: O(n)
     */
    private int solveRecursion(int i, int left, int right) {
        // Base Case
        if (i == n) {
            return 0;
        }
        // Recursion Calls
        // ith character typed with left finger
        int leftDist = computeDistance(word.charAt(i) - 'A', left) +
            solveRecursion(i + 1, word.charAt(i) - 'A', right);
        // ith character typed with right finger
        int rightDist = computeDistance(word.charAt(i) - 'A', right) +
            solveRecursion(i + 1, left, word.charAt(i) - 'A');
        return Math.min(leftDist, rightDist);
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    private int computeDistance(int current, int prev) {
        if (prev == 26) {
            return 0;
        }
        int x1 = current / 6;
        int y1 = current % 6;
        int x2 = prev / 6;
        int y2 = prev % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
