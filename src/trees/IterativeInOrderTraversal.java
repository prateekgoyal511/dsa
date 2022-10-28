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
class IterativeInOrderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        //Current node denotes which node we are at while traversing this tree.
        TreeNode curr = root;
        //This stack will always contain nodes whose left subtrees haven't been done yet.
        //They'll be in the order in which the left subtrees need to be visited.
        Stack<TreeNode> stack = new Stack<TreeNode>();

        //In every iteration, we plan to reach next element in inOrderTraversal.
        //There are 2 types of visits happening. First is traversal when we go to its left subtree and all
        //Then, second is when we actually visit the node in its inorder position. When, we are in
        //second type of visit => what is the next node. If right subtree isn't null => then leftmost child
        //in that is next node. If it is null => then topmost element of stack is next.
        while(curr != null || !stack.isEmpty()) {
            //Traverse till leftmost if curr is not null. Keep storing these elements in stack as their
            //left subtrees are yet to be explored.
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            //At this point curr must be null. So, next element in inorder traversal is stack's top.
            curr = stack.pop();
            result.add(curr.val);

            //To find the next element, we have to go either to leftmost child of right subtree if right
            //subtree is not null. If it is null => stack's topmost element will be next.
            curr = curr.right;
        }

        return result;
    }
}
