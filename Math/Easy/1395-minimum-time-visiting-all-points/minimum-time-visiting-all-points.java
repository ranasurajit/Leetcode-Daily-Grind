class Solution {
    /**
     * Approach II : Using Greedy + Math (Optimal) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int n = points.length;
        int[] last = { points[0][0], points[0][1] };
        int time = 0;
        /**
         * to visit in mininum time, we should try to go via diagonal
         * as much as possible and rest vertically or horizontally
         */
        for (int i = 1; i < n; i++) { // TC: O(N)
            int[] current = points[i];
            time += Math.max(Math.abs(current[0] - last[0]), Math.abs(current[1] - last[1]));
            last = current;
        }
        return time;
    }

    /**
     * Approach I : Using Greedy + Math (Better) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minTimeToVisitAllPointsBetter(int[][] points) {
        int n = points.length;
        int[] last = { points[0][0], points[0][1] };
        int time = 0;
        /**
         * to visit in mininum time, we should try to go via diagonal
         * as much as possible and rest vertically or horizontally
         */
        for (int i = 1; i < n; i++) { // TC: O(N)
            int[] current = points[i];
            int minDiff = Math.min(Math.abs(current[0] - last[0]), Math.abs(current[1] - last[1]));
            int remainder = Math.max(Math.abs(current[0] - last[0]), 
                Math.abs(current[1] - last[1])) - minDiff;
            time += (minDiff + remainder);
            last = current;
        }
        return time;
    }
}
