class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach II : Using Better (String Simulation + Bit-Manipulation) Approach
     *
     * TC: O(N) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N x log(N))
     *
     * Each number i, has (log(i) Base 2) bits 
     * so total String can become of length (N x log(N))
     */
    public int concatenatedBinary(int n) {
        /**
         * as per constraints n can go upto 10⁵ so 
         * maximum left most bit can go upto 17 digits
         */
        StringBuilder sb = new StringBuilder(); // SC: O(N x log(N))
        for (int i = 1; i <= n; i++) { // TC: O(N)
            sb.append(Integer.toBinaryString(i)); // TC: O(1), SC: O(1)
        }
        long result = 0;
        int m = sb.length();
        for (int i = 0; i < m; i++) { // TC: O(N x log(N))
            result = (result * 2 + (sb.charAt(i) - '0')) % MOD;
        }
        return (int) result;
    }

    /**
     * Approach I : Using Brute-Force (String Simulation + Bit-Manipulation) Approach
     *
     * TC: O(N) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N x log(N))
     *
     * Each number i, has (log(i) Base 2) bits 
     * so total String can become of length (N x log(N))
     */
    public int concatenatedBinaryBruteForce(int n) {
        /**
         * as per constraints n can go upto 10⁵ so 
         * maximum left most bit can go upto 17 digits
         */
        StringBuilder sb = new StringBuilder(); // SC: O(N x log(N))
        for (int i = 1; i <= n; i++) { // TC: O(N)
            sb.append(convertNumberToBinary(i)); // TC: O(1), SC: O(1)
        }
        long result = 0;
        int m = sb.length();
        for (int i = 0; i < m; i++) { // TC: O(N x log(N))
            result = (result * 2 + (sb.charAt(i) - '0')) % MOD;
        }
        return (int) result;
    }

    /**
     * Using String Simulation + Bit-Manipulation Approach
     *
     * TC: O(17) ~ O(1)
     * SC: O(17) ~ O(1)
     */
    private String convertNumberToBinary(int num) {
        int rightMostSetIndex = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 16; i >= 0; i--) { // TC: O(17 - K)
            if (((num >> i) & 1) == 1) {
                rightMostSetIndex = i;
                break;
            }
        }
        for (int i = rightMostSetIndex; i >= 0; i--) { // TC: O(K)
            sb.append(((num >> i) & 1));
        }
        return sb.toString();
    }
}
