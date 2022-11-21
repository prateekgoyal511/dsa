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
class ValidateBSTInorderTraversalValueHolder {
    public boolean isValidBST(TreeNode root) {
        PrevNodeHolder prevNodeHolder = new PrevNodeHolder();
        return inOrderTraversal(root, prevNodeHolder);
    }

    private boolean inOrderTraversal(TreeNode node, PrevNodeHolder prevNodeHolder) {
        if(node == null) return true;
        //If for some reason, left subtree returns that it is not a valid BST => return false.
        if(!inOrderTraversal(node.left, prevNodeHolder)) {
            return false;
        }
        //If current node's value is lesser than or equal to previous node's value => return false.
        if(prevNodeHolder.prevNode != null && node.val <= prevNodeHolder.prevNode.val){
            return false;
        }
        //Now, for inOrder traversal => this will be our previous node for whenever next node is visited.
        prevNodeHolder.prevNode = node;

        if(!inOrderTraversal(node.right, prevNodeHolder)){
            return false;
        }
        return true;
    }
}

class PrevNodeHolder {
    TreeNode prevNode;
}
