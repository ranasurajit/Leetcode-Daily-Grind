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
     * Approach II : Using DFS (Pre-Order Traversal - Without Extra Space) Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int sumRootToLeaf(TreeNode root) {
        return dfsTreeOptimal(0, root);
    }

    /**
     * Using DFS (Pre-Order Traversal) Approach
     *
     * TC: O(N)
     * SC: O(H) ~ O(N) - recursion stack
     */
    private int dfsTreeOptimal(int lastValue, TreeNode node) {
        // Base Case
        if (node == null) {
            return 0;
        }
        int currentValue = (lastValue << 1) + node.val;
        if (node.left == null && node.right == null) {
            // leaf node
            return currentValue;
        }
        // Recursion Calls
        int leftValue = dfsTreeOptimal(currentValue, node.left);
        int rightValue = dfsTreeOptimal(currentValue, node.right);
        return leftValue + rightValue;
    }

    /**
     * Approach I : Using DFS (Pre-Order Traversal - With Extra Space) Approach
     *
     * TC: O(N)
     * SC: O(N) + O(K) ~ O(N)
     *
     * where K = number of leaf nodes
     */
    public int sumRootToLeafWithExtraSpace(TreeNode root) {
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
