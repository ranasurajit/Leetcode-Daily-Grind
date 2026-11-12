class Solution {
    /**
     * Approach II : Using Array Prefix-Suffix (No Extra Space) Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int inc = 1;
        int dec = 1;
        int maxLength = 1;
        for (int i = 1; i < n; i++) {      // TC : O(n)
            if (nums[i] > nums[i - 1]) {
                inc++;
                dec = 1;
            } else if (nums[i] < nums[i - 1]) {
                inc = 1;
                dec++;
            } else {
                inc = 1;
                dec = 1;
            }
            maxLength = Math.max(maxLength, Math.max(inc, dec));
        }
        return maxLength;
    }

    /**
     * Approach I : Using Array Prefix-Suffix Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    public int longestMonotonicSubarrayBetter(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];         // SC : O(n)
        Arrays.fill(prefix, 1);
        int maxPrefix = 1;
        for (int i = 1; i < n; i++) {      // TC : O(n)
            if (nums[i] > nums[i - 1]) {
                prefix[i] = 1 + prefix[i - 1];
                maxPrefix = Math.max(maxPrefix, prefix[i]);
            }
        }
        int[] suffix = new int[n];         // SC : O(n)
        Arrays.fill(suffix, 1);
        int maxSuffix = 1;
        for (int i = n - 2; i >= 0; i--) { // TC : O(n)
            if (nums[i] > nums[i + 1]) {
                suffix[i] = 1 + suffix[i + 1];
                maxSuffix = Math.max(maxSuffix, suffix[i]);
            }
        }
        return Math.max(maxPrefix, maxSuffix);
    }
}
