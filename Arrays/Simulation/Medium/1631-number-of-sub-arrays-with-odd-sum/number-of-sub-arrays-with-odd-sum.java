class Solution {
    /** 
     * Approach :  Optimal Approach
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        int count = 0;
        int MOD = (int) 1e9 + 7;
        int even = 1; // initially prefixSum is 0 so even count = 1
        int odd = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(n)
            sum += arr[i];
            if (sum % 2 == 0) {
                // even (encountered) so any previous odd + even(this) = odd
                count = (count + odd) % MOD;
                even++;
            } else {
                // odd (encountered) so any previous even + odd(this) = odd
                count = (count + even) % MOD;
                odd++;
            }
        }
        return count;
    }
}
