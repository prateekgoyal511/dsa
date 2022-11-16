package backtracking;

import java.util.LinkedList;
import java.util.List;

//Problem - https://leetcode.com/problems/combination-sum/description/
class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = combinationSumUtil(candidates, 0, candidates.length-1, target);
        if(result != null) {
            return result;
        } else {
            List<List<Integer>> result2 = new LinkedList<>();
            return result2;
        }
    }

    private List<List<Integer>> combinationSumUtil(int[] candidates, int i, int j, int target) {
        //If target is 0 => empty list is one of the solutions.
        List<Integer> solution = new LinkedList<>();;
        List<List<Integer>> result = new LinkedList<>();;
        if(target == 0) {
            result.add(solution);
            return result;
        }
        //If target has reached negative => no solution possible in this recursion path.
        if(target < 0) return null;

        //In every iteration, we fix that at least one instance of this element will be taken.
        //Rest of the solution has to be found from that element onwards.
        int k;
        List<List<Integer>> subResult;
        List<List<Integer>> kResult;
        for(k=i; k<=j; k++) {
            //candidates[k] is taken at least once.
            subResult = combinationSumUtil(candidates, k, j, target-candidates[k]);
            kResult = addElementToAllLists(candidates[k], subResult);
            if(kResult != null) {
                result.addAll(kResult);
            }
        }

        return result;
    }

    private List<List<Integer>> addElementToAllLists(int element, List<List<Integer>> lists) {
        if(lists == null) return null;
        List<List<Integer>> result = new LinkedList<>();
        for(List<Integer> list: lists) {
            list.add(0, element);
            result.add(list);
        }
        return lists;
    }
}
