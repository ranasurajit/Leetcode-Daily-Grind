class Solution {
    /**
     * Approach : Using Binary Search on Answers Approach
     *
     * TC : O(n x log(n)) + O(n) + O(n x log(k)) ~ O(n x (log(n) + log(k)))
     * SC : O(1)
     */
    public int minimumEffort(int[][] tasks) {
        int n = tasks.length;
        /**
         * we can greedily try to pick the task with
         * maximum difference (minimum - actual)
         * so, we can sort tasks in descending order
         * of (minimum - actual) energies needed
         */
        Arrays.sort(tasks, (a, b) -> {
            return (b[1] - b[0]) - (a[1] - a[0]);
        }); // TC : O(n x log(n))
        long low = 0L;
        long high = 0L; 
        for (int[] task : tasks) { // TC : O(n)
            high += task[1];
        }
        long minEnergy = high;
        while (low <= high) { // TC : O(log(k))
            long mid = low + (high - low) / 2;
            if (canTasksBeFinished(tasks, mid)) { // TC : O(n)
                minEnergy = Math.min(minEnergy, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int) minEnergy;
    }

    /**
     * Using Simulation Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    private boolean canTasksBeFinished(int[][] tasks, long mid) {
        for (int[] task : tasks) {
            if (mid >= task[1]) {
                mid -= task[0];
            } else {
                return false;
            }
        }
        return true;
    }
}
