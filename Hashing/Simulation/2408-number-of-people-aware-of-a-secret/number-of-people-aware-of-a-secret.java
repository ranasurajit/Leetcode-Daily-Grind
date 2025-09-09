class Solution {
    private static final int MOD = (int) 1e9 + 7;
 
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1, 1);
        for (int d = 2; d <= n; d++) {
            for (Integer key : new ArrayList<Integer>(map.keySet())) {
                if (d - key >= delay && d - key < forget) {
                    map.put(d, (map.getOrDefault(d, 0) + (map.get(key)) % MOD) % MOD);
                }
                if (d - key >= forget) {
                    map.put(key, 0);
                }
            }
        }
        int count = 0;
        for (Integer key : map.keySet()) {
            count = (count + map.get(key)) % MOD;
        }
        return count;
    }
}
