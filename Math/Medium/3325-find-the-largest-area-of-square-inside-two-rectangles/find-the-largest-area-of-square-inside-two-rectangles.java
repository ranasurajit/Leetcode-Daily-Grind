class Solution {
    /**
     * Approach : Using Array + Math Approach
     *
     * TC: O(N²)
     * SC: O(1)
     */
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxSide = 0L;
        for (int i = 0; i < n - 1; i++) {     // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                long leftMax = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                long rightMin = Math.min(topRight[i][0], topRight[j][0]);
                long bottomMax = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                long topMin = Math.min(topRight[i][1], topRight[j][1]);
                long width = rightMin - leftMax;
                long height = topMin - bottomMax;
                maxSide = Math.max(maxSide, Math.min(width, height));
            }
        }
        return maxSide * maxSide;
    }
}
