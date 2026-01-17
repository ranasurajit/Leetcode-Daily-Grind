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
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                maxSide = Math.max(maxSide, getSquareSide(bottomLeft, topRight, i, j));
            }
        }
        return maxSide * maxSide;
    }

    /**
     * Using Enumeration Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    private long getSquareSide(int[][] bottomLeft, int[][] topRight, int i, int j) {
        int[] bottomLeftA = bottomLeft[i];
        int[] bottomLeftB = bottomLeft[j];
        int[] topRightA = topRight[i];
        int[] topRightB = topRight[j];
        long leftMax = Math.max(bottomLeftA[0], bottomLeftB[0]);
        long rightMin = Math.min(topRightA[0], topRightB[0]);
        long bottomMax = Math.max(bottomLeftA[1], bottomLeftB[1]);
        long topMin = Math.min(topRightA[1], topRightB[1]);
        long width = rightMin - leftMax;
        long height = topMin - bottomMax;
        if (width <= 0 || height <= 0) {
            return 0;
        }
        return Math.min(width, height);
    }
}
