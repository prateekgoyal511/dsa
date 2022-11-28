package backtracking;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/permutations/submissions/851127114/
class AllPermutations {
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> candidateSolution = new ArrayList<Integer>();
        int indexToFillInCandidateSolution = 0;
        permuteUtil(nums, n, candidateSolution, indexToFillInCandidateSolution, result);
        return result;
    }

    //In backtracking we build a list of solutions. To build every solution, we exercise a series of choices/
    //At every stage when we are about to exercise a choice, we have to try out all choices.
    //So, we need to pass something that demonstrates, what stage are we at in building the candidate solution
    //We need all parameters needed to exercise that choice.
    private void permuteUtil(int[] nums, int n, List<Integer> candidateSolution,
                             int indexToFillInCandidateSolution, List<List<Integer>> result) {
        //If we are done
        if(indexToFillInCandidateSolution == n) {
            addCandidateSolutionToResult(candidateSolution, result);
            return;
        }

        //Otherwise, we want to fill at indexToFillInCandidateSolution. Prior to this index, all values in
        //candidateSolution are now fixed. At this index, we have options to fill from any of the numbers
        //that are present in nums from index indexToFillInCandidateSolution to end.
        int i;
        for(i=indexToFillInCandidateSolution; i<n; i++) {
            //We want to take element and index i and have it fill the position in candidate solution.
            //But, for that, we need to make sure that we don't use this same element again. So, we can
            //swap the current element in nums at indexToFillInCandidateSolution with element at i. So, element
            //at i will now be at indexToFillInCandidateSolution. And in further recursive calls, we won't look
            //at this index'
            swap(nums, indexToFillInCandidateSolution, i);
            //Now, that we have swapped, exercise this choice.
            candidateSolution.add(nums[indexToFillInCandidateSolution]);
            permuteUtil(nums, n, candidateSolution, indexToFillInCandidateSolution+1, result);
            //Removing the last element added so that now we can exercise next choice at this position
            //and also swapping back
            swap(nums, i, indexToFillInCandidateSolution);
            candidateSolution.remove(candidateSolution.size()-1);
        }
    }

    private void addCandidateSolutionToResult(List<Integer> candidateSolution, List<List<Integer>> result) {
        List<Integer> copy = new ArrayList<Integer>();
        int i;
        for(i=0; i<candidateSolution.size(); i++){
            copy.add(candidateSolution.get(i));
        }
        result.add(copy);
    }

    private void swap(int[] nums, int ind1, int ind2) {
        int temp = nums[ind1];
        nums[ind1] = nums[ind2];
        nums[ind2] = temp;
    }
}
