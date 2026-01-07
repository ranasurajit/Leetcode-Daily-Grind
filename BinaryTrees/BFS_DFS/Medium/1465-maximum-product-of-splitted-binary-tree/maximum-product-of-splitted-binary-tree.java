/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach II : Using Optimal (DFS) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(H) + O(H) ~ O(H) ~ O(N in worst case for skewed tree)
     *
     * Accepted (54 / 54 testcases passed)
     */
    public int maxProduct(TreeNode root) {
        if (root == null) {
            return 0;
        }
        long totalSum = dfsTreeSum(root); // TC: O(N), SC: O(H)
        /**
         * assume if I cut an edge prior to any node, then I need to 
         * compute sum 'nodeSum' till that node, so that product of two sub-trees
         * in that case will be nodeSum x (totalSum - nodeSum)
         */
        long[] maxProduct = { 0L };
        dfsMaxProduct(root, maxProduct, totalSum); // TC: O(N), SC: O(H)
        return (int) (maxProduct[0] % MOD);
    }

    /**
     * Using DFS Approach
     *
     * TC: O(N)
     * SC: O(H)
     */
    private long dfsMaxProduct(TreeNode node, long[] maxProduct, long totalSum) {
        // Base Case
        if (node == null) {
            return 0L;
        }
        // Recursion Calls
        long leftSum = dfsMaxProduct(node.left, maxProduct, totalSum);
        long rightSum = dfsMaxProduct(node.right, maxProduct, totalSum);
        long currentSum = (long) node.val + leftSum + rightSum;
        maxProduct[0] = Math.max(maxProduct[0], currentSum * (totalSum - currentSum));
        return currentSum;
    }

    /**
     * Approach I : Using Brute-Force (DFS) Approach
     *
     * TC: O(N²) + O(N) ~ O(N²)
     * SC: O(H) + O(H) ~ O(H) ~ O(N in worst case for skewed tree)
     *
     * Time Limit Exceeded (52 / 54 testcases passed)
     */
    public int maxProductBruteForce(TreeNode root) {
        if (root == null) {
            return 0;
        }
        long totalSum = dfsTreeSum(root); // TC: O(N), SC: O(H)
        /**
         * assume if I cut an edge prior to any node, then I need to 
         * compute sum 'nodeSum' till that node, so that product of two sub-trees
         * in that case will be nodeSum x (totalSum - nodeSum)
         */
        long[] maxProduct = { 0L };
        dfsComputeProduct(root, totalSum, maxProduct); // TC: O(N²), SC: O(H)
        return (int) (maxProduct[0] % MOD);
    }

    /**
     * Using DFS Approach
     *
     * TC: O(N²)
     * SC: O(H)
     */
    private void dfsComputeProduct(TreeNode node, long totalSum, long[] maxProduct) {
        // Base Case
        if (node == null) {
            return;
        }
        // leaf node
        if (node.left == null && node.right == null) {
            maxProduct[0] = Math.max(maxProduct[0], node.val * (totalSum - node.val));
            return;
        }
        // Recursion Calls
        long currentSum = dfsTreeSum(node); // TC: O(N), SC: O(N)
        maxProduct[0] = Math.max(maxProduct[0], currentSum * (totalSum - currentSum));
        dfsComputeProduct(node.left, totalSum, maxProduct);
        dfsComputeProduct(node.right, totalSum, maxProduct);
    }

    /**
     * Using DFS Approach
     *
     * TC: O(N)
     * SC: O(H)
     */
    private long dfsTreeSum(TreeNode node) {
        // Base Case
        if (node == null) {
            return 0L;
        }
        if (node.left == null && node.right == null) {
            // leaf node
            return (long) node.val;
        }
        // Recursion Calls
        return (long) node.val + dfsTreeSum(node.left) + dfsTreeSum(node.right);
    }
}
