class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(M x N) + O(M x N) ~ O(M x N)
     * SC: O(M x N) + O(N) ~ O(M x N)
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n]; // SC: O(N)
        int maxArea = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (i == 0) {
                    heights[j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    if (matrix[i][j] == '0') {
                        heights[j] = 0;
                    } else {
                        heights[j] += matrix[i][j] == '1' ? 1 : 0;
                    }
                }
            }
            maxArea = Math.max(maxArea, maxAreaHistogram(heights, n)); // TC: O(N), SC: O(N)
        }
        return maxArea;
    }

    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    private int maxAreaHistogram(int[] heights, int n) {
        int maxArea = 0;
        int[] pse = new int[n]; // SC: O(N)
        int[] nse = new int[n]; // SC: O(N)
        Deque<Integer> deque = new ArrayDeque<Integer>(); // SC: O(N)
        previousSmallerElement(heights, n, pse, deque); // TC: O(N), SC: O(1)
        deque.clear();
        nextSmallerElement(heights, n, nse, deque); // TC: O(N), SC: O(1)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int leftIdx = pse[i] + 1;
            int rightIdx = nse[i] - 1;
            int currentArea = (rightIdx - leftIdx + 1) * heights[i];
            maxArea = Math.max(maxArea, currentArea);
        }
        return maxArea;
    }

    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private void previousSmallerElement(int[] heights, int n, int[] pse, Deque<Integer> deque) {
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!deque.isEmpty() && heights[i] <= heights[deque.peek()]) {
                deque.pop();
            }
            pse[i] = deque.isEmpty() ? -1 : deque.peek();
            deque.push(i);
        }
    }

    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private void nextSmallerElement(int[] heights, int n, int[] nse, Deque<Integer> deque) {
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!deque.isEmpty() && heights[i] <= heights[deque.peek()]) {
                deque.pop();
            }
            nse[i] = deque.isEmpty() ? n : deque.peek();
            deque.push(i);
        }
    }
}
