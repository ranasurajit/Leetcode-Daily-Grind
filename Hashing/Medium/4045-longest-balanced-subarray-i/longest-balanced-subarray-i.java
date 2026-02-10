class Solution {
    /**
     * Approach : Using Two Pointers + Hashing Approach
     *
     * TC: O(N²)
     * SC: O(N1 + N2) ~ O(N)
     */
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> evenMap = new HashMap<Integer, Integer>(); // SC: O(N1)
        Map<Integer, Integer> oddMap = new HashMap<Integer, Integer>();  // SC: O(N2)
        int maxLength = 0;
        for (int i = 0; i < n; i++) {     // TC: O(N)
            evenMap.clear();
            oddMap.clear();
            for (int j = i; j < n; j++) { // TC: O(N)
                if ((nums[j] & 1) == 0) {
                    evenMap.put(nums[j], evenMap.getOrDefault(nums[j], 0) + 1);
                } else {
                    oddMap.put(nums[j], oddMap.getOrDefault(nums[j], 0) + 1);
                }
                if (evenMap.size() == oddMap.size()) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }
}
