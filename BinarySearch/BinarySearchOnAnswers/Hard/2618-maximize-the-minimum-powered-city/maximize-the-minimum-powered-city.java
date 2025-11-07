class Solution {
    /**
     * Approach : Binary Search on Answers Approach
     *
     * TC: O(N) + O(N) + O(N x log(K)) ~ O(N x log(K)), where K = Max(stations)
     * SC: O(N) + O(N) ~ O(N)
     */
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long low = 0L;
        long high = 0L;
        long[] prefixSum = new long[n]; // SC: O(N)
        prefixSum[0] = (long) stations[0];
        for (int i = 0; i < n; i++) { // TC: O(N)
            high = Math.max(high, (long) stations[i]);
            if (i > 0) {
                prefixSum[i] = prefixSum[i - 1] + (long) stations[i];
            }
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            int left = Math.max(0, i - r);
            int right = Math.min(n - 1, i + r);
            long base = prefixSum[right] - (left > 0 ? prefixSum[left - 1] : 0L);
            high = Math.max(high, base);
        }
        high += k;
        // Applying Binary Search on Answers
        long minPower = 0L;
        while (low <= high) { // TC: O(log(K)), where K = Max(stations)
            long mid = low + (high - low) / 2;
            if (isMaximumPowerPossible(mid, prefixSum, r, (long) k, n)) { // TC: O(N), SC: O(N)
                // try to maximize power
                minPower = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return minPower;
    }

    /**
     * Using Difference Array Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private boolean isMaximumPowerPossible(long target, long[] prefixSum, int r, long k, int n) {
        long[] diff = new long[n + 1]; // SC: O(N)
        long windowAdd = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            windowAdd += diff[i];
            int left = Math.max(0, i - r);
            int right = Math.min(n - 1, i + r);
            long base = prefixSum[right] - (left > 0 ? prefixSum[left - 1] : 0L);
            if (base + windowAdd < target) {
                long need = target - base - windowAdd;
                if (need > k) {
                    // achieving target is not possible
                    return false;
                }
                k = k - (int) need;
                windowAdd += need;
                int end = Math.min(n - 1, i + 2 * r);
                if (end + 1 <= n) {
                    diff[end + 1] -= need;
                }
            }
        }
        return true;
    }
}
