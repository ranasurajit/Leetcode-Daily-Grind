class Solution {
    /**
     * Approach II : Using Optimal (Sliding Window + Hashing) Approach
     *
     * TC : O(n)
     * SC : O(n)
     *
     * Accepted (994 / 994 testcases passed)
     */
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Map<Integer, Integer> freqMap = new HashMap<>(); // SC : O(n)
        int maxLength = 0;
        while (j < n) { // TC : O(n)
            freqMap.put(nums[j], freqMap.getOrDefault(nums[j], 0) + 1);
            /**
             * we don't need to iterate over HashMap to check if any
             * elements increase frequency affected the count > k
             * instead the element that was added to 'freqMap' is
             * nums[j] so we need to check only it's frequency if it 
             * exceeded k
             */
            while (freqMap.get(nums[j]) > k) { // TC : O(1)
                // we need to remove the computation from index 'i'
                int freq = freqMap.get(nums[i]);
                if (freq == 1) {
                    freqMap.remove(nums[i]);
                } else {
                    freqMap.put(nums[i], freq - 1);
                }
                i++;
            }
            // by this line we have all elements frequency <= k
            maxLength = Math.max(maxLength, j - i + 1);
            j++;
        }
        return maxLength;
    }

    /**
     * Approach I : Using Brute-Force (Sliding Window + Hashing) Approach
     *
     * TC : O(n x k)
     * SC : O(n)
     *
     * Time Limit Exceeded (875 / 994 testcases passed)
     */
    public int maxSubarrayLengthBruteForce(int[] nums, int k) {
        int n = nums.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Map<Integer, Integer> freqMap = new HashMap<>(); // SC : O(n)
        int maxLength = 0;
        while (j < n) { // TC : O(n)
            freqMap.put(nums[j], freqMap.getOrDefault(nums[j], 0) + 1);
            while (hasGreaterFrequency(freqMap, k)) { // TC : O(k)
                // we need to remove the computation from index 'i'
                int freq = freqMap.get(nums[i]);
                if (freq == 1) {
                    freqMap.remove(nums[i]);
                } else {
                    freqMap.put(nums[i], freq - 1);
                }
                i++;
            }
            // by this line we have all elements frequency <= k
            maxLength = Math.max(maxLength, j - i + 1);
            j++;
        }
        return maxLength;
    }

    /**
     * Using Hashing Approach
     *
     * TC : O(k)
     * SC : O(1)
     */
    private boolean hasGreaterFrequency(Map<Integer, Integer> freqMap, int k) {
        for (Integer freq : freqMap.values()) { // TC : O(k)
            if (freq > k) {
                return true;
            }
        }
        return false;
    }
}
