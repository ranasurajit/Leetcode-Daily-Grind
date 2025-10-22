class Solution {
    /**
     * Approach : Using Difference Array Approach
     *
     * TC: O(N) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N) + O(N) + O(N) + O(N) ~ O(N)
     */
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();       // SC: O(N)
        TreeMap<Integer, Integer> events = new TreeMap<Integer, Integer>(); // SC: O(N)
        Set<Integer> candidates = new HashSet<Integer>();                   // SC: O(N)
        // Using Difference Array Approach using TreeMap (Sorted Time-Series)
        for (int num : nums) { // TC: O(N)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            events.put(num - k, events.getOrDefault(num - k, 0) + 1);
            events.put(num + k + 1, events.getOrDefault(num + k + 1, 0) - 1);
            candidates.add(num - k);
            candidates.add(num);
            candidates.add(num + k + 1);
        }
        List<Integer> sortedCandidates = new ArrayList<Integer>(candidates); // SC: O(N)
        Collections.sort(sortedCandidates); // TC: O(N x log(N))
        int targetConversions = 0;
        int maxFreq = 0;
        for (Integer x : sortedCandidates) { // TC: O(N)
            targetConversions += events.getOrDefault(x, 0);
            int targetCount = freq.getOrDefault(x, 0);
            int operationsNeeded = targetConversions - targetCount;
            int maxFreqSupported = Math.min(operationsNeeded, numOperations);
            maxFreq = Math.max(maxFreq, targetCount + maxFreqSupported);
        }
        return maxFreq;
    }
}
