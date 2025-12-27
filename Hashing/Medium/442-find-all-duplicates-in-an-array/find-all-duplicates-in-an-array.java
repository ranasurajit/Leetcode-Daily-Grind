class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public List<Integer> findDuplicates(int[] nums) {
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
