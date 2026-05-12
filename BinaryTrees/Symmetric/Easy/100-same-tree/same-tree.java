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
     * Approach : Using DFS Pre-Order Approach
     *
     * TC : O(n)
     * SC : O(k) ~ O(log(n)) (O(n) in worst case)
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base Case
        if (p == null && q == null) {
            // if both nodes are null then we should return true
            return true;
        }
        if (p == null || q == null) {
            // if anyone node becomes null then it dis-qualifies
            return false;
        }
        if (p.val != q.val) {
            // if node values are different it dis-qualifies
            return false;
        }
        // Recursion Calls
        // at this point both nodes 'p' and 'q' are valid
        // now check for similarity in both node's children
        boolean isLeftSameTree = isSameTree(p.left, q.left);
        boolean isRightSameTree = isSameTree(p.right, q.right);
        return isLeftSameTree && isRightSameTree;
    }
}
