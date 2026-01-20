class Solution {
    /**
     * Approach II : Using Optimal (Bit-Manipulation) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
        /**
        * Observation:
        * For any integer x, (x | (x + 1)) is always an odd number.
        * Hence, if nums[i] is even, no valid ans[i] exists and we return -1.
        *
        * For odd nums[i]:
        * When adding 1 to a number, all trailing 1s flip to 0 and the first 0
        * (from the right) flips to 1. Therefore, (x | (x + 1)) forces all bits
        * below that first 0-bit to become 1.
        *
        * To construct the minimum x such that:
        *      x | (x + 1) == nums[i]
        * we locate the lowest 0-bit in nums[i], and clear the bit immediately
        * below it. This produces the smallest possible x that still satisfies
        * the OR condition.
        *
        * Bit trick used:
        *   (nums[i] + 1) & ~nums[i]   → isolates the lowest 0-bit of nums[i]
        *   shifting it right by 1     → targets the bit to be cleared
        *   applying NOT and AND       → clears that bit in nums[i]
        *
        * Final formula:
        *   ans[i] = nums[i] & ~(((nums[i] + 1) & ~nums[i]) >> 1)
        *
        */
        for (int i = 0; i < n; i++) { // TC: O(N)
            int value = nums.get(i);
            if ((value & 1) == 0) {
                result[i] = -1;
            } else {
                result[i] = value & ~(((value + 1) & ~value) >> 1);
            }
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(N²)
     * SC: O(1)
     */
    public int[] minBitwiseArrayBruteForce(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(N)
            /**
             * To satisfy the condition of ans[i] OR ans[i] + 1 = nums.get(i),
             * ans[i] cannot exceed nums.get(i)
             */
            boolean found = false;
            for (int j = 0; j <= nums.get(i); j++) { // TC: O(N)
                if ((j | (j + 1)) == nums.get(i)) {
                    result[i] = j;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result[i] = -1;
            }
        }
        return result;
    }
}
