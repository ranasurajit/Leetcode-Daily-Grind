class Solution {
    /**
     * Approach : Using DFS Graph Traversal Approach
     *
     * TC: O(V + E) + O(2 x E) ~ O(V + E)
     * SC: O(V) + O(V) + O(V + E) ~ O(V + E)
     *
     * - O(V) - visited array memory
     * - O(V) - recursion stack
     * - O(V + E) - adjacency list memory
     */
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        Map<Integer, ArrayList<Integer>> adj = createGraph(edges); // TC: O(2 x E), SC: O(V + E)
        boolean[] visited = new boolean[n]; // SC: O(V)
        int[] components = { 0 };
        int[] sum = { 0 };
        dfsGraphSum(0, adj, visited, k, values, components); // TC: O(V + E), SC: O(V)
        return components[0];
    }

    /**
     * Using DFS Graph Traversal Approach
     *
     * TC: O(V + E)
     * SC: O(V)
     */
    private int dfsGraphSum(int u, Map<Integer, ArrayList<Integer>> adj, 
        boolean[] visited, int k, int[] values, int[] components) {
        visited[u] = true;
        int subTotal = values[u];
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) { // TC: O(E)
            if (!visited[v]) {
                subTotal += dfsGraphSum(v, adj, visited, k, values, components);
            }
        }
        if (subTotal % k == 0) {
            components[0]++;
            return 0;
        }
        return subTotal % k;
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(2 x E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.computeIfAbsent(u, k -> new ArrayList<Integer>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<Integer>()).add(u);
        }
        return adj;
    }
}
