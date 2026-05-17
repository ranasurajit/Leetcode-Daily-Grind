class Solution {
    /**
     * Approach III : Using Graph (BFS) Approach
     *
     * TC : O(n)
     * SC : O(n) + O(n)
     * - O(n) - 'visited' and 'queue' memory
     */
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n]; // SC : O(n)
        Queue<Integer> queue = new LinkedList<>(); // SC : O(n)
        queue.offer(start);
        while (!queue.isEmpty()) { // TC : O(n)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer u = queue.poll();
                if (u < 0 || u >= n || visited[u]) {
                    continue;   
                }
                if (arr[u] == 0) {
                    return true;
                }
                visited[u] = true;
                queue.offer(u + arr[u]);
                queue.offer(u - arr[u]);
            }
        }
        return false;
    }

    /**
     * Approach II : Using Graph (DFS) Approach
     *
     * TC : O(n)
     * SC : O(n) + O(n)
     * - O(n) - 'visited' array and DFS recursion stack
     */
    public boolean canReachDFS(int[] arr, int start) {
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
