class Solution {
    /**
     * Approach : Using Prefix-Count Approach
     *
     * TC : O(n) + O(n) + O(n²) ~ O(n²)
     * SC : O(n) + O(n) ~ O(n)
     */
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        /**
         * we will pre-compute the prefix count of target
         * for every index and so we create a 'prefix' array
         * prefix[i] = number of target occurrences in nums[0..i-1]
         */
        int[] prefix = new int[n + 1];    // SC : O(n)
        for (int i = 0; i < n; i++) {     // TC : O(n)
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : 0);
        }
        /**
         * for a number target to be majority elements so
         * count of that number in a sub-array of size 'len'
         * should be such that count > len / 2
         * i.e. freq > (r - l + 1) / 2
         * i.e. prefix[r] - prefix[l - 1] > (r - l + 1) / 2
         * i.e. 2 * prefix[r] - 2 x prefix[l - 1] > r - l + 1
         * i.e. (2 * prefix[r] - r) > (2 x prefix[l - 1] - (l - 1))
         * i.e. score[r] > score[l - 1]
         * where score[i] = (2 * prefix[i] - i)
         * so now, let's pre-compute score[i] for each index 'i'
         */
        int[] score = new int[n + 1];     // SC : O(n)
        for (int i = 0; i <= n; i++) {    // TC : O(n)
            score[i] = (2 * prefix[i]) - i;
        }
        /**
         * now we will count the valid sub-arrays with 
         * score[r] > score[l - 1]
         */
        int count = 0;
        for (int l = 0; l <= n; l++) {         // TC : O(n)
            for (int r = l + 1; r <= n; r++) { // TC : O(n)
                if (score[r] > score[l]) {
                    count++;
                }
            }
        }
        return count;
    }
}
