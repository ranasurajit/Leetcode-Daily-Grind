class Solution {
    /**
     * Approach II : Using Two Pointers Approach
     *
     * TC : O(m + n)
     * SC : O(1)
     */
    public int getCommon(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (nums1[m - 1] < nums2[0] || nums2[n - 1] < nums1[0]) {
            // no common values possible
            return -1;
        }
        int i = 0; // pointer at the start of array 'nums1'
        int j = 0; // pointer at the start of array 'nums2'
        /**
         * Since both the arrays are sorted so we can
         * use Two pointers approach to compare elements
         */
        while (i < m && j < n) { // TC : O(m + n)
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                // nums1[i] == nums2[j], then return any
                return nums1[i];
            }
        }
        return -1;
    }

    /**
     * Approach I : Using Hashing Approach
     *
     * TC : O(n + m)
     * SC : O(n)
     */
    public int getCommonUsingHashing(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        Set<Integer> set = new HashSet<>(); // SC : O(n)
        /**
         * to minimize the storage, let's keep the 
         * 'nums1' array param always of greater size
         */
        if (m < n) {
            return getCommonUsingHashing(nums2, nums1);
        }
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
