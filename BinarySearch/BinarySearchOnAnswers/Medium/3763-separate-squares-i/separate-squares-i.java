class Solution {
    /**
     * Approach II : Using Binary Search on Answers (Optimal) Approach
     *
     * TC: O(N) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(1)
     */
    public double separateSquares(int[][] squares) {
        int n = squares.length;
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;
        double totalArea = 0d;
        for (int[] sq : squares) { // TC: O(N)
            low = Math.min(low, sq[1]);
            high = Math.max(high, sq[1] + sq[2]);
            totalArea += (double) sq[2] * (double) sq[2];
        }
        double halfArea = totalArea / 2;
        double delta = 1e-6;
        // this is a floating pointer binary search
        while (high - low > delta) { // TC: O(log(N))
            double mid = low + (high - low) / 2;
            double belowArea = getBelowAreaOfSquares(squares, mid); // TC: O(N)
            if (belowArea >= halfArea) {
                // we need to decrease the mid towards low
                high = mid;
            } else {
                // we need to increase the mid towards high
                low = mid;
            }
        }
        return low;
    }

    /**
     * Using Array Simulation + Math Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private double getBelowAreaOfSquares(int[][] squares, double mid) {
        double belowYAreas = 0d;
        for (int[] sq : squares) { // TC: O(N)
            double yLow = (double) sq[1];
            double yHigh = (double) sq[1] + (double) sq[2];
            if (mid > yLow && mid < yHigh) {
                double heightBelow = (mid - yLow);
                belowYAreas += heightBelow * sq[2];
            } else if (yHigh <= mid) {
                belowYAreas += (double) sq[2] * (double) sq[2];;
            }
        }
        return belowYAreas;
    }

    /**
     * Approach I : Using Binary Search on Answers (Better) Approach
     *
     * TC: O(N) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(1)
     */
    public double separateSquaresBetter(int[][] squares) {
        int n = squares.length;
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;
        double highDelta = 0;
        for (int[] sq : squares) { // TC: O(N)
            low = Math.min(low, sq[1]);
            high = Math.max(high, sq[1] + sq[2]);
        }
        double delta = 1e-6;
        // this is a floating pointer binary search
        while (high - low > delta) { // TC: O(log(N))
            double mid = low + (high - low) / 2;
            double[] totalArea = getAreaOfSquares(squares, mid); // TC: O(N)
            if (totalArea[0] >= totalArea[1]) {
                // we need to decrease the mid towards low
                high = mid;
            } else {
                // we need to increase the mid towards high
                low = mid;
            }
        }
        return low;
    }

    /**
     * Using Array Simulation + Math Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private double[] getAreaOfSquares(int[][] squares, double mid) {
        double belowYAreas = 0d;
        double aboveYAreas = 0d;
        for (int[] sq : squares) { // TC: O(N)
            double yHigh = (double) sq[1] + (double) sq[2];
            double yLow = (double) sq[1];
            double currentArea = (double) sq[2] * (double) sq[2];
            if (mid > yLow && mid < yHigh) {
                double heightBelow = (mid - yLow);
                double heightAbove = (yHigh - mid);
                belowYAreas += heightBelow * sq[2];
                aboveYAreas += heightAbove * sq[2];
            } else if (yHigh <= mid) {
                belowYAreas += currentArea;
            } else if (yLow >= mid) {
                aboveYAreas += currentArea;
            }
        }
        return new double[] { belowYAreas, aboveYAreas };
    }
}
