class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(q x n) + O(n) ~ O(q x n)
     * SC: O(n)
     */
    public int xorAfterQueries(int[] nums, int[][] queries) {
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
