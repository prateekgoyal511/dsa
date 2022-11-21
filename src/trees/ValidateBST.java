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
class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        boolean leftValid;
        boolean rightValid;

        return isValidBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

        // return leftValid && rightValid;
    }

    private boolean isValidBSTUtil(TreeNode node, int minVal, int maxVal) {
        if(node == null) return true;
        if(node.val < minVal) return false;
        if(node.val > maxVal) return false;

        boolean leftValid, rightValid;
        leftValid = isValidBSTUtil(node.left, minVal, node.val-1);
        rightValid = isValidBSTUtil(node.right, node.val+1, maxVal);

        return leftValid && rightValid;
    }
}
