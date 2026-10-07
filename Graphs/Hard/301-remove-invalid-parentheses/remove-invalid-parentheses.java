class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            String current = queue.poll();
            // If current string is valid, we have found
            // the minimum-removal level.
            if (isValid(current)) {
                result.add(current);
                found = true;
            }
            // Don't generate strings with more removals
            // once a valid string has been found.
            if (found) {
                continue;
            }
            // Try removing every parenthesis
            for (int i = 0; i < current.length(); i++) {
                char ch = current.charAt(i);
                // We only remove parentheses, never letters.
                if (ch != '(' && ch != ')') {
                    continue;
                }
                String next =
                    current.substring(0, i) +
                    current.substring(i + 1);
                // Avoid duplicate strings.
                if (visited.add(next)) {
                    queue.offer(next);
                }
            }
        }
        return result;
    }

    private boolean isValid(String s) {
        int balance = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;
            }
            // More closing parentheses than opening
            // at any point => invalid.
            if (balance < 0) {
                return false;
            }
        }
        // Every opening parenthesis must be closed.
        return balance == 0;
    }
}
