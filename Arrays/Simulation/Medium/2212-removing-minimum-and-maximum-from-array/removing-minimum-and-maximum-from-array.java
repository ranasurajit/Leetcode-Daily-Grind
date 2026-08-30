class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int minIdx = -1;
        int maxIdx = -1;
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (min > nums[i]) {
                min = nums[i];
                minIdx = i;
            }
            if (max < nums[i]) {
                max = nums[i];
                maxIdx = i;
            }
        }
        int minDel = n + 1;
        // case 1 : if minIdx and maxIdx considered from left
        minDel = Math.min(minDel, Math.max(minIdx, maxIdx) + 1);
        // case 2 : if minIdx and maxIdx considered from right
        minDel = Math.min(minDel, Math.max(n - minIdx, n - maxIdx));
        // case 3 : if minIdx and maxIdx considered from left and right respectively
        minDel = Math.min(minDel, 
            Math.min(minIdx + 1 + n - maxIdx, 
                maxIdx + 1 + n - minIdx)
        );
        return minDel;
    }
}
