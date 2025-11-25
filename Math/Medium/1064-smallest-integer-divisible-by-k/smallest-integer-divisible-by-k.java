class Solution {
    /**
     * Approach II : Using Math Approach
     *
     * TC: O(K)
     * SC: O(1)
     */
    public int smallestRepunitDivByK(int k) {
        if (k == 2 || k == 5) {
            // number ending with 1 can never be divisible by 2 or 5
            return -1;
        }
        int remainder = 0;
        for (int len = 1; len <= k; len++) { // TC: O(K)
            /**
             * below calculation overflows as A double can only 
             * store ~16 digits accurately. 
             * prefixNum = 10 * prefixNum + 1d;
             * so we will work with remainders (MOD)
             */
            remainder = (10 * remainder + 1) % k;
            if (remainder == 0) {
                return len;
            }
        }
        return -1;
    }

    /**
     * Approach I : Using Math Approach
     *
     * TC: O(K)
     * SC: O(1)
     */
    public int smallestRepunitDivByKBruteForce(int k) {
        if (k == 2 || k == 5) {
            // number ending with 1 can never be divisible by 2 or 5
            return -1;
        }
        double prefixNum = 1d;
        int count = 1;
        for (int len = 1; len <= k; len++) { // TC: O(K)
            if (prefixNum % k == 0) {
                return count;
            }
            count++;
            // below calculation overflows as A double can only store ~16 digits accurately.
            prefixNum = 10 * prefixNum + 1d;
        }
        return -1;
    }
}
