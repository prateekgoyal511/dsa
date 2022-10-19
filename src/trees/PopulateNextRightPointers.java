package trees;

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) {val = x;}
}

public class PopulateNextRightPointers {

    /**
     * Definition for binary tree with next pointer.
     * public class TreeLinkNode {
     *     int val;
     *     TreeLinkNode left, right, next;
     *     TreeLinkNode(int x) { val = x; }
     * }
     */
    public class Solution {
        public void connect(TreeLinkNode root) {
            if(root == null) return;

            root.next = null;

            //This node points to first node in a level
            TreeLinkNode firstNodeInLevel;

            //This pointer will traverse across a level
            TreeLinkNode currentNodeInLevel;

            TreeLinkNode firstChildNode;
            TreeLinkNode secondChildNode;

            firstNodeInLevel = root;

            //This will go on till there is at least one node in a level. We'll try to connect children of this level with each other.
            while(firstNodeInLevel != null) {

                currentNodeInLevel = firstNodeInLevel;

                //Till the time this level is not done, we'll try linking children.
                while(currentNodeInLevel != null) {
                    if(currentNodeInLevel.left != null) {
                        if(currentNodeInLevel.right != null) {
                            //If left and right children are not null. Then, left should be connected with right.
                            currentNodeInLevel.left.next = currentNodeInLevel.right;
                        } else {
                            //If left is not null but right is null => left should be connected with whatever comes next in right.
                            currentNodeInLevel.left.next = getNextRight(currentNodeInLevel);
                        }
                    }
                    if (currentNodeInLevel.right != null) {
                        //If right is not null => right should be connected with whatever comes next in right.
                        currentNodeInLevel.right.next = getNextRight(currentNodeInLevel);
                    }
                    //If no children or we are done connecting children => nothing to do here => move to next node.
                    currentNodeInLevel = currentNodeInLevel.next;
                }
                //If left child was not null, it'll be the leftmost node in next level.
                if(firstNodeInLevel.left != null) {
                    firstNodeInLevel = firstNodeInLevel.left;
                } else if (firstNodeInLevel.right != null) {
                    //If right child was not null, it'll be the leftmost node in next level.
                    firstNodeInLevel = firstNodeInLevel.right;
                } else {
                    //If both children were null, then whatever comes in next right will be the leftmost node in next level.
                    firstNodeInLevel = getNextRight(firstNodeInLevel);
                }
            }
        }

        //This method takes in a node. And its job is to leave this node, go to right and find first child among its right side nodes
        public static TreeLinkNode getNextRight(TreeLinkNode node) {
            if(node == null) return null;
            if(node.next == null) return null;

            node = node.next;
            while(node != null) {
                if(node.left != null) return node.left;
                if(node.right != null) return node.right;
                node = node.next;
            }
            return null;
        }
    }

}
