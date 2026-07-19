class Solution {
    /**
     * Approach III : Using Monotonic Deque Approach
     *
     * TC : O(n) + O(n) + O(k) ~ O(n)
     * SC : O(k) + O(26) ~ O(n)
     *
     * Accepted (68 / 68 testcases passed)
     */
    public String smallestSubsequence(String s) {
        int n = s.length();
        int[] lastIndex = new int[26]; // SC : O(26)
        for (int i = 0; i < n; i++) {  // TC : O(n)
            int charIdx = s.charAt(i) - 'a';
            lastIndex[charIdx] = i;
        }
        /**
         * now we need a Set to check if a character is used already
         * and a Stacl to store the best smallest subsequence of 
         * distinct characters till any index 'i'
         */
        boolean[] visited = new boolean[26]; // SC : O(26)
        Deque<Character> deque = new ArrayDeque<>(); // SC : O(k)
        for (int i = 0; i < n; i++) { // TC : O(n)
            char ch = s.charAt(i);
            int chIdx = ch - 'a';
            if (visited[chIdx]) {
                // already the character has been used
                continue;
            }
            while (!deque.isEmpty() && deque.peekLast() > ch && 
                lastIndex[deque.peekLast() - 'a'] > i) {
                /**
                 * we can remove a Character from stack if current
                 * character at index 'i' is smaller than Stack's top
                 * and is occuring in String 's' at a further index
                 */
                int removedIdx = deque.pollLast() - 'a'; 
                visited[removedIdx] = false;
            }
            deque.addLast(ch);
            visited[chIdx] = true;
        }
        StringBuilder sb = new StringBuilder(); // SC : O(k)
        while (!deque.isEmpty()) {     // TC : O(k)
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }

    /**
     * Approach II : Using Monotonic Stack Approach
     *
     * TC : O(n) + O(n) + O(k) ~ O(n)
     * SC : O(k) + O(26) ~ O(n)
     *
     * Accepted (68 / 68 testcases passed)
     */
    public String smallestSubsequenceMonotonicStack(String s) {
        int n = s.length();
        int[] lastIndex = new int[26]; // SC : O(26)
        for (int i = 0; i < n; i++) {  // TC : O(n)
            int charIdx = s.charAt(i) - 'a';
            lastIndex[charIdx] = i;
        }
        /**
         * now we need a Set to check if a character is used already
         * and a Stacl to store the best smallest subsequence of 
         * distinct characters till any index 'i'
         */
        boolean[] visited = new boolean[26]; // SC : O(26)
        Stack<Character> st = new Stack<>(); // SC : O(k)
        for (int i = 0; i < n; i++) { // TC : O(n)
            char ch = s.charAt(i);
            int chIdx = ch - 'a';
            if (visited[chIdx]) {
                // already the character has been used
                continue;
            }
            while (!st.isEmpty() && st.peek() > ch && 
                lastIndex[st.peek() - 'a'] > i) {
                /**
                 * we can remove a Character from stack if current
                 * character at index 'i' is smaller than Stack's top
                 * and is occuring in String 's' at a further index
                 */
                int removedIdx = st.pop() - 'a'; 
                visited[removedIdx] = false;
            }
            st.push(ch);
            visited[chIdx] = true;
        }
        int k = st.size();
        char[] chars = new char[k]; // SC : O(k)
        int idx = k - 1;
        while (!st.isEmpty()) {     // TC : O(k)
            chars[idx--] = st.pop();
        }
        return String.valueOf(chars);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(2ⁿ) + O(n) ~ O(2ⁿ)
     * SC : O(k) + O(26) ~ O(k)
     *
     * Time Limit Exceeded (33 / 68 testcases passed)
     */
    public String smallestSubsequenceRecursion(String s) {
        int n = s.length();
        int[] freq = new int[26]; // SC : O(26)
        int k = 0; // count of distinct characters in String 's'
        for (int i = 0; i < n; i++) { // TC : O(n)
            int charIdx = s.charAt(i) - 'a';
            if (freq[charIdx] == 0) {
                k++;
            }
            freq[charIdx]++;
        }
        /**
         * now our subsequence size should be k
         */
        StringBuilder sb = new StringBuilder();
        String[] result = { "" };
        Set<Character> set = new HashSet<>();
        solve(0, n, k, sb, s, result, set);
        return result[0];
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(2ⁿ)
     * SC : O(k)
     */
    private void solve(int idx, int n, int k,
        StringBuilder sb, String s, String[] result, Set<Character> set) {
        // Base Case
        if (k < 0) {
            return;
        }
        if (n - idx < k) {
            // pruning, we know it is not possible to generate anything further
            return;
        }
        if (idx == n) {
            if (k == 0) {
                String str = sb.toString();
                if (result[0].equals("")) {
                    result[0] = str;
                } else {
                    if (str.compareTo(result[0]) < 0) {
                        result[0] = str;
                    }
                }
            }
            return;
        }
        // Recursion Calls
        // pick or skip
        // skip
        solve(idx + 1, n, k, sb, s, result, set);
        // pick - we can pick a character if it is not present in 'set'
        char ch = s.charAt(idx);
        if (!set.contains(ch)) {
            sb.append(ch); // modify
            set.add(ch);
            solve(idx + 1, n, k - 1, sb, s, result, set); // explore
            sb.setLength(sb.length() - 1); // backtrack
            set.remove(ch); // backtrack
        }
    }
}
