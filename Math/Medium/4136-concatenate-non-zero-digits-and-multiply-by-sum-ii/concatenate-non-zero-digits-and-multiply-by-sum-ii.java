class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach II : Using Optimal (Prefix-Sum + Math + Simulation) Approach
     *
     * TC : O(n) + O(q) ~ O(n + q)
     * SC : O(n) + O(n) ~ O(n)
     *
     * Accepted (523 / 523 testcases passed)
     */
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long[] prefixSum = new long[n];    // SC : O(n)
        long[] prefixDigits = new long[n]; // SC : O(n)
        long[] countNonZeroes = new long[n];  // SC : O(n)
        long[] pow = new long[n];          // SC : O(n)

        prefixSum[0] = (long) (s.charAt(0) - '0');
        prefixDigits[0] = (long) (s.charAt(0) - '0');
        countNonZeroes[0] = s.charAt(0) != '0' ? 1 : 0;
        pow[0] = 1;
        for (int i = 1; i < n; i++) { // TC : O(n)
            int digit = s.charAt(i) - '0';
            prefixSum[i] = prefixSum[i - 1] + digit;
            if (digit != 0) {
                prefixDigits[i] = (prefixDigits[i - 1] * 10 + digit) % MOD;
                countNonZeroes[i] = 1 + countNonZeroes[i - 1];
            } else {
                prefixDigits[i] = prefixDigits[i - 1];
                countNonZeroes[i] = countNonZeroes[i - 1];
            }
            pow[i] = (10 * pow[i - 1]) % MOD;
        }

        int q = queries.length;
        int[] result = new int[q];
        for (int i = 0; i < q; i++) { // TC : O(q)
            int start = queries[i][0];
            int end = queries[i][1];
            long currentSum = prefixSum[end] -
                (start > 0 ? prefixSum[start - 1] : 0);
            long startNum = 0L;
            if (start > 0) {
                long powIdx = countNonZeroes[end] - countNonZeroes[start - 1];
                startNum = (prefixDigits[start - 1] * pow[(int) powIdx]) % MOD;
            }
            long currentNum = (prefixDigits[end] - startNum + MOD) % MOD;
            result[i] = (int) ((currentSum * currentNum) % MOD);
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force (Math + Simulation) Approach
     *
     * TC : O(q x l)
     * SC : O(l)
     *
     * Time Limit Exceeded (508 / 523 testcases passed)
     */
    public int[] sumAndMultiplyBruteForce(String s, int[][] queries) {
        int q = queries.length;
        int[] result = new int[q];
        for (int i = 0; i < q; i++) { // TC : O(q)
            int start = queries[i][0];
            int end = queries[i][1];
            String subStr = s.substring(start, end + 1); // SC : O(l)
            result[i] = (int) (getComputedValue(subStr) % MOD); // TC : O(l)
        }
        return result;
    }

    /**
     * Using Math Approach
     *
     * TC : O(l)
     * SC : O(1)
     */
    private long getComputedValue(String s) {
        int n = s.length();
        long sum = 0L;
        long x = 0L;
        for (int i = 0; i < n; i++) { // TC : O(l)
            int digit = s.charAt(i) - '0';
            if (digit != 0) {
                sum += (long) digit;
                x = (10 * x + digit) % MOD;
            }
        }
        return (x * sum) % MOD;
    }
}
