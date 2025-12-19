class Solution {
    /**
     * Approach : Using Disjoint Set Union Find Approach
     *
     * TC: O(M x log(M) + M x α(N))
     * SC: O(M + N)
     */
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        int m = meetings.length;
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]); // TC: O(M x log(M))
        Set<Integer> knowsSecret = new HashSet<Integer>(); // SC: O(N)
        knowsSecret.add(0);
        knowsSecret.add(firstPerson);
        int i = 0;
        while (i < m) { // TC: O(M)
            int time = meetings[i][2];
            List<int[]> meetingsList = new ArrayList<int[]>(); // SC: O(M) - reused
            // collect all meetings at the timestamp 'time'
            while (i < m && time == meetings[i][2]) {
                meetingsList.add(meetings[i]);
                i++;
            }
            // collect unique participants
            Set<Integer> participants = new HashSet<Integer>(); // SC: O(M) - reused
            for (int[] edge : meetingsList) {
                participants.add(edge[0]);
                participants.add(edge[1]);
            }
            List<Integer> ppl = new ArrayList<Integer>(participants); // SC: O(N) - reused
            // map person -> UnionFind index
            Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(M) - reused
            for (int idx = 0; idx < ppl.size(); idx++) {
                map.put(ppl.get(idx), idx);
            }
            // build fresh Union Find for each unique timestamp 'time'
            UnionFind uf = new UnionFind(ppl.size()); // SC: O(M) - reused
            // union the edges
            for (int[] edge : meetingsList) { // TC: O(M) - ammortized
                int u = map.get(edge[0]);
                int v = map.get(edge[1]);
                int parentU = uf.find(u); // TC: O(α(N))
                int parentV = uf.find(v); // TC: O(α(N))
                uf.unionByRank(parentU, parentV);
            }
            // group by Union Find root
            Map<Integer, ArrayList<Integer>> groupByRoot = 
                new HashMap<Integer, ArrayList<Integer>>(); // SC: O(N) - reused
            for (Integer person : ppl) {
                int root = uf.find(map.get(person));
                groupByRoot.computeIfAbsent(root, k -> new ArrayList<Integer>()).add(person);
            }
            // now add the person knowing secret in knowsSecret
            for (ArrayList<Integer> grp : groupByRoot.values()) {
                boolean canBeRevealed = false;
                for (Integer p : grp) {
                    if (knowsSecret.contains(p)) {
                        canBeRevealed = true;
                        break;
                    }
                }
                if (canBeRevealed) {
                    knowsSecret.addAll(grp);
                }
            }
        }
        return new ArrayList<Integer>(knowsSecret);
    }

    private class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * Using Find By Path Compression Approach
         *
         * TC: O(α(V))
         * SC: O(V)
         */
        private int find(int x) {
            if (x == parent[x]) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        /**
         * Using Union By Rank Approach
         *
         * TC: O(1)
         * SC: O(1)
         */
        private void unionByRank(int xParent, int yParent) {
            if (rank[xParent] > rank[yParent]) {
                // make xParent as parent
                parent[yParent] = xParent;
            } else if (rank[xParent] < rank[yParent]) {
                // make yParent as parent
                parent[xParent] = yParent;
            } else {
                // make anyone as parent
                parent[yParent] = xParent;
                rank[xParent]++;
            }
        }
    }
}
