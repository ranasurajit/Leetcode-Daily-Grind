class Solution {
    /**
     * Approach : Using Binary Search on Answers Approach
     *
     * TC: O(N) + O(N x log(M))
     * SC: O(1)
     */
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int n = workerTimes.length;
        /**
         * this looks like a minimize the max time taken by workers so
         * it is hinting towards usage of Binary Search on Answers
         */
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(N)
            min = Math.min(min, (long) workerTimes[i]);
        }
        long low = 0L;
        long high = min * (((long) mountainHeight * ((long) mountainHeight + 1)) / 2);
        long result = 0L;
        while (low <= high) { // TC: O(log(M))
            long mid = low + (high - low) / 2;
            if (canBeDone(workerTimes, mountainHeight, n, mid)) { // TC: O(N)
                // minimize the time
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    /**
     * Using Math + Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private boolean canBeDone(int[] workerTimes, int mountainHeight, int n, long mid) {
        long xsum = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            /**
             * we need to find out x where x² + x - 2 * k = 0
             * where k =  mid / workerTimes[i]
             * x = (-1 + Sqrt(1 + 8 * k)) / 2
             */
            long k = mid / workerTimes[i];
            xsum += (long) (Math.sqrt(1 + 8 * k) - 1) / 2;
            if (xsum >= mountainHeight) {
                return true;
            }
        }
        return false;
    }
}
