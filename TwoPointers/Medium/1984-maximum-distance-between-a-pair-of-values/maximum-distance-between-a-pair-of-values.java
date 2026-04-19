class Solution {
    /**
     * Approach III : Using Optimal Approach (Two Pointers) Approach
     *
     * TC : O(m + n)
     * SC : O(1)
     *
     * Accepted (32 / 32 testcases passed)
     */
    public int maxDistance(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int maxDist = 0;
        int i = 0; // pointer at the start of nums1
        int j = 0; // pointer at the start of nums2
        while (i < m && j < n) { // TC: O(m + n)
            if (nums2[j] >= nums1[i]) {
                maxDist = Math.max(maxDist, j - i);
                j++;
            } else {
                i++;
            }
        }
        return maxDist;
    }

    /**
     * Approach II : Using Better Approach (Binary Search) Approach
     *
     * TC : O(m x log(n))
     * SC : O(1)
     *
     * Accepted (32 / 32 testcases passed)
     */
    public int maxDistanceBetter(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int maxDist = 0;
        for (int i = 0; i < m; i++) { // TC: O(m)
            /**
             * we need to find the farthest index 'j' in 'nums2'
             * such that j >= i and  nums1[i] <= nums2[j] so
             * we can perform Binary Search on 'nums2'
             */
            if (i < n) {
                int j = findFarthestIndex(nums2, i,
                    n - 1, nums1[i]); // TC : O(log(n))
                maxDist = Math.max(maxDist, j - i);
            }
        }
        return maxDist;
    }

    /**
     * Using Binary Search Approach
     *
     * TC : O(log(n))
     * SC : O(1)
     */
    private int findFarthestIndex(int[] nums, int low, int high, int target) {
        int farthest = low;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                // we need to move towards left
                high = mid - 1;
            } else {
                // we need to move right to get farthest index
                farthest = Math.max(farthest, mid);
                low = mid + 1;
            }
        }
        return farthest;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC : O(m x n)
     * SC : O(1)
     *
     * Time Limit Exceeded (22 / 32 testcases passed)
     */
    public int maxDistanceBruteForce(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int maxDist = 0;
        for (int i = 0; i < m; i++) {     // TC: O(m)
            for (int j = i; j < n; j++) { // TC: O(n)
                if (nums1[i] <= nums2[j]) {
                    maxDist = Math.max(maxDist, j - i);
                }
            }
        }
        return maxDist;
    }
}
