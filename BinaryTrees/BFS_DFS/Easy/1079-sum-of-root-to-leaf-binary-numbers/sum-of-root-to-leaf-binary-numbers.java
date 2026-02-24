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
     * Approach : Using DFS (Pre-Order Traversal) Approach
     *
     * TC: O(N)
     * SC: O(N) + O(K) ~ O(N)
     *
     * where K = number of leaf nodes
     */
    public int sumRootToLeaf(TreeNode root) {
        List<Integer> binaryList = new ArrayList<>(); // SC: O(K)
        dfsTree(0, root, binaryList);
        int sum = 0;
        for (Integer x : binaryList) {
            sum += x;
        }
        return sum;
    }

    /**
     * Using DFS (Pre-Order Traversal) Approach
     *
     * TC: O(N)
     * SC: O(H) ~ O(N) - recursion stack
     */
    private void dfsTree(int lastValue, TreeNode node, List<Integer> binaryList) {
        // Base Case
        if (node == null) {
            return;
        }
        int nodeVal = lastValue * 2 + node.val;
        if (node.left == null && node.right == null) {
            // leaf node
            binaryList.add(nodeVal);
            return;
        }
        // Recursion Calls
        dfsTree(nodeVal, node.left, binaryList);
        dfsTree(nodeVal, node.right, binaryList);
    }
}
