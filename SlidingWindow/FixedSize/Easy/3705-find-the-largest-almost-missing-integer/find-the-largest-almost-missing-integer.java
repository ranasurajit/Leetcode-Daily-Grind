class Solution {
    /**
     * Approach II : Using Sliding Window (Fixed Size) + Hashing Approach
     *
     * TC : O(k x n) ~ O(n²)
     * SC : O(n) + O(n) ~ O(n)
     */
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int maxMissing = -1;
        /**
         * we will be storing the frequencies of nums[i]
         * in the fixed sliding window of size k 
         */
        Map<Integer, Integer> windowFreq = new HashMap<>(); // SC : O(n)
        Map<Integer, Integer> windowCount = new HashMap<>(); // SC : O(n)
        /**
         * performing 1st window pass
         */
        for (int i = 0; i < k; i++) { // TC : O(k)
            windowFreq.put(nums[i], windowFreq.getOrDefault(nums[i], 0) + 1);
        }
        for (Integer key : windowFreq.keySet()) { // TC : O(k)
            windowCount.put(key, 1);
        }
        int i = 1; // start pointer of sliding window (after 1st pass) 
        int j = k; // end pointer of sliding window (after 1st pass) 
        while (j < n) {
            /**
             * here the window size is k, so we need to remove 
             * computation from index 'i' before adding element at
             * index 'j'
             */
            int freq = windowFreq.get(nums[i - 1]);
            if (freq == 1) {
                windowFreq.remove(nums[i - 1]);
            } else {
                windowFreq.put(nums[i - 1], freq - 1);
            }
            /**
             * now adding computation for index 'j'
             */
            windowFreq.put(nums[j], windowFreq.getOrDefault(nums[j], 0) + 1);
            for (Integer key : windowFreq.keySet()) { // TC : O(k)
                windowCount.put(key, windowCount.getOrDefault(key, 0) + 1);
            }
            i++;
            j++;
        }
        for (Integer key : windowCount.keySet()) { // TC : O(n)
            if (windowCount.get(key) == 1) {
                maxMissing = Math.max(maxMissing, key);
            }
        }
        return maxMissing;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation + Hashing) Approach
     *
     * TC : O(k x (n - k + 1)) ~ O(n²)
     * SC : O(n) + O(k) ~ O(n)
     */
    public int largestIntegerBruteForce(int[] nums, int k) {
        int n = nums.length;
        int maxMissing = -1;
        /**
         * we will be storing the frequencies of nums[i]
         * in the fixed sliding window of size k 
         */
        Map<Integer, Integer> freqMap = new HashMap<>(); // SC : O(n)
        for (int i = 0; i < n - k + 1; i++) {   // TC : O(n - k + 1)
            Set<Integer> set = new HashSet<>(); // SC : O(k)
            for (int j = i; j < i + k; j++) {   // TC : O(k)
                set.add(nums[j]);
            }
            for (Integer num : set) {
                freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
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
