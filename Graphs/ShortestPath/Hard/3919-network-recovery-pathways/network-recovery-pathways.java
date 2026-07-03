class Solution {
    /**
     * Approach : Using Binary Search on Answers + Dikjstra's Algorithm Approach
     *
     * TC : O(e) + O((v + e) x log(v) x log(r)) ~ O((v + e) x log(v) x log(r))
     * SC : O(v + e) + O(v + e) ~ O(v + e)
     */
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        Map<Integer, ArrayList<int[]>> adj = new HashMap<>(); // SC : O(v + e)
        for (int[] edge : edges) { // TC : O(e)
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            low = Math.min(low, cost);
            high = Math.max(high, cost);
            if (!online[u] || !online[v]) {
                /**
                 * if any of the node is offline so do
                 * not include it in Adjacency List
                 */
                continue;
            }
            adj.computeIfAbsent(u,
                p -> new ArrayList<>()).add(new int[] { v, cost });
        }
        /**
         * since we need to find the Maximum path score (i.e. Maximum of
         * Minimum Edge cost), so this hints us towards usage of Binary
         * Search on Answers
         */
        int result = -1;
        while (low <= high) { // TC : O(log(r))
            int mid = low + (high - low) / 2;
            if (isPossible(adj, n, mid, k)) { // TC : O((v + e) x log(v))
                result = mid;
                // we need to maximize this
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) result;
    }

    /**
     * Using Dikjstra's Algorithm Approach
     *
     * TC : O((v + e) x log(v))
     * SC : O(v + e)
     */
    private boolean isPossible(Map<Integer, ArrayList<int[]>> adj, int n,
        int mid, long k) {
        /**
         * we will store { cost, u } to Min-Heap
         */
        PriorityQueue<long[]> pq = new PriorityQueue<>((p, q) -> {
            return Long.compare(p[0], q[0]);
        }); // SC : O(e)
        pq.offer(new long[] { 0, 0 });
        long[] minPathCost = new long[n]; // SC : O(v)
        Arrays.fill(minPathCost, Long.MAX_VALUE);
        minPathCost[0] = 0L;
        while (!pq.isEmpty()) { // TC : O(e)
            long[] current = pq.poll();
            long cost = current[0];
            int u = (int) current[1];
            if (cost > k) {
                continue;
            }
            if (u == n - 1) {
                return true;
            }
            if (cost > minPathCost[u]) {
                continue;
            }
            for (int[] ngbr : 
                adj.getOrDefault(u, new ArrayList<>())) { // TC : O(v)
                int v = ngbr[0];
                int edgeCost = ngbr[1];
                if (edgeCost < mid) {
                    // cost of any edge cannot be less than mid
                    continue;
                }
                if (cost + edgeCost < minPathCost[v]) {
                    minPathCost[v] = cost + edgeCost;
                    pq.offer(new long[] { cost + edgeCost, v }); // TC : O(log(v))
                }
            }
        }
        return false;
    }
}
