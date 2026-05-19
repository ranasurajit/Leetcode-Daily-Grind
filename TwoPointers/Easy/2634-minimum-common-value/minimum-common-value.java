class Solution {
    /**
     * Approach I : Using Hashing Approach
     *
     * TC : O(n + m)
     * SC : O(n)
     */
    public int getCommon(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        Set<Integer> set = new HashSet<>(); // SC : O(n)
        /**
         * to minimize the storage, let's keep the 
         * 'nums1' array param always of greater size
         */
        // if (m < n) {
        //     return getCommon(nums2, nums1);
        // }
        for (int i = 0; i < n; i++) { // TC : O(n)
            set.add(nums2[i]);
        }
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) { // TC : O(m)
            if (set.contains(nums1[i])) {
                minValue = Math.min(minValue, nums1[i]);
            }
        }
        return minValue == Integer.MAX_VALUE ? -1 : minValue;
    }
}
