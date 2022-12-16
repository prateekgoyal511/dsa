package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

//Original problem - https://leetcode.com/problems/last-stone-weight-ii/description/
//Equivalent to subset partition with minimum difference problem.
class SubsetPartitionMinDiff {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int s1 = 0;
        int s2 = 0;
        Map<String, Integer> calculatedResultsMap = new HashMap<String, Integer>();
        //This problem is simply the application of partitioning an array into 2 subsets with minimum difference
        return findMinDiffUtil(stones, n-1, s1, s2, calculatedResultsMap);
    }

    //This method returns the minimum difference between s1 and s2. Given, there are some initial values of s1
    //and s2. And now, new elements are to be filled from array into S1/S2 only upto index i.
    private int findMinDiffUtil(int[] arr, int i, int s1, int s2, Map<String, Integer> calculatedResultsMap) {
        //If there are no more elements to be inserted into S1/S2
        if(i<0) {
            return Math.abs(s1-s2);
        }

        //A combo of i and s1 will denote a unique combo. We need not take s2 into account. Since, s2 can be
        //determined deterministically from i and s1
        String key = i + "_" + s1;

        if(calculatedResultsMap.containsKey(key)) {
            return calculatedResultsMap.get(key);
        }

        int s1Ans, s2Ans;
        int lastElementVal = arr[i];
        //Last element can be included in s1 => let's see what will be the answer then.'
        //Find min difference between s1 and s2. Initial values of s1 and s2 are as following. You can use
        //elements upto index i-1 only as new elements to add in s1/s2 to chase your goal
        s1Ans = findMinDiffUtil(arr, i-1, s1+lastElementVal, s2, calculatedResultsMap);
        //Find min difference between s1 and s2. Initial values of s1 and s2 are as following. You can use
        //elements upto index i-1 only as new elements to add in s1/s2 to chase your goal
        s2Ans = findMinDiffUtil(arr, i-1, s1, s2+lastElementVal, calculatedResultsMap);

        int minAns;
        //Who could give us minimum answer - including last element in S1 or including last element in S2
        minAns = Math.min(s1Ans, s2Ans);
        calculatedResultsMap.put(key, minAns);
        return minAns;
    }
}
