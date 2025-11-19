class Solution {
    /**
     * Approach I : Using Brute-Force (Hashing) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int findFinalValue(int[] nums, int original) {
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int num : nums) { // TC: O(N)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        while (freqMap.containsKey(original)) { // TC: O(N)
            original *= 2;
        }
        return original;
    }
}
