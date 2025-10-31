class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length;
        int[] result = { -1, -1 };
        int[] map = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            map[nums[i]]++;
            if (map[nums[i]] > 1) {
                if (result[0] == -1) {
                    result[0] = nums[i];
                } else {
                    result[1] = nums[i];
                }
            }
        }
        return result;
    }
}
