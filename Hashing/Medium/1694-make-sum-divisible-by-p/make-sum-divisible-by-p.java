class Solution {
    /**
     * Approach : Using Array Simulation + Hashing Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long prefixSum = 0L;
        for (int num : nums) { // TC: O(N)
            prefixSum += (long) num;
        }
        long target = prefixSum % p;
        if (target == 0) {
            return 0;
        }
        /**
         * now we need to find the minimum sub-array length 
         * whose (prefix sum - target) % p is observed last
         */
        prefixSum = 0L;
        int minLength = n;
        Map<Long, Integer> map = new HashMap<Long, Integer>(); // SC: O(N)
        map.put(0L, -1);
        for (int i = 0; i < n; i++) { // TC: O(N)
            prefixSum = (prefixSum + (long) nums[i]) % p;
            long rem = (prefixSum - target + p) % p;
            if (map.containsKey(rem)) {
                minLength = Math.min(minLength, i - map.get(rem));
            }
            map.put(prefixSum, i);
        }
        return minLength == n ? -1 : minLength;
    }
}
