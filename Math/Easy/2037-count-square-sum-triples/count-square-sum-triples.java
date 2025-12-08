class Solution {
    /**
     * Approach I : Using Enumeration + Hashing Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    public int countTriples(int n) {
        int count = 0;
        Map<Integer, ArrayList<int[]>> map = new HashMap<Integer, ArrayList<int[]>>(); // SC: O(N)
        for (int i = 1; i <= n; i++) {     // TC: O(N)
            for (int j = 1; j <= n; j++) { // TC: O(N)
                map.computeIfAbsent((i * i + j * j), k -> new ArrayList<int[]>()).add(new int[] { i, j });
            }
        }
        for (int i = 1; i <= n; i++) { // TC: O(N)
            if (map.containsKey(i * i)) {
                count += map.get(i * i).size();
            }
        }
        return count;
    }
}
