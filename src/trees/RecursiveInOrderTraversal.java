package trees;

import java.util.ArrayList;
import java.util.List;

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
class RecursiveInOrderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        inorderTraversalUtil(root, result);
        return result;
    }

    /*
    This method takes in a node of a tree. It does an inorderTraversal of subtree rooted at this node
    and adds the items in the result.
    */
    private void inorderTraversalUtil(TreeNode node, List<Integer> result) {
        //We first do, inorderTraversal of left subtree. Now, everything is inorder for left subtree.
        //Then, we add central node. Then, we do inorderTraversal of right subtree. Now, everything is in
        //order for right subtree.
        if(node == null) return;

        if(node.left != null) {
            inorderTraversalUtil(node.left, result);
        }
        result.add(node.val);
        if(node.right != null) {
            inorderTraversalUtil(node.right, result);
        }
    }
}
