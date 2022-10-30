package trees;

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class PathToANodeInATree {
    public static void main (String[] args) {
        Node root=new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        int x=5;
        List<Integer> path = printPath(root, x);
        System.out.println(path);
    }

    public static List<Integer> printPath(Node node, int x) {
        //If node is null => return null since no path exists
        if(node == null) return null;
        //If node's value is x => make an arraylist with this node and return
        if(node.data == x) {
            List<Integer> result = new ArrayList<Integer>();
            result.add(x);
            return result;
        }

        //If node is not null and node's value is not x => it is possible that path
        //is in left subtree or right subtree
        List<Integer> leftResult = printPath(node.left, x);
        List<Integer> rightResult = printPath(node.right, x);
        List<Integer> finalResult;

        //If it wasn't found in any subtree => it isn't there at least at this node
        if(leftResult == null && rightResult == null) return null;
        if(leftResult != null) {
            finalResult = new ArrayList<Integer>();
            finalResult.add(node.data);
            finalResult.addAll(leftResult);
            return finalResult;
        }
        if(rightResult != null) {
            finalResult = new ArrayList<Integer>();
            finalResult.add(node.data);
            finalResult.addAll(rightResult);
            return finalResult;
        }
        return null;
    }

    //ANOTHER WAY OF GETTING PATH TO A NODE.
	/*
	// Returns true if there is a path from root
    // to the given node. It also populates
    // 'arr' with the given path
    public static boolean hasPath(Node root, ArrayList<Integer> arr, int x)
    {
        // if root is NULL
        // there is no path
        if (root==null)
            return false;

        // push the node's value in 'arr'
        arr.add(root.data);

        // if it is the required node
        // return true
        if (root.data == x)
            return true;

        // else check whether the required node lies
        // in the left subtree or right subtree of
        // the current node
        if (hasPath(root.left, arr, x) ||
            hasPath(root.right, arr, x))
            return true;

        // required node does not lie either in the
        // left or right subtree of the current node
        // Thus, remove current node's value from
        // 'arr'and then return false
        arr.remove(arr.size()-1);
        return false;
    }
	*/

    //ANOTHER VERY SIMPLE WAY OF GETTING PATH

    /*
        //Finding the path of target node from root node
    public boolean findPath(TreeNode node, TreeNode target)
    {
        if (node == null)
            return false;

        if (node == target || findPath(node.left, target)
            || findPath(node.right, target)) {
            path.add(node);
            return true;
        }

        return false;
    }
     */
}
