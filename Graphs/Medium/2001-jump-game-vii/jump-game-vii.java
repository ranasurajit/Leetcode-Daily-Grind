class Solution {
    /**
     * Approach : Using Graph + BFS Approach
     *
     * TC : O(n)
     * SC : O(n) + O(n) ~ O(n)
     *
     * Accepted (143 / 143 testcases passed)
     */
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        /**
         * Here we need to return if there is a path or way
         * by which we can reach '(n - 1)'' index from '0' 
         * index. So, we can consider this as a Graph with 
         * vertex = indexes of String 's' and we can perform
         * BFS / DFS to check if '(n - 1)' vertex is reachable
         * from vertex '0'
         */
        boolean[] visited = new boolean[n];        // SC : O(n)
        Queue<Integer> queue = new LinkedList<>(); // SC : O(n)
        queue.offer(0);
        visited[0] = true;
        int nextStartPoint = 0;
        while (!queue.isEmpty()) { // TC : O(n)
            Integer u = queue.poll();
            if (u == n - 1) {
                return true;
            }
            int start = Math.max(nextStartPoint, u + minJump);
            int end = Math.min(u + maxJump, n - 1);
            for (int v = start; v <= end; v++) {
                if (!visited[v] && s.charAt(v) == '0') {
                    visited[v] = true;
                    queue.offer(v);
                }
            }
            nextStartPoint = end + 1;
        }
        return false;
    }
}
