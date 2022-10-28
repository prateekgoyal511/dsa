package trees;

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
class TwoPairWithTargetSum {
    public boolean findTarget(TreeNode root, int k) {
        BSTIterator normalIterator = new BSTIterator(root, true);
        BSTIterator reverseIterator = new BSTIterator(root, false);

        TreeNode i, j;
        i = normalIterator.next();
        j = reverseIterator.next();
        int currentSum;

        //In every iteration, we have 2 nodes - one at extreme left end, one at extreme right end. We sum up
        //If the sum is lesser => we progress the left one. If the sum is greater => we progress the right one.
        while(i.val < j.val) {
            currentSum = i.val + j.val;
            if(currentSum == k) {
                return true;
            }
            if(currentSum < k) {
                i = normalIterator.next();
            } else {
                j = reverseIterator.next();
            }
        }
        return false;
    }
}

//This is an iterator for BST. It can iterate in normal order or in reverse order.
class BSTIterator {
    private Stack<TreeNode> stack;
    private boolean normalOrder;

    //In its constructor, we'll pass root node and the iteratorType. Then, depending on the iterator type,
    //we'll add either left elements or right elements on the stack.
    public BSTIterator(TreeNode root, boolean order) {
        this.stack = new Stack<TreeNode>();
        this.normalOrder = order;
        //If it is normalOrder => left elements.
        TreeNode current = root;
        if(order) {
            while(current != null) {
                this.stack.push(current);
                current = current.left;
            }
        } else {
            while(current != null) {
                this.stack.push(current);
                current = current.right;
            }
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    //Next node is always the topmost node of stack. However, now, we have to ready next node as well. This
    //will either be in right subtree or it'll be the next node on top of stack. This if the iterator is for
    //normal order.
    public TreeNode next() {
        TreeNode current = stack.pop();
        TreeNode currentCopy = current;
        if(normalOrder) {
            currentCopy = currentCopy.right;
            while(currentCopy != null) {
                this.stack.push(currentCopy);
                currentCopy = currentCopy.left;
            }
        } else {
            currentCopy = currentCopy.left;
            while(currentCopy != null) {
                this.stack.push(currentCopy);
                currentCopy = currentCopy.right;
            }
        }
        return current;
    }
}
