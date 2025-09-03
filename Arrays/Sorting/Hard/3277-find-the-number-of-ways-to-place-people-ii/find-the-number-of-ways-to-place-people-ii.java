class Solution {
    /**
     * Approach : Using Sorting + Math (Geometry) Approach
     *
     * Intuition:
     * - First, sort points by x ascending (and by y descending for tie-breaking).
     *   This ensures we only need to scan "to the right" when checking rectangles.
     * - For each fixed point i, we move rightward to find candidate j.
     * - A valid pair (i, j) requires: xi < xj and yi >= yj.
     * - But we must also ensure no point lies inside the rectangle (xi, yi) to (xj, yj).
     *
     * Optimization Trick:
     * - While scanning j to the right, maintain `maxY` = lowest y seen so far.
     * - If yj <= yi and yj > maxY → (i, j) is valid → update maxY = yj.
     * - If yj <= maxY → it means a lower point already exists that blocks the rectangle.
     * - This avoids scanning all intermediate points (O(N)) for each (i, j).
     *
     * TC: O(N x log(N)) for sorting + O(N ^ 2) for double loop ~ O(N ^ 2)
     * SC: O(1)
     */
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        /**
         * Sorting array 'points' such that x1 <= x2 and if x1 == x2, y1 > y2
         */
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        }); // TC: O(N x log(N))
        int count = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int maxY = Integer.MIN_VALUE; // track maximum y coordinate towards right
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                if (points[i][1] >= points[j][1]) {
                    // cannot be a vertical line
                    if (points[j][1] > maxY) { // if not then it will block the rectangle
                        maxY = points[j][1];
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
