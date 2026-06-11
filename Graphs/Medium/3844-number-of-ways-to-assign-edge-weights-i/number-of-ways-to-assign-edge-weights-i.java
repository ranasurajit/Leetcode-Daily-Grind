class Solution {
    private static final int mod = (int) 1e9 + 7;

    /**
     * Approach : Using Graph BFS + Math Approach
     *
     * TC : O(n) + O(2 x n) + O(log(n)) ~ O(n)
     * SC : O(n) + O(n) + O(n) + O(log(n)) ~ O(n)
     */
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        ArrayList<Integer>[] adj = createGraph(edges, n); // TC : O(n), SC : O(n)
        /**
         * we need to perform BFS to find the deepest node
         */
        boolean[] visited = new boolean[n + 1]; // SC : O(n)
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // SC : O(n)
        queue.offer(1);
        visited[0] = true;
        visited[1] = true; // marking start node visited
        int level = 0;
        while (!queue.isEmpty()) { // TC : O(n)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer u = queue.poll();
                for (Integer v : adj[u]) { // TC : O(n)
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.offer(v);
                    }
                }
            }
            level++;
        }
        /**
         * weight of 1 contributes to odd sum if its occurence is odd times
         * weight of 2 contributes to even sum always
         */
        int depth = level - 1;
        return (int) modPower(2, depth - 1); // TC : O(log(n)), SC : O(log(n))
    }

    /**
     * Using Binary Exponentiation Approach
     *
     * TC : O(log(n))
     * SC : O(log(n))
     */
    private long modPower(long x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        long half = modPower(x, n / 2) % mod;
        long ans = (half * half) % mod;
        if ((n & 1) != 0) {
            // n is odd
            return (x * ans) % mod;
        }
        return ans;
    }

    /**
     * Using Hashing Approach
     *
     * TC : O(n) + O(n - 1) ~ O(n)
     * SC : O(n)
     */
    private ArrayList<Integer>[] createGraph(int[][] edges, int n) {
        ArrayList<Integer>[] adj = new ArrayList[n + 1]; // SC : O(n)
        for (int i = 1; i <= n; i++) { // TC : O(n)
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) { // TC : O(n - 1)
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        return adj;
    }
}
