package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
class IterativePreOrderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //For iterative, we first process/visit the node. Then, we want to process the left child first and
        //then the right child. So, we'll first add right child in stack and then left child in stack.
        //Here, stack contains elements that aren't processed yet.
        if(root == null)return result;
        stack.push(root);
        TreeNode visitingNode;

        while(!stack.isEmpty()) {
            visitingNode = stack.pop();
            result.add(visitingNode.val);
            if(visitingNode.right != null) {
                stack.push(visitingNode.right);
            }
            if(visitingNode.left != null) {
                stack.push(visitingNode.left);
            }
        }

        return result;
    }
}
