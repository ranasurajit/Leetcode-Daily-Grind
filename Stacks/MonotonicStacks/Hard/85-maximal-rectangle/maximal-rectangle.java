class Solution {
    /**
     * Approach II : Using Monotonic Deque Approach
     *
     * TC: O(M x 2 x N) ~ O(M x N)
     * SC: O(N) + O(M x N) ~ O(M x N)
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] height = new int[n]; // SC: O(N)
        int maxArea = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (matrix[i][j] == '1') {
                    height[j] += 1;
                } else {
                    height[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, maxAreaHistogramOptimal(height, n)); // TC: O(N), SC: O(N)
        }
        return maxArea;
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    private int maxAreaHistogramOptimal(int[] height, int n) {
        int maxRowArea = 0;
        Deque<Integer> deque = new LinkedList<Integer>();                 // SC: O(N)
        int[] pse = previousSmallerElementIndexOptimal(height, n, deque); // TC: O(N), SC: O(N)
        deque.clear();
        int[] nse = nextSmallerElementIndexOptimal(height, n, deque);     // TC: O(N), SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int endIndex = nse[i] - 1;
            int startIndex = pse[i] + 1;
            int currentArea = (endIndex - startIndex + 1) * height[i];
            maxRowArea = Math.max(maxRowArea, currentArea);
        }
        return maxRowArea;
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int[] previousSmallerElementIndexOptimal(int[] row, int n, Deque<Integer> deque) {
        int[] pse = new int[n];
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && row[i] <= row[deque.peek()]) {
                deque.pop();
            }
            pse[i] = deque.isEmpty() ? -1 : deque.peek();
            deque.push(i);
        }
        return pse;
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int[] nextSmallerElementIndexOptimal(int[] row, int n, Deque<Integer> deque) {
        int[] nse = new int[n];
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!deque.isEmpty() && row[i] <= row[deque.peek()]) {
                deque.pop();
            }
            nse[i] = deque.isEmpty() ? n : deque.peek();
            deque.push(i);
        }
        return nse;
    }

    /**
     * Approach I : Using Monotonic Stack Approach
     *
     * TC: O(M x 2 x N) ~ O(M x N)
     * SC: O(N) + O(M x N) ~ O(M x N)
     */
    public int maximalRectangleUsingStack(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] height = new int[n]; // SC: O(N)
        int maxArea = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (matrix[i][j] == '1') {
                    height[j] += 1;
                } else {
                    height[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, maxAreaHistogram(height, n)); // TC: O(N), SC: O(N)
        }
        return maxArea;
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    private int maxAreaHistogram(int[] height, int n) {
        int maxRowArea = 0;
        Stack<Integer> st = new Stack<Integer>();               // SC: O(N)
        int[] pse = previousSmallerElementIndex(height, n, st); // TC: O(N), SC: O(N)
        st.clear();
        int[] nse = nextSmallerElementIndex(height, n, st);     // TC: O(N), SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int endIndex = nse[i] - 1;
            int startIndex = pse[i] + 1;
            int currentArea = (endIndex - startIndex + 1) * height[i];
            maxRowArea = Math.max(maxRowArea, currentArea);
        }
        return maxRowArea;
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int[] previousSmallerElementIndex(int[] row, int n, Stack<Integer> st) {
        int[] pse = new int[n];
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && row[i] <= row[st.peek()]) {
                st.pop();
            }
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pse;
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int[] nextSmallerElementIndex(int[] row, int n, Stack<Integer> st) {
        int[] nse = new int[n];
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && row[i] <= row[st.peek()]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return nse;
    }
}
