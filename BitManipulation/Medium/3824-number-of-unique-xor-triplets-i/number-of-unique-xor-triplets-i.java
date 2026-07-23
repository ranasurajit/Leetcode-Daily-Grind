class Solution {
    /**
     * Approach II : Using Math + Bit Manipulation Approach
     *
     * TC : O(32) ~ O(1)
     * SC : O(1)
     *
     * Accepted (785 / 785 testcases passed)
     */
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        /**
         * we need to find the number of bits used for n
         * i.e. if n = 6 so it's binary representation is 110
         * so maximum we can have all 1's in 3 bits i.e. 111
         * after performing XOR operations so we need to find
         * 3 = 32 - number of leading zeroes in bit 
         * representation of n
         */
        int zeroes = 0;
        for (int i = 31; i >= 0; i--) { // TC : O(32)
            if ((n & (1 << i)) == 0) {
                zeroes++;
            } else {
                break;
            }
        }
        /**
         * all unique values that can be formed are 2 ^ (bits used in n)
         */
        return 1 << (32 - zeroes);
    }

    /**
     * Approach I : Using Brute-Force Approach
     *
     * TC : O(n³)
     * SC : O(1)
     *
     * Time Limit Exceeded (512 / 785 testcases passed)
     */
    public int uniqueXorTripletsBruteForce(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {         // TC : O(n)
            for (int j = i; j < n; j++) {     // TC : O(n)
                for (int k = j; k < n; k++) { // TC : O(n)
                    set.add((nums[i] ^ nums[j] ^ nums[k]));
                }
            }
        }
        return set.size();
    }
}
