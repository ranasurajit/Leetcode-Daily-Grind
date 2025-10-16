class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(N) + O(N) + O(Max(nums / value)) ~ O(N)
     * SC: O(N)
     */
    public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int rem = nums[i] % value;
            if (rem < 0) {
                rem = value + rem;
            }
            freqMap.put(rem, freqMap.getOrDefault(rem, 0) + 1);
        }
        int x = 0;
        while (true) {
            int rem = x % value;
            if (freqMap.getOrDefault(rem, 0) == 0) {
                return x;
            }
            freqMap.put(rem, freqMap.get(rem) - 1);
            x++;
        }
    }
}
