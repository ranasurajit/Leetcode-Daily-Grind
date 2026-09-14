class Solution {
    /**
     * Approach : Using Math + Geometry Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int xOverlap = Math.min(rec1[2], rec2[2]) - Math.max(rec1[0], rec2[0]);
        int yOverlap = Math.min(rec1[3], rec2[3]) - Math.max(rec1[1], rec2[1]);
        return xOverlap > 0 && yOverlap > 0;
    }
}
