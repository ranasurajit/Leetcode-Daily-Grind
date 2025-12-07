class Solution {
    /**
     * Approach II : Using Math Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int countOdds(int low, int high) {
        boolean isStartOdd = (low & 1) == 1;
        boolean isEndOdd = (high & 1) == 1;
        int range = high - low + 1;
        if (isStartOdd && isEndOdd) {
            return (range / 2) + 1;
        }
        return range / 2;
    }

    /**
     * Approach I : Using Simulation Approach
     *
     * TC: O(R), where R is the range = (high - low + 1)
     * SC: O(1)
     */
    public int countOddsBruteForce(int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if ((i & 1) != 0) {
                count++;
            }
        }
        return count;
    }
}
