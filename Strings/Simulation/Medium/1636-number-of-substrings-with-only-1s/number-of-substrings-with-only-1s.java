class Solution {
    private static final int MOD = (int) 1e9 + 7;
    /**
     * Approach II : Using String + Math Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     *
     * Accepted (56 / 56 testcases passed)
     */
    public int numSub(String s) {
        int n = s.length();
        long count = 0L;
        // we need to find the consecutive length of 1s together
        long k = 0L; // length of continuous 1s
        for (int i = 0; i < n; i++) {     // TC: O(N)
            if (s.charAt(i) == '0') {
                count = (count + ((k * (k + 1)) / 2)) % MOD;
                // reset k here
                k = 0L;
            } else {
                k++;
            }
        }
        // add count for remaining k continuous 1s after loop ended
        if (k > 0) {
            count = (count + ((k * (k + 1)) / 2)) % MOD;
        }
        return (int) count;
    }

    /**
     * Approach I : Using Brute-Force (String Simulation) Approach
     *
     * TC: O(N) + O(N²) ~ O(N²)
     * SC: O(N)
     *
     * Time Limit Exceeded (51 / 56 testcases passed)
     */
    public int numSubBruteForce(String s) {
        int n = s.length();
        int count = 0;
        int[] prefix1s = new int[n];      // SC: O(N)
        prefix1s[0] = s.charAt(0) == '1' ? 1 : 0;
        for (int i = 1; i < n; i++) {     // TC: O(N)
            prefix1s[i] = prefix1s[i - 1] + (s.charAt(i) == '1' ? 1 : 0);
        }
        for (int i = 0; i < n; i++) {     // TC: O(N)
            for (int j = i; j < n; j++) { // TC: O(N)
                int count1s = prefix1s[j] - (i > 0 ? prefix1s[i - 1] : 0);
                if (count1s == j - i + 1) {
                    // this condition checks if all elements are 1 in substring [i...j] 
                    count++;
                }
            }
        }
        return count;
    }
}
