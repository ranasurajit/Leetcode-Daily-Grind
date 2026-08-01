class Solution {
    /**
     * Approach IV : Using Memoization-II (Top-Down) Approach
     *
     * TC : O(n²)
     * SC : O(n) + O(n²) ~ O(n²)
     * - O(n) - recursion stack
     * - O(n²) - memoization memory
     */
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        /**
         * we will try to maximize the difference of
         * score(Player 1) - score(Player 2)
         * return true : 
         * if score(Player 1) - score(Player 2) > = 0
         * solveMem(i, j) returns the maximum score difference
         * (Current Player - Opponent)obtainable from nums[i...j].
         */
        Integer[][] memo = new Integer[n][n]; // SC : O(n²)
        return solveMem(0, n - 1, nums, memo) >= 0; // TC : O(n²), SC : O(n)
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(n²)
     * SC : O(n)
     */
    private int solveMem(int i, int j, int[] nums, Integer[][] memo) {
        // Base Case
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return nums[i];
        }
        // Memoization Check
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        // Recursion Calls
        /**
         * if Player 1 takes score at index 'i' then,
         * Player 2 can take the score either at [i + 1, j]
         * if Player 1 takes score at index 'j' then,
         * Player 2 can take the score either at [i, j - 1]
         */
        int take_diff_i = nums[i] - solveMem(i + 1, j, nums, memo);
        int take_diff_j = nums[j] - solveMem(i, j - 1, nums, memo);
        return memo[i][j] = Math.max(take_diff_i, take_diff_j);
    }

    /**
     * Approach III : Using Recursion-II Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     */
    public boolean predictTheWinnerRecursionII(int[] nums) {
        int n = nums.length;
        /**
         * we will try to maximize the difference of
         * score(Player 1) - score(Player 2)
         * return true : 
         * if score(Player 1) - score(Player 2) > = 0
         * solve(i, j) returns the maximum score difference
         * (Current Player - Opponent)obtainable from nums[i...j].
         */
        return solve(0, n - 1, nums) >= 0; // TC : O(2ⁿ), SC : O(n)
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solve(int i, int j, int[] nums) {
        // Base Case
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return nums[i];
        }
        // Recursion Calls
        /**
         * if Player 1 takes score at index 'i' then,
         * Player 2 can take the score either at [i + 1, j]
         * if Player 1 takes score at index 'j' then,
         * Player 2 can take the score either at [i, j - 1]
         */
        int take_diff_i = nums[i] - solve(i + 1, j, nums);
        int take_diff_j = nums[j] - solve(i, j - 1, nums);
        return Math.max(take_diff_i, take_diff_j);
    }

    /**
     * Approach II : Using Memoization-I (Top-Down) Approach
     *
     * TC : O(n) + O(n²) + O(n²) ~ O(n²)
     * SC : O(n) + O(n²) ~ O(n²)
     * - O(n) - recursion stack
     * - O(n²) - memoization memory
     */
    public boolean predictTheWinnerMemoization(int[] nums) {
        int n = nums.length;
        int totalScores = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            totalScores += nums[i];
        }
        /**
         * we will try to compute the scoreA of Player 1
         * Player 1 can choose either 0th or (n - 1)th 
         * element from array 'nums'
         */
        int[][] memo = new int[n][n]; // SC : O(n²)
        for (int[] mem : memo) { // TC : O(n²)
            Arrays.fill(mem, -1);
        }
        int scoreA = 
            solveMemoization(0, n - 1, nums, memo); // TC : O(n²), SC : O(n)
        int scoreB = totalScores - scoreA;
        return scoreA >= scoreB;
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(n²)
     * SC : O(n)
     */
    private int solveMemoization(int i, int j, int[] nums, int[][] memo) {
        // Base Case
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return nums[i];
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        /**
         * If Player 1 selects index 'i', then Player 2 can choose
         * any element from [i + 1, j]
         * so if Player 2 chooses:
         * 1. Index '(i + 1)' so, Player 1 can be able to chooose from
         * [i + 2, j]
         * 2. Index 'j' so, Player 1 can be able to chooose from
         * [i + 1, j - 1] and as Player 2 plays optimally too, so
         * Player 1 will get the Minimum of both
         */
        int take_i = nums[i] + 
            Math.min(solveMemoization(i + 2, j, nums, memo),
                     solveMemoization(i + 1, j - 1, nums, memo));
        /**
         * If Player 1 selects index 'j', then Player 2 can choose
         * any element from [i, j - 1]
         * so if Player 2 chooses:
         * 1. Index 'i' so, Player 1 can be able to chooose from
         * [i + 1, j - 1]
         * 2. Index '(j - 1)' so, Player 1 can be able to chooose from
         * [i, j - 2] and as Player 2 plays optimally too, so
         * Player 1 will get the Minimum of both
         */
        int take_j = nums[j] + 
            Math.min(solveMemoization(i + 1, j - 1, nums, memo),
                     solveMemoization(i, j - 2, nums, memo));
        return memo[i][j] = Math.max(take_i, take_j);
    }

    /**
     * Approach I : Using Recursion-I Approach
     *
     * TC : O(n) + O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     */
    public boolean predictTheWinnerRecursionI(int[] nums) {
        int n = nums.length;
        int totalScores = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            totalScores += nums[i];
        }
        /**
         * we will try to compute the scoreA of Player 1
         * Player 1 can choose either 0th or (n - 1)th 
         * element from array 'nums'
         */
        int scoreA = solveRecursion(0, n - 1, nums); // TC : O(2ⁿ), SC : O(n)
        int scoreB = totalScores - scoreA;
        return scoreA >= scoreB;
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int i, int j, int[] nums) {
        // Base Case
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return nums[i];
        }
        // Recursion Calls
        /**
         * If Player 1 selects index 'i', then Player 2 can choose
         * any element from [i + 1, j]
         * so if Player 2 chooses:
         * 1. Index '(i + 1)' so, Player 1 can be able to chooose from
         * [i + 2, j]
         * 2. Index 'j' so, Player 1 can be able to chooose from
         * [i + 1, j - 1] and as Player 2 plays optimally too, so
         * Player 1 will get the Minimum of both
         */
        int take_i = nums[i] + 
            Math.min(solveRecursion(i + 2, j, nums),
                     solveRecursion(i + 1, j - 1, nums));
        /**
         * If Player 1 selects index 'j', then Player 2 can choose
         * any element from [i, j - 1]
         * so if Player 2 chooses:
         * 1. Index 'i' so, Player 1 can be able to chooose from
         * [i + 1, j - 1]
         * 2. Index '(j - 1)' so, Player 1 can be able to chooose from
         * [i, j - 2] and as Player 2 plays optimally too, so
         * Player 1 will get the Minimum of both
         */
        int take_j = nums[j] + 
            Math.min(solveRecursion(i + 1, j - 1, nums),
                     solveRecursion(i, j - 2, nums));
        return Math.max(take_i, take_j);
    }
}
