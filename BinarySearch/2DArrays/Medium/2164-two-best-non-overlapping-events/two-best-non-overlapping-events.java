class Solution {
    private int n;
    /**
     * Approach IV : Using Greedy + Sorting + Min-Heap Approach
     *
     * TC: O(N x log(N)) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N)
     * - O(N) - min-heap memory
     *
     * Accepted (64 / 64 testcases passed)
     */
    public int maxTwoEvents(int[][] events) {
        this.n = events.length;
        Arrays.sort(events, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0]; // sort by start time
            }
            if (a[1] != b[1]) {
                return a[1] - b[1]; // sort by end time
            }
            return a[2] - b[2]; // sort by value
        }); // TC: O(N x log(N))
        // we will store { endTime, value } in Min-Heap order by endTime
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(N)
        int maxValue = 0;
        int result = 0;
        for (int[] currentEvent : events) { // TC: O(N)
            int start = currentEvent[0];
            int end = currentEvent[1];
            int value = currentEvent[2];
            while (!pq.isEmpty() && start > pq.peek()[0]) {
                maxValue = Math.max(maxValue, pq.poll()[1]);
            }
            result = Math.max(result, maxValue + value);
            pq.offer(new int[] { end, value }); // TC: O(log(N))
        }
        return result;
    }

    /**
     * Approach III : Using Sorting + Prefix Max + Binary Search Approach
     *
     * TC: O(N x log(N)) + O(N) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N)
     * - O(N) - prefix max array memory
     *
     * Accepted (64 / 64 testcases passed)
     */
    public int maxTwoEventsBinarySearch(int[][] events) {
        this.n = events.length;
        Arrays.sort(events, (a, b) -> a[1] - b[1]); // TC: O(N x log(N))
        /**
         * here we have maximum two choices of events so prevIndex is not 
         * needed, instead we should check for maximum value that we can
         * get before current index start time
         */
        int[] maxSumTillIndex = new int[n]; // SC: O(N)
        maxSumTillIndex[0] = events[0][2];
        for (int i = 1; i < n; i++) { // TC: O(N)
            maxSumTillIndex[i] = Math.max(maxSumTillIndex[i - 1], events[i][2]);
        }
        int maxValue = Integer.MIN_VALUE; 
        for (int i = 0; i < n; i++) { // TC: O(N)
            int j = previousNonOverlappingEvent(events, i); // TC: O(log(N))
            int currentMax = events[i][2] + (j >= 0 ? maxSumTillIndex[j] : 0);
            maxValue = Math.max(maxValue, currentMax);
        }
        return maxValue;
    }

    /**
     * Using Binary Search Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    private int previousNonOverlappingEvent(int[][] events, int idx) {
        if (idx == 0) {
            return -1;
        }
        int low = 0;
        int high = idx - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (events[mid][1] < events[idx][0]) {
                // we need the max index satisfying this condition
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x N) + O(N x log(N)) ~ O(N x N)
     * SC: O(N x N) + O(N)
     * - O(N x N) - memoization array memory 
     * - O(N) - recursion stack memory
     *
     * Memory Limit Exceeded (35 / 64 testcases passed)
     */
    public int maxTwoEventsMemoization(int[][] events) {
        this.n = events.length;
        Arrays.sort(events, (a, b) -> a[1] - b[1]); // TC: O(N x log(N))
        int[][][] memo = new int[n][n + 1][3]; // SC: O(N x N x 3) ~ O(N x N)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        return solveMemoization(0, -1, events, 2, memo); // TC: O(N x N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N x 3) ~ O(N x N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int prevIdx, int[][] events, 
        int remaining, int[][][] memo) {
        // Base Case
        if (idx == n || remaining == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][prevIdx + 1][remaining] != -1) {
            return memo[idx][prevIdx + 1][remaining];
        }
        // Recursion Calls - pick or skip
        int skip = solveMemoization(idx + 1, prevIdx, events, remaining, memo);
        int pick = 0;
        if (prevIdx == -1 || events[idx][0] > events[prevIdx][1]) {
            pick = events[idx][2] + solveMemoization(idx + 1, idx, events, remaining - 1, memo);
        }
        return memo[idx][prevIdx + 1][remaining] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N) + O(N x log(N)) ~ O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack memory
     *
     * Time Limit Exceeded (40 / 64 testcases passed)
     */
    public int maxTwoEventsRecursion(int[][] events) {
        this.n = events.length;
        Arrays.sort(events, (a, b) -> a[1] - b[1]); // TC: O(N x log(N))
        return solveRecursion(0, -1, events, 2); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int prevIdx, int[][] events, int remaining) {
        // Base Case
        if (idx == n || remaining == 0) {
            return 0;
        }
        // Recursion Calls - pick or skip
        int skip = solveRecursion(idx + 1, prevIdx, events, remaining);
        int pick = 0;
        if (prevIdx == -1 || events[idx][0] > events[prevIdx][1]) {
            pick = events[idx][2] + solveRecursion(idx + 1, idx, events, remaining - 1);
        }
        return Math.max(pick, skip);
    }
}
