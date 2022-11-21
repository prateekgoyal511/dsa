package trees;

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
class ConstructBinaryTreeFromInorderAndPreorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        PreorderIndexHolder preorderIndexHolder = new PreorderIndexHolder();
        return buildTreeUtil(preorder, inorder, 0, n-1, preorderIndexHolder);
    }

    //This is a method that takes elements between start and end indices of an inorder array. It returns a tree
    //built by these elements. To figure out, what should come first and what next, it takes the help of preorder
    //array
    private TreeNode buildTreeUtil(int[] preorder, int[] inorder, int start, int end, PreorderIndexHolder preorderIndexHolder) {
        if(start > end) return null;

        int currentRootVal = preorder[preorderIndexHolder.preorderIndex];
        preorderIndexHolder.preorderIndex++;
        TreeNode currentRoot = new TreeNode(currentRootVal);

        //If there was just one element => that is the whole tree
        if(start == end) {
            return currentRoot;
        }

        //Find the index of root element in inorder traversal. All elements left to it will contribute to left subtree. All elements to the right of it
        //will contribute to the right subtree.
        int rootIndexInInorder = search(inorder, start, end, currentRootVal);
        currentRoot.left = buildTreeUtil(preorder, inorder, start, rootIndexInInorder-1, preorderIndexHolder);
        currentRoot.right = buildTreeUtil(preorder, inorder, rootIndexInInorder+1, end, preorderIndexHolder);
        return currentRoot;
    }

    private int search(int[] arr, int start, int end, int value) {
        int i;
        for(i=start; i<=end; i++) {
            if(arr[i] == value) return i;
        }
        return i;
    }
}

class PreorderIndexHolder {
    int preorderIndex = 0;
}
