class Solution {
    private static final long INF = (long) 1e10;

    /**
     * Approach II : Using Memoization + Dijkstra's Algorithm Approach
     *
     * TC: O(N x log(N)) + O(M) + O(N) ~ O(N x log(M) + M)
     * SC: O(N) + O(N) + O(M) ~ O(N + M)
     *
     * Accepted (647 / 647 testcases passed)
     */
    public long minimumCost(String source, String target, String[] original, 
        String[] changed, int[] cost) {
        int n = cost.length;
        Map<String, ArrayList<Pair>> adj = createGraph(original, changed, cost, n);
        // we need an ordered set to store different unique sizes of elements in original
        TreeSet<Integer> sizes = new TreeSet<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            sizes.add(original[i].length());
        }
        int m = source.length();
        long[][] memo = new long[m][1001]; // SC: O(M)
        for (long[] mem : memo) {
            Arrays.fill(mem, -1L);
        }
        long minCost = solveMemoization(0, m, source, target, sizes, adj, memo);
        if (minCost == INF) {
            return -1L;
        }
        return minCost;
    }

    /**
     * Using Memoization + Dijkstra's Algorithm Approach
     *
     * TC: O(N x log(N)) + O(2 ^ N)
     * SC: O(N) + O(N) ~ O(N)
     */
    private long solveMemoization(int idx, int m, String source, String target, 
        TreeSet<Integer> sizes, Map<String, ArrayList<Pair>> adj, long[][] memo) {
        // Base Case
        if (idx >= m) {
            return 0L;
        }
        // Memoization Check
        if (memo[idx][m] != -1L) {
            return memo[idx][m];
        }
        // Recursion Calls
        long minCost = INF; 
        if (source.charAt(idx) == target.charAt(idx)) {
            minCost = solveMemoization(idx + 1, m, source, target, sizes, adj, memo);
        }
        for (Integer len : sizes) { // TC: O(100)
            if (idx + len > m) {
                // no substrings possible
                break;
            }
            String srcSub = source.substring(idx, idx + len);
            String tarSub = target.substring(idx, idx + len);
            if (!adj.containsKey(srcSub)) {
                // no point in comparing the strings further
                continue;
            }
            long minCostPath =
                dijkstraMinCost(srcSub, tarSub, adj); // TC: O(N x log(N)), SC: O(N)
            if (minCostPath == INF) {
                continue;
            }
            minCost = Math.min(minCost, 
                minCostPath + solveMemoization(idx + len, m, source, target, sizes, adj, memo));
        }
        return memo[idx][m] = minCost;
    }

    /**
     * Approach I : Using Recursion + Dijkstra's Algorithm Approach
     *
     * TC: O(N x log(N)) + O(2 ^ N) + O(N) ~ O(2 ^ N)
     * SC: O(N) + O(N) ~ O(N)
     *
     * Time Limit Exceeded (103 / 647 testcases passed)
     */
    public long minimumCostRecursion(String source, String target, String[] original, 
        String[] changed, int[] cost) {
        int n = cost.length;
        Map<String, ArrayList<Pair>> adj = createGraph(original, changed, cost, n);
        // we need an ordered set to store different unique sizes of elements in original
        TreeSet<Integer> sizes = new TreeSet<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            sizes.add(original[i].length());
        }
        int m = source.length();
        long minCost = solveRecursion(0, m, source, target, sizes, adj);
        if (minCost == INF) {
            return -1L;
        }
        return minCost;
    }

    /**
     * Using Recursion + Dijkstra's Algorithm Approach
     *
     * TC: O(N x log(N)) + O(2 ^ N)
     * SC: O(N) + O(N) ~ O(N)
     */
    private long solveRecursion(int idx, int m, String source, String target, 
        TreeSet<Integer> sizes, Map<String, ArrayList<Pair>> adj) {
        // Base Case
        if (idx >= m) {
            return 0L;
        }
        // Recursion Calls
        long minCost = INF; 
        if (source.charAt(idx) == target.charAt(idx)) {
            minCost = solveRecursion(idx + 1, m, source, target, sizes, adj);
        }
        for (Integer len : sizes) { // TC: O(100)
            if (idx + len > m) {
                // no substrings possible
                break;
            }
            String srcSub = source.substring(idx, idx + len);
            String tarSub = target.substring(idx, idx + len);
            if (!adj.containsKey(srcSub)) {
                // no point in comparing the strings further
                continue;
            }
            long minCostPath =
                dijkstraMinCost(srcSub, tarSub, adj); // TC: O(N x log(N)), SC: O(N)
            if (minCostPath == INF) {
                continue;
            }
            minCost = Math.min(minCost, 
                minCostPath + solveRecursion(idx + len, m, source, target, sizes, adj));
        }
        return minCost;
    }

    /**
     * Using Dijkstra's Algorithm Approach
     *
     * TC: O(N x log(N))
     * SC: O(N) + O(N) ~ O(N)
     */
    private long dijkstraMinCost(String srcSub, String tarSub, 
        Map<String, ArrayList<Pair>> adj) {
        Map<String, Long> dist = new HashMap<String, Long>(); // SC: O(N)
        dist.put(srcSub, 0L);
        PriorityQueue<Pair> pq = 
            new PriorityQueue<Pair>((p, q) -> Long.compare(p.w, q.w)); // SC: O(N)
        pq.offer(new Pair(srcSub, 0L));
        while (!pq.isEmpty()) { // TC: O(N)
            Pair current = pq.poll();
            String u = current.node;
            long w = current.w;
            if (w > dist.getOrDefault(u, INF)) {
                continue;
            }
            if (u.equals(tarSub)) {
                return w;
            }
            for (Pair ngbr : adj.getOrDefault(u, new ArrayList<Pair>())) {
                String v = ngbr.node;
                long edgeCost = ngbr.w;
                if (edgeCost + w < dist.getOrDefault(v, INF)) {
                    dist.put(v, edgeCost + w);
                    pq.offer(new Pair(v, edgeCost + w)); // TC: O(log(N))
                }
            }
        }
        return INF;
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(N) ~ O(1)
     * SC: O(N) ~ O(1) as 1 <= cost.length == original.length == changed.length <= 100
     */
    private Map<String, ArrayList<Pair>> createGraph(String[] original, String[] changed,
        int[] cost, int n) {
        Map<String, ArrayList<Pair>> adj = new HashMap<String, ArrayList<Pair>>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            String from = original[i];
            String to = changed[i];
            long w = (long) cost[i];
            adj.computeIfAbsent(from, k -> new ArrayList<Pair>()).add(new Pair(to, w));
        }
        return adj;
    }

    class Pair {
        String node;
        long w;

        public Pair (String node, long w) {
            this.node = node;
            this.w = w;
        }
    }
}
