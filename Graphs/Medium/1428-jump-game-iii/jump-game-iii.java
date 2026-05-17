class Solution {
    /**
     * Approach : Using Recursion (DFS + Visited) Approach
     *
     * TC : O(n)
     * SC : O(n) + O(n)
     * - O(n) - visited array and recursion stack
     */
    public boolean canReach(int[] arr, int start) {
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
