class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countTrapezoids(int[][] points) {
        /**
         * a horizontal Trapezoid will have two parallel sides with 
         * coordinate that share same y coordinates, so we will store
         * coordinates in a HashMap with y-coordinate as key
         */
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int[] point : points) {
            map.put(point[1], map.getOrDefault(point[1], 0) + 1);
        }
        List<Long> levelSides = new ArrayList<Long>();
        for (Integer key : map.keySet()) {
            int xpoints = map.get(key);
            if (xpoints >= 2) {
                long comb = ((long) xpoints * (xpoints - 1) / 2) % MOD;
                levelSides.add(comb);
            }
        }
        int n = levelSides.size();
        if (n < 2) {
            // cannot form any trapezoid
            return 0;
        }
        long suffixSum = levelSides.get(n - 1);
        long sumCount = 0;
        for (int i = n - 2; i >= 0; i--) {
            sumCount = (sumCount + (levelSides.get(i) * suffixSum) % MOD) % MOD;
            suffixSum = (levelSides.get(i) + suffixSum) % MOD;
        }
        return (int) sumCount;
    }
}
