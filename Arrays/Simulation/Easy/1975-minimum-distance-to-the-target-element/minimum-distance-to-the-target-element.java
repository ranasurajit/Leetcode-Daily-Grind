class Solution {
    /**
     * Approach II : Using Optimal (Two Pointers) Approach
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(n)
            int left = Math.abs(start - i);
            int right = Math.abs(start + i);
            if (left >= 0 && nums[left] == target) {
                return i;
            }
            if (right < n && nums[right] == target) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int getMinDistanceBruteForce(int[] nums, int target, int start) {
        int n = nums.length;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(n)
            if (nums[i] == target) {
                minDist = Math.min(minDist, Math.abs(i - start));
            }
        }
        return minDist;
    }
}
