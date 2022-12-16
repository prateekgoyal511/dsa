package backtracking;

import java.util.ArrayList;
import java.util.Collections;

public class CombinationSumBacktracking {
    // Declare a 2D Arraylist that will store the result
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
        // Sort the given arraylist.
        Collections.sort(a);

        // Declare an empty arraylist that will store the current solution
        ArrayList<Integer> temp = new ArrayList<Integer>();

        // Now, we cannot recurse on this function. Because, backtracking needs to track following things:
        // 1. The temp solution which is being built
        // 2. The remaining part of input which is to be processed.
        // So, we will call a Utility function

        // Also, passing in can_be_picked_start. This variable maintains that our solution is being built
        // starting with this index. We need it because otherwise [2,2,3] [2,3,2] and [3,2,2] all were
        // coming.
        // We can also think as - we either decide to include the first element or not. If we decide to include => great. We can keep on including more.
        // However, once we have decided that we are excluding this, then, further in recursion, also, we should not be able to include it.
        backtrack(a, b, temp, 0);
        return result;
    }

    public void backtrack(ArrayList<Integer> a, int b, ArrayList<Integer> temp, int can_be_picked_start){
        // If b==0, this temp solution that was being built is a candidate solution, add it to result
        if(b==0)
        {
            // Always at such point, add to result by completely creating new. Don't directly add temp.
            // Temp keeps modifying and creates problem.
            ArrayList<Integer> temp_result = new ArrayList<Integer>(temp);
            result.add(temp_result);
        }

        // If b<0 => we have overshot. Return
        if(b<0)return;

        // If b>0, continue with computation

        // For every option => Let's take this element into the solution
        for(int i=can_be_picked_start; i<a.size(); i++)
        {
            // This was required because input a may contain duplicate elements but we should not return
            // duplicate. So, after sorting, all duplicates will be adjacent. So, basically, if you have
            // done for i-1, don't do for i. Go for next.
            if((i>0) &&(a.get(i) == a.get(i-1)))continue;

            // We have taken this element into our current solution being built up.
            temp.add(a.get(i));

            // a does not change because we can use it repeatedly. Target sum reduces.
            backtrack(a, b-a.get(i), temp, i);

            // We have tried with inclusion of this element. Now, we will remove this element
            temp.remove(temp.size()-1);
        }
    }
}
