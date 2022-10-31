package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) {
 *      val = x;
 *      left=null;
 *      right=null;
 *     }
 * }
 */
public class LowestCommonAncestorArrayListApproach {
    public int lca(TreeNode A, int B, int C) {
        //Approach is the following:-
        //1. Get node1 for val1
        //2. Get node2 for val2
        //3. If either of these don't exist => return -1
        //4. If both exist => get path1 and path2
        //5. Iterate over path1 and path2 to get the lca.

        TreeNode node1 = findNode(A, B);
        TreeNode node2 = findNode(A, C);

        //If either of these is null
        if(node1 == null || node2 == null) return -1;

        List<TreeNode> path1 = findPath(A, node1);
        List<TreeNode> path2 = findPath(A, node2);

        return lca(path1, path2);
    }

    //This function takes in a node as input and a value as input. It returns the targetnode if found in the tree rooted at this node
    //or else returns null
    private TreeNode findNode(TreeNode node, int value) {
        //If node is null => can't find the value.
        if(node == null) return null;
        //If same value => return
        if(node.val == value) return node;

        TreeNode leftAns = findNode(node.left, value);
        TreeNode rightAns = findNode(node.right, value);

        if(leftAns != null) return leftAns;
        if(rightAns != null) return rightAns;
        return null;
    }

    //This function takes in as input a root of the tree and a target node. It returns the path to this node in the form of arraylist.
    //If it exists. If it doesn't exist => it returns null.
    private List<TreeNode> findPath(TreeNode root, TreeNode node) {
        return findPathUtil(root, node);
    }

    //This function takes in an input as node of a tree. It also takes in as an input a target node of the tree. It returns path to the targetnode
    //from this node as output. If the target node does not exist in a tree rooted at node, it returns null.
    private List<TreeNode> findPathUtil(TreeNode node, TreeNode target) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        //Base condition 1 - node is null
        if(node == null) return null;
        //Base condition 2 - node = target
        if(node == target) {
            result.add(node);
            return result;
        }
        //Base condition 3 - node is a leaf node. No further exploration possible
        if(node.left == null && node.right == null) return null;

        List<TreeNode> leftPath = findPathUtil(node.left, target);
        List<TreeNode> rightPath = findPathUtil(node.right, target);

        //Exists in left subtree
        if(leftPath != null) {
            result.add(node);
            result.addAll(leftPath);
            return result;
        }

        if(rightPath != null) {
            result.add(node);
            result.addAll(rightPath);
            return result;
        }
        return null;
    }

    //This function takes in as input 2 paths from root. It returns the lca.
    private int lca(List<TreeNode> path1, List<TreeNode> path2) {
        int i;
        TreeNode nodeOnPath1, nodeOnPath2;
        TreeNode lcaSoFar = null;
        //We will iterate till i is less than both sizes
        for(i=0; (i < path1.size() && i < path2.size()); i++) {
            nodeOnPath1 = path1.get(i);
            nodeOnPath2 = path2.get(i);
            if(nodeOnPath1 == nodeOnPath2) {
                lcaSoFar = nodeOnPath1;
            } else {
                break;
            }
        }
        return lcaSoFar.val;
    }
}

