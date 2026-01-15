class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     *
     * TC: O(M x log(M)) + O(N x log(N)) + O(M) + O(N) ~ O(M x log(M) + N x log(N))
     * SC: O(1)
     */
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int M = hBars.length;
        int N = vBars.length;
        if (M == 0 || N == 0) {
            /**
             * even if any of hBars or vBars is empty, then we cannot
             * remove any bar to create a square hole so maximum area
             * 1 x 1 = 1
             */
            return 1;
        }
        /**
         * we can sort hBars and vBars to figure out consecutive bars so 
         * that gaps can be increased horizontally, vertically
         *
         * if we remove k consecutive bars, then gap = k + 1 in opposite
         * direction, so MaxArea = Min (MaxHGap, MaxVGap)
         */
        Arrays.sort(hBars); // TC: O(M x log(M))
        Arrays.sort(vBars); // TC: O(N x log(N))
        int maxHGap = findMaxGap(vBars, N); // TC: O(N)
        int maxVGap = findMaxGap(hBars, M); // TC: O(M)
        int squareDim = Math.min(maxHGap, maxVGap);
        return squareDim * squareDim;
    }

    /**
     * Using Array Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private int findMaxGap(int[] bars, int n) {
        int maxConsectiveBars = 1;
        int count = 1;
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (bars[i] - bars[i - 1] == 1) {
                count++;
            } else {
                // reset count
                count = 1;
            }
            maxConsectiveBars = Math.max(maxConsectiveBars, count);
        }
        return maxConsectiveBars + 1;
    }
}
