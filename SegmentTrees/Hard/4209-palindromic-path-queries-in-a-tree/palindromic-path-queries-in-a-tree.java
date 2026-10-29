class Solution {
    private List<Integer>[] adj;
    private int[] parent, depth, size, heavy;
    private int[] head, pos;
    private int currentPos;
    private SegmentTree segTree;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        // 1. Build adjacency list
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        // 2. Initialize HLD arrays
        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];

        Arrays.fill(heavy, -1);

        // 3. Find subtree sizes and heavy child
        dfs(0, -1);

        // 4. Heavy-Light Decomposition
        currentPos = 0;
        decompose(0, 0);

        // 5. Build flattened array
        int[] base = new int[n];

        for (int node = 0; node < n; node++) {
            base[pos[node]] = getMask(s.charAt(node));
        }

        // 6. Build Segment Tree
        segTree = new SegmentTree(base);

        List<Boolean> answer = new ArrayList<>();

        // 7. Process queries
        for (String query : queries) {

            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {

                int node = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                segTree.update(
                    pos[node],
                    getMask(c)
                );

            } else {

                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = queryPath(u, v);

                // At most one character can have odd frequency
                answer.add(Integer.bitCount(mask) <= 1);
            }
        }

        return answer;
    }

    // --------------------------------------------------
    // DFS: calculate size, depth, parent and heavy child
    // --------------------------------------------------
    private void dfs(int node, int par) {

        parent[node] = par;
        size[node] = 1;

        int maxChildSize = 0;

        for (int next : adj[node]) {

            if (next == par) {
                continue;
            }

            depth[next] = depth[node] + 1;

            dfs(next, node);

            size[node] += size[next];

            if (size[next] > maxChildSize) {
                maxChildSize = size[next];
                heavy[node] = next;
            }
        }
    }

    // --------------------------------------------------
    // Heavy-Light Decomposition
    // --------------------------------------------------
    private void decompose(int node, int chainHead) {

        head[node] = chainHead;
        pos[node] = currentPos++;

        // Heavy child stays in same chain
        if (heavy[node] != -1) {
            decompose(heavy[node], chainHead);
        }

        // Light children start new chains
        for (int next : adj[node]) {

            if (next == parent[node] ||
                next == heavy[node]) {
                continue;
            }

            decompose(next, next);
        }
    }

    // --------------------------------------------------
    // XOR of all characters on path u -> v
    // --------------------------------------------------
    private int queryPath(int u, int v) {

        int result = 0;

        // Until u and v are in same chain
        while (head[u] != head[v]) {

            // Keep u's chain deeper
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            // Query head[u] -> u
            result ^= segTree.query(
                pos[head[u]],
                pos[u]
            );

            // Jump above this chain
            u = parent[head[u]];
        }

        // Same chain now.
        // Make u the shallower node.
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        result ^= segTree.query(
            pos[u],
            pos[v]
        );

        return result;
    }

    // Character -> bitmask
    private int getMask(char c) {
        return 1 << (c - 'a');
    }

    // ==================================================
    // Segment Tree
    // ==================================================
    static class SegmentTree {

        private int n;
        private int[] tree;

        SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n];

            build(arr, 1, 0, n - 1);
        }

        private void build(
                int[] arr,
                int node,
                int left,
                int right) {

            if (left == right) {
                tree[node] = arr[left];
                return;
            }

            int mid = left + (right - left) / 2;

            build(
                arr,
                node * 2,
                left,
                mid
            );

            build(
                arr,
                node * 2 + 1,
                mid + 1,
                right
            );

            tree[node] =
                tree[node * 2]
                ^ tree[node * 2 + 1];
        }

        // Point update
        public void update(int index, int value) {

            update(
                1,
                0,
                n - 1,
                index,
                value
            );
        }

        private void update(
                int node,
                int left,
                int right,
                int index,
                int value) {

            if (left == right) {
                tree[node] = value;
                return;
            }

            int mid = left + (right - left) / 2;

            if (index <= mid) {

                update(
                    node * 2,
                    left,
                    mid,
                    index,
                    value
                );

            } else {

                update(
                    node * 2 + 1,
                    mid + 1,
                    right,
                    index,
                    value
                );
            }

            tree[node] =
                tree[node * 2]
                ^ tree[node * 2 + 1];
        }

        // Range XOR query
        public int query(int ql, int qr) {

            return query(
                1,
                0,
                n - 1,
                ql,
                qr
            );
        }

        private int query(
                int node,
                int left,
                int right,
                int ql,
                int qr) {

            // No overlap
            if (right < ql || qr < left) {
                return 0;
            }

            // Complete overlap
            if (ql <= left && right <= qr) {
                return tree[node];
            }

            int mid = left + (right - left) / 2;

            int leftXor = query(
                node * 2,
                left,
                mid,
                ql,
                qr
            );

            int rightXor = query(
                node * 2 + 1,
                mid + 1,
                right,
                ql,
                qr
            );

            return leftXor ^ rightXor;
        }
    }
}
