class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC : O(n) + O(r)
     * SC : O(n)
     * where r = max - min + 1
     */
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> missingList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>(); // SC : O(n)
        for (int x : nums) {       // TC : O(n)
            min = Math.min(min, x);
            max = Math.max(max, x);
            set.add(x);
        }
        for (int i = min; i <= max; i++) {  // TC : O(r)
            if (!set.contains(i)) {
                missingList.add(i);
            }
        }
        return missingList;
    }
}
