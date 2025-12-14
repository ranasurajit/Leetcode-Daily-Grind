class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach : Using Greedy Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(1)
     */
    public int numberOfWays(String corridor) {
        int n = corridor.length();
        int countPlants = 0;
        int countSeats = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (corridor.charAt(i) == 'S') {
                countSeats++;
            }
        }
        if (countSeats == 0 || (countSeats & 1) != 0) {
            /**
             * if total seats is odd we cannot divide it into 
             * partitions with each partition having exactly 2 
             * seats, so immediately return 0
             */
            return 0;
        }
        countSeats = 0;
        long ways = 1L;
        boolean isFirstSection = true;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (corridor.charAt(i) == 'P') {
                if (countSeats == 2) {
                    countPlants++;
                }
            } else {
                if (!isFirstSection && countSeats == 2) {
                    /**
                     * we can start partition here based on number of 
                     * plants before starting next set of two seats
                     * we can multiply with all previous ways (if no previous
                     * section found, then previous ways = 1)
                     */
                    ways = (ways * (countPlants + 1)) % MOD;
                    countSeats = 0;
                    countPlants = 0;
                }
                isFirstSection = false;
                countSeats++;
            }
        }
        return (int) ways;
    }
}
