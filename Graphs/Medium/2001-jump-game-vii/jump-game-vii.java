class Solution {
    /**
     * Approach IV : Using Tabulation Approach
     *
     * TC : O(n), where r = Max Range(maxJump - minJump)
     * SC : O(n)
     * - O(n) - dp memory
     *
     * Accepted (143 / 143 testcases passed)
     */
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n]; // SC : O(n)
        dp[0] = true; // possible to reach from index '0' to '0'
        int reachableCount = 0;
        for (int i = 1; i < n; i++) { // TC : O(n)
            if (i - minJump >= 0 && dp[i - minJump]) {
                // sliding next window
                reachableCount++;
            }
            if (i - maxJump - 1 >= 0 && dp[i - maxJump - 1]) {
                // removing last index from leaving window
                reachableCount--;
            }
            dp[i] = reachableCount > 0 && s.charAt(i) == '0';
        }
        return dp[n - 1];
    }

    /**
     * Approach III : Using Memoization Approach
     *
     * TC : O(r x n), where r = Max Range(maxJump - minJump)
     * SC : O(n) + O(n)
     * - O(n) - memoization memory
     * - O(n) - recursion stack
     *
     * Time Limit Exceeded (117 / 143 testcases passed)
     */
    public boolean canReachMemoization(String s, int minJump, int maxJump) {
        int n = s.length();
        Boolean[] memo = new Boolean[n]; // SC : O(n)
        return solveMemoization(0, n, s, minJump, maxJump, memo);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(r x n), where r = Max Range(maxJump - minJump)
     * SC : O(n)
     */
    private boolean solveMemoization(int idx, int n, String s,
        int minJump, int maxJump, Boolean[] memo) {
        // Base Case
        if (idx == n - 1) {
            return true;
        }
        // Memoization Check
        if (memo[idx] != null) {
            return memo[idx];
        }
        // Recursion Calls
        int start = idx + minJump;
        int end = Math.min(idx + maxJump, n - 1);
        for (int i = start; i <= end; i++) { // TC : O(r)
            if (s.charAt(i) == '0' &&
                solveMemoization(i, n, s, minJump, maxJump, memo)) {
                return memo[idx] = true;
            }
        }
        return memo[idx] = false;
    }

    /**
     * Approach II : Using Recursion Approach
     *
     * TC : O(rⁿ), where r = Max Range(maxJump - minJump)
     * SC : O(n)
     * - O(n) - recursion stack
     */
    public boolean canReachRecursion(String s, int minJump, int maxJump) {
        int n = s.length();
        return solveRecursion(0, n, s, minJump, maxJump);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(rⁿ), where r = Max Range(maxJump - minJump)
     * SC : O(n)
     */
    private boolean solveRecursion(int idx, int n, String s, int minJump,
        int maxJump) {
        // Base Case
        if (idx == n - 1) {
            return true;
        }
        // Recursion Calls
        int start = idx + minJump;
        int end = Math.min(idx + maxJump, n - 1);
        for (int i = start; i <= end; i++) { // TC : O(r)
            if (s.charAt(i) == '0' && solveRecursion(i, n, s, minJump, maxJump)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Approach I : Using Graph + BFS Approach
     *
     * TC : O(n)
     * SC : O(n) + O(n) ~ O(n)
     *
     * Accepted (143 / 143 testcases passed)
     */
    public boolean canReachBFS(String s, int minJump, int maxJump) {
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
