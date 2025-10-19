class Solution {
    /**
     * Approach : Using Graph BFS Approach
     *
     * TC: O(N x N)
     * SC: O(N x N) + O(N) + O(N) ~ O(N x N)
     */
    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        Set<String> visited = new HashSet<String>();    // SC: O(N)
        Queue<String> queue = new LinkedList<String>(); // SC: O(N)
        queue.offer(s);
        visited.add(s);
        String smallest = s;
        while (!queue.isEmpty()) { // TC: O(N)
            String current = queue.poll();
            if (smallest.compareTo(current) > 0) {
                smallest = current;
            }
            // operation 1
            String added = performAddOperation(current, n, a); // TC: O(N), SC: O(N)
            if (visited.add(added)) {
                queue.offer(added);
            }
            // operation 2
            String rotate = performRotateOperation(current, n, b); // TC: O(N), SC: O(N)
            if (visited.add(rotate)) {
                queue.offer(rotate);
            }
        }
        return smallest;
    }

    /**
     * Using String Simulation Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private String performRotateOperation(String current, int n, int b) {
        b = b % n;
        return current.substring(n - b) + current.substring(0, n - b);
    }

    /**
     * Using String Simulation Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private String performAddOperation(String current, int n, int a) {
        // add operation performed on odd indices
        char[] currentStr = current.toCharArray(); // SC: O(N)
        for (int i = 1; i < n; i += 2) { // TC: O(N / 2)
            currentStr[i] = (char)(((currentStr[i] - '0') + a) % 10 + '0');
        }
        return String.valueOf(currentStr);
    }
}
