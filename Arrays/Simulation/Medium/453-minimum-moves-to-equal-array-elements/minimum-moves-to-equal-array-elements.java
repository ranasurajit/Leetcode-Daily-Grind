class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;

        // Find the minimum element
        for (int num : nums) {
            min = Math.min(min, num);
        }

        int moves = 0;

        // Reduce every element conceptually to the minimum
        for (int num : nums) {
            moves += num - min;
        }

        return moves;
    }
}
