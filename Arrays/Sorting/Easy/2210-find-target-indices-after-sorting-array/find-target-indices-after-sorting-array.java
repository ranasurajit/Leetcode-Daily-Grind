class Solution {
    /**
     * Approach : Using Sorting Approach
     *
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(1)
     */
    public List<Integer> targetIndices(int[] nums, int target) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums);            // TC : O(n x log(n))
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (nums[i] == target) {
                result.add(i);
            } else if (nums[i] > target) {
                break;
            }
        }
        return result;
    }
}
