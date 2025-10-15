class Solution {
    /**
     * Approach : Using Binary Search + Array Pre-Processing + Simulation Approach
     *
     * TC: O(N) + O(N) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N) + O(N) ~ O(N)
     */
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int[] start = new int[n]; // SC: O(N) - length of increasing sub-array starting at any index 'i'
        int[] end = new int[n];   // SC: O(N) - length of increasing sub-array ending at any index 'i'
        start[n - 1] = 1;
        end[0] = 1;
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            start[i] = nums.get(i) < nums.get(i + 1) ? start[i + 1] + 1 : 1;
        }
        for (int i = 1; i < n; i++) {      // TC: O(N)
            end[i] = nums.get(i) > nums.get(i - 1) ? end[i - 1] + 1 : 1;
        }
        /**
         * the length of the two sub-array can vary from k = 1 to k = n / 2
         */
        int low = 1;
        int high = n / 2;
        int result = 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (canFindAdjacentSubArrays(start, end, n, mid)) { // TC: O(N)
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    /**
     * Using Array Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private boolean canFindAdjacentSubArrays(int[] start, int[] end, int n, int k) {
        for (int i = 0; i + k < n; i++) { // TC: O(N)
            if (start[i + k] >= k && end[i + k - 1] >= k) {
                return true;
            }
        }
        return false;
    }
}
