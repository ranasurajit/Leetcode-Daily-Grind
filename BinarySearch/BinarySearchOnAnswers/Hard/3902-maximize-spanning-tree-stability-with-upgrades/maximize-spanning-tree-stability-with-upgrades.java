class Solution {
    /**
     * Approach : Using Binary Search on Answers + Disjoint Set Union Approach
     *
     * TC: O(E x α(V)) + O(E x α(V)) + O(V x α(V)) ~ O((E + V) x α(V))
     * SC: O(2 x V) + O(E x α(V)) + O(E x α(V) x log(S)) ~ O(V + (E x α(V) x log(S)))
     */
    public int maxStability(int n, int[][] edges, int k) {
        DSU dsu = new DSU(n); // SC: O(2 x V)
        for (int[] edge : edges) { // TC: O(E)
            int u = edge[0];
            int v = edge[1];
            int s = edge[2];
            int m = edge[3];
            if (m == 1) {
                // we can check if must have edges form a cycle during making it united
                if (dsu.find(u) == dsu.find(v)) { // TC: O(α(V)), SC: O(α(V))
                    // there is a cycle so return -1
                    return -1;
                }
                dsu.union(u, v);
            }
        }
        /**
         * We need to maximize the minimum strength 
         * (stability of Spanning Tree) which hints us
         * towards usage of Binary Search on Answers
         * as per constraint 1 <= si <= 10^5
         */
        int result = -1;
        int low = 1;
        int high = 2 * (int) 1e5;
        while (low <= high) { // TC: O(log(S))
            int mid = low + (high - low) / 2;
            if (checkIfMSTPossibleWithStrength(mid, edges, n, k)) {
                // TC: O((E + V) x α(V)), SC: O((E + V) x α(V))
                // we need to maximize mid
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    /**
     * Using Disjoint Set Union Approach
     *
     * TC: O(E x α(V)) + O(E x α(V)) + O(V x α(V)) ~ O((E + V) x α(V))
     * SC: O(E) + O(E x α(V)) + O(E x α(V)) + O(V x α(V)) ~ O((E + V) x α(V))
     */
    private boolean checkIfMSTPossibleWithStrength(int mid, int[][] edges, int n, int k) {
        DSU dsu = new DSU(n);
        List<int[]> upgradableEdges = new ArrayList<>(); // SC: O(E)
        for (int[] edge : edges) { // TC: O(E)
            int u = edge[0];
            int v = edge[1];
            int s = edge[2];
            int m = edge[3];
            if (m == 1) {
                if (s < mid) {
                    /**
                     * then s will be the minimum strength which
                     * would not satisfy Spanning Tree with minimum
                     * strength mid so, return false
                     */
                    return false;
                }
                dsu.union(u, v); // must have edges // TC: O(α(V)), SC: O(α(V))
            } else {
                if (s >= mid) {
                    // no need to upgrade, just union it
                    dsu.union(u, v); // TC: O(α(V)), SC: O(α(V))
                } else if (2 * s >= mid) {
                    // can be upgraded
                    upgradableEdges.add(new int[] { u, v });
                }
            }
        }
        for (int[] edge : upgradableEdges) { // TC: O(E)
            int u = edge[0];
            int v = edge[1];
            if (dsu.find(u) != dsu.find(v)) { // TC: O(α(V)), SC: O(α(V))
                // we can upgrade if k > 0
                if (k <= 0) {
                    return false;
                }
                dsu.union(u, v);
                k--;
            }
        }
        /**
         * finally we need to check if all the vertices are 
         * connected to form Spanning Tree
         */
        int root = dsu.find(0);
        for (int i = 1; i < n; i++) { // TC: O(V)
            if (dsu.find(i) != root) { // TC: O(α(V))
                return false;
            }
        }
        return true;
    }
}

/**
 * Using Disjoint Set Union Approach
 */
class DSU {
    private int n;
    private int[] parent;
    private int[] rank;

    public DSU(int n) {
        this.n = n;
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }
    }

    /**
     * Using DSU (Find By Path Compression) Approach
     *
     * TC: O(α(V))
     * SC: O(α(V))
     */
    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    /**
     * Using DSU (Union By Rank) Approach
     *
     * TC: O(α(V))
     * SC: O(α(V))
     */
    public void union(int x, int y) {
        int xParent = find(x); // TC: O(α(V))
        int yParent = find(y); // TC: O(α(V))
        if (xParent == yParent) {
            // already connected
            return;
        }
        if (rank[xParent] > rank[yParent]) {
            // make xParent as parent of yParent
            parent[yParent] = xParent;
        } else if (rank[xParent] < rank[yParent]) {
            // make yParent as parent of xParent
            parent[xParent] = yParent;
        } else {
            // make anyone as parent as ranks are same
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }
}
