class Solution {
    /**
     * Approach II : Using Monotonic Deque Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] pse = new int[n]; // SC: O(N)
        int[] nse = new int[n]; // SC: O(N)
        Deque<Integer> st = new ArrayDeque<Integer>(); // SC: O(N)
        previousSmallerElement(heights, pse, n, st); // TC: O(N), SC: O(1)
        st.clear();
        nextSmallerElement(heights, nse, n, st); // TC: O(N), SC: O(1)
        int maxArea = 0;
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
    private void previousSmallerElement(int[] heights, int[] pse, int n, Deque<Integer> st) {
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && heights[i] <= heights[st.peek()]) {
                st.pop();
            }
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
    }

    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private void nextSmallerElement(int[] heights, int[] nse, int n, Deque<Integer> st) {
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && heights[i] <= heights[st.peek()]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
    }

    /**
     * Approach I : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public int largestRectangleAreaUsingStack(int[] heights) {
        int n = heights.length;
        int[] pse = new int[n]; // SC: O(N)
        int[] nse = new int[n]; // SC: O(N)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        previousSmallerElementStack(heights, pse, n, st); // TC: O(N), SC: O(1)
        st.clear();
        nextSmallerElementStack(heights, nse, n, st); // TC: O(N), SC: O(1)
        int maxArea = 0;
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
    private void previousSmallerElementStack(int[] heights, int[] pse, int n, Stack<Integer> st) {
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && heights[i] <= heights[st.peek()]) {
                st.pop();
            }
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
    }

    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private void nextSmallerElementStack(int[] heights, int[] nse, int n, Stack<Integer> st) {
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && heights[i] <= heights[st.peek()]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
    }
}
