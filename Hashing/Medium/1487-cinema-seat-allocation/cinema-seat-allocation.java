class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC : O(m) + O(m) ~ O(m)
     * SC : O(m)
     * where m = size(reservedSeats)
     */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> reservedMap = new HashMap<>(); // SC : O(m)
        for (int[] seats : reservedSeats) { // TC : O(m)
            reservedMap.computeIfAbsent(seats[0],
                k -> new HashSet<>()).add(seats[1]);
        }
        int count = 0;
        for (Set<Integer> reserved : reservedMap.values()) { // TC : O(m)
            boolean leftAvailable = true;
            boolean midAvailable = true;
            boolean rightAvailable = true;
            // check if middle slot is available
            if (reserved.contains(4) || reserved.contains(5) || 
                reserved.contains(6) || reserved.contains(7)) {
                midAvailable = false;
            }
            // check if left slot is available
            if (reserved.contains(2) || reserved.contains(3) || 
                reserved.contains(4) || reserved.contains(5)) {
                leftAvailable = false;
            }
            // check if right slot is available
            if (reserved.contains(6) || reserved.contains(7) || 
                reserved.contains(8) || reserved.contains(9)) {
                rightAvailable = false;
            }
            if (leftAvailable && rightAvailable) {
                count += 2;
            } else if (leftAvailable || midAvailable || rightAvailable) {
                count += 1;
            }
        }
        /**
         * we need to count the seat blocks for unused rows 
         * as 2 to maximize 4-person groups
         */
        count += 2 * (n - reservedMap.size());
        return count;
    }
}
