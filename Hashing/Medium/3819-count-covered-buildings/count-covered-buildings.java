class Solution {
    /**
     * Approach : Using Hashing (Sorted Set) Approach
     *
     * TC: O(M x log(M)) + O(M x log(M)) ~ O(M x log(M))
     * SC: O(M) + O(M) ~ O(M)
     */
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, TreeSet<Integer>> xMap = 
            new HashMap<Integer, TreeSet<Integer>>(); // SC: O(M)
        Map<Integer, TreeSet<Integer>> yMap = 
            new HashMap<Integer, TreeSet<Integer>>(); // SC: O(M)
        for (int[] build : buildings) { // TC: O(M)
            int x = build[0];
            int y = build[1];
            xMap.computeIfAbsent(x, k -> new TreeSet<Integer>()).add(y); // TC: O(log(M))
            yMap.computeIfAbsent(y, k -> new TreeSet<Integer>()).add(x); // TC: O(log(M))
        }
        int countCovered = 0;
        for (int[] build : buildings) { // TC: O(M)
            int x = build[0];
            int y = build[1];
            TreeSet<Integer> xSet = xMap.get(x);
            TreeSet<Integer> ySet = yMap.get(y);
            if (xSet.size() < 3 || ySet.size() < 3) {
                // covered building is not possible
                continue;
            }
            boolean hasUp = xSet.lower(y) != null; // check up building
            boolean hasDown = xSet.higher(y) != null; // check down building
            boolean hasLeft = ySet.lower(x) != null; // check left building
            boolean hasRight = ySet.higher(x) != null; // check right building
            if (hasUp && hasDown && hasLeft && hasRight) { // TC: O(log(M))
                countCovered++;
            }
        }
        return countCovered;
    }
}
