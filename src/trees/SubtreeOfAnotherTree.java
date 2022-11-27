package trees;

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
class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null)return false;

        //If root itself is identical to subRoot.
        if(isIdentical(root, subRoot)) return true;

        if(isSubtree(root.left, subRoot)) return true;
        if(isSubtree(root.right, subRoot)) return true;
        return false;
    }

    private boolean isIdentical(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) return true;
        if(node1 == null && node2 != null) return false;
        if(node1 != null && node2 == null) return false;

        //The 2 values must be same and then their left and right subtrees must be identical too.
        if(node1.val != node2.val) return false;
        if(isIdentical(node1.left, node2.left) && isIdentical(node1.right, node2.right)) return true;
        return false;
    }
}
