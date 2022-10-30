package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class NodesAtDistanceKSolution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        //The answer for this will be combination of two things:-
        //First is the list of nodes that are at distance K from target node in a tree rooted at target node
        //Second is the list of nodes that are at distance K from target node not part of tree rooted
        //at target node.

        if(k==0) {
            return new ArrayList<Integer>(Arrays.asList(target.val));
        }

        List<Integer> rootedNodes = distanceKRooted(target, k);
        // System.out.println("RootedNodes: " + rootedNodes);

        //In this list, we'll populate all ancestors of our target node.
        List<AncestorNode> ancestors = new ArrayList<AncestorNode>();
        populateAncestors(root, target, ancestors);
        // System.out.println("AncestorNodes: " + ancestors);

        List<Integer> nodesFromAncestor = new ArrayList<Integer>();
        AncestorNode current;
        //Go through every ancestorNode. If distance > k => move to next.
        //If distance == k => add that ancestor node. If distance == 0 => targetnode.
        //If distance < k => add nodes at distance k-d-1 in opposite subtree. We need '-1' extra here because
        //d is the distance till this node. Then, d+1 is the distance till its left/right child
        for(AncestorNode ancestorNode: ancestors) {
            if(ancestorNode.getDistance() > k) continue;
            if(ancestorNode.getDistance() == 0) continue;
            if(ancestorNode.getDistance() == k) {
                nodesFromAncestor.add(ancestorNode.getAncestorNode().val);
            }
            if(ancestorNode.getDistance() < k) {
                if(ancestorNode.getLeftSubtree()) {
                    nodesFromAncestor.addAll(distanceKRooted(ancestorNode.getAncestorNode().right, k-ancestorNode.getDistance()-1));
                } else {
                    nodesFromAncestor.addAll(distanceKRooted(ancestorNode.getAncestorNode().left, k-ancestorNode.getDistance()-1));
                }
            }
        }

        List<Integer> finalResult = new ArrayList<>();
        finalResult.addAll(rootedNodes);
        finalResult.addAll(nodesFromAncestor);
        return finalResult;
    }

    private List<Integer> distanceKRooted(TreeNode node, int k) {
        List<Integer> result = new ArrayList<Integer>();
        //Base Case 1 - if node is null => return empty list
        if(node == null) return result;

        //Base Case 2 - We have already established that node is not null. k=0 => this node is part of the
        //result
        if(k == 0) {
            result.add(node.val);
            return result;
        }

        //By this time, we have established that node is not null and k is not 0
        //So, we get all nodes that are at distance k-1 from left and right children and put them together
        //and return
        List<Integer> leftResult = distanceKRooted(node.left, k-1);
        List<Integer> rightResult = distanceKRooted(node.right, k-1);

        List<Integer> finalResult = new ArrayList<Integer>();
        finalResult.addAll(leftResult);
        finalResult.addAll(rightResult);

        return finalResult;
    }

    //This method takes in a node, takes in a targetnode. It returns the distance between this node and target
    //node. Meanwhile, it also populates this ancestor list.
    private int populateAncestors(TreeNode node, TreeNode target, List<AncestorNode> ancestors) {
        //Base condition
        if(node == target) {
            AncestorNode ancestorNode = new AncestorNode(node);
            ancestorNode.setDistance(0);
            ancestors.add(ancestorNode);
            return 0;
        }

        //Base Condition 2 - Current node is a leaf node and no possibility further.
        if(node.left == null && node.right == null) return -1;

        int leftSubtreeDistance = -1;
        int rightSubtreeDistance = -1;
        if(node.left != null) {
            leftSubtreeDistance = populateAncestors(node.left, target, ancestors);
        }

        if(node.right != null) {
            rightSubtreeDistance = populateAncestors(node.right, target, ancestors);
        }

        //Found in left subtree
        if(leftSubtreeDistance != -1) {
            AncestorNode ancestorNode = new AncestorNode(node);
            ancestorNode.setDistance(leftSubtreeDistance+1);
            ancestorNode.setLeftSubtree(true);
            ancestors.add(ancestorNode);
            return leftSubtreeDistance + 1;
        }

        //Found in right subtree
        if(rightSubtreeDistance != -1) {
            AncestorNode ancestorNode = new AncestorNode(node);
            ancestorNode.setDistance(rightSubtreeDistance+1);
            ancestorNode.setLeftSubtree(false);
            ancestors.add(ancestorNode);
            return rightSubtreeDistance + 1;
        }

        return -1;
    }

    //If we didn't want to store AncestorNodes => then, as soon as we got to know that this is an ancestorNode, we could directly call the rooted
    //method to get relevant integers and add it to our result.
}

class AncestorNode {
    private TreeNode ancestorNode;
    private int distance;
    private boolean leftSubtree;

    public AncestorNode(TreeNode node) {
        this.ancestorNode = node;
    }

    public TreeNode getAncestorNode() {
        return this.ancestorNode;
    }

    public int getDistance() {
        return this.distance;
    }

    public boolean getLeftSubtree() {
        return this.leftSubtree;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setLeftSubtree(boolean leftSubtree) {
        this.leftSubtree = leftSubtree;
    }
}
