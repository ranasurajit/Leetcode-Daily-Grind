class Solution {
    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(n) + O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     */
    public boolean predictTheWinner(int[] nums) {
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
