package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

class PartitionEqualSubsetSumTopDownDPSolution {
    public boolean canPartition(int[] nums) {

        //This map will store answers for inputs already calculated.
        Map<String, Boolean> calculatedResultsMap = new HashMap<String, Boolean>();

        //Length of the array
        int n = nums.length;

        //Sum of all elements of the array.
        int sumTotal = sumOfArray(nums, n);

        //if Sum is odd => it is not possible to divide it into two equal subsets.
        if(sumTotal % 2 != 0) {
            return false;
        }

        //We need to find a subset whose sum is equal to half of the sum of the whole array.
        int requiredSum = sumTotal/2;

        //If it is possible to find a subset whose sum is 'requiredSum' using first n elements of the array,
        //we have to return true
        return canPartitionUtil(nums, n, requiredSum, calculatedResultsMap);
    }

    private static int sumOfArray(int[] nums, int n) {
        int i=0;
        int sum = 0;
        for(i=0; i<n; i++) {
            sum = sum + nums[i];
        }
        return sum;
    }

    private static boolean canPartitionUtil(int[] nums, int i, int requiredSum, Map<String, Boolean> calculatedResultsMap) {

        //If there are no items to pick from => we can't form any subset.
        if(i==0) return false;

        //An empty subset will always sum upto 0. Therefore, we can return true.
        if(requiredSum == 0) return true;

        //We need a unique key corresponding to the combination of i and requiredSum. We'll store our answers
        //in the map against this key.
        String key = buildKey(i, requiredSum);

        //If we have seen this key before => we have calculated for this input before => return directly
        //from the map instead of calculating again.
        if(calculatedResultsMap.containsKey(key)) {
            return calculatedResultsMap.get(key);
        }

        //includeAns stores whether there exists a subset whose sum is equal to requiredSum when the constraint
        //is that we have to include the last element among first i elements.
        //excludeAns stores whether there exists a subset whose sum is equal to requiredSum when the constraint
        //is that we will never include the last element among first i-1 elements.
        //finalAns is true if either includeAns or excludeAns is true.
        boolean includeAns, excludeAns, finalAns;

        //lastElement will store what is the value of last element among first i elements.
        int lastElement;

        //If we have included the last element among first i elements => what is the remaining sum that we
        //have to make
        int remainingSum;

        lastElement = nums[i-1];

        //Thinking about includeAns makes sense only if the requiredSum >= lastElement. If it is lesser => then
        //it doesn't make sense to include the last element.
        if(requiredSum >= lastElement) {
            //If we have decided to include the last element among first i elements => Now, remaining task
            //is to find if there exists a subset among first 'i-1' elements that can sum up to
            //'requiredSum - lastElement'
            remainingSum = requiredSum - lastElement;
            includeAns = canPartitionUtil(nums, i-1, remainingSum, calculatedResultsMap);
        } else {
            includeAns = false;
        }

        //If includeAns is true => we can directly return true. We don't even need to calculate the excludeAns.
        if(includeAns) {
            calculatedResultsMap.put(key, true);
            return true;
        }

        //If we have decided to exclude the last element among first i elements => our remaining task
        //is to find out if we can form the requiredSum using any subset of first i-1 elements.
        excludeAns = canPartitionUtil(nums, i-1, requiredSum, calculatedResultsMap);

        finalAns = includeAns || excludeAns;

        //Before returning, put our answer in the map against the input. Therefore, if we now receive the same
        //input again, we'll not have to go through same calculation.
        calculatedResultsMap.put(key, finalAns);
        return finalAns;

    }

    private static String buildKey(int i, int j) {
        return (String.valueOf(i) + "_" + String.valueOf(j));
    }

    //If we were asked to also return the subsets => how will we do it.

    //When we are calculating answer for (i, j) => we know whether we have included the last element or not.
    //At that time, we can store this information in another map too. For (i, j) => did we include the last
    //element or not. Then, using these 2 maps and array, we can derive the subset.
    //We can simply start with (n, requiredSum). At every iteration, we'll know whether we included the last
    //element using 2nd map. If we have => save this element in a stack and move to next i, j. If we have not
    //move to next i, j. Move till sum becomes 0.

    //Another way, is that this recursive method would return a subset for a pair of (i, j). But, this would
    //take up a lot of memory.
}
