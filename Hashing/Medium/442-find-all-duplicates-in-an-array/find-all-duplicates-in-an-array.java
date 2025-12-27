class Solution {
    /**
     * Approach II : Using Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> duplicates = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                duplicates.add(Math.abs(nums[i]));
            } else {
                nums[index] = -nums[index];
            }
        }
        return duplicates;
    }

    /**
     * Approach I : Using Hashing Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public List<Integer> findDuplicatesHashingApproach(int[] nums) {
        List<Integer> duplicates = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>(); // SC: O(N)
        for (int num : nums) { // TC: O(N)
            if (!set.add(num)) {
                duplicates.add(num);
            }
        }
        return duplicates;
    }
}
