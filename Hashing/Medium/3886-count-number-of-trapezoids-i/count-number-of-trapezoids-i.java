class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach : Using Hashing + Math Approach
     *
     * TC: O(N) + O(K) + O(K) ~ O(N + K)
     * SC: O(K), where K = keys of common y coordinates in points
     */
    public int countTrapezoids(int[][] points) {
        /**
         * a horizontal Trapezoid will have two parallel sides with 
         * coordinate that share same y coordinates, so we will store
         * coordinates in a HashMap with y-coordinate as key
         */
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(K)
        for (int[] point : points) { // TC: O(N)
            map.put(point[1], map.getOrDefault(point[1], 0) + 1);
        }
        List<Long> levelSides = new ArrayList<Long>(); // SC: O(K)
        for (Integer key : map.keySet()) { // TC: O(K)
            int xpoints = map.get(key);
            if (xpoints >= 2) {
                long comb = ((long) xpoints * (xpoints - 1) / 2) % MOD;
                levelSides.add(comb);
            }
        }
        int k = levelSides.size();
        if (k < 2) {
            // cannot form any trapezoid
            return 0;
        }
        long suffixSum = levelSides.get(k - 1);
        long sumCount = 0;
        for (int i = k - 2; i >= 0; i--) { // TC: O(K)
            sumCount = (sumCount + (levelSides.get(i) * suffixSum) % MOD) % MOD;
            suffixSum = (levelSides.get(i) + suffixSum) % MOD;
        }
        return (int) sumCount;
    }
}
