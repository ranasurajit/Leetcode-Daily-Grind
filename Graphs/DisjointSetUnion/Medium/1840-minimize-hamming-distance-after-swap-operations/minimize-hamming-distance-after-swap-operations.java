class Solution {
    /**
     * Approach : Using Disjoint Set Union Find + Hashing Approach
     *
     * TC : O(n) + O(e x α(e)) + O(n x α(e)) + O(n) ~ O((n + e) x α(e))
     * SC : O(n) + O(e x α(e)) + O(k) ~ O(n + e x α(e) + k)
     */
    public int minimumHammingDistance(int[] source, int[] target,
        int[][] allowedSwaps) {
        int n = source.length;
        DSU dsu = new DSU(n); // TC : O(n), SC : O(n)
        for (int[] edge : allowedSwaps) { // TC: O(e)
            int a = edge[0];
            int b = edge[1];
            int aParent = dsu.find(a);    // TC : O(α(e)), SC : O(α(e))
            int bParent = dsu.find(b);    // TC : O(α(e)), SC : O(α(e))
            if (aParent != bParent) {
                dsu.unionByRank(aParent, bParent); // TC : O(1)
            }
        }
        Map<Integer, ArrayList<Integer>> groups = new HashMap<>(); // TC: O(k)
        for (int i = 0; i < n; i++) { // TC: O(n)
            int root = dsu.find(i);   // TC : O(α(n))
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }
        int mismatches = 0;
        for (ArrayList<Integer> indices : groups.values()) { // TC: O(k)
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (Integer idx : indices) { // TC: O(n / k)
                freqMap.put(source[idx], freqMap.getOrDefault(source[idx], 0) + 1);
            }
            for (Integer idx : indices) { // TC: O(n / k)
                if (freqMap.getOrDefault(target[idx], 0) > 0) {
                    freqMap.put(target[idx],
                        freqMap.getOrDefault(target[idx], 0) - 1);
                } else {
                    mismatches++;
                }
            }
        }
        return mismatches;
    }
}

class DSU {
    private int n;
    private int[] parent;
    private int[] rank;

    /**
     * Using DSU Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    public DSU (int n) {
        this.n = n;
        this.parent = new int[n]; // SC: O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            this.parent[i] = i;
        }
        this.rank = new int[n];
    }

    /**
     * Using DSU (Find by Path Compression) Approach
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
     * Using DSU (Union by Rank) Approach
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
