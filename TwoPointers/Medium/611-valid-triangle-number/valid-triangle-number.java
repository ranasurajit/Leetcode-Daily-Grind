class Solution {
    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(N x log(N)) + O(N x N) ~ O(N x N)
     * SC: O(1)
     */
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        // order does not matter so sorting the nums
        Arrays.sort(nums); // TC: O(N x log(N))
        int count = 0;
        for (int k = n - 1; k >= 2; k--) { // TC: O(N)
            int i = 0;
            int j = k - 1;
            // Using Two Pointers Approach
            while (i < j) { // TC: O(N)
                if (nums[i] + nums[j] > nums[k]) {
                    count += j - i;
                    j--;
                } else {
                    i++;
                }
            }
        }
        return count;
    }
}
