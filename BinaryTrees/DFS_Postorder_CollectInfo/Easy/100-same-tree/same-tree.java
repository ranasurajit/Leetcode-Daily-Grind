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
     * TC: O(N)
     * SC: O(H) ~ O(N) (worst case in case of skewed tree, else H = log(N))
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return dfsTree(p, q);
    }

    /**
     * Using DFS Approach
     *
     * TC: O(N)
     * SC: O(H)
     */
    private boolean dfsTree(TreeNode p, TreeNode q) {
        // Base Case
        if (p == null || q == null) {
            return p == q;
        }
        // Recursion Calls
        boolean isLeftSame = dfsTree(p.left, q.left);
        boolean isRightSame = dfsTree(p.right, q.right);
        // combine / merge result from left and right children with node
        return p.val == q.val && isLeftSame && isRightSame;
    }
}
