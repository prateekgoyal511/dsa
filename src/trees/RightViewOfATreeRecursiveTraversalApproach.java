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
class RightViewOfATreeRecursiveTraversalApproach {
    public List<Integer> rightSideView(TreeNode root) {

        //If there is no tree => no list.
        if(root == null) return new ArrayList<Integer>();

        //This will store the final result.
        List<Integer> result = new ArrayList<Integer>();

        //Our aim is to store the rightmost value at every level. We are not going here level-wise but are going recursively. So, we may sometimes
        //be at higher level and sometimes be at lower level. So, we need to keep track of what is the maximum level that we have seen so far.
        //This variable will do this.
        MaxLevelSeenSoFar maxLevelSeenSoFar = new MaxLevelSeenSoFar(0);

        //We are passing 1 as currentLevel for the node.
        rightSideViewUtil(root, 1, maxLevelSeenSoFar, result);

        return result;
    }

    private void rightSideViewUtil(TreeNode node, int currentLevel, MaxLevelSeenSoFar maxLevelSeenSoFar, List<Integer> result) {
        //If this level is greater than whatever was the maximum we had seen => this is a part of right view. Why? Because, we are traversing in
        //right first manner. First, we try to go to the rightmost elements => If right child is not present => only then, we go to the left one.
        if(currentLevel > maxLevelSeenSoFar.getMaxLevel()) {
            result.add(node.val);
            //We now set the new maxmimum. So, any more treenodes we encounter at this level won't be part of our right side view since we have
            //already included the rightmost element of this level in our right side view.
            maxLevelSeenSoFar.setMaxLevel(currentLevel);
        }

        //If right child is not null => traverse similarly for right one.
        if(node.right != null) {
            rightSideViewUtil(node.right, currentLevel+1, maxLevelSeenSoFar, result);
        }

        //If left child is not null => traverse similarly for left one.
        if(node.left != null) {
            rightSideViewUtil(node.left, currentLevel+1, maxLevelSeenSoFar, result);
        }
    }
}

class MaxLevelSeenSoFar {
    private int maxLevel;

    public MaxLevelSeenSoFar(int level) {
        this.maxLevel = level;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public void setMaxLevel(int level) {
        this.maxLevel = level;
    }
}
