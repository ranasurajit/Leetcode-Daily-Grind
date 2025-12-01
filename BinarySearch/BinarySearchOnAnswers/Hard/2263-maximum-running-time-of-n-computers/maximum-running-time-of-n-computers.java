class Solution {
    /**
     * Approach : Using Binary Search on Answers Approach
     *
     * TC: O(M) + O(M x log(K)) ~ O(M x log(K)), where K = Sum(batteries) / N
     * SC: O(1)
     */
    public long maxRunTime(int n, int[] batteries) {
        int m = batteries.length;
        long sumPower = 0L;
        for (int i = 0; i < m; i++) { // TC: O(M)
            sumPower += (long) batteries[i];
        }
        /**
         * Maximum time in minutes in which computers will last is sumPower / n
         */
        long low = 0;
        long high = sumPower / n;
        long maxTime = 0;
        while (low <= high) { // TC: O(log(K))
            long mid = low + (high - low) / 2;
            if (canRunComputer(mid, batteries, m, n)) { // TC: O(M)
                maxTime = mid;
                // we need to maximize
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return maxTime;
    }

    /**
     * Using Array Simulation Approach
     *
     * TC: O(M)
     * SC: O(1)
     */
    private boolean canRunComputer(long target, int[] batteries, int m, int n) {
        long totalPowerServed = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            totalPowerServed += Math.min(batteries[i], target);
        }
        return totalPowerServed >= target * (long) n;
    }
}
