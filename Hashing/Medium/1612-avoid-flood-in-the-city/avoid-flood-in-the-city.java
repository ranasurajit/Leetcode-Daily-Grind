class Solution {
    /**
     * Approach : Using Hashing + Ordered Set + Greedy Approach
     *
     * TC: O(N x log(N))
     * SC: O(N) + O(N) ~ O(N)
     */
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] result = new int[n];
        Arrays.fill(result, 1);
        // storing { lakeIndex, day } in HashMap 
        Map<Integer, Integer> rainMap = new HashMap<Integer, Integer>(); // SC: O(N)
        TreeSet<Integer> dryDaySet = new TreeSet<Integer>();             // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int lake = rains[i];
            if (lake > 0) {
                result[i] = -1;
                if (rainMap.containsKey(lake)) {
                    // check if we can dry this lake up on any day after rainMap.get(lake)
                    Integer dryDay = dryDaySet.higher(rainMap.get(lake)); // TC: O(log(N))
                    if (dryDay == null) {
                        // not possible to dry any day
                        return new int[] {};
                    }
                    result[(int) dryDay] = lake;
                    dryDaySet.remove(dryDay); // TC: O(log(N))
                }
                rainMap.put(lake, i);
            } else {
                dryDaySet.add(i);
            }
        }
        return result;
    }
}
