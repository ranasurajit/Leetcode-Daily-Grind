class Solution {
    /**
     * Approach II: Using Optimal (Hash Array) Approach
     *
     * TC: O(N)
     * SC: O(1001) ~ O(1)
     */
    public int findFinalValue(int[] nums, int original) {
        int[] freq = new int[1001]; // SC: O(1001) as from constraints, 1 <= nums[i], original <= 1000
        for (int num : nums) { // TC: O(N)
            freq[num]++;
        }
        while (original < freq.length && freq[original] != 0) { // TC: O(1000) ~ O(1)
            freq[original]--;
            original *= 2;
        }
        return original;
    }

    /**
     * Approach I : Using Brute-Force (Hashing) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int findFinalValueBruteForce(int[] nums, int original) {
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int num : nums) { // TC: O(N)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        while (freqMap.containsKey(original)) { // TC: O(N)
            freqMap.put(original, freqMap.getOrDefault(original, 0) - 1);
            if (freqMap.get(original) == 0) {
                freqMap.remove(original);
            }
            original *= 2;
        }
        return original;
    }
}
