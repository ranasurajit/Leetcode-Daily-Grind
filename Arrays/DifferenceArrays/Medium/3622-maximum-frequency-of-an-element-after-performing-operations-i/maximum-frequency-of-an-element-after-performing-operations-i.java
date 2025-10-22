class Solution {
    /**
     * Approach : Using Difference Array Approach
     *
     * TC: O(N) + O(N) + O(Max(nums)) ~ O(Max(nums))
     * SC: O(N) + O(Max(nums)) ~ O(Max(nums))
     */
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int maxVal = Integer.MIN_VALUE;
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int num : nums) { // TC: O(N)
            maxVal = Math.max(maxVal, num);
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        // Applying Difference Array Technique
        int[] diff = new int[maxVal + 2]; // SC: O(Max(nums))
        for (int num : nums) { // TC: O(N)
            int left = Math.max(0, num - k);
            int right = Math.min(maxVal, num + k);
            diff[left]++;
            diff[right + 1]--;
        }
        int maxFreq = 0;
        for (int target = 0; target < diff.length; target++) { // TC: O(Max(nums))
            diff[target] += target > 0 ? diff[target - 1] : 0; // cumulative sum
            int targetFreq = freq.getOrDefault(target, 0);
            int operationsNeeded = diff[target] - targetFreq;
            int maxPossibleFreq = Math.min(operationsNeeded, numOperations);
            maxFreq = Math.max(maxFreq, targetFreq + maxPossibleFreq);
        }
        return maxFreq;
    }
}
