class Solution {
    /**
     * Approach : Using Math + Coordinate Geometry (Shoelace's Formula)
     *
     * TC: O(N x N x N)
     * SC: O(1)
     */
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0d;
        int n = points.length;
        for (int i = 0; i < n - 2; i++) {         // TC: O(N)
            double x1 = (double) points[i][0];
            double y1 = (double) points[i][1];
            for (int j = i + 1; j < n - 1; j++) { // TC: O(N)
                double x2 = (double) points[j][0];
                double y2 = (double) points[j][1];
                for (int k = j + 1; k < n; k++) { // TC: O(N)
                    double x3 = (double) points[k][0];
                    double y3 = (double) points[k][1];
                    maxArea = Math.max(maxArea, computeCurrentArea(x1, y1, x2, y2, x3, y3)); // TC: O(1)
                }
            }
        }
        return maxArea;
    }

    /**
     * Using Math + Coordinate Geometry (Shoelace's Formula)
     *
     * TC: O(1)
     * SC: O(1)
     */
    private double computeCurrentArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        // Using shoelace formula formula : ½ |x₁(y₂ – y₃) + x₂(y₃ – y₁) + x₃(y₁ – y₂)|
        return 0.5 * Math.abs((x1 * (y2 - y3)) + (x2 * (y3 - y1)) + (x3 * (y1 - y2))); 
    }
}
