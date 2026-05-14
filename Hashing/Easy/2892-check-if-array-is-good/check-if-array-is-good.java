class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC : O(n) + O(n) + O(n) ~ O(n)
     * SC : O(n)
     */
    public boolean isGood(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {     // TC : O(n)
            max = Math.max(max, nums[i]);
        }
        if (n <= max) {
            // cannot be a good array
            return false;
        }
        int[] base = new int[n];          // SC : O(n)
        for (int i = 0; i < n; i++) {     // TC : O(n)
            base[nums[i]]++;
        }
        for (int i = 1; i < n - 1; i++) { // TC : O(n)
            if (base[i] != 1) {
                return false;
            }
        }
        return base[n - 1] == 2;
    }
}
