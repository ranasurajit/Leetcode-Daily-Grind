class Solution {
    /**
     * Approach : Using Disjoin Set Union Approach
     *
     * TC : O(n) + O(n x α(n)) + O(q x α(n)) ~ O((n + q) x α(n))
     * SC : O(n) + O(α(n)) ~ O(n)
     *
     * Accepted (550 / 550 testcases passed)
     */
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff,
        int[][] queries) {
        DSU dsu = new DSU(n); // TC : O(n), SC : O(n)
        /**
         * 'nums' array is in non-decreasing order and we can form an undirected
         * egde between i and j if absolute difference between nums[i] and nums[j] * is at most 'maxDiff', so we can check if adjacent vertices are within 
         * limits of 'maxDiff'
         */
        for (int i = 1; i < n; i++) { // TC : O(n)
            if (nums[i] - nums[i - 1] <= maxDiff) {
                // then (i, i - 1) forms a pair of edges
                int parentI = dsu.find(i); // TC : O(α(n)), SC : O(α(n))
                int parentJ = dsu.find(i - 1); // TC : O(α(n)), SC : O(α(n))
                if (parentI == parentJ) {
                    continue;
                }
                dsu.unionByRank(parentI, parentJ); // TC : O(1), SC : O(1)
            }
        }
        int q = queries.length;
        boolean[] result = new boolean[q];
        for (int i = 0; i < q; i++) { // TC : O(q)
            int u = queries[i][0];
            int v = queries[i][1];
            result[i] = dsu.find(u) == dsu.find(v); // TC : O(α(n)), SC : O(α(n))
        }
        return result;
    }
}

class DSU {
    private int n;
    private int[] parent;
    private int[] rank;

    /**
     * Using Disjoint Set Union Approach
     *
     * TC : O(n)
     * SC : O(n) + O(n) ~ O(n) 
     */
    public DSU(int n) {
        this.n = n;
        this.parent = new int[n];
        for (int i = 0; i < n; i++) { // TC : O(n)
            parent[i] = i;
        }
        this.rank = new int[n];
    }

    /**
     * Using DSU Find by Path Compression Approach
     *
     * TC : O(α(n))
     * SC : O(α(n))
     */
    public int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    /**
     * Using DSU Union by Rank Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public void unionByRank(int xParent, int yParent) {
        if (xParent == yParent) {
            return;
        }
        if (rank[xParent] > rank[yParent]) {
            // make xParent as parent of yParent
            parent[yParent] = xParent;
        } else if (rank[xParent] > rank[yParent]) {
            // make yParent as parent of xParent
            parent[xParent] = yParent;
        } else {
            // make anyone as parent and increase its rank
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }
}
