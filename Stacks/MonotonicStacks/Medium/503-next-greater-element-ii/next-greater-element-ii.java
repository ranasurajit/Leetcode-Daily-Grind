class Solution {
    /**
     * Approach : Using Monotonic Stack (Monotonic Decreasing) Approach
     *
     * TC: O(N) + O(2 x N) + O(N) ~ O(N)
     * SC: O(2 x N) + O(2 x N) + O(2 x N) ~ O(N)
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] cirNums = new int[2 * n]; // SC: O(2 x N)
        for (int i = 0; i < n; i++) {   // TC: O(N)
            cirNums[i] = nums[i];
            cirNums[n + i] = nums[i];
        }
        Stack<Integer> st = new Stack<Integer>(); // SC: O(2 x N)
        int[] nge = new int[2 * n]; // SC: O(2 x N)
        for (int i = 2 * n - 1; i >= 0; i--) { // TC: O(2 x N)
            while (!st.isEmpty() && st.peek() <= cirNums[i]) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(cirNums[i]);
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {   // TC: O(N)
            result[i] = nge[i];
        }
        return result;
    }
}
