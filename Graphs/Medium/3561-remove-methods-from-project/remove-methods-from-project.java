class Solution {
    /**
     * Approach : Using Graph BFS + Hashing Approach
     *
     * TC : O(m) + O(n + m) + O(m) + O(n) ~ O(n + m)
     * SC : O(n + m) + O(n) ~ O(n + m)
     *
     * where n = number of nodes and m = number of edges
     */
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        Map<Integer, ArrayList<Integer>> adj = 
            createGraph(invocations);          // TC : O(m), SC : O(n + m)
        boolean[] suspicious = new boolean[n]; // SC : O(n)
        /**
         * we need to mark all the nodes that are called from node 'k'
         * and all those needs to be marked as suspicious so, we can
         * perform BFS from node 'k' and array 'suspicious' will act as
         * visited array for the BFS
         */
        Queue<Integer> queue = new LinkedList<>(); // SC : O(n)
        queue.offer(k);
        suspicious[k] = true;
        while (!queue.isEmpty()) { // TC : O(n)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                ArrayList<Integer> ngbr = adj.get(u);
                if (ngbr == null) {
                    continue;
                }
                for (Integer v : ngbr) { // TC : O(m)
                    if (!suspicious[v]) {
                        suspicious[v] = true;
                        queue.offer(v);
                    }
                }
            }
        }
        /**
         * here we would have all our suspicious nodes marked as 'true'
         * now we need to loop through 'invocations' edges array to
         * check if any non-suspicious node calls the suspicious ones
         */
        boolean hasCalledSuspicious = false;
        for (int[] edge : invocations) { // TC : O(m)
            int u = edge[0];
            int v = edge[1];
            if (!suspicious[u] && suspicious[v]) {
                hasCalledSuspicious = true;
                break;
            }
        }
        List<Integer> result = new ArrayList<>();
        if (hasCalledSuspicious) {
            // we should not remove any nodes
            for (int i = 0; i < n; i++) { // TC : O(n)
                result.add(i);
            }
        } else {
            // we should return non-suspicious nodes only
            for (int i = 0; i < n; i++) { // TC : O(n)
                if (!suspicious[i]) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    /**
     * Using Hashing Approach
     *
     * TC : O(m)
     * SC : O(n + m)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) { // TC : O(e)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }
        return adj;
    }
}
