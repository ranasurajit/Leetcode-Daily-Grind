class Solution {
    /**
     * Approach II : Using Optimized (Monotonic Stack (Monotonic Decreasing)) Approach
     *
     * TC: O(N) + O(M) ~ O(N + M)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        int[] nge = new int[n];       // SC: O(N)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        int i = n - 1;
        while (i >= 0) { // TC: O(N)
            while (!st.isEmpty() && st.peek() <= nums2[i]) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(nums2[i]);
            map.put(nums2[i], i);
            i--;
        }
        int[] result = new int[m];
        for (i = 0; i < m; i++) { // TC: O(M)
            result[i] = nge[map.get(nums1[i])];
        }
        return result;
    }

    /**
     * Approach I : Using Monotonic Stack (Monotonic Decreasing) Approach
     *
     * TC: O(N) + O(N) + O(M) ~ O(N + M)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public int[] nextGreaterElementMonotonicStack(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            map.put(nums2[i], i);
        }
        int[] nge = new int[n];       // SC: O(N)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        int i = n - 1;
        while (i >= 0) { // TC: O(N)
            while (!st.isEmpty() && st.peek() <= nums2[i]) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(nums2[i]);
            i--;
        }
        int[] result = new int[m];
        for (i = 0; i < m; i++) { // TC: O(M)
            result[i] = nge[map.get(nums1[i])];
        }
        return result;
    }
}
