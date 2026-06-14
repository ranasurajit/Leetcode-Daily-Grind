class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private int[][] ancestor;
    private int LOG;
 
    /**
     * Approach : Using DP, Graphs, DFS, Hashing, Binary Lifting and LCA Approach
     *
     * TC : O(n) + O(n) + O(n x log(n)) + O(n) + O(q x log(n))
     *      ~ O((n + q) x log(n))
     * SC : O(n x log(n)) + O(5 x n) ~ O(n x log(n))
     */
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1; // as this is a tree
        LOG = (int) (Math.log(n) / Math.log(2)) + 1;
        ancestor = new int[n][LOG]; // SC : O(n x log(n))
    
        ArrayList<Integer>[] adj = createGraph(n, edges); // TC : O(n), SC : O(n)
        /**
         * we will perform DFS from node 1 (i.e. node 0 - 0-based indexing)
         * and store the depth of all nodes in a HashMap to query node depth
         * based on node's value in O(1) complexity,
         * also we need to fill the ancestor array to fill column 0 which is
         * 2^0 i.e 1 jump which means to store parent node also in same DFS
         */
        Map<Integer, Integer> depthMap = new HashMap<>(); // SC : O(n)
        boolean[] visited = new boolean[n]; // SC : O(n)
        dfsGraph(0, -1, 0, adj, visited, depthMap); // TC: O(n), SC : O(n)
        /**
         * we need to pre-process the ancestor array using Binary Lifting
         */
        for (int j = 1; j < LOG; j++) { // TC : O(log(n))
            for (int node = 0; node < n; node++) { // TC : O(n)
                int prevNode = ancestor[node][j - 1];
                if (prevNode != -1) {
                    ancestor[node][j] = ancestor[prevNode][j - 1];
                }
            }
        }
        /**
         * we will pre-compute powers of 2 for easy retrieval in O(1) 
         */
        int[] powers = new int[n + 1]; // SC : O(n)
        powers[0] = 1;
        for (int i = 1; i <= n; i++) { // TC : O(n)
            powers[i] = (2 * powers[i - 1]) % MOD;
        }
        /**
         * now we need to process queries
         */
        int q = queries.length;
        int[] result = new int[q];
        for (int i = 0; i < q; i++) { // TC : O(q)
            int[] query = queries[i];
            int u = query[0] - 1; // 0 based indexing
            int v = query[1] - 1; // 0 based indexing
            int lcaNode = findLCANode(u, v, depthMap); // TC : O(log(n))
            int depth = depthMap.get(u)
                + depthMap.get(v) 
                - 2 * depthMap.get(lcaNode);
            if (depth > 0) {
                result[i] = powers[depth - 1];
            }
        }
        return result;
    }

    /**
     * Using Binary Lifting Approach to find LCA
     *
     * TC : O(log(n) Base 2)
     * SC : O(1)
     */
    private int findLCANode(int u, int v, Map<Integer, Integer> depthMap) {
        int depthU = depthMap.get(u);
        int depthV = depthMap.get(v);
        if (depthU < depthV) {
            // keeping depth of node 'u' always larger
            int temp = u;
            u = v;
            v = temp;
        }
        int k = Math.abs(depthU - depthV);
        /**
         * now we need to jump from node 'u' up by k jumps
         * to match both the depths same i.e. we need to 
         * find the kth ancestor of node 'u'
         */
        for (int j = 0; j < LOG; j++) { // TC : O(log(n))
            if ((k & (1 << j)) != 0) {
                u = ancestor[u][j];
            }
        }
        if (u == v) {
            // we can return either 'u' or 'v'
            return v;
        }
        /**
         * here both nodes 'u' and 'v' are at same depths
         * so we can try to maximum jump fast to reach to
         * the LCA node as soon as possible
         */
        for (int j = LOG - 1; j >= 0; j--) { // TC : O(log(n))
            if (ancestor[u][j] == -1) {
                continue;
            }
            if (ancestor[u][j] != ancestor[v][j]) {
                u = ancestor[u][j];
                v = ancestor[v][j];
            }
        }
        /**
         * here the nodes would reach a one level down of LCA
         * so we will jump 1 level up i.e. 2^0 level up
         */
        return ancestor[u][0];
    }

    /**
     * Using DFS Approach
     *
     * TC : O(n)
     * SC : O(h) ~ O(n)
     */
    private void dfsGraph(int u, int parent, int depth, ArrayList<Integer>[] adj,
        boolean[] visited, Map<Integer, Integer> depthMap) {
        visited[u] = true;
        ancestor[u][0] = parent;
        depthMap.put(u, depth);
        for (Integer v : adj[u]) {
            if (!visited[v]) {
                dfsGraph(v, u, depth + 1, adj, visited, depthMap);
            }
        }
    }

    /**
     * Using Hashing Approach
     *
     * TC : O(n) + O(e) ~ O(n) as e = (n - 1)
     * SC : O(n)
     */
    private ArrayList<Integer>[] createGraph(int n, int[][] edges) {
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) { // TC : O(n)
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) { // TC : O(e)
            adj[edge[0] - 1].add(edge[1] - 1); // 0 based indexing
            adj[edge[1] - 1].add(edge[0] - 1); // 0 based indexing
        }
        return adj;
    }
}
