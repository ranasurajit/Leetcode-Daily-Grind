class Solution {
    /**
     * Approach : Using Binary Search on Answers Approach
     *
     * TC : O(p) + O(p x log(p)) + O(k x p x log(p) x log(side))
     *      ~ O(k x p x log(p) x log(side))
     * SC : O(p)
     */
    public int maxDistance(int side, int[][] points, int k) {
        /**
         * Intuition: the problem days to maximize the minimum 
         * Manhattan distance which clearly hints us to use
         * Binary Search on Answers
         */
        long low = 0L;
        /**
         * high is the longest Manhattan distance which is the 
         * top-right vertex (side, side) so Manhattan distance
         * = side + side = 2 * side
         */
        long high = 2L * side;
        /**
         * we need to create the answers array from points
         * such that we can think of transforming 2-D points
         * on a 1-D number line (acheived after sorting)
         */
        List<Long> sortedPoints = new ArrayList<>(); // SC : O(p)
        for (int[] point : points) { // TC : O(p)
            int x = point[0];
            int y = point[1];
            long pos = 0L;
            if (y == 0) {
                // bottom edge of square
                pos = (long) x;
            } else if (x == side) {
                // right edge of square
                pos = (long) side + y;
            } else if (y == side) {
                // top edge of square
                pos = 3 * (long) side - x;
            } else {
                // left edge of square
                pos = 4 * (long) side - y;
            }
            sortedPoints.add(pos);
        }
        Collections.sort(sortedPoints); // TC : O(p x log(p))
        while (low <= high) { // TC : O(log(side))
            long mid = low + (high - low) / 2;
            boolean canPlace =
                isPossibleToPlaceKPoints(sortedPoints, 
                    side, k, mid); // TC : O(k x p x log(p))
            if (canPlace) {
                // try to increase the span
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) high;
    }

    /**
     * Using Simulation + Binary Search (Lower Bound) Approach
     *
     * TC : O(k x p x log(p))
     * SC : O(1)
     */
    private boolean isPossibleToPlaceKPoints(List<Long> sortedPoints,
        int side, int k, long dist) {
        long peri = side * 4l;
        for(int i = 0; i < sortedPoints.size(); i++) { // TC : O(p)
            long start = sortedPoints.get(i);
            long end = start + peri - dist;

            for(int j = 0; j < k - 1; j++) { // TC : O(k)
                int next = lowerBound(sortedPoints, start + dist); // TC : O(log(p))
                if(next >= sortedPoints.size() || sortedPoints.get(next) > end) {
                    start = -1;
                    break;
                }
                start = sortedPoints.get(next);                    
            }
            if(start >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Using Binary Search (Lower Bound) Approach
     *
     * TC : O(log(p))
     * SC : O(1)
     */
    private int lowerBound(List<Long> sortedPoints, long target) {
        int low = 0;
        int high = sortedPoints.size() - 1;
        while (low <= high) { // TC : O(log(p))
            int mid = low + (high - low) / 2;
            if (sortedPoints.get(mid) >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
