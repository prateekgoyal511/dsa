package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
class RightViewOfATreeLevelOrderApproach {
    public List<Integer> rightSideView(TreeNode root) {

        //If there is no tree => no list.
        if(root == null) return new ArrayList<Integer>();

        //This will store the final result.
        List<Integer> result = new ArrayList<Integer>();

        //This queue will store the nodes of tree that are to be visited in
        //order. We are using QueueNode here because we will also store the
        //level information in this queue.
        Queue<QueueNode> queue = new LinkedList<QueueNode>();

        //First element to visit as per level-order traversal is the root.
        queue.add(new QueueNode(root, 1));

        //This variable will point to the front element in the queue.
        QueueNode currentElement;
        int currentElementLevel;

        //This variable will point to the last element that was seen.
        QueueNode previousElement;

        //What is the previousElement to root. Null with level 0.
        previousElement = new QueueNode(null, 0);
        int previousElementLevel;

        //We will iterate till there is at least one element in the queue.
        //Because, this means, there is at least one element that we are yet
        //to visit.
        while(!queue.isEmpty()) {
            //Poll method returns the queue element and also removes it
            //from the queue.
            currentElement = queue.poll();
            currentElementLevel = currentElement.getLevel();

            TreeNode currentElementTreeNode = currentElement.getTreeNode();

            //If left child is not null => we can add it to queue and its level
            //will be 1 greater than the current level.
            if(currentElementTreeNode.left != null) {
                QueueNode node = new QueueNode(currentElementTreeNode.left, currentElementLevel+1);
                queue.add(node);
            }

            //If right child is not null => we can add it to queue and its level
            //will be 1 greater than the current level.
            if(currentElementTreeNode.right != null) {
                QueueNode node = new QueueNode(currentElementTreeNode.right, currentElementLevel+1);
                queue.add(node);
            }

            previousElementLevel = previousElement.getLevel();

            //If this element's level is greater than its previous element's
            //level => previous element had ended a level. And it was the
            //rightmost element at that level. So, we can add that to result.
            //We want to exclude the case of previousElement of root.
            if(currentElementLevel > previousElementLevel && previousElementLevel != 0) {
                TreeNode treeNode = previousElement.getTreeNode();
                result.add(treeNode.val);
            }

            //Now, for next iteration, current element will be the previous
            //element
            previousElement = currentElement;
        }

        //The last previousElement will point to the last element in this
        //level. But, queue would be empty. So, we won't have moved to next
        //level => hence it wouldn't have been added to next level. But,
        //since this is last element in this level => this will also be visible.
        result.add(previousElement.getTreeNode().val);

        return result;
    }
}

//This is a custom class that we have defined. It will hold the information
//that there is a TreeNode and its level is this.
class QueueNode {
    private TreeNode node;
    private int level;

    public QueueNode(TreeNode node, int level) {
        this.node = node;
        this.level = level;
    }

    public int getLevel(){
        return this.level;
    }

    public TreeNode getTreeNode() {
        return this.node;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
