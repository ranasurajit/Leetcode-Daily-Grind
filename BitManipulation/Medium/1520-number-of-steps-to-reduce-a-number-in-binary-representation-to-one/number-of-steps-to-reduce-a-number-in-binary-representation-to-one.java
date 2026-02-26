class Solution {
    /**
     * Approach III : Using Optimal (String Simulation + Bit-Manipulation) Approach
     *
     * TC: O(N)
     * SC: O(1) 
     *
     * Note: This approach will handle overflows
     */
    public int numSteps(String s) {
        int n = s.length();
        int steps = 0;
        int carry = 0;
        for (int i = n - 1; i >= 1; i--) { // TC: O(N)
            int effectiveBit = carry + (s.charAt(i) - '0');
            if (effectiveBit == 1) {
                // then we add 1 (+1 step) and it becomes even and then we divide it by 2
                steps += 2;
                carry = 1;
            } else {
                // only divide it by 2
                steps += 1;
            }
        }
        /**
         * if carry is 1 here so Most Significant Bit has an 
         * effective bit of 1, so we need 1 more additional step
         */
        return steps + carry;
    }

    /**
     * Approach II : Using Better (String Simulation + Bit-Manipulation) Approach
     *
     * TC: O(N²)
     * SC: O(1) 
     *
     * Note: This approach will handle overflows
     */
    public int numStepsBetterApproach(String s) {
        int steps = 0;
        StringBuilder sb = new StringBuilder(s); // SC: O(N)
        while (!sb.toString().equals("1")) { // TC: O(N)
            int n = sb.length();
            int lastBit = (sb.charAt(n - 1) - '0');
            if (lastBit == 1) {
                /**
                 * s denotes an odd number, so to add 1 to it,
                 * we need to find the 1st zero from right to left
                 * and make it 1 and rest all towards right to zero
                 */
                int j = n - 1;
                while (j > 0 && sb.charAt(j) != '0') {
                    j--;
                }
                if (j == 0) {
                    sb = new StringBuilder("1");
                } else {
                    sb.delete(j, sb.length());
                    sb.append("1");
                    j++;
                }
                while (j < n) { // TC: O(N)
                    sb.append("0");
                    j++;
                }
            } else {
                // s denotes an even number
                sb.deleteCharAt(sb.length() - 1); // TC: O(N)
            }
            steps++;
        }
        return steps;
    }

    /**
     * Approach I : Using Brute-Force (String Simulation + Bit-Manipulation) Approach
     *
     * TC: O(N)
     * SC: O(1)
     *
     * Note: This approach will result in wrong results in case of overflow
     */
    public int numStepsBruteForce(String s) {
        int n = s.length();
        long num = getNumber(s, n); // TC: O(N), SC: O(1)
        int steps = 0;
        while (num > 1L) { // TC: O(N)
            if ((num & 1) == 0) {
                // even
                num = num / 2;
            } else {
                // odd
                num += 1;
            }
            steps++;
        }
        return steps;
    }

    /**
     * Using String Simulation + Bit-Manipulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private long getNumber(String s, int n) {
        long num = 0L;
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            num += (s.charAt(i) - '0') * (long) (1 << (n - i - 1));
        }
        return num;
    }
}
