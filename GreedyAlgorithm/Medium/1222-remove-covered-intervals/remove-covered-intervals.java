class Solution {
    /**
     * Approach II : Using Greedy + Sorting Approach
     *
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(1)
     */
    public int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        }); // TC : O(n x log(n))
        int count = 0;
        int lastEnd = -1;
        for (int i = 0; i < n; i++) { // TC : O(n)
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (end <= lastEnd) {
                continue;
            }
            count++;
            lastEnd = end;
        }
        return count;
    }

    /**
     * Approach I : Using Greedy + Sorting + Stack Approach
     *
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(n)
     */
    public int removeCoveredIntervalsUsingStack(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        }); // TC : O(n x log(n))
        Stack<int[]> st = new Stack<>(); // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (!st.isEmpty()) {
                int[] prev = st.peek();
                if (start >= prev[0] && end <= prev[1]) {
                    // current interval is covered
                    continue;
                } else {
                    st.push(intervals[i]);
                }
            } else {
                st.push(intervals[i]);
            }
        }
        return st.size();
    }
}
