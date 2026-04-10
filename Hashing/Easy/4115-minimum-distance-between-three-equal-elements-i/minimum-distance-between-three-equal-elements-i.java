class Solution {
    /**
     * Approach: Using Array Simulation Approach
     *
     * TC: O(n³)
     * SC: O(1)
     */
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {         // TC: O(n)
            for (int j = i + 1; j < n - 1; j++) { // TC: O(n)
                for (int k = j + 1; k < n; k++) { // TC: O(n)
                    if (nums[i] == nums[j] && nums[j] == nums[k]) {
                        int current = Math.abs(i - j) +
                            Math.abs(j - k) +
                            Math.abs(k - i);
                        minDist = Math.min(minDist, current);
                    }
                }
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
