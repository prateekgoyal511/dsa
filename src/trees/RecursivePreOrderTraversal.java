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
class RecursivePreOrderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        preorderTraversalUtil(root, result);
        return result;
    }

    //This method takes as input a treenode and an arraylist. What it does is - it adds this node into list
    //Then, it adds its left subtrees in preorder fashion and then its right subtree in preorder fashion
    private void preorderTraversalUtil(TreeNode node, List<Integer> result) {
        if(node == null) return;
        result.add(node.val);
        preorderTraversalUtil(node.left, result);
        preorderTraversalUtil(node.right, result);
    }
}
