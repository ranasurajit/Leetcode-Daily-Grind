class Solution {
    /**
     * Approach I : Using Hashing Approach
     *
     * TC: O(M) ~ O(2 x N) ~ O(N)
     * SC: O(N)
     */
    public int repeatedNTimes(int[] nums) {
        int m = nums.length;
        Set<Integer> set = new HashSet<Integer>(); // SC: O(N)
        int countAddFailures = 0;
        int repeated = -1;
        for (int num : nums) { // TC: O(M)
            if (!set.add(num)) {
                countAddFailures++;
                repeated = num;
            }
            if (countAddFailures == (m / 2) - 1) {
                // as Set will already be having 1 occurence of repeated
                return repeated;
            }
        }
        return -1;
    }
}
