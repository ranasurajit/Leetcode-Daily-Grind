class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int maxDistinctElements(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums); // TC: O(N x log(N))
        long lastUsed = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            long left = (long) nums[i] - k;
            long right = (long) nums[i] + k;
            long placed = Math.max(lastUsed + 1, left);
            if (placed <= right) {
                count++;
                lastUsed = placed;
            }
        }
        return count;
    }
}
