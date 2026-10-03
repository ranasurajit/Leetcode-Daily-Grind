//Approach-2 (Using Stack)
//T.C : O(n) - 1 Pass
//S.C : O(n)
class Solution {
    public int longestValidParentheses(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);

        int maxL = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else {
                st.pop();
                if (st.isEmpty()) {
                    st.push(i);
                } else {
                    maxL = Math.max(maxL, i - st.peek());
                }
            }
        }
        return maxL;
    }
}
