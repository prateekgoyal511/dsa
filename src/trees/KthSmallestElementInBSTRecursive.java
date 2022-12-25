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
class KthSmallestElementInBSTRecursive {
    public int kthSmallest(TreeNode root, int k) {
        CurrentValue cur = new CurrentValue();
        inorderTraversal(root, cur, k);
        return cur.value;
    }

    private void inorderTraversal(TreeNode node, CurrentValue cur, int k) {
        if(cur.i >= k) return;
        if(node.left != null) {
            inorderTraversal(node.left, cur, k);
        }
        cur.i = cur.i+1;
        if(cur.i == k) {
            cur.value = node.val;
        }
        if(node.right != null) {
            inorderTraversal(node.right, cur, k);
        }
    }
}

class CurrentValue {
    int i;
    int value;
    public CurrentValue() {
        i=0;
    }
}
