class Solution {
    public int sortPermutation(int[] nums) {
        int k = -1;
        boolean misplaced = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                misplaced = true;
                k &= nums[i];
            }
        }
        return misplaced ? k : 0;
    }
}
