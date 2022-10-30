package trees;

//{ Driver Code Starts
//Initial Template for Java

import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}

class Tree {

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
    static void printInorder(Node root)
    {
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

            MaxSumRootToLeafPathSolution g = new MaxSumRootToLeafPathSolution();

            System.out.println(g.maxPathSum(root));

            t--;
        }
    }
}



// } Driver Code Ends


//User function Template for Java

class MaxSumRootToLeafPathSolution
{
    public static int maxPathSum(Node root)
    {
        //code here
        return maxPathSumUtil(root);
    }

    //This method takes in a node and returns a max root-to-leaf path sum.
    private static int maxPathSumUtil(Node node) {
        //Recursive condition ends at a leaf and not at a null node. Be careful about it.
        if(node.left == null && node.right == null) return node.data;
        int leftSubtreeSum = Integer.MIN_VALUE;
        int rightSubtreeSum = Integer.MIN_VALUE;

        //We have already checked that this node is not a leaf node. So, if it has a left
        //subtree => that max root-to-leaf path may lie in left subtree.
        if(node.left != null) {
            leftSubtreeSum = maxPathSumUtil(node.left);
        }

        if(node.right != null) {
            rightSubtreeSum = maxPathSumUtil(node.right);
        }

        if(leftSubtreeSum > rightSubtreeSum) {
            return (leftSubtreeSum + node.data);
        } else {
            return (rightSubtreeSum + node.data);
        }
    }

    //What if we were asked to print that target leaf node?
    //We could return the node and sum both from recursion.
    //Another way:- Track maximum sum while traversing the tree.
    /*
        // This function Sets the target_leaf_ref to refer
    // the leaf node of the maximum path sum. Also,
    // returns the max_sum using max_sum_ref
    void getTargetLeaf(Node node, Maximum max_sum_ref,
                       int curr_sum)
    {
        if (node == null)
            return;

        // Update current sum to hold sum of nodes on
        // path from root to this node
        curr_sum = curr_sum + node.data;

        // If this is a leaf node and path to this node
        // has maximum sum so far, the n make this node
        // target_leaf
        if (node.left == null && node.right == null) {
            if (curr_sum > max_sum_ref.max_no) {
                max_sum_ref.max_no = curr_sum;
                target_leaf = node;
            }
        }

        // If this is not a leaf node, then recur down
        // to find the target_leaf
        getTargetLeaf(node.left, max_sum_ref, curr_sum);
        getTargetLeaf(node.right, max_sum_ref, curr_sum);
    }
    */

}
