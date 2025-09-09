class Solution {
    private static final int MOD = (int) 1e9 + 7;
 
    /**
     * Approach II : Using Dynamic Programming Approach
     *
     * TC: O(N x K) + O(N) ~ O(N x K)
     * SC: O(N)
     *
     * where K = forget - delay
     */
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long[] dp = new long[n + 1];  // SC: O(N)
        dp[1] = 1L;
        for (int i = 2; i <= n; i++) { // TC: O(N)
            for (int j = delay; j < forget; j++) { // TC: O(K)
                if (i - j > 0) {
                    dp[i] = (dp[i] + dp[i - j]) % MOD;
                }
            }
        }
        long count = 0;
        for (int i = n - forget + 1; i <= n; i++) { // TC: O(N)
            count = (count + dp[i]) % MOD;
        }
        return (int) count;
    }

    /**
     * Approach I : Using Hashing + Simulation Approach
     *
     * TC: O(N x N) + O(N) ~ O(N x N)
     * SC: O(N)
     */
    public int peopleAwareOfSecretHashing(int n, int delay, int forget) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        map.put(1, 1);
        for (int d = 2; d <= n; d++) { // TC: O(N)
            for (Integer key : new ArrayList<Integer>(map.keySet())) { // TC: O(N)
                if (d - key >= delay && d - key < forget) {
                    map.put(d, (map.getOrDefault(d, 0) + (map.get(key)) % MOD) % MOD);
                }
                if (d - key >= forget) {
                    // removing the count of people who forgot on dth day
                    map.put(key, 0);
                }
            }
        }
        int count = 0;
        for (Integer key : map.keySet()) { // TC: O(N)
            count = (count + map.get(key)) % MOD;
        }
        return count;
    }
}
