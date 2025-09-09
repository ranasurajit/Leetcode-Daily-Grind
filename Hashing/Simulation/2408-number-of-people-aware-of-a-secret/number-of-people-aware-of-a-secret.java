class Solution {
    private static final int MOD = (int) 1e9 + 7;
 
    /**
     * Approach : Using Hashing + Simulation Approach
     *
     * TC: O(N x N) + O(N) ~ O(N x N)
     * SC: O(N)
     */
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        map.put(1, 1);
        for (int d = 2; d <= n; d++) { // TC: O(N)
            for (Integer key : new ArrayList<Integer>(map.keySet())) { // TC: O(N)
                if (d - key >= delay && d - key < forget) {
                    map.put(d, (map.getOrDefault(d, 0) + (map.get(key)) % MOD) % MOD);
                }
                if (d - key >= forget) {
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
