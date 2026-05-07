class Solution {
    /**
     * Approach : Using Array Prefix-Suffix Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     */
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int[] prefixMax = new int[n]; // SC : O(n)
        for (int i = 0; i < n; i++) {  // TC : O(n)
            prefixMax[i] = i == 0 ?
                nums[i] :
                Math.max(prefixMax[i - 1], nums[i]);
        }
        int suffixMin = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {  // TC : O(n)
            if (i == n - 1) {
                result[i] = prefixMax[i];
            } else {
                if (prefixMax[i] > suffixMin) {
                    result[i] = result[i + 1];
                } else {
                    result[i] = prefixMax[i];
                }
            }
            suffixMin = Math.min(suffixMin, nums[i]);
        }
        return result;
    }
}
