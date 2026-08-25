class Solution {
    /**
     * Approach : Using Array Simulation + Hashing Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int missingMultiple(int[] nums, int k) {
        int n = nums.length;
        int max = ((100 / k) + 1) * k;
        /**
         * we can store the frequencies of elements
         * in the map so that we can track the
         * multiples of K i.e absent in it
         */
        int[] map = new int[max + 1]; // SC : O(max)
        for (int i = 0; i < n; i++) { // TC : O(n)
            map[nums[i]]++;
        }
        for (int i = k; i <= max; i += k) { // TC : O(max / k)
            if (map[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
