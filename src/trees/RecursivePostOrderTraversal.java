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
class RecursivePostOrderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        postorderTraversalUtil(root, result);
        return result;
    }

    //This method takes in a node of a BT. It adds postOrder traversal of tree rooted at that node in result
    private void postorderTraversalUtil(TreeNode node, List<Integer> result) {
        if(node == null)return;
        //We want that first we have postOrderTraversal of left node in result
        postorderTraversalUtil(node.left, result);
        //Then, we want that we have postOrderTraversal of right node in result
        postorderTraversalUtil(node.right, result);
        //Then, we want to add our current node in result
        result.add(node.val);
    }
}
