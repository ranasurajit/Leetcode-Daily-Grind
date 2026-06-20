class Solution {
    /**
     * Approach : Using Greedy + Math + Sorting Approach
     *
     * TC : O(4 x m) + O(m x log(m)) ~ O(m x log(m))
     * SC : O(m)
     */
    public int maxBuilding(int n, int[][] restrictions) {
        /**
         * we will add all restrictions in ArrayList 
         * and sort it based upon id so that we can
         * scan through left to right and vice-versa
         */
        ArrayList<int[]> resList = new ArrayList<>(); // SC : O(m)
        resList.add(new int[] { 1, 0 }); // added index '1' building
        for (int[] res : restrictions) { // TC : O(m)
            int idx = res[0];
            int max = res[1];
            resList.add(new int[] { idx, max });
        }
        // maximum possible height of index 'n' building is (n - 1)
        resList.add(new int[] { n, n - 1 });
        /**
         * sort the 'resList' in ascending order of index
         */
        Collections.sort(resList, (a, b) -> a[0] - b[0]); // TC : O(m x log(m))
        /**
         * scanning from left to right to keep setting the 
         * probable heights in restrictions from left to right
         */
        int m = resList.size();
        for (int i = 1; i < m; i++) { // TC : O(m)
            int dist = resList.get(i)[0] - resList.get(i - 1)[0];
            resList.get(i)[1] = Math.min(resList.get(i)[1], 
                resList.get(i - 1)[1] + dist);
        }
        /**
         * we will now scan from right to left to validate and
         * solidify the heights restrictions assigned in previous scan
         */
        for (int i = m - 2; i >= 0; i--) { // TC : O(m)
            int dist = resList.get(i + 1)[0] - resList.get(i)[0];
            resList.get(i)[1] = Math.min(resList.get(i)[1], 
                resList.get(i + 1)[1] + dist);
        }
        // compute peaks between two restricting indices
        int maxHeight = 0;
        for (int i = 1; i < m; i++) { // TC : O(m)
            int idx1 = resList.get(i - 1)[0];
            int h1 = resList.get(i - 1)[1];
            int idx2 = resList.get(i)[0];
            int h2 = resList.get(i)[1];
            // current peak
            /**
             * left (idx1, h1), right (idx2, h2)
             * for same peak towards right x is travelled
             * at peak, height = (h1 + x) for left
             * for same peak towards right distance = dist - x
             * at peak, height = (h2 + dist - x) for left
             * so, h1 + x = h2 + dist - x, so, 2x = (h2 - h1) + dist
             * so, x = ((h2 - h1) + dist) / 2,
             * so, peak = h1 + x = h1 + ((h2 - h1) + dist) / 2
             * so, peak = (h1 + h2 + dist) / 2
             * where dist = (idx2 - idx1)
             */
            int peak = (h1 + h2 + (idx2 - idx1)) / 2;
            maxHeight = Math.max(maxHeight, peak);
        }
        return maxHeight;
    }
}
