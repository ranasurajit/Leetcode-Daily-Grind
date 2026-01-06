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
     * Approach II : Using DFS Approach
     *
     * TC: O(N) + O(H) ~ O(N)
     * SC: O(N) + O(H) - recursion stack
     * where N = number of nodes of the tree, H = height of tree = log(N) ~ N (in worst case)
     */
    public int maxLevelSum(TreeNode root) {
        List<Long> sumLevel = new ArrayList<Long>();
        dfsTree(root, 0, sumLevel); // TC: O(N), SC: O(H)
        long maxSum = Long.MIN_VALUE;
        int maxLevel = 0;
        for (int i = 0; i < sumLevel.size(); i++) { // TC: O(H)
            if (sumLevel.get(i) > maxSum) {
                maxSum = sumLevel.get(i);
                maxLevel = i + 1;
            }
        }
        return maxLevel;
    }

    /**
     * Using DFS Approach
     *
     * TC: O(N)
     * SC: O(log(N)) - recursion stack
     * where N = number of nodes of the tree
     */
    private void dfsTree(TreeNode node, int level, List<Long> sumLevel) {
        if (node == null) {
            return;
        }
        if (sumLevel.size() == level) {
            sumLevel.add(0L);
        }
        sumLevel.set(level, sumLevel.get(level) + node.val); // adding current node's value
        dfsTree(node.left, level + 1, sumLevel);
        dfsTree(node.right, level + 1, sumLevel);
    }

    /**
     * Approach I : Using BFS Approach
     *
     * TC: O(N)
     * SC: O(N)
     * where N = number of nodes of the tree
     */
    public int maxLevelSumBFS(TreeNode root) {
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
