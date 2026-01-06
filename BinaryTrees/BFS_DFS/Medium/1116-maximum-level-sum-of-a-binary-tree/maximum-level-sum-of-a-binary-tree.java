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
     * TC: O(W + H)
     * SC: O(W + H)
     * where W = number of nodes at the highest level (leaf node level)
     * and H = height of tree
     */
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 1;
        }
        Map<Integer, Long> map = new HashMap<Integer, Long>(); // SC: O(H)
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); // SC: O(W)
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) { // TC: O(W)
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
            map.put(level, sumLevel);
            level++;
        }
        long maxSum = Long.MIN_VALUE;
        int result = 0;
        for (Integer key : map.keySet()) { // TC: O(H)
            if (map.get(key) > maxSum) {
                maxSum = map.get(key);
                result = key;
            }
        }
        return result;
    }
}
