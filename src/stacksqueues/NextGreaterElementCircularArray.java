package stacksqueues;

import java.util.Arrays;
import java.util.Stack;

class NextGreaterElementCircularArray {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        //Filling all elements with -1 in case NGE is not found for them.
        Arrays.fill(result, -1);

        int i;
        int currentElement;

        //We'll traverse from L to R and we'll keep a stock of all elements whose
        //NGE is yet to be found. When we find an element greater than stack's top
        //=> we have found NGE for stack's top.
        Stack<Integer> ngeYetToFind = new Stack<Integer>();

        //In every iteration, we'll have a current element. For that current element
        //we'll check, if it can be NGE for any of the previous elements.
        //In a normal array, this would have sufficed. But, in a circular array,
        //that element can be an NGE for elements in its right too. So, we need to do
        //something. Now, circular array is nothing but in a way, same elements
        //repeated again in the array. But, we need not expand array capacity. We just
        //need to loop till 2n and just read the same elements again.
        for(i=0; i<2*n; i++) {
            currentElement = nums[i%n];

            //Check with top of stack. If current element is greater => current
            //element is NGE for stack top. Repeat this process till either stack
            //is empty or top is greater.
            while(!ngeYetToFind.isEmpty()
                    && currentElement > nums[ngeYetToFind.peek()]) {
                result[ngeYetToFind.peek()] = currentElement;
                ngeYetToFind.pop();
            }
            //We have to push an index only once.
            if(i<n) {
                ngeYetToFind.push(i);
            }
        }
        return result;
    }
}
