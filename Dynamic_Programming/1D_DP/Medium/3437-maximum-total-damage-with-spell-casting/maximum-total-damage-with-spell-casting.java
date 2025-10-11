class Solution {
    private static Map<Long, Long> freqMap;

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N x log(N))
     * SC: O(N) + O(N) + O(N) ~ O(N)
     *
     * Accepted (554 / 554 testcases passed)
     */
    public long maximumTotalDamage(int[] power) {
        this.freqMap = new HashMap<Long, Long>(); // SC: O(N)
        for (int x : power) {    // TC: O(N)
            freqMap.put((long) x, freqMap.getOrDefault((long) x, 0L) + 1);
        }
        List<Long> keys = new ArrayList<Long>(freqMap.keySet()); // SC: O(N)
        Collections.sort(keys);  // TC: O(N x log(N))
        int n = keys.size();
        long[] dp = new long[n]; // SC: O(N)
        long result = Long.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            long skip = i + 1 < n ? dp[i + 1] : 0;
            int j = lowerBound(keys, n, i + 1, keys.get(i) + 3); // TC: O(log(N))
            long pick = (long) freqMap.get(keys.get(i)) * (long) keys.get(i) + ((j < n) ? dp[j] : 0);
            dp[i] = Math.max(pick, skip);
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x log(N))
     * SC: O(N) + O(N) + O(N) ~ O(N)
     *
     * Accepted (554 / 554 testcases passed)
     */
    public long maximumTotalDamageMemoization(int[] power) {
        this.freqMap = new HashMap<Long, Long>(); // SC: O(N)
        for (int x : power) { // TC: O(N)
            freqMap.put((long) x, freqMap.getOrDefault((long) x, 0L) + 1);
        }
        List<Long> keys = new ArrayList<Long>(freqMap.keySet()); // SC: O(N)
        Collections.sort(keys); // TC: O(N x log(N))
        int n = keys.size();
        long[] memo = new long[n]; // SC: O(N)
        Arrays.fill(memo, -1L);
        return solveMemoization(0, n, keys, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x log(N))
     * SC: O(N)
     */
    private long solveMemoization(int idx, int n, List<Long> keys, long[] memo) {
        // Base Case
        if (idx >= n) {
            return 0L;
        }
        if (memo[idx] != -1L) {
            return memo[idx];
        }
        // Recursion Calls
        // skip or pick
        // skip
        long skip = solveMemoization(idx + 1, n, keys, memo);
        /**
         * if index 'idx' is chosen then next index chosen will be 'j' 
         * where jth index will be having power[idx] + 3
         */
        int j = lowerBound(keys, n, idx + 1, keys.get(idx) + 3); // TC: O(log(N))
        long currentPower = (long) freqMap.get(keys.get(idx)) * (long) keys.get(idx);
        long pick = currentPower + solveMemoization(j, n, keys, memo);
        return memo[idx] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(log(N) x 2 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (504 / 554 testcases passed)
     */
    public long maximumTotalDamageRecursion(int[] power) {
        this.freqMap = new HashMap<Long, Long>();
        for (int x : power) {
            freqMap.put((long) x, freqMap.getOrDefault((long) x, 0L) + 1);
        }
        List<Long> keys = new ArrayList<Long>(freqMap.keySet());
        Collections.sort(keys);
        int n = keys.size();
        return solveRecursion(0, n, keys);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(log(N) x 2 ^ N)
     * SC: O(N)
     */
    private long solveRecursion(int idx, int n, List<Long> keys) {
        // Base Case
        if (idx >= n) {
            return 0L;
        }
        // Recursion Calls
        // skip or pick
        // skip
        long skip = solveRecursion(idx + 1, n, keys);
        /**
         * if index 'idx' is chosen then next index chosen will be 'j' 
         * where jth index will be having power[idx] + 3
         */
        int j = lowerBound(keys, n, idx + 1, keys.get(idx) + 3); // TC: O(log(N))
        long currentPower = (long) freqMap.get(keys.get(idx)) * (long) keys.get(idx);
        long pick = currentPower + solveRecursion(j, n, keys);
        return Math.max(pick, skip);
    }

    /**
     * Using Binary Search Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    private int lowerBound(List<Long> keys, int n, int start, long target) {
        int low = start;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (keys.get(mid) >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
