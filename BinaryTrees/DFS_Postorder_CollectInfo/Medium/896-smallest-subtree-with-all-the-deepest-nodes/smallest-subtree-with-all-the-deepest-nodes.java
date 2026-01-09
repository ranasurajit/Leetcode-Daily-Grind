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
     * Approach : Using DFS Traversal Approach
     *
     * TC: O(N)
     * SC: O(H) ~ O(N)
     *
     * where H = height of Tree (O(N) in worst case)
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfsTree(root).node;
    }

    /**
     * Using DFS Traversal Approach
     *
     * TC: O(N)
     * SC: O(H), where H = height of Tree (O(N) in worst case)
     */
    private TreePair dfsTree(TreeNode node) {
        // Base Case
        if (node == null) {
            return new TreePair(0, null);
        }
        // Recursion Calls
        /**
         * We will apply DFS PostOrder Traversal as we need 
         * to get some information from children nodes
         */
        TreePair leftPair = dfsTree(node.left);   // get pair information from left 
        TreePair rightPair = dfsTree(node.right); // get pair information from right 
        if (leftPair.depth > rightPair.depth) {
            return new TreePair(leftPair.depth + 1, leftPair.node);
        } else if (leftPair.depth < rightPair.depth) {
            return new TreePair(rightPair.depth + 1, rightPair.node);
        }
        return new TreePair(leftPair.depth + 1, node);
    }

    private class TreePair {
        int depth;
        TreeNode node;

        public TreePair(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
    }
}
