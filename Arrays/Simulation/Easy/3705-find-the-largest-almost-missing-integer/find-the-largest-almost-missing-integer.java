class Solution {
    /**
     * Approach I : Using Array Simulation + Hashing Approach
     *
     * TC : O(k x (n - k + 1)) ~ O(n²)
     * SC : O(n)
     */
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int maxMissing = -1;
        if (k == n) {
            for (int i = 0; i < n; i++) {
                maxMissing = Math.max(maxMissing, nums[i]);
            }
        }
        /**
         * we will be storing the frequencies of nums[i]
         * in the fixed sliding window of size k 
         */
        Map<Integer, Integer> freqMap = new HashMap<>(); // SC : O(n)
        for (int i = 0; i < n - k + 1; i++) { // TC : O(n - k + 1)
            for (int j = i; j < i + k; j++) { // TC : O(k)
                freqMap.put(nums[j], freqMap.getOrDefault(nums[j], 0) + 1);
            }
        }
        for (Integer key : freqMap.keySet()) { // TC : O(n)
            if (freqMap.get(key) == 1) {
                maxMissing = Math.max(maxMissing, key);
            }
        }
        return maxMissing;
    }
}
