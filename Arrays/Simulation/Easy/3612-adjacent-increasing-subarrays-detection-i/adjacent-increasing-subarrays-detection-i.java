class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(2 x K x (N - K)) ~ O(K x (N - K))
     * SC: O(1)
     */
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        if (n == k) {
            return false;
        }
        if (k == 1) {
            // all sub-arrays with size 1 will always be strictly increasing
            return true;
        }
        for (int i = 0; i < n - k + 1; i++) { // TC: O(N - K)
            if (i + k < n && isStrictlyIncreasing(nums, n, k, i) && 
                isStrictlyIncreasing(nums, n, k, i + k)) { // TC: O(2 x K)
                return true;
            }
        }
        return false;
    }

    /**
     * Using Array Simulation Approach
     *
     * TC: O(K)
     * SC: O(1)
     */
    private boolean isStrictlyIncreasing(List<Integer> nums, int n, int k, int startIdx) {
        for (int i = startIdx + 1; i < startIdx + k; i++) { // TC: O(K)
            if (i >= n || nums.get(i) <= nums.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
