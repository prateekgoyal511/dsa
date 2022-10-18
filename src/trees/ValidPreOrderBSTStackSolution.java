package trees;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ValidPreOrderBSTStackSolution {
    public class Solution {
        public int solve(int[] A) {
            int n = A.length;

            //If there are duplicates, reject it.
            int duplicates;
            duplicates = checkDuplicates(A, 0, n-1);
            if(duplicates == 1) return 0;

            //preorder traversal of bst means => first root, then left subtree and then right subtree.
            //Left subtree will have all elements lesser than root. Right subtree will have all elements greater
            //than root.
            //How do we know that this array is a valid preorder traversal of bst
            //Ideally, our first element is root. First greater element than this means the beginning of right
            //subtree on this root. Now, once the rightSubtree has begun on this root => then, we should not encounter
            //any element that is lesser than this root. That means, we need to have a root to keep track of.
            //In fact, we need to keep track of the root whose right subtree has begun => now, there cannot be
            //a lesser element than this. And it
            //will keep changing. Now, if first 3 elements are 15, 10, 7. Then, I know that when I am at 15 => RightSubtree
            //of root@15 has not begun. But, it will begin at some time. So, let's store it somewhere. Then, I am at 10
            //Now, I know that Right subtree of root@10 and root@15 - both have not begun. But, it may begin sometime, so
            //let's store 10 too. Now, I see 7. Again, rightSubtree hasn't begun. Store it too. We still don't have a root
            //whose rightSubtree has begun. Now, let's see, we saw 8. That means, 7 is done. LeftSubtree of 7 is done. Now,
            //there can't be any element < 7 that we encounter. If it is => it is not a valid preorder traversal.
            //Track 7 as root and remove it from stack. Elements in stack denote the roots whose right subtree hasn't
            //started. Now, if we see 12. This means, 10 is also done. Now, no one can come below 10. So, how is new
            //root calculated? We basically, see if current element is greater than stack's top.

            Stack<Integer> rightSubtreeYetToBegin = new Stack<Integer>();

            int i;
            int elementCantBeLesserThan = Integer.MIN_VALUE;
            for(i=0; i<n; i++) {


                //If current element is lesser than what we have established as elementCantBeLesserThan
                //this is not a valid preorder traversal
                if(A[i] < elementCantBeLesserThan) {
                    return 0;
                }

                //Till the time current element is greater than the stack's top => stack's top is now no longer
                //of use. Because, stack's top's left subtree is done. So, we gotta pop it. But, before we pop it,
                //it is that element whose rightsubtree has begun and hence we should not see any element lesser than
                //it.
                while(!rightSubtreeYetToBegin.isEmpty() && (A[i] > rightSubtreeYetToBegin.peek())) {
                    elementCantBeLesserThan = rightSubtreeYetToBegin.peek();
                    rightSubtreeYetToBegin.pop();
                }

                //Now, this element is also an element whose rightsubtree hasn't begun.
                rightSubtreeYetToBegin.push(A[i]);
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
