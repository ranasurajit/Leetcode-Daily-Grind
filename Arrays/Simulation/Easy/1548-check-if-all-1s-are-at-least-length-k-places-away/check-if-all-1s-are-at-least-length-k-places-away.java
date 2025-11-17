class Solution {
    /**
     * Approach I : Using Brute-Force (Extra Space for Indices) Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;
        List<Integer> listOfOnesIndices = new ArrayList<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (nums[i] == 1) {
                listOfOnesIndices.add(i);
            }
        }
        int lastIdx = -1;
        for (Integer idx : listOfOnesIndices) { // TC: O(N)
            if (lastIdx == -1) {
                lastIdx = idx;
            } else {
                if (idx - lastIdx - 1 < k) {
                    return false;
                }
                lastIdx = idx;
            }
        }
        return true;
    }
}
