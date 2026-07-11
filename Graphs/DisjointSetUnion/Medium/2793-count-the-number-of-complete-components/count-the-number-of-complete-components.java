class Solution {
    /**
     * Approach : Using Disjoint Set Union Approach
     *
     * TC : O(n) + O(e x α(n)) + O(n) + O(n) ~ O(n + e x α(n)) ~ O(n + e)
     * SC : O(4 x n) ~ O(n)
     */
    public int countCompleteComponents(int n, int[][] edges) {
        int[] parent = new int[n];    // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            parent[i] = i;
        }
        int[] rank = new int[n];    // SC : O(n)
        int[] degrees = new int[n]; // SC : O(n)
        for (int[] edge : edges) {  // TC : O(e)
            int u = edge[0];
            int v = edge[1];
            int uParent = find(u, parent); // TC : O(α(n)), SC : O(α(n))
            int vParent = find(v, parent); // TC : O(α(n)), SC : O(α(n))
            degrees[u]++;
            degrees[v]++;
            if (uParent == vParent) {
                continue;
            }
            union(uParent, vParent, parent, rank); // TC : O(1)
        }
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            int root = find(i, parent);
            map.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }
        int count = 0;
        for (ArrayList<Integer> children : map.values()) { // TC : O(k)
            /**
             * For a component to be complete, there should
             * be an edge between every pair of vertices,
             * so every vertex should be a degree of its
             * (size - 1)
             */
            boolean isComplete = true;
            for (Integer v : children) { // TC : O(n / k)
                if (degrees[v] != children.size() - 1) {
                    isComplete = false;
                    break;
                }
            }
            if (isComplete) {
                count++;
            }
        }
        return count;
    }

    /**
     * Using DSU Find By Path Compression Approach
     *
     * TC : O(α(n))
     * SC : O(α(n))
     */
    private int find(int x, int[] parent) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x], parent);
    }

    /**
     * Using DSU Union By Rank Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    private void union(int xParent, int yParent, int[] parent, int[] rank) {
        if (rank[xParent] > rank[yParent]) {
            // make xParent as parent of yParent
            parent[yParent] = xParent;
        } else if (rank[xParent] < rank[yParent]) {
            // make yParent as parent of xParent
            parent[xParent] = yParent;
        } else {
            // make xParent as parent of yParent and increase its rank
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }
}
