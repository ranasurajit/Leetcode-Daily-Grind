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
     * Approach I : Using BFS Approach
     *
     * TC: O(N)
     * SC: O(N)
     * where N = number of nodes of the tree
     */
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 1;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); // SC: O(N)
        queue.offer(root);
        int level = 1;
        long maxSum = Long.MIN_VALUE;
        int maxLevel = 0;
        while (!queue.isEmpty()) { // TC: O(N)
            int size = queue.size();
            long sumLevel = 0L;
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                sumLevel += current.val;
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            if (sumLevel > maxSum) {
                maxSum = sumLevel;
                maxLevel = level;
            }
            level++;
        }
        return maxLevel;
    }
}
