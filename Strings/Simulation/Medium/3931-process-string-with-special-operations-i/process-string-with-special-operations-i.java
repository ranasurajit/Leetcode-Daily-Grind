class Solution {
    /**
     * Approach II : Using Optimal (Lazy Deque and Reverse Flag) Approach
     *
     * TC : O(n + LIMIT)
     * SC : O(LIMIT)
     */
    public String processStr(String s) {
        int n = s.length();
        int LIMIT = (int) 1e8;
        Deque<Character> deque = new ArrayDeque<>();
        boolean isReversed = false;
        for (char ch : s.toCharArray()) { // TC : O(n)
            if (ch == '*') {
                if (!deque.isEmpty()) {
                    if (isReversed) {
                        deque.pollFirst(); // TC : O(1)
                    } else {
                        deque.pollLast(); // TC : O(1)
                    }
                }
            } else if (ch == '#') {
                Deque<Character> copy = new ArrayDeque<>(deque);
                if (isReversed) {
                    while (!copy.isEmpty()) {
                        deque.offerFirst(copy.pollLast());
                    }
                } else {
                    deque.addAll(copy);
                }
            } else if (ch == '%') {
                isReversed = !isReversed; // TC : O(1)
            } else {
                // ch is lowercase letter 
                if (isReversed) {
                    deque.offerFirst(ch); // TC : O(1)
                } else {
                    deque.offerLast(ch); // TC : O(1)
                }
            }
            // if (deque.size() > LIMIT) {
            //     break;
            // }
        }
        StringBuilder sb = new StringBuilder();
        if (isReversed) {
            while (!deque.isEmpty()) { // TC : O(m)
                sb.append(deque.pollLast());
            }
        } else {
            while (!deque.isEmpty()) { // TC : O(m)
                sb.append(deque.pollFirst());
            }
        }
        return sb.toString();
    }

    /**
     * Approach I : Using Brute-Force (String Simulation) Approach
     *
     * TC : O(2ⁿ)
     * SC : O(2ⁿ)
     */
    public String processStrBruteForce(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) { // TC : O(n)
            char ch = s.charAt(i);
            if (ch == '*') {
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1); // TC : O(1)
                }
            } else if (ch == '#') {
                sb.append(sb.toString()); // TC : O(k)
            } else if (ch == '%') {
                sb.reverse(); // TC : O(k)
            } else {
                // ch is lowercase letter 
                sb.append(ch); // TC : O(1)
            }
        }
        return sb.toString();
    }
}
