class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    public int minMoves(int[] nums) {
        int max = 0;
        for (int num : nums) { // TC : O(n)
            max = Math.max(max, num);
        }
        int moves = 0;
        for (int num : nums) { // TC : O(n)
            moves += (max - num);
        }
        return moves;
    }
}
