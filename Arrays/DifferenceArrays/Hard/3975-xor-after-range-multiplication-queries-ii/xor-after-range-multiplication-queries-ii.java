class Solution {
    private static final int MOD = (int) 1e9 + 7;
    /**
     * Approach II : Using Optimal (Difference Array with Multiplication) Approach
     *
     * TC: O((q + n) x √n))
     * SC: O(q + n)
     *
     * Accepted (605 / 605 testcases passed)
     */
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        long[] result = new long[n];  // SC: O(n)
        for (int i = 0; i < n; i++) { // TC: O(n)
            result[i] = (long) nums[i];
        }
        int blockSize = (int) Math.ceil(Math.sqrt(n));
        Map<Integer, ArrayList<int[]>> smallKMap = new HashMap<>(); // SC: O(n)
        for (int[] q : queries) { // TC: O(q)
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];
            if (k >= blockSize) {
                // process larger k's >= blockSize
                for (int i = l; i <= r; i += k) { // TC: O(√n)
                    result[i] = (result[i] * v) % MOD;
                }
            } else {
                // pre-process smaller k's
                smallKMap.computeIfAbsent(k,
                    p -> new ArrayList<>()).add(q);
            }
        }
        // process smaller k's using Difference Array Technique with k jumps
        for (Integer key : smallKMap.keySet()) { // TC: O(√n)
            int K = key;
            ArrayList<int[]> qs = smallKMap.get(key);
            // Using Diff Array
            long[] diff = new long[n]; // SC: O(n)
            Arrays.fill(diff, 1L);
            for (int[] q : qs) { // TC: O(q)
                int l = q[0];
                int r = q[1];
                int k = q[2];
                int v = q[3];
                diff[l] = (v * diff[l]) % MOD;
                int steps = (r - l) / k;
                int next = l + (steps + 1) * k;
                if (next < n) {
                    diff[next] = (diff[next] * fastPower(v, MOD - 2)) % MOD;
                }
            }
            // propagate k jumps
            for (int i = 0; i < n; i++) { // TC: O(n)
                if (i >= K) {
                    diff[i] = (diff[i] * diff[i - K]) % MOD;
                }
            }
            // process cumulative products
            for (int i = 0; i < n; i++) { // TC: O(n)
                result[i] = (result[i] * diff[i]) % MOD;
            }
        }
        long xor = 0L;
        for (int i = 0; i < n; i++) { // TC: O(n)
            xor ^= result[i];
        }
        return (int) xor;
    }

    /**
     * Using Binomial Exponentiation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private long fastPower(long base, long pow) {
        long res = 1;
        base %= MOD;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            pow >>= 1;
        }
        return res;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(n) + O(q x (n / k)) + O(n) ~ O(q x n)
     * SC: O(n)
     *
     * Time Limit Exceeded (602 / 605 testcases passed)
     */
    public int xorAfterQueriesBruteForce(int[] nums, int[][] queries) {
        int n = nums.length;
        long[] result = new long[n];  // SC: O(n)
        for (int i = 0; i < n; i++) { // TC: O(n)
            result[i] = (long) nums[i];
        }
        for (int[] q : queries) { // TC: O(q)
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];
            for (int i = l; i <= r; i += k) { // TC: O(n / k)
                result[i] = (result[i] * v) % MOD;
            }
        }
        long xor = 0L;
        for (int i = 0; i < n; i++) { // TC: O(n)
            xor ^= result[i];
        }
        return (int) xor;
    }
}
