class Solution {
    /**
     * Approach III : Using Optimal (Adjacency Check With Fixed Window) Approach
     *
     * TC: O(M) ~ O(2 x N) ~ O(N)
     * SC: O(1)
     */
    public int repeatedNTimes(int[] nums) {
        int m = nums.length;
        /**
         * the repeated number should be present within sub-array of 4 elements
         */
        for (int j = 1; j <= 3; j++) { // TC: O(3)
            for (int i = 0; i < m - j; i++) { // TC: O(M)
                if (nums[i] == nums[i + j]) {
                    // early return
                    return nums[i];
                }
            }
        }
        return -1;
    }

    /**
     * Approach II : Using Better (Hashing) Approach
     *
     * TC: O(M) ~ O(2 x N) ~ O(N)
     * SC: O(N)
     */
    public int repeatedNTimesBetter(int[] nums) {
        int m = nums.length;
        Set<Integer> set = new HashSet<Integer>(); // SC: O(N)
        for (int num : nums) { // TC: O(M)
            if (!set.add(num)) {
                /**
                 * as constraint guarantees that nums contains
                 * n + 1 unique elements and one of them is 
                 * repeated exactly n times
                 */
                return num;
            }
        }
        return -1;
    }

    /**
     * Approach I : Using Brute-Force (Hashing) Approach
     *
     * TC: O(M) ~ O(2 x N) ~ O(N)
     * SC: O(N)
     */
    public int repeatedNTimesBruteForce(int[] nums) {
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
