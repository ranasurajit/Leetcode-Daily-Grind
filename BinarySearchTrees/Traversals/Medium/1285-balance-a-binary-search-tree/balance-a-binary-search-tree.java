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
     * Approach : Using Preorder + Inorder Traversal Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     * - O(N) - 'inorderList' memory
     * - O(N) - recursion stack (reused)
     */
    public TreeNode balanceBST(TreeNode root) {
        /**
         * inorder travseral of a Binary Search Tree
         * forms a sorted array
         */
        List<Integer> inorderList = new ArrayList<Integer>(); // SC: O(N)
        dfsInorder(root, inorderList); // TC: O(N), SC: O(N)
        /**
         * now we can use this sorted ArrayList 'inorderList'
         * to form Balanced Binary Search Tree
         */
        return dfsBST(inorderList, 0, inorderList.size() - 1); // TC: O(N), SC: O(N)
    }

    /**
     * Using Preorder Traversal Approach
     *
     * TC: O(N)
     * SC: O(H), where H = log(N) but in worst case it is O(N)
     */
    private TreeNode dfsBST(List<Integer> inorderList, int low, int high) {
        // Base Case
        if (low > high) {
            return null;
        }
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(inorderList.get(mid));
        root.left = dfsBST(inorderList, low, mid - 1);
        root.right = dfsBST(inorderList, mid + 1, high);
        return root;
    }

    /**
     * Using Inorder Traversal Approach
     *
     * TC: O(N)
     * SC: O(H), where H = log(N) but in worst case it is O(N)
     */
    private void dfsInorder(TreeNode node, List<Integer> inorderList) {
        // Base Case
        if (node == null) {
            return;
        }
        // Recursion Calls
        dfsInorder(node.left, inorderList);
        inorderList.add(node.val);
        dfsInorder(node.right, inorderList);
    }
}
