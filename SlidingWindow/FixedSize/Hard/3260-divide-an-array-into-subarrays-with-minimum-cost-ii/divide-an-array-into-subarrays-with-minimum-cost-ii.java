class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) + Ordered Set Approach
     *
     * TC: O(K) + O(N - K) ~ O(N)
     * SC: O(K) + O(K) ~ O(K)
     */
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        /**
         * 1st sub-array will always start from index 0, so we
         * need to pick the start of (k - 1) sub-arrays such that
         * kth sub-array start index <= dist + 1, so we bascially
         * need to compute (k - 1) smallest elements that can
         * be the potential start indices of (k - 1) sub-arrays
         * so for storing that we need a Sorted Set sorted in 
         * ascending order of values and another Sorted Set 
         * sorted in ascending order to store removed ones
         */
        TreeSet<int[]> kMins = new TreeSet<int[]>((p, q) -> {
            if (p[0] != q[0]) {
                return Integer.compare(p[0], q[0]);
            }
            return Integer.compare(p[1], q[1]);
        }); // SC: O(K)
        TreeSet<int[]> remaining = new TreeSet<int[]>((p, q) -> {
            if (p[0] != q[0]) {
                return Integer.compare(p[0], q[0]);
            }
            return Integer.compare(p[1], q[1]);
        }); // SC: O(K)
        // as ik-1 - i1 <= dist, so sliding window size = dist + 1
        int i = 1;
        // preparing first window
        long result = Long.MAX_VALUE;
        long sum = 0L;
        while (i < n && i - 1 < dist) { // TC: O(K)
            int[] current = new int[] { nums[i], i };
            sum += (long) current[0];
            kMins.add(current);
            if (kMins.size() > k - 1) {
                // remove it from kMins and sum then add it to remaining
                int[] removed = kMins.pollLast();
                sum -= removed[0];
                remaining.add(removed);
            }
            i++;
        }
        while (i < n) { // TC: O(N - K)
            int[] current = new int[] { nums[i], i };
            sum += (long) current[0];
            kMins.add(current);
            if (kMins.size() > k - 1) {
                // remove it from kMins and sum then add it to remaining
                int[] removed = kMins.pollLast();
                sum -= removed[0];
                remaining.add(removed);
            }
            result = Math.min(result, sum);
            // remove computation from index out of sliding window
            int redundantIdx = i - dist;
            int[] redundant = new int[] { nums[redundantIdx], redundantIdx };
            if (kMins.remove(redundant)) {
                sum -= redundant[0];
                if (!remaining.isEmpty()) {
                    int[] promote = remaining.pollFirst();
                    kMins.add(promote);
                    sum += promote[0];
                }
            } else {
                remaining.remove(redundant);
            }
            i++;
        }
        return (long) nums[0] + result;
    }
}
