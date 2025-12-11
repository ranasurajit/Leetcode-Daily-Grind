class Solution {
    /**
     * Approach I : Using Hashing (Sorted Set) Approach
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
            if (xMap.get(x).lower(y) != null &&  // has up building
                xMap.get(x).higher(y) != null && // has down building
                yMap.get(y).lower(x) != null && // has left building
                yMap.get(y).higher(x) != null) { // has right building // TC: O(log(M))
                countCovered++;
            }
        }
        return countCovered;
    }
}
