package trees;

import java.util.HashSet;
import java.util.Set;

//https://www.interviewbit.com/problems/valid-bst-from-preorder/
public class ValidPreOrderBST {

    public class Solution {
        public int solve(int[] A) {
            int n = A.length;

            int i = 0;
            int j = n-1;

            //If there are duplicates, reject it.
            int duplicates;
            duplicates = checkDuplicates(A, i, j);
            if(duplicates == 1) return 0;

            //We know that to check if it is the correct preorder traversal, we will first check for left subtree, then for right subtree
            //Left subtree and RightSubtree traversals are nothing but subarrays => similar checks will be required on subarrays. Therefore,
            //it is better to express our original array in terms of start and end indices.
            return solveUtil(A, 0, n-1);
        }

        //This method takes an array, its starting index and its end index. For this range, it checks if these elements can represent
        //a valid preorder traversal of a BST
        private static int solveUtil(int[] A, int i, int j) {
            //BaseCondition - Only one element.
            //If there is only one element => always correct.
            if(i==j) return 1;

            //Base Condition - Only 2 elements.
            //If there are 2 elements => first one is root, 2nd one maybe left child or right child. It can be smaller or greater but not equal
            if(j-i == 1) {
                if(A[j] != A[i]) return 1;
                else return 0;
            }

            //If more than 2 elements => try to solve recursively
            //Root denotes the root of this preorder traversal
            //LeftRoot denotes the root of the left subtree of this preorder traversal
            //RightRoot denotes the root of the right subtree of this preorder traversal
            int root;
            int leftRoot = -1;
            int rightRoot = -1;

            int leftSubtreeValid;
            int rightSubtreeValid;
            int rightSubtreeElementsGreater;


            //First element is always root
            root = A[i];

            //If next element is lesser => leftSubtree has begun.
            if(A[i+1] < A[i]) {
                leftRoot = A[i+1];
                //First greater element in this array now signifies the beginning of right subtree.
                rightRoot = findFirstGreater(A, i+2, j, root);
                //There was at least one element that was greater than the root.
                if(rightRoot != -1) {
                    //We need to verify that left subtree traversal is valid
                    //We need to verify that right subtree traversal is valid
                    //We need to verify that all elements in right subtree are greater than the root.
                    rightSubtreeElementsGreater = checkRightSubtreeElementsGreater(root, A, rightRoot, j);
                    if(rightSubtreeElementsGreater == 0) return 0;
                    leftSubtreeValid = solveUtil(A, i+1, rightRoot-1);
                    if(leftSubtreeValid == 0) return 0;
                    rightSubtreeValid = solveUtil(A, rightRoot, j);
                    if(rightSubtreeValid == 0) return 0;
                }
                else {
                    //RightRoot was -1 => Everything else was leftSubtree
                    leftSubtreeValid = solveUtil(A, i+1, j);
                    if(leftSubtreeValid == 0) return 0;
                }
            } else {
                //Everything else should be right subtree
                rightSubtreeElementsGreater = checkRightSubtreeElementsGreater(root, A, i+1, j);
                if(rightSubtreeElementsGreater == 0) return 0;
                rightSubtreeValid = solveUtil(A, i+1, j);
                if(rightSubtreeValid == 0) return 0;
            }
            return 1;
        }

        //This element scans the array from i and j and returns the index of the first element that is greater than root.
        //If there is no such element => return -1
        private static int findFirstGreater(int[] A, int i, int j, int root) {
            int k;
            for(k=i; k<=j; k++) {
                if(A[k] > root) return k;
            }
            return -1;
        }

        //This method scans the array from i to j and checks that all elements are greater than root. If they are nothing
        //it returns 0, else returns 1
        private static int checkRightSubtreeElementsGreater(int root, int[] A, int i, int j) {
            int k;
            for(k=i; k<=j; k++) {
                if(A[k] <= root) return 0;
            }
            return 1;
        }

        //This method scans all elements from i and j and if there are any duplicate elements => it returns 1 else returns 0
        private static int checkDuplicates(int[] A, int i, int j) {
            Set<Integer> distinctElements = new HashSet<Integer>();
            int k;
            for(k=i; k<=j; k++) {
                if(distinctElements.contains(A[k])) return 1;
                else distinctElements.add(A[k]);
            }
            return 0;
        }
    }

}
