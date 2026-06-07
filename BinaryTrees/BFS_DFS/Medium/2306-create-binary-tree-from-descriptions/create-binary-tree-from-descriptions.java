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
     * Approach II : Using Hashing Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    public TreeNode createBinaryTree(int[][] descriptions) {
        /**
         * We will store { parent/child, TreeNode } in the HashMap
         */
        Map<Integer, TreeNode> map = new HashMap<>(); // SC : O(n)
        Set<Integer> children = new HashSet<>(); // SC: O(n)
        for (int[] edge : descriptions) { // TC : O(n)
            int parent = edge[0];
            int child = edge[1];
            int isLeft = edge[2];
            map.putIfAbsent(parent, new TreeNode(parent));
            map.putIfAbsent(child, new TreeNode(child));
            if (isLeft == 1) {
                map.get(parent).left = map.get(child);
            } else {
                map.get(parent).right = map.get(child);
            }
            children.add(child);
        }
        for (Integer key : map.keySet()) { // TC : O(n)
            if (!children.contains(key)) {
                // this is our root node
                return map.get(key);
            }
        }
        return null;
    }

    /**
     * Approach I : Using Brute-Force (Hashing + DFS) Approach
     *
     * TC : O(n) + O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    public TreeNode createBinaryTreeBruteForce(int[][] descriptions) {
        /**
         * We will store { parent, ArrayList<[child, isLeft]> } in the HashMap
         */
        Map<Integer, ArrayList<int[]>> map = new HashMap<>(); // SC : O(n)
        /**
         * we also need to find the root so let's compute the
         * incoming edges to a node i.e. indegrees
         */
        Map<Integer, Integer> indegrees = new HashMap<>();   // SC : O(n)
        for (int[] edge : descriptions) { // TC : O(n)
            int parent = edge[0];
            int child = edge[1];
            int isLeft = edge[2];
            map.computeIfAbsent(parent, k -> new ArrayList<>()).add(new int[] { child, isLeft });
            if (!indegrees.containsKey(parent)) {
                indegrees.put(parent, 0);
            }
            indegrees.put(child, indegrees.getOrDefault(child, 0) + 1);
        }
        int rootValue = 0;
        for (Integer key : indegrees.keySet()) { // TC : O(n)
            if (indegrees.get(key) == 0) {
                rootValue = key;
                break;
            }
        }
        TreeNode root = new TreeNode(rootValue);
        buildTree(root, map); // TC : O(n), SC : O(n)
        return root;
    }

    /**
     * Using DFS Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    private void buildTree(TreeNode root, Map<Integer, ArrayList<int[]>> map) {
        if (root == null) {
            return;
        }
        ArrayList<int[]> children = map.get(root.val);
        if (children == null) {
            return;
        }
        for (int[] item : children) {
            int value = item[0];
            int isLeft = item[1];
            if (isLeft == 1) {
                root.left = new TreeNode(value);
                buildTree(root.left, map);
            } else {
                root.right = new TreeNode(value);
                buildTree(root.right, map);
            }
        }
    }
}
