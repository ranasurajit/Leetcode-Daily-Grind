class Solution {

    // Max coordinate as per constraints
    private static final int MAX = 50000;

    // Segment Tree to store maximum gap ending at index i
    int[] segTree;

    // Build empty segment tree
    private void build() {
        segTree = new int[4 * MAX];
    }

    /**
     * Update segment tree at position `idx` with value `val`
     * idx represents a point where a gap ends
     */
    private void update(int idx, int val, int node, int left, int right) {
        // Leaf node
        if (left == right) {
            segTree[node] = val;
            return;
        }

        int mid = left + (right - left) / 2;

        if (idx <= mid) {
            update(idx, val, 2 * node + 1, left, mid);
        } else {
            update(idx, val, 2 * node + 2, mid + 1, right);
        }

        // Maintain maximum gap in this segment
        segTree[node] = Math.max(segTree[2 * node + 1], segTree[2 * node + 2]);
    }

    /**
     * Query maximum gap in range [ql, qr]
     */
    private int query(int ql, int qr, int node, int left, int right) {
        // No overlap
        if (right < ql || left > qr) return 0;

        // Complete overlap
        if (left >= ql && right <= qr) return segTree[node];

        int mid = left + (right - left) / 2;

        return Math.max(
            query(ql, qr, 2 * node + 1, left, mid),
            query(ql, qr, 2 * node + 2, mid + 1, right)
        );
    }

    public List<Boolean> getResults(int[][] queries) {
        build();

        // Stores obstacle positions in sorted order
        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0); // Base boundary

        List<Boolean> result = new ArrayList<>();

        for (int[] q : queries) {

            // Type 1: Insert obstacle
            if (q[0] == 1) {
                int x = q[1];

                // Find nearest neighbors
                Integer next = obstacles.higher(x);   // first > x
                Integer prev = obstacles.floor(x);    // last <= x

                // Create new gap: (prev -> x)
                update(x, x - prev, 0, 0, MAX - 1);

                // Update next gap: (x -> next)
                if (next != null) {
                    update(next, next - x, 0, 0, MAX - 1);
                }

                obstacles.add(x);
            }

            // Type 2: Query
            else {
                int x = q[1];
                int size = q[2];

                // Find last obstacle <= x
                int prev = obstacles.floor(x);

                // Maximum gap fully inside [0, prev]
                int maxGap = query(0, prev, 0, 0, MAX - 1);

                // Tail gap: (prev -> x)
                int tailGap = x - prev;

                int best = Math.max(maxGap, tailGap);

                result.add(best >= size);
            }
        }

        return result;
    }
}
