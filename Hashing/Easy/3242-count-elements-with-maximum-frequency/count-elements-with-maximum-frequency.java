class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int maxFrequencyElements(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int maxFreq = 0;
        for (Integer key : map.keySet()) { // TC: O(N)
            maxFreq = Math.max(maxFreq, map.get(key));
        }
        int count = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (map.get(nums[i]) == maxFreq) {
                count++;
            }
        }
        return count;
    }
}
