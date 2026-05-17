class Solution {
    /**
     * Approach III : Using Graph (DFS) Approach
     *
     * TC : O(n)
     * SC : O(n) + O(n)
     * - O(n) - 'visited' array and DFS recursion stack
     */
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n]; // SC : O(n)
        return dfsGraph(start, n, arr, visited);
    }

    /**
     * Using Graph (DFS) Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    private boolean dfsGraph(int u, int n, int[] arr, boolean[] visited) {
        // Base Case
        if (u < 0 || u >= n || visited[u]) {
            // out of bounds check or if already visited
            return false;
        }
        if (arr[u] == 0) {
            return true;
        }
        visited[u] = true;
        if (dfsGraph(u + arr[u], n, arr, visited) || 
            dfsGraph(u - arr[u], n, arr, visited)) {
            return true;
        }
        return false;
    }

    /**
     * Approach II : Using Graph (BFS) Approach
     *
     * TC : O(n)
     * SC : O(n) + O(n)
     * - O(n) - 'visited' and 'queue' memory
     */
    public boolean canReachBFS(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n]; // SC : O(n)
        Queue<Integer> queue = new LinkedList<>(); // SC : O(n)
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) { // TC : O(n)
            Integer u = queue.poll();
            if (arr[u] == 0) {
                return true;
            }
            int fwdIdx = u + arr[u];
            int revIdx = u - arr[u];
            if (fwdIdx < n && !visited[fwdIdx]) {
                visited[fwdIdx] = true;
                queue.offer(fwdIdx);
            }
            if (revIdx >= 0 && !visited[revIdx]) {
                visited[revIdx] = true;
                queue.offer(revIdx);
            }
        }
        return false;
    }

    /**
     * Approach I : Using Recursion (DFS + Visited) Approach
     *
     * TC : O(n)
     * SC : O(n) + O(n)
     * - O(n) - 'visited' array and recursion stack
     */
    public boolean canReachRecursiveDFS(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n]; // SC : O(n)
        return solveRecursion(start, n, arr, visited);
    }

    /**
     * Using Recursion (DFS + Visited) Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    private boolean solveRecursion(int idx, int n, int[] arr, boolean[] visited) {
        // Base Case
        if (idx < 0 || idx >= n) {
            // out of bounds check
            return false;
        }
        if (visited[idx]) {
            // already visited
            return false;
        }
        if (arr[idx] == 0) {
            return true;
        }
        // Recursion Calls
        visited[idx] = true;
        boolean option1 = solveRecursion(idx + arr[idx], n, arr, visited);
        boolean option2 = solveRecursion(idx - arr[idx], n, arr, visited);
        return option1 || option2;
    }
}
