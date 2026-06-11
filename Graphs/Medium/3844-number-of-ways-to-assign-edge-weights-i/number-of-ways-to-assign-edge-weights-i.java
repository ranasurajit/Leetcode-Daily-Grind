class Solution {
    private static final int mod = (int) 1e9 + 7;

    /**
     * Approach : Using Graph BFS + Math Approach
     *
     * TC : O(n) + O(2 x n) + O(log(n)) ~ O(n)
     * SC : O(n) + O(n) + O(n) + O(log(n)) ~ O(n)
     */
    public int assignEdgeWeights(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = 
            createGraph(edges); // TC : O(n), SC : O(n)
        /**
         * we need to perform BFS to find the deepest node
         */
        Set<Integer> visited = new HashSet<>(); // SC : O(n)
        Queue<Integer> queue = new LinkedList<>(); // SC : O(n)
        queue.offer(1);
        visited.add(1);
        long level = 0L;
        while (!queue.isEmpty()) { // TC : O(n)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer u = queue.poll();
                for (Integer v : adj.get(u)) { // TC : O(n)
                    if (visited.add(v)) {
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
        long depth = level - 1;
        return (int) modPower(2, depth - 1); // TC : O(log(n)), SC : O(log(n))
    }

    /**
     * Using Binary Exponentiation Approach
     *
     * TC : O(log(n))
     * SC : O(log(n))
     */
    private long modPower(long x, long n) {
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
     * TC : O(2 x e) ~ O(n)
     * SC : O(v + e) ~ O(n)
     *
     * as v = n and e = (n - 1)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // undirected edges
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        return adj;
    }
}
