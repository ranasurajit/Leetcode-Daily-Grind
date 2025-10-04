class Solution {
    /**
     * Approach II : Using Two Pointers + Greedy Approach
     *
     * TC: O(N)
     * SC: O(1)
     *
     * Accepted (65 / 65 testcases passed)
     */
    public int maxArea(int[] height) {
        int n = height.length;
        int maxValue = 0;
        int i = 0; // start pointer
        int j = n - 1; // end pointer
        while (i < j) { // TC: O(N)
            int width = j - i;
            int minH = Math.min(height[i], height[j]);
            int currentArea = minH * width;
            maxValue = Math.max(maxValue, currentArea);
            if (height[i] <= height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxValue;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(N x N)
     * SC: O(1)
     *
     * Time Limit Exceeded (59 / 65 testcases passed)
     */
    public int maxAreaBruteForce(int[] height) {
        int n = height.length;
        int maxValue = 0;
        for (int i = 0; i < n - 1; i++) {     // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                int minH = Math.min(height[i], height[j]);
                int width = j - i;
                maxValue = Math.max(maxValue, minH * width);
            }
        }
        return maxValue;
    }
}
