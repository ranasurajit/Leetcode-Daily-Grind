class Solution {
    /**
     * Approach III : Using Optimal (Bit-Manipulation) Approach
     *
     * TC: O(N)
     * SC: O(1)
     *
     * Accepted (24 / 24 testcases passed)
     */
    public List<Boolean> prefixesDivBy5(int[] nums) {
        int n = nums.length;
        List<Boolean> result = new ArrayList<Boolean>();
        /**
         * We will build the number from Binary to Decimal
         * keeping track of only remainder i.e. mod
         */
        int prefix = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            prefix = ((prefix << 1) + nums[i]) % 5;
            result.add(prefix == 0);
        }
        return result;
    }

    /**
     * Approach II : Using Optimal (Bit-Manipulation) Approach
     *
     * TC: O(N)
     * SC: O(1)
     *
     * Accepted (24 / 24 testcases passed)
     */
    public List<Boolean> prefixesDivBy5BitManipulation(int[] nums) {
        int n = nums.length;
        List<Boolean> result = new ArrayList<Boolean>();
        /**
         * For any new addition of bit at last what we do 
         * is newValue = prevValue * 2 + bit so we need
         * to check if newValue % 5 == 0, so we can do
         * similar approach to mod
         */
        int oldMod = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int newMod = (oldMod * 2 + nums[i]) % 5;
            result.add(newMod % 5 == 0);
            oldMod = newMod;
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force Approach
     *
     * TC: O(N ^ 2)
     * SC: O(N)
     *
     * Wrong answer due to overflow (10 / 24 testcases passed)
     */
    public List<Boolean> prefixesDivBy5BruteForce(int[] nums) {
        int n = nums.length;
        List<Boolean> result = new ArrayList<Boolean>();
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        for (int i = 0; i < n; i++) {    // TC: O(N)
            sb.append(nums[i]);
            result.add(isDivisible(sb)); // TC: O(N)
        }
        return result;
    }

    /**
     * Using String + Bit-Manipulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     *
     * Wrong answer due to overflow (10 / 24 testcases passed)
     */
    private Boolean isDivisible(StringBuilder sb) {
        if (sb.toString().equals("0")) {
            return true;
        }
        long decimalNum = 0L;
        int pow = 0;
        for (int i = sb.length() - 1; i >= 0; i--) { // TC: O(N)
            decimalNum += (sb.charAt(i) - '0') * (1L << pow);
            pow++;
        }
        return decimalNum % 5 == 0;
    }
}
