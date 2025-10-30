class Solution {
    /**
     * Approach : Using Greedy Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public long minimumOperations(int[] nums, int[] target) {
        int n = nums.length;
        long[] diff = new long[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            diff[i] = (long) target[i] - (long) nums[i];
        }
        long prev = 0L;
        long operations = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            long current = (long) diff[i];
            if ((prev < 0 && current > 0) || (prev > 0 && current < 0)) {
                /**
                 * if prev and current are of different signs, 
                 * then we need to increment/decrement separately
                 */
                operations += Math.abs(current);
            } else {
                if (Math.abs(current) - Math.abs(prev) > 0) {
                    operations += Math.abs(current) - Math.abs(prev);
                }
            }
            prev = current;
        }
        return operations;
    }
}
