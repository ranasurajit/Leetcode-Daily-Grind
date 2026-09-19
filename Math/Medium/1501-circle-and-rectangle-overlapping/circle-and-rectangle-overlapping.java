class Solution {
    /**
     * Approach : Using Math + Geometry Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1,
        int y1, int x2, int y2) {
        /**
         * find the closest point of rectangle to the center 
         * of circle in both (x, y) coordinates
         */
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));
        /**
         * find the distance of the closest point 
         */
        int dx = xCenter - closestX;
        int dy = yCenter - closestY;
        /**
         * the circle and rectangle will ovrelap if the squared
         * distance of coordinated distance <= squared radius
         */
         return dx * dx + dy * dy <= radius * radius;
    }
}
