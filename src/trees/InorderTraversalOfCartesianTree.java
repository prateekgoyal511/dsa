package trees;

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
public class InorderTraversalOfCartesianTree {
    public TreeNode buildTree(int[] A) {
        int n = A.length;
        return buildTreeUtil(A, 0, n-1);
    }

    //This method takes in as input an array and its start and end indices. It returns root of the tree
    //as output.
    private TreeNode buildTreeUtil(int[] A, int i, int j) {
        TreeNode root;
        //If i and j are not proper => no tree possible
        if(i>j) return null;
        //If only one element => that node itself is the answer.
        if(i==j) {
            root = new TreeNode(A[i]);
            return root;
        }
        int maxIndex = findMaxIndex(A, i, j);
        root = new TreeNode(A[maxIndex]);
        TreeNode leftSubtreeBuilt = buildTreeUtil(A, i, maxIndex-1);
        TreeNode rightSubtreeBuilt = buildTreeUtil(A, maxIndex+1, j);

        root.left = leftSubtreeBuilt;
        root.right = rightSubtreeBuilt;
        return root;
    }

    private int findMaxIndex(int[] A, int i, int j) {
        int k;
        int currentMax = Integer.MIN_VALUE;
        int maxIndex = -1;
        for(k=i; k<=j; k++) {
            if(A[k] > currentMax) {
                currentMax = A[k];
                maxIndex = k;
            }
        }
        return maxIndex;
    }


}

