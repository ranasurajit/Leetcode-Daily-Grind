class Solution {
    private static final int MOD = (int) 1e9 + 7;
    /**
     * Approach II : Using Difference Array (Multiplication Teachnique) Approach
     *
     * TC: O(n) + O(n x √n) + O(q x √n) + O(n x √n) + O(n) ~ O((n + q)x √n)
     * SC: O(n) + O(n x √n) ~ O(n x √n)
     */
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        long[] result = new long[n]; // SC: O(n)
        for (int i = 0; i < n; i++) { // TC: O(n)
            result[i] = (long) nums[i];
        }
        int sqrt = (int) Math.sqrt(n) + 1;
        // storing multiplier index for small k's
        long[][] mult = new long[sqrt][n];
        for (int k = 0; k < sqrt; k++) { // TC: O(√n)
            for (int i = 0; i < n; i++) { // TC: O(n)
                mult[k][i] = 1L;
            }
        }
        for (int[] q : queries) { // TC: O(q)
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];
            if (k >= sqrt) {
                for (int i = l; i <= r; i += k) { // TC: O(√n)
                    result[i] = (result[i] * v) % MOD;
                }
            } else {
                for (int i = l; i <= r; i += k) { // TC: O(√n)
                    mult[k][i] = (mult[k][i] * v) % MOD;
                }
            }
        }
        // applying multipliers for small k's to result
        for (int k = 0; k < sqrt; k++) { // TC: O(√n)
            for (int i = 0; i < n; i++) { // TC: O(n)
                result[i] = (result[i] * mult[k][i]) % MOD;
            }
        }
        long xor = 0L;
        for (int i = 0; i < n; i++) { // TC: O(n)
            xor = (xor ^ result[i]);
        }
        return (int) xor;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(n) + O(q x n) + O(n) ~ O(q x n)
     * SC: O(n)
     */
    public int xorAfterQueriesBruteForce(int[] nums, int[][] queries) {
        int n = nums.length;
        long[] result = new long[n]; // SC: O(n)
        for (int i = 0; i < n; i++) { // TC: O(n)
            result[i] = (long) nums[i];
        }
        for (int[] q : queries) { // TC: O(q)
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];
            for (int i = l; i <= r; i += k) { // TC: O(n)
                result[i] = ((long) result[i] * v) % MOD;
            }
        }
        long xor = 0L;
        for (int i = 0; i < n; i++) { // TC: O(n)
            xor = (xor ^ result[i]);
        }
        return (int) xor;
    }
}
