class Solution {
    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(N²)
     * SC: O(1)
     */
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(N)
            /**
             * To satisfy the condition of ans[i] OR ans[i] + 1 = nums.get(i),
             * ans[i] cannot exceed nums.get(i)
             */
            boolean found = false;
            for (int j = 0; j <= nums.get(i); j++) { // TC: O(N)
                if ((j | (j + 1)) == nums.get(i)) {
                    result[i] = j;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result[i] = -1;
            }
        }
        return result;
    }
}
