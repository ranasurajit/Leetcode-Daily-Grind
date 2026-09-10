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
    private int countNodes;

    /**
     * Approach : Using DFS Post Order Traversal Approach
     *
     * returns { sum, count } of sub-tree nodes
     *
     * TC : O(n)
     * SC : O(h) ~ O(n)
     */
    public int averageOfSubtree(TreeNode root) {
        dfsTree(root);
        return countNodes;
    }

    /**
     * Using DFS Post Order Traversal Approach
     *
     * returns { sum, count } of sub-tree nodes
     *
     * TC : O(n)
     * SC : O(h)
     */
    private int[] dfsTree(TreeNode node) {
        // Base Case
        if (node == null) {
            return new int[] { 0, 0 };
        }
        // Recursion Calls
        int[] left = dfsTree(node.left);
        int[] right = dfsTree(node.right);
        int sum = left[0] + right[0] + node.val;
        int count = left[1] + right[1] + 1;
        if ((sum / count) == node.val) {
            countNodes++;
        }
        return new int[] { sum, count };
    }
}
