class Solution {
    private static final long INF = (long) 1e9;
    
    /**
     * Approach II : Using Dijkstra's Algorithm Approach
     *
     * TC: O(N x M) + O(26 x N), where N = size(source/target), M = size(original/changed)
     * SC: O(26 x 26) + O(26) ~ O(1)
     *
     * Accepted (581 / 581 testcases passed)
     */
    public long minimumCost(String source, String target, char[] original, 
        char[] changed, int[] cost) {
        int n = source.length();
        int m = cost.length;
        /**
         * Intuition: original elements to changed elements seems like a 
         * directed graph and we need to find the shortest path/cost
         * to travel from original[i] to changed[i] in case the corresponding
         * elements in each index in source vs target do not match
         * so let's create the adjacency list for directed weighted graph
         */
        Map<Integer, ArrayList<int[]>> adj =
            createGraph(original, changed, cost, m); // TC: O(M), SC: O(1)
        /**
         * we will create a 2D matrix to store minimum cost from any source 
         * character (a-z) to any other destination character (a-z)
         */
        long[][] minCostAll = new long[26][26]; // SC: O(26 x 26)
        Set<Integer> computedSet = new HashSet<Integer>(); // SC: O(26)
        for (int i = 0; i < n; i++) {     // TC: O(N)
            int src = source.charAt(i) - 'a';
            if (computedSet.contains(src)) {
                continue;
            }
            for (int des = 0; des < 26; des++) { // TC: O(26)
                computedSet.add(src);
                if (src == des) {
                    minCostAll[src][des] = 0L;
                    continue;
                }
                minCostAll[src][des] = dijkstraMinCost(src, des, adj); // TC: O(M), SC: O(1)
            }
        }
        /**
         * as per constraints, 1 <= cost[i] <= 10^6 so, we can perform Dijkstra's
         * algorithm approach for each characters from original to changed if 
         * characters at any respective index of String source and target do not
         * match to find out the minimum cost from source to destination
         */
        long minCost = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int src = source.charAt(i) - 'a';
            int des = target.charAt(i) - 'a';
            if (src == des) {
                continue;
            }
            long currentCost = minCostAll[src][des];
            if (currentCost == INF) {
                return -1;
            }
            minCost += currentCost;
        }
        return minCost;
    }

    /**
     * Approach I : Using Dijkstra's Algorithm Approach
     *
     * TC: O(N x M), where N = size(source/target), M = size(original/changed)
     * SC: O(1)
     *
     * Time Limit Exceeded (571 / 581 testcases passed)
     */
    public long minimumCostRunningDijkstraPerCharacter(String source, String target,
        char[] original, char[] changed, int[] cost) {
        int n = source.length();
        int m = cost.length;
        /**
         * Intuition: original elements to changed elements seems like a 
         * directed graph and we need to find the shortest path/cost
         * to travel from original[i] to changed[i] in case the corresponding
         * elements in each index in source vs target do not match
         * so let's create the adjacency list for directed weighted graph
         */
        Map<Integer, ArrayList<int[]>> adj =
            createGraph(original, changed, cost, m); // TC: O(M), SC: O(1)
        /**
         * as per constraints, 1 <= cost[i] <= 10^6 so, we can perform Dijkstra's
         * algorithm approach for each characters from original to changed if 
         * characters at any respective index of String source and target do not
         * match to find out the minimum cost from source to destination
         */
        long minCost = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int src = source.charAt(i) - 'a';
            int des = target.charAt(i) - 'a';
            if (src == des) {
                continue;
            }
            long currentCost = dijkstraMinCost(src, des, adj); // TC: O(M), SC: O(1)
            if (currentCost == INF) {
                return -1;
            }
            minCost += currentCost;
        }
        return minCost;
    }

    /**
     * Using Dijkstra's Algorithm Approach
     *
     * TC: O((E + V) x log(V)) ~ O(E) as V is at max 26
     * SC: O(V) ~ O(1)
     */
    private long dijkstraMinCost(int src, int des, Map<Integer, ArrayList<int[]>> adj) {
        /**
         * as per constraints, source, target consist of lowercase English letters 
         * so we can create a minCost array of size 26 to store min cost to change
         * from src to des
         */
        long[] minCost = new long[26]; // SC: O(26)
        Arrays.fill(minCost, INF);
        minCost[src] = 0L;
        /**
         * we need a Min-Heap to store the { cost, node } in order of cost
         */
        PriorityQueue<Pair> pq =
            new PriorityQueue<Pair>((p, q) -> Long.compare(p.cost, q.cost)); // SC: O(V)
        pq.offer(new Pair(0L, src));
        while (!pq.isEmpty()) { // TC: O(E)
            Pair current = pq.poll();
            long cost = current.cost;
            int u = current.node;
            if (cost > minCost[u]) {
                continue;
            }
            if (u == des) {
                return cost;
            }
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(V)
                int v = ngbr[0];
                long edgeCost = (long) ngbr[1];
                if (cost + edgeCost < minCost[v]) {
                    minCost[v] = cost + edgeCost;
                    pq.offer(new Pair(cost + edgeCost, v)); // TC: O(log(V))
                }
            }
        }
        return INF;
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(M)
     * SC: O(26) ~ O(1) as maximum lowercase characters key = 26
     */
    private Map<Integer, ArrayList<int[]>> createGraph(char[] original, 
        char[] changed, int[] cost, int m) {
        Map<Integer, ArrayList<int[]>> adj = 
            new HashMap<Integer, ArrayList<int[]>>(); // SC: O(26)
        for (int i = 0; i < m; i++) { // TC: O(M)
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            int w = cost[i];
            adj.computeIfAbsent(u, k -> new ArrayList<int[]>()).add(new int[] { v, w });
        }
        return adj;
    }

    private class Pair {
        long cost;
        int node;

        public Pair (long cost, int node) {
            this.cost = cost;
            this.node = node;
        }
    }
}
