class Solution {
    /**
     * Approach II : Using Optimal Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;
        int lastIdx = -1;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (nums[i] == 1) {
                if (lastIdx == -1) {
                    lastIdx = i;
                } else {
                    if (i - lastIdx - 1 < k) {
                        return false;
                    }
                    lastIdx = i;
                }
            }
        }
        return true;
    }

    /**
     * Approach I : Using Brute-Force (Extra Space for Indices) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public boolean kLengthApartBruteForce(int[] nums, int k) {
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
