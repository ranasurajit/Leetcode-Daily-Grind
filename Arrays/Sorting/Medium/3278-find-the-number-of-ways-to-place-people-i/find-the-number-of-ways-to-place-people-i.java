class Solution {
    /**
     * Approach : Using Sorting + Math (Geometry) Approach
     *
     * TC: O(N x log(N)) + O(N x N) ~ O(N x N)
     * SC: O(1)
     */
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                // x coordinates are same, return y1 > y2
                return b[1] - a[1];
            }
            return a[0] - b[0]; // return x1 < x2
        }); // TC: O(N x log(N))
        int countPairs = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int maxY = -1; // track maximum y coordinate towards right
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                int x1 = points[i][0];
                int x2 = points[j][0];
                int y1 = points[i][1];
                int y2 = points[j][1];
                if (y1 >= y2) {
                    if (y2 > maxY) { // if not then it will block the rectangle
                        maxY = y2;
                        countPairs++;
                    }
                }
            }
        }
        return countPairs;
    }
}
