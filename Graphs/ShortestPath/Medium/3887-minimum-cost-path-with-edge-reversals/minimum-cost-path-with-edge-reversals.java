class Solution {
    /**
     * Approach II : Using Dijkstra's Algorithm + Min-Heap (Optimal) Approach
     *
     * TC: O(E) + O((V + E) x log(V)) ~ O((V + E) x log(V))
     *     - 2 states per node, each edge relaxed at most twice
     * SC: O(V + E) + O(V + E) + O(V) + O(V) ~ O(V + E)
     */
    public int minCost(int n, int[][] edges) {
        List<int[]>[] adj = new ArrayList[n]; // SC: O(V + E)
        List<int[]>[] revAdj = new ArrayList[n]; // SC: O(V + E)
        processGraphOptimal(n, edges, adj, revAdj); // TC: O(E), SC: O(1)
        /**
         * we will be using Dijkstra's Algorithm to find the shortest cost/path
         * for each vertices (as edge weight as per constraint 1 <= wi <= 1000 
         * i.e. wi is positive)
         */
        int[] dist = new int[n]; // SC: O(V)
        dikstrasOptimalAlgorithm(0, n, adj, revAdj, dist); // TC: O((V + E) x log(V)), SC: O(V)
        return dist[n - 1] == (int) 1e9 ? -1 : dist[n - 1];
    }

    /**
     * Using Dijkstra's Algorithm Approach
     *
     * TC: O(V) + O((V + E) x log(E)) ~ O((V + E) x log(V))
     * SC: O(2 x V) ~ O(V)
     */
    private void dikstrasOptimalAlgorithm(int src, int n, List<int[]>[] adj, 
        List<int[]>[] revAdj, int[] dist) {
        // we need a PriorityQueue (Min-Heap) to store { cost, vertex } in order of costs
        PriorityQueue<int[]> pq =
            new PriorityQueue<int[]>((p, q) -> Integer.compare(p[0], q[0])); // SC: O(V)
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0; // cost from vertex 0 to 0 is 0
        pq.offer(new int[] { 0, src });
        while (!pq.isEmpty()) { // TC: O(2 x V)
            int[] pair = pq.poll();
            int w = pair[0];
            int u = pair[1];
            if (w > dist[u]) {
                continue;
            }
            // normal node traversal
            for (int[] ngbr : adj[u]) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                // normal node traversal
                if (w + edgeWeight < dist[v]) {
                    dist[v] = w + edgeWeight;
                    pq.offer(new int[] { w + edgeWeight, v }); // TC: O(log(V))
                }
            }
            // reverse node traversal
            for (int[] ngbr : revAdj[u]) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (w + 2 * edgeWeight < dist[v]) {
                    dist[v] = w + 2 * edgeWeight;
                    pq.offer(new int[] { w + 2 * edgeWeight, v }); // TC: O(log(V))
                }
            }
        }
    }

    /**
     * Using Hashing Approach (ArrayList Approach)
     *
     * TC: O(V + E)
     * SC: O(1)
     */
    private void processGraphOptimal(int n, int[][] edges, 
        List<int[]>[] adj, List<int[]>[] revAdj) {
        for (int i = 0; i < n; i++) { // TC: O(V)
            adj[i] = new ArrayList<int[]>();
            revAdj[i] = new ArrayList<int[]>();
        }
        for (int[] edge : edges) { // TC: O(E)
            adj[edge[0]].add(new int[] { edge[1], edge[2] });
            revAdj[edge[1]].add(new int[] { edge[0], edge[2] });
        }
    }

    /**
     * Approach I : Using Dijkstra's Algorithm + Min-Heap + Hashing Approach
     *
     * TC: O(E) + O((V + E) x log(V)) ~ O((V + E) x log(V))
     *     - 2 states per node, each edge relaxed at most twice
     * SC: O(V + E) + O(V + E) + O(V) + O(V) ~ O(V + E)
     */
    public int minCostUsingDijkstrasAlgorithm(int n, int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = 
            new HashMap<Integer, ArrayList<int[]>>(); // SC: O(V + E)
        Map<Integer, ArrayList<int[]>> revAdj =
            new HashMap<Integer, ArrayList<int[]>>(); // SC: O(V + E)
        processGraph(edges, adj, revAdj); // TC: O(E), SC: O(1)
        /**
         * we will be using Dijkstra's Algorithm to find the shortest cost/path
         * for each vertices (as edge weight as per constraint 1 <= wi <= 1000 
         * i.e. wi is positive)
         */
        int[] dist = new int[n]; // SC: O(V)
        dikstrasAlgorithm(0, n, adj, revAdj, dist); // TC: O((V + E) x log(V)), SC: O(V)
        return dist[n - 1] == (int) 1e9 ? -1 : dist[n - 1];
    }

    /**
     * Using Dijkstra's Algorithm Approach
     *
     * TC: O(V) + O((V + E) x log(E)) ~ O((V + E) x log(V))
     * SC: O(2 x V) ~ O(V)
     */
    private void dikstrasAlgorithm(int src, int n, Map<Integer, ArrayList<int[]>> adj, 
        Map<Integer, ArrayList<int[]>> revAdj, int[] dist) {
        // we need a PriorityQueue (Min-Heap) to store { cost, vertex } in order of costs
        PriorityQueue<int[]> pq =
            new PriorityQueue<int[]>((p, q) -> Integer.compare(p[0], q[0])); // SC: O(V)
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0; // cost from vertex 0 to 0 is 0
        pq.offer(new int[] { 0, src });
        while (!pq.isEmpty()) { // TC: O(2 x V)
            int[] pair = pq.poll();
            int w = pair[0];
            int u = pair[1];
            if (w > dist[u]) {
                continue;
            }
            // normal node traversal
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                // normal node traversal
                if (w + edgeWeight < dist[v]) {
                    dist[v] = w + edgeWeight;
                    pq.offer(new int[] { w + edgeWeight, v }); // TC: O(log(V))
                }
            }
            // reverse node traversal
            for (int[] ngbr : revAdj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (w + 2 * edgeWeight < dist[v]) {
                    dist[v] = w + 2 * edgeWeight;
                    pq.offer(new int[] { w + 2 * edgeWeight, v }); // TC: O(log(V))
                }
            }
        }
    }

    /**
     * Using Hashing Approach (Normal and Reversed Adjacency List)
     *
     * TC: O(E)
     * SC: O(1)
     */
    private void processGraph(int[][] edges, 
        Map<Integer, ArrayList<int[]>> adj, Map<Integer, ArrayList<int[]>> revAdj) {
        for (int[] edge : edges) { // TC: O(E)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<int[]>())
                .add(new int[] { edge[1], edge[2] });
            revAdj.computeIfAbsent(edge[1], k -> new ArrayList<int[]>())
                .add(new int[] { edge[0], edge[2] });
        }
    }
}
