package trees;

//{ Driver Code Starts
//Initial Template for Java

import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;

//THIS IS A SOLUTION FOR CASE WHEN ROOT NODE CANNOT ACT AS LEAF.

class MaxSumPathBetweenTwoLeaves {

    static Node buildTree(String str){

        if(str.length()==0 || str.charAt(0)=='N'){
            return null;
        }

        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        // Starting from the second element

        int i = 1;
        while(queue.size()>0 && i < ip.length) {

            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if(!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if(i >= ip.length)
                break;

            currVal = ip[i];

            // If the right child is not null
            if(!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }
    static void printInorder(Node root){
        if(root == null)
            return;

        printInorder(root.left);
        System.out.print(root.data+" ");

        printInorder(root.right);
    }

    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());

        while(t > 0){
            String s = br.readLine();
            Node root = buildTree(s);

            MaxSumPathBetweenTwoLeavesSolution ob = new MaxSumPathBetweenTwoLeavesSolution();
            System.out.println(ob.maxPathSum(root));
            t--;
        }
    }
}

// } Driver Code Ends


//User function Template for Java

/* class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
} */
class MaxSumPathBetweenTwoLeavesSolution
{
    int maxPathSum(Node root)
    {
        // code here
        Result result = new Result();
        result.setValue(Integer.MIN_VALUE);
        maxPathSumUtil(root, result);
        return result.getValue();
    }

    //This method takes in as input a node of a tree and the Result data structure. It returns the maximum sum from this node
    //to any leaf. It also calculates a sum for a path between two leaves passing through itself and updates result if it is
    //greater.
    private int maxPathSumUtil(Node node, Result result) {
        //If this is a leaf node.
        if(node.left == null && node.right == null) {
            return node.data;
        }
        int leftSubtreeSum = Integer.MIN_VALUE;
        int rightSubtreeSum = Integer.MIN_VALUE;

        if(node.left != null) {
            leftSubtreeSum = maxPathSumUtil(node.left, result);
        }

        if(node.right != null) {
            rightSubtreeSum = maxPathSumUtil(node.right, result);
        }

        int acrossRootCandidateSum;
        //If both children are not null, it is a possibility that there is a path between 2 leaves with some sum.
        if(node.left != null && node.right != null) {
            acrossRootCandidateSum = leftSubtreeSum + node.data + rightSubtreeSum;
            if(acrossRootCandidateSum > result.getValue()) {
                result.setValue(acrossRootCandidateSum);
            }
        }

        //Return the correct value.
        if(leftSubtreeSum > rightSubtreeSum) {
            return leftSubtreeSum + node.data;
        } else {
            return rightSubtreeSum + node.data;
        }
    }
}

class Result {
    private int value;
    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}


//IF ROOT NODE CAN ALSO ACT AS LEAF, THEN FOLLOWING IS THE SOLUTION

/*
// Java program to find maximum path sum between two leaves
// of a binary tree
class Node {

    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

// An object of Res is passed around so that the
// same value can be used by multiple recursive calls.
class Res {
    int val;
}

class BinaryTree {

    static Node root;
      Node setTree(Node root){

      Node temp = new Node(0);
      //if tree is left most
      if(root.right==null){
          root.right=temp;
      }
      else{    //if tree is right most
          root.left=temp;
      }

      return root;
    }

    // A utility function to find the maximum sum between any
    // two leaves.This function calculates two values:
    // 1) Maximum path sum between two leaves which is stored
    //    in res.
    // 2) The maximum root to leaf path sum which is returned.
    // If one side of root is empty, then it returns INT_MIN
    int maxPathSumUtil(Node node, Res res) {

        // Base cases
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return node.data;

        // Find maximum sum in left and right subtree. Also
        // find maximum root to leaf sums in left and right
        // subtrees and store them in ls and rs
        int ls = maxPathSumUtil(node.left, res);
        int rs = maxPathSumUtil(node.right, res);

        // If both left and right children exist
        if (node.left != null && node.right != null) {

            // Update result if needed
            res.val = Math.max(res.val, ls + rs + node.data);

            // Return maximum possible value for root being
            // on one side
            return Math.max(ls, rs) + node.data;
        }

        // If any of the two children is empty, return
        // root sum for root being on one side
        return (node.left == null) ? rs + node.data
                : ls + node.data;
    }

    // The main function which returns sum of the maximum
    // sum path between two leaves. This function mainly
    // uses maxPathSumUtil()
    int maxPathSum(Node node)
    {
        Res res = new Res();
        res.val = Integer.MIN_VALUE;

          if(root.left==null || root.right==null){
            root=setTree(root);
        }
          //if tree is left most or right most
          //call setTree() method to adjust tree first
        maxPathSumUtil(root, res);
        return res.val;
    }

    //Driver program to test above functions
    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(-15);
        tree.root.left = new Node(5);
        tree.root.right = new Node(6);
        tree.root.left.left = new Node(-8);
        tree.root.left.right = new Node(1);
        tree.root.left.left.left = new Node(2);
        tree.root.left.left.right = new Node(6);
        tree.root.right.left = new Node(3);
        tree.root.right.right = new Node(9);
        tree.root.right.right.right = new Node(0);
        tree.root.right.right.right.left = new Node(4);
        tree.root.right.right.right.right = new Node(-1);
        tree.root.right.right.right.right.left = new Node(10);
        System.out.println("Max pathSum of the given binary tree is "
                + tree.maxPathSum(root));
    }
}

 */
