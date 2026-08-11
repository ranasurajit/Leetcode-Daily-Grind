class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC : O(n) + O(n) + O(r) ~ O(n + r)
     * SC : O(1)
     *
     * where r = (totalSum - longestPrefixSum)
     */
    public int missingInteger(int[] nums) {
        int n = nums.length;
        boolean[] present = new boolean[51]; // SC : O(51) ~ O(1)
        int totalSum = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            present[nums[i]] = true; 
            totalSum += nums[i];
        }
        int sum = nums[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                // we cannot start prefix without nums[0]
                break;
            }
        }
        for (int i = sum; i <= totalSum; i++) { // TC : O(r)
            if (i >= 51 || (i < 51 && !present[i])) {
                return i;
            }
        }
        return sum + 1;
    }
}
