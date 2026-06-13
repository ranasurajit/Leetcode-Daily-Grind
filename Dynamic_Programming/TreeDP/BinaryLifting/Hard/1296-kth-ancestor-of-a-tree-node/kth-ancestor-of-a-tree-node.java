/**
 * Approach : Using Binary Lifting + DP on Trees Approach
 *
 * TC : O(n) + O(n x (log(n) Base 2)) + O(q x (log(n) Base 2))
 *      ~ O((n + q) x log(n) Base 2)
 * SC : O(n x (log(n) Base 2))
 */
class TreeAncestor {
    private final int LOG;
    private final int[][] ancestor;

    /**
     * Using Binary Lifting + DP on Trees Approach
     *
     * TC : O(n x (log(n) Base 2)) + O(n) + O(n x (log(n) Base 2))
     *      ~ O(n x (log(n) Base 2))
     * SC : O(n x (log(n) Base 2))
     */
    public TreeAncestor(int n, int[] parent) {
        this.LOG = (int) (Math.log(n) / Math.log(2)) + 1;
        this.ancestor = new int[n][LOG]; // SC : O(n x (log(n) Base 2))
        /**
         * Filling up default value -1 to 'ancestor' array
         */
        for (int node = 0; node < n; node++) { // TC : O(n)
            for (int j = 0; j < LOG; j++) { // TC : O(log(n) Base 2)
                ancestor[node][j] = -1;
            }
        }
        /**
         * Filling up the 0th column of 'ancestor' array
         * from the given 'parent' array
         */
        for (int node = 0; node < n; node++) { // TC : O(n)
            ancestor[node][0] = parent[node];
        }
        /**
         * ancestor[node][j] denotes jump of 2^j steps from node
         * so, 2^j jump = 2 x 2^(j - 1) = 2^(j - 1) + 2^(j - 1)
         * so jumping from node 'e' to node 'a' can be achieved
         * by jumping from node 'e' to node 'c' by 2^(j - 1) steps
         * and then jumping from node 'c' to node 'a' by 2^(j - 1)
         * steps, so ancestor[node][j - 1] = c where node = e, 
         * so, ancestor[node][j] = ancestor[c] + ancestor[node][j - 1]
         * i.e. prev = ancestor[node][j - 1] and
         * ancestor[node][j] = ancestor[prev][j - 1]
         */
        // we will fill other values in ancestor array
        for (int j = 1; j < LOG; j++) { // TC : O(log(n) Base 2)
            for (int node = 0; node < n; node++) { // TC : O(n)
                int prevNode = ancestor[node][j - 1];
                if (prevNode != -1) {
                    ancestor[node][j] = ancestor[prevNode][j - 1];
                }
            }
        }
    }
    
    /**
     * Using Binary Lifting + DP on Trees Approach
     *
     * TC : O(log(n) Base 2) per query
     * SC : O(1)
     */
    public int getKthAncestor(int node, int k) {
        for (int j = 0; j < LOG && node != -1; j++) { // TC : O(log(n) Base 2)
            if ((k & (1 << j)) != 0) {
                // jth bit is 1
                node = ancestor[node][j];
                if (node == -1) {
                    // jumped out of bounds
                    return -1;
                }
            }
        }
        return node;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
