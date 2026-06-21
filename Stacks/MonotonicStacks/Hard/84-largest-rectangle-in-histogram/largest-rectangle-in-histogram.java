class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     *
     * TC : O(n) + O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) + O(n) ~ O(n)
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        /**
         * we need to expand the current height heights[i]
         * towards left and right so see how much it can
         * expand with the same height
         */
        Stack<Integer> st = new Stack<>(); // SC : O(n)
        int[] pse =
            getPreviousSmallerElement(heights, n, st); // TC : O(n), SC : O(n)
        st.clear();
        int[] nse =
            getNextSmallerElement(heights, n, st); // TC : O(n), SC : O(n)
        int maxArea = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            /**
             * heights[i] can be expanded till index 
             * pse[i] + 1 in the left and
             * nse[i] - 1 in the right
             */
            int currentArea = heights[i] * ((nse[i] - 1) - (pse[i] + 1) + 1);
            maxArea = Math.max(maxArea, currentArea);
        }
        return maxArea;
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    private int[] getPreviousSmallerElement(int[] heights, int n,
        Stack<Integer> st) {
        int[] pse = new int[n]; // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            while (!st.isEmpty() && heights[i] <= heights[st.peek()]) {
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
     * TC : O(n)
     * SC : O(n)
     */
    private int[] getNextSmallerElement(int[] heights, int n,
        Stack<Integer> st) {
        int[] nse = new int[n]; // SC : O(n)
        for (int i = n - 1; i >= 0; i--) { // TC : O(n)
            while (!st.isEmpty() && heights[i] <= heights[st.peek()]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return nse;
    }
}
