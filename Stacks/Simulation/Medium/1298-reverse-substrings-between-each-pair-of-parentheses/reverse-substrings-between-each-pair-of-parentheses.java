class Solution {
    public String reverseParentheses(String s) {
        String result = "";
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(result.length());
            } else if (s.charAt(i) == ')') {
                int start = st.pop();
                result = reverse(result, start, result.length() - 1);
            } else {
                result += s.charAt(i);
            }
        }
        return result;
    }

    private String reverse(String s, int start, int end) {
        char[] ch = s.toCharArray();
        while (start < end) {
            char temp = ch[end];
            ch[end] = ch[start];
            ch[start] = temp;
            start++;
            end--;
        }
        return new String(ch);
    }
}
