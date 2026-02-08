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
    /**
     * Approach II : Using Post-order Traversal Approach
     *
     * TC: O(N) - every node is visited once
     * SC: O(N) - recursion stack
     */
    public boolean isBalanced(TreeNode root) {
        if (dfsTreeOptimal(root) == -1) {
            // returns unbalanced
            return false;
        }
        return true;
    }

    /**
     * Using Post-order Traversal Approach
     * To concise the below approach we will
     * return actual node depth/height only if
     * it is balanced and all invalid cases
     * will be returned with -1
     *
     * TC: O(N) - every node is visited once
     * SC: O(N) - recursion stack
     */
    private int dfsTreeOptimal(TreeNode root) {
        // Base Case
        if (root == null) {
            return 0;
        }
        // Recursion Calls
        int left = dfsTreeOptimal(root.left);
        if (left == -1) {
            return -1;
        }
        int right = dfsTreeOptimal(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            // not balanced
            return -1;
        }
        return 1 + Math.max(left, right);
    }

    /**
     * Approach I : Using Post-order Traversal Approach
     *
     * TC: O(N) - every node is visited once
     * SC: O(N) - recursion stack
     */
    public boolean isBalancedDFSTreeApproach(TreeNode root) {
        NodeInfo nodeInfo = dfsTree(root); // TC: O(N), SC: O(N)
        return nodeInfo.balanced;
    }

    /**
     * Using Post-order Traversal Approach
     *
     * TC: O(N) - every node is visited once
     * SC: O(N) - recursion stack
     */
    private NodeInfo dfsTree(TreeNode node) {
        // Base Case
        if (node == null) {
            // null node has height/depth 0
            return new NodeInfo(0, true);
        }
        // Recursion Calls
        NodeInfo left = dfsTree(node.left);
        NodeInfo right = dfsTree(node.right);
        // check if TreeNode 'node' is balanced
        boolean balanced = left.balanced && 
            right.balanced && 
            Math.abs(left.height - right.height) <= 1;
        // height at TreeNode 'node'
        int height = 1 + Math.max(left.height, right.height);
        return new NodeInfo(height, balanced);
    }

    private class NodeInfo {
        int height;
        boolean balanced;

        public NodeInfo (int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }
}
