class Solution {
    /**
     * Approach : Using Disjoint Set Union Find Approach
     *
     * TC : O(n) + O(m x α(n)) + O(m x α(n)) ~ O(n + m x α(n))
     * SC : O(n) + O(n) + O(α(n)) ~ O(n)
     */
    public int minScore(int n, int[][] roads) {
        int[] parent = new int[n];    // SC : O(n)
        int[] rank = new int[n];      // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            parent[i] = i;
        }
        for (int[] edge : roads) { // TC : O(m)
            int u = edge[0] - 1; // 0-based indexing
            int v = edge[1] - 1; // 0-based indexing
            int uParent = find(u, parent); // TC : O(α(n)), SC : O(α(n))
            int vParent = find(v, parent); // TC : O(α(n)), SC : O(α(n))
            if (uParent == vParent) {
                // already under same parent (or union perfomed already)
                continue;
            }
            unionByRank(uParent, vParent, parent, rank); // TC : O(1)
        }
        /**
         * now we would be again going through each roads or edges to
         * find the minimum score to reach from node 1 ((0) - 0-based)
         * to node n ((n - 1) - 0 based) and as per constraint
         * 'There is at least one path between 1 and n' so they belong
         * to same parent so we need to see all nodes whose parent is 
         * same, so that we can compare their edge scores and store the
         * minimum out of it
         */
        int parentStart = find(0, parent);
        int minScore = Integer.MAX_VALUE;
        for (int[] edge : roads) { // TC : O(m)
            int u = edge[0] - 1; // 0-based indexing
            int v = edge[1] - 1; // 0-based indexing
            int score = edge[2];
            if (find(u, parent) == parentStart) { // TC : O(α(n)), SC : O(α(n))
                minScore = Math.min(minScore, score);
            }
        }
        return minScore;
    }

    /**
     * Using DSU (Find By Path Compression) Approach
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
     * Using DSU (Union By Rank) Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    private void unionByRank(int xParent, int yParent, int[] parent, int[] rank) {
        if (xParent == yParent) {
            return;
        }
        if (rank[xParent] > rank[yParent]) {
            // make xParent as parent of yParent
            parent[yParent] = xParent;
        } else if (rank[xParent] < rank[yParent]) {
            // make yParent as parent of xParent
            parent[xParent] = yParent;
        } else {
            // make anyone as parent and increase its rank
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }
}
