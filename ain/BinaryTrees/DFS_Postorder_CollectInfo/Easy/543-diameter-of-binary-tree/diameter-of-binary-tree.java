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
     * Approach : Using DFS Approach
     *
     * Intuition - Return Type is TreePair so node 
     * returns { depth, diameter } to its parent
     * 
     * TC: O(N) - all nodes are visited exactly once
     * SC: O(H) ~ O(log(N)) (O(N) in worst case) 
     *
     * height of Tree (in case of Binary Tree, 
     * it is log(N) and in case of skewed Tree, it is N) 
     */
    public int diameterOfBinaryTree(TreeNode root) {
        TreePair pair = dfsTree(root);
        return pair.diameter;
    }

    /**
     * Using DFS Approach
     *
     * Intuition - Return Type is TreePair so node 
     * returns { depth, diameter } to its parent
     * 
     * TC: O(N) - all nodes are visited exactly once
     * SC: O(H) - height of Tree (in case of Binary Tree, 
     * it is log(N) and in case of skewed Tree, it is N) 
     */
    private TreePair dfsTree(TreeNode node) {
        // Base Case
        if (node == null) {
            return new TreePair(0, 0);
        }
        // Recursion Calls
        TreePair leftPair = dfsTree(node.left);
        TreePair rightPair = dfsTree(node.right);
        // combine current node with its children values
        /**
         * case 1: when diameter passes through current node
         */
        int diameterOption1 = leftPair.depth + rightPair.depth;
        /**
         * case 2: when diameter does not pass through node then 
         * diameter passes via either its left or right children
         */
        int diameterOption2 = Math.max(leftPair.diameter, rightPair.diameter);
        int diameter = Math.max(diameterOption1, diameterOption2);
        int depth = 1 + Math.max(leftPair.depth, rightPair.depth);
        return new TreePair(depth, diameter);
    }

    private class TreePair {
        int depth;
        int diameter;

        public TreePair(int depth, int diameter) {
            this.depth = depth;
            this.diameter = diameter;
        }
    }
}
