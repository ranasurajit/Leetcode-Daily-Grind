class Solution {
    /**
     * Approach I: Using Simulation Approach
     *
     * TC: O(R), where R is the range = (high - low + 1)
     * SC: O(1)
     */
    public int countOdds(int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if ((i & 1) != 0) {
                count++;
            }
        }
        return count;
    }
}
