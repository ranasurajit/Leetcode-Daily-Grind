class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(9 x n) ~ O(n)
     * SC: O(n)
     */
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); // SC: O(n)
        int minDist = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) { // TC: O(n)
            int rev = reverse(nums[i]);
            if (map.containsKey(rev)) {    // TC: O(9)
                minDist = Math.min(minDist, map.get(rev) - i);
            }
            map.put(nums[i], i);
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    /**
     * Using Math Simulation Approach
     *
     * TC: O(9)
     * SC: O(1)
     *
     * 1 <= nums[i] <= 10⁹ so at most 9 iterations can happen
     */
    private int reverse(int num) {
        int rev = 0;
        while (num > 0) {
            int rem = num % 10;
            num = num / 10;
            rev = rev * 10 + rem;
        }
        return rev;
    }
}
